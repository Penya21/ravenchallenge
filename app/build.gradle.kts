plugins {
    alias(libs.plugins.hilt.gradle.plugin)
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("kotlin-parcelize")
    id("kotlin-kapt")



}

android {
    namespace = "com.example.ravenchallenge"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.ravenchallenge"
        minSdk = 28
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "com.example.ravenchallenge.HiltTestRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }

    packaging {
        resources {
            excludes += "/META-INF/*"
        }
    }
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}

hilt {
    enableAggregatingTask = false
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)


    // Retrofit
    implementation(libs.retrofit2.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.okhttp)

    // Coroutines
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)

    // Coroutine Lifecycle Scopes
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)


    //Glide
    implementation(libs.landscapist.glide)

    testImplementation(libs.junit)
    testImplementation(libs.junit.jupiter)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    androidTestImplementation(libs.junit.jupiter)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // Local unit tests
    androidTestImplementation( libs.androidx.core)
    androidTestImplementation( libs.junit)
    androidTestImplementation( libs.androidx.core.testing)
    androidTestImplementation( libs.kotlinx.coroutines.test)
    androidTestImplementation( libs.truth)
    androidTestImplementation( libs.mockwebserver)
    androidTestImplementation( libs.mockk)
    debugImplementation( libs.ui.test.manifest)

    // Instrumentation tests
    androidTestImplementation(libs.dagger.hilt.android.testing)
    kaptAndroidTest(libs.hilt.android.compiler.v237)
    androidTestImplementation( libs.junit)
    androidTestImplementation( libs.kotlinx.coroutines.test)
    androidTestImplementation( libs.androidx.core.testing.v210)
    androidTestImplementation( libs.truth)
    androidTestImplementation( libs.junit.v113)
    androidTestImplementation( libs.core.ktx)
    androidTestImplementation( libs.mockwebserver)
    androidTestImplementation( libs.mockk.android)
    androidTestImplementation( libs.androidx.runner)


    //Room
    implementation(libs.androidx.room.ktx)
    implementation (libs.androidx.room.runtime)
    kapt (libs.androidx.room.compiler)

    //Hilt
    implementation(libs.androidx.hilt.navigation.compose)
    kapt(libs.hilt.android.compiler)
    implementation(libs.hilt.android)
    implementation(libs.androidx.hilt.work)
    implementation(libs.androidx.work.runtime.ktx)


}