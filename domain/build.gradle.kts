plugins {
    id("kotlin")
}

dependencies {
    implementation(AsyncDependencies.coroutines)
    implementation(AsyncDependencies.coroutines_android)
    implementation(DependencyInjectionDependencies.koin_core)
}