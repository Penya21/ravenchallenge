package com.example.ravenchallenge.news_list.ui


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ravenchallenge.utils.SnackbarController
import com.example.ravenchallenge.utils.SnackbarEvent
import com.example.ravenchallenge.news_list.data.local.NewsDao
import com.example.ravenchallenge.news_list.domain.model.BlacklistedNews
import com.example.ravenchallenge.news_list.domain.model.News
import com.example.ravenchallenge.news_list.domain.repository.DatabaseRepository
import com.example.ravenchallenge.news_list.domain.repository.NewsRepository
import com.example.ravenchallenge.utils.ConnectivityObserver
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val databaseRepository: DatabaseRepository,
    private val repository: NewsRepository,
    private val connectivityObserver: ConnectivityObserver
) : ViewModel(){


    val isConnected = connectivityObserver
        .isConnected
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            false
        )

    private val _uiState = MutableStateFlow(HomeUIState(loading = true))
    val uiState: StateFlow<HomeUIState> = _uiState

    private val _news = databaseRepository.getNewsOrderedByDate().stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    val news: StateFlow<List<News>>
        get() = _news


    fun loadNews(){
        viewModelScope.launch {

            _uiState.value = HomeUIState(
                isRefreshing = true
            )

            val result = repository.getNews().hits

            result.forEach { it ->
                databaseRepository.upsertNews(it)
            }

            _uiState.value = HomeUIState(
                news = databaseRepository.getNewsOrderedByDate().first(),
                selectedNews = result.first(),
                isRefreshing = false
            )

        }
    }

    fun blackListItem(item: News){

        viewModelScope.launch {
            databaseRepository.blacklistNews(BlacklistedNews(item.objectID))

            _uiState.value = HomeUIState(
                news = databaseRepository.getNewsOrderedByDate().first()
            )
        }

    }

    fun onConnectionMissing(){
        viewModelScope.launch {

            _uiState.value = HomeUIState(
                news = databaseRepository.getNewsOrderedByDate().first(),
                selectedNews = null
            )
        }
    }

    fun setSelectedNews(id: Int){
        val news = uiState.value.news.find { it.story_id == id }
        if (news != null) {
            if(news.story_url.isNullOrEmpty()){
                viewModelScope.launch {
                    SnackbarController.sendEvents(SnackbarEvent("Missing URL", null))
                }
            }else if(!isConnected.value) {
                viewModelScope.launch {
                    SnackbarController.sendEvents(SnackbarEvent("No Internet Connection", null))
                }
            }else{
                _uiState.value = _uiState.value.copy(
                    selectedNews = news,
                    isDetailOpen = true
                )
            }
        }
    }

    fun closeDetailScreen() {
        _uiState.value = _uiState
            .value.copy(
                isDetailOpen = false,
                selectedNews = _uiState.value.news.first()
            )
    }



}

data class HomeUIState(

    val news: List<News> = emptyList(),
    val loading: Boolean = false,
    val selectedNews : News? = null,
    val isDetailOpen : Boolean = false,
    val error: String? = null,
    val isRefreshing : Boolean = false
)
