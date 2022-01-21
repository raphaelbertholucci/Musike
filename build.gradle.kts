import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask

buildscript {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }

    dependencies {
        classpath(AndroidDependencies.gradle)
        classpath(AndroidDependencies.kotlin)
        classpath(AndroidDependencies.googleServices)
        classpath(AndroidDependencies.navigationSafeArgs)
        classpath(AndroidDependencies.firebasePerf)
        classpath(AndroidDependencies.dependencyUpdate)
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.10-RC")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }

    afterEvaluate {
        if (this.hasProperty("android")) {
            configure<BaseAppModuleExtension> {
                compileSdkVersion = Config.compileSdkVersion.toString()
                buildToolsVersion = Config.buildTools

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
            }
        }
    }
}

tasks.register("clean") {
    delete(rootProject.buildDir)
}

apply(plugin = "com.github.ben-manes.versions")

fun isNonStable(version: String): Boolean {
    val stableKeyword = listOf("RELEASE", "FINAL", "GA").any { version.toUpperCase().contains(it) }
    val regex = "^[0-9,.v-]+(-r)?$".toRegex()
    val isStable = stableKeyword || regex.matches(version)
    return isStable.not()
}

tasks.withType<DependencyUpdatesTask> {
    rejectVersionIf {
        isNonStable(candidate.version) && !isNonStable(currentVersion)
    }
}