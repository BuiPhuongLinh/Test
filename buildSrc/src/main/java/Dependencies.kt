object ApplicationId {
    val id = "com.template.base"
}

object AndroidVersion {
    val gradle = "4.0.2"
    val android = "7.1.2"
    val compileSdk = 32
    val minSdk = 24
}

object Module {
    val common = mapOf("path" to ":common")
    val widgetCommon = mapOf("path" to ":widgetcommon")
    val domain = mapOf("path" to ":domain")
    val data = mapOf("path" to ":data")
    val share = mapOf("path" to ":sharelocal")

    //----feature----//
    val note = mapOf("path" to ":feature:note")
    val weather = mapOf("path" to ":feature:weather")
}

object Realse {
    val versionCode = 1
    val versionName = "1.0"
}

object Versions {
    val kotlinExtension = "1.6.10"
    val multidex = "2.0.1"
    val safeArgs = "2.4.2"
    val appCompat = "1.4.1"
    val coreKtx = "1.7.0"
    val androidTestRunner = "1.4.0"
    val androidJunit = "1.1.3"
    val espressoCore = "3.4.0"
    val constraintLayout = "2.1.3"
    val materialDesignVersion = "1.5.0"
    val nav = "2.4.2"
    val fragment = "1.4.1"
    val kotlinVersion = "1.6.21"
    val kotlinCoroutine = "1.6.1"
    val lifecycle = "2.4.1"
    val lifecycleExtensions = "2.0.0-alpha1"
    val okHttp = "4.9.3"
    val retrofit = "2.9.0"
    val retrofitMoshi = "2.9.0"
    val moshiVersion = "1.13.0"
    val logginInterceptor = "4.9.3"
    val daggerHilt = "2.42"
    val roomVersion = "2.4.0-alpha03"
    val sdp = "1.1.0"
    val ssp = "1.1.0"
}

object Libraries {
    //RESPONESIVE
    val sdp = "com.intuit.sdp:sdp-android:${Versions.sdp}"
    val ssp = "com.intuit.ssp:ssp-android::${Versions.ssp}"

    // RETROFIT
    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    val retrofitMoshiConverter = "com.squareup.retrofit2:converter-moshi:${Versions.retrofitMoshi}"
    val httpLoggingInterceptor =
        "com.squareup.okhttp3:logging-interceptor:${Versions.logginInterceptor}"
    val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okHttp}"

    val daggerHilt = "com.google.dagger:hilt-android:${Versions.daggerHilt}"
    val daggerCompiler = "com.google.dagger:hilt-android-compiler:${Versions.daggerHilt}"

    //ROOM
    val room = "androidx.room:room-runtime:${Versions.roomVersion}"
    val roomKtx = "androidx.room:room-ktx:${Versions.roomVersion}"
    val roomCompiler = "androidx.room:room-compiler:${Versions.roomVersion}"
}

object AndroidLibraries {
    // ANDROID
    val multidex = "androidx.multidex:multidex:${Versions.multidex}"
    val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    val navigation = "androidx.navigation:navigation-ui-ktx:${Versions.nav}"
    val navigationFrag = "androidx.navigation:navigation-fragment-ktx:${Versions.nav}"
    val fragmentKtx = "androidx.fragment:fragment-ktx:${Versions.fragment}"
    val fragment = "androidx.fragment:fragment:${Versions.fragment}"

    val materialDesign = "com.google.android.material:material:${Versions.materialDesignVersion}"

    // ViewModel and LiveData
    val lifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    val lifecycleExtensions =
        "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycleExtensions}"
    val lifecycleCompile = "androidx.lifecycle:lifecycle-compiler:${Versions.lifecycle}"
    val lifecycleLivedata = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
}

object KotlinLibraries {
    val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlinVersion}"
    val coreKotlinCoroutine =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlinCoroutine}"
    val androidKotlinCoroutine =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.kotlinCoroutine}"

    // MOSHI
    val moshiKotlin = "com.squareup.moshi:moshi-kotlin:${Versions.moshiVersion}"
    val moshiKotlinCodegen = "com.squareup.moshi:moshi-kotlin-codegen:${Versions.moshiVersion}"
}

object TestLibraries {
    // ANDROID TEST
    val androidTestRunner = "androidx.test:runner:${Versions.androidTestRunner}"
    val espresso = "androidx.test.espresso:espresso-core:${Versions.espressoCore}"
    val espressoContrib = "androidx.test.espresso:espresso-contrib:${Versions.espressoCore}"
    val junit = "androidx.test.ext:junit:${Versions.androidJunit}"

}
