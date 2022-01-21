plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
}

android {
    defaultConfig {
        applicationId = Releases.applicationId
        versionCode = Releases.versionCode
        versionName = Releases.versionName

        compileSdk = Config.compileSdkVersion
        minSdk = Config.minSdkVersion
        targetSdk = Config.targetSdkVersion
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        setProperty("archivesBaseName", "${parent?.name?.capitalize()}-$versionName")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            isDebuggable = false
            isJniDebuggable = false
            proguardFiles("proguard-android.txt", "proguard-rules.pro")
        }
    }
    buildFeatures {
        viewBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
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
    implementation(SupportDependencies.lottie)

    api(TestDependencies.espresso)
    androidTestImplementation(TestDependencies.junit_instrumentation)
    androidTestImplementation(TestDependencies.mockk_android)
    androidTestImplementation(TestDependencies.test_core)
    testImplementation(TestDependencies.junit)
    testImplementation(TestDependencies.mockk)
    testImplementation(TestDependencies.coroutines_test)
    testImplementation(TestDependencies.arch)
}