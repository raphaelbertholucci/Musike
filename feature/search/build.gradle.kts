plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
}

android {
    defaultConfig {
        compileSdk = Config.compileSdkVersion
        minSdk = Config.minSdkVersion
        targetSdk = Config.targetSdkVersion
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(project(":common"))
    implementation(project(":domain"))

    implementation(SupportDependencies.core)
    implementation(SupportDependencies.material)
    implementation(SupportDependencies.app_compat)
    implementation(SupportDependencies.recycler_view)
    implementation(SupportDependencies.constraint)
    implementation(SupportDependencies.lifecycle)
    implementation(SupportDependencies.live_data)
    implementation(SupportDependencies.navigation)
    implementation(SupportDependencies.navigation_fragment)
    implementation(SupportDependencies.coil)
    implementation(SupportDependencies.shimmer)
    implementation(SupportDependencies.swipe_refresh)
    implementation(DependencyInjectionDependencies.koin)
    implementation(DependencyInjectionDependencies.koin_core)

    api(TestDependencies.espresso)
    androidTestImplementation(TestDependencies.junit_instrumentation)
    androidTestImplementation(TestDependencies.mockk_android)
    androidTestImplementation(TestDependencies.test_core)
    testImplementation(TestDependencies.junit)
    testImplementation(TestDependencies.mockk)
    testImplementation(TestDependencies.coroutines_test)
    testImplementation(TestDependencies.arch)
}