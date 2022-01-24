plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
}

android {
    defaultConfig {
        compileSdk = Config.compileSdkVersion
        minSdk = Config.minSdkVersion
        targetSdk = Config.targetSdkVersion
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        val baseUrl = "\"http://ws.audioscrobbler.com/\""
        val apiKey = "\"3ea37c98f103a08151caf70424f6c08e\""
        all {
            buildConfigField("String", "API_KEY", apiKey)
            buildConfigField("String", "BASE_URL", baseUrl)
        }
    }
}

dependencies {
    implementation(project(":common"))
    implementation(project(":domain"))

    implementation(NetworkDependencies.retrofit)
    implementation(NetworkDependencies.okhttp)
    implementation(NetworkDependencies.gson)
    implementation(NetworkDependencies.gson_converter)
    implementation(DatabaseDependencies.room)
    implementation(DatabaseDependencies.room_ktx)
    implementation(DependencyInjectionDependencies.koin)
    implementation(DependencyInjectionDependencies.koin_core)

    kapt(DatabaseDependencies.room_compiler)

    api(TestDependencies.espresso)
    androidTestImplementation(TestDependencies.junit_instrumentation)
    androidTestImplementation(TestDependencies.mockk_android)
    androidTestImplementation(TestDependencies.test_core)
    testImplementation(TestDependencies.junit)
    testImplementation(TestDependencies.mockk)
    testImplementation(TestDependencies.coroutines_test)
    testImplementation(TestDependencies.arch)
}