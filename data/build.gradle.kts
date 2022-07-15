plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}
android {
    compileSdk = AndroidVersion.compileSdk
    flavorDimensions.add("default")
    productFlavors {
        create("dev") {
            dimension = "default"
            buildConfigField("String", "BASE_URL", "\"https://api.openweathermap.org/\"")
            buildConfigField("String", "API_KEY", "\"1160f381fa33aa1827ed0f1be7b7db2e\"")
        }
        create("stg") {
            dimension = "default"
            buildConfigField("String", "BASE_URL", "\"https://api.openweathermap.org/\"")
            buildConfigField("String", "API_KEY", "\"1160f381fa33aa1827ed0f1be7b7db2e\"")
        }
        create("prod") {
            dimension = "default"
            buildConfigField("String", "BASE_URL", "\"https://api.openweathermap.org/\"")
            buildConfigField("String", "API_KEY", "\"1160f381fa33aa1827ed0f1be7b7db2e\"")
        }
    }

    hilt {
        enableAggregatingTask = true
    }
}

dependencies {

    implementation(project(Module.common))
    implementation(project(Module.domain))
    implementation(project(Module.share))

    implementation(Libraries.retrofit)
    implementation(Libraries.retrofitMoshiConverter)
    implementation(Libraries.httpLoggingInterceptor)
    implementation(Libraries.okhttp)

    implementation(KotlinLibraries.moshiKotlin)
    implementation(KotlinLibraries.moshiKotlinCodegen)

    implementation(KotlinLibraries.coreKotlinCoroutine)
    implementation(KotlinLibraries.androidKotlinCoroutine)

    implementation(Libraries.room)
    kapt(Libraries.roomCompiler)
    implementation(Libraries.roomKtx)

    implementation(Libraries.daggerHilt)
    kapt(Libraries.daggerCompiler)
}