plugins {
    id("com.android.library")
    id("kotlin-android")
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
    api(SupportDependencies.core)
    api(SupportDependencies.material)
    api(SupportDependencies.app_compat)
    api(SupportDependencies.constraint)
    api(SupportDependencies.navigation)
    api(SupportDependencies.navigation_fragment)
}