plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
}

dependencies {
    implementation(NetworkDependencies.retrofit)
    implementation(NetworkDependencies.okhttp)
    implementation(NetworkDependencies.gson)
    implementation(NetworkDependencies.gson_converter)
    implementation(DatabaseDependencies.room)
    implementation(DatabaseDependencies.room_ktx)
    implementation(DependencyInjectionDependencies.koin)
    implementation(DependencyInjectionDependencies.koin_core)
    implementation(PreferencesDependencies.datastore)
    implementation(PreferencesDependencies.secure_shared_preferences)

    api(TestDependencies.espresso)
    androidTestImplementation(TestDependencies.junit_instrumentation)
    androidTestImplementation(TestDependencies.mockk_android)
    androidTestImplementation(TestDependencies.test_core)
    testImplementation(TestDependencies.junit)
    testImplementation(TestDependencies.mockk)
    testImplementation(TestDependencies.coroutines_test)
    testImplementation(TestDependencies.arch)
}