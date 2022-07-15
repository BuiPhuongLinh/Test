// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "7.1.2" apply false
    id("com.android.library") version "7.1.2" apply false
    id("org.jetbrains.kotlin.android") version "1.6.10" apply false
    id("com.android.dynamic-feature") version AndroidVersion.android apply false
    id("org.jetbrains.kotlin.jvm") version "1.6.10" apply false
    id("androidx.navigation.safeargs.kotlin") version Versions.safeArgs apply false
}

buildscript {
    dependencies {
        classpath("com.google.dagger:hilt-android-gradle-plugin:${Versions.daggerHilt}")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}