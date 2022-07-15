plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-android-extensions")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = AndroidVersion.compileSdk
    flavorDimensions.add("default")
    productFlavors {
        create("dev") {
            dimension = "default"
        }
        create("stg") {
            dimension = "default"
        }
        create("prod") {
            dimension = "default"
        }
    }

    hilt {
        enableAggregatingTask = true
    }
}

dependencies {
    implementation(project(Module.common))
    implementation(project(Module.share))
    implementation(AndroidLibraries.lifecycleExtensions)
    kapt(AndroidLibraries.lifecycleCompile)
    implementation(KotlinLibraries.moshiKotlin)
    kapt(KotlinLibraries.moshiKotlinCodegen)

    implementation(Libraries.daggerHilt)
    kapt(Libraries.daggerCompiler)

    implementation(KotlinLibraries.coreKotlinCoroutine)
    kapt(KotlinLibraries.androidKotlinCoroutine)

}
