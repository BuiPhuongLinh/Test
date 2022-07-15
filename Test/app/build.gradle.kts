plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-android-extensions")
    kotlin("android.extensions")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}
android {
    compileSdk = AndroidVersion.compileSdk

    defaultConfig {
        applicationId = ApplicationId.id
        minSdk = AndroidVersion.minSdk
        targetSdk = AndroidVersion.compileSdk
        versionCode = Realse.versionCode
        versionName = Realse.versionName
        multiDexEnabled = true

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true // Enables code shrinking for the release build type.
            proguardFiles(
                getDefaultProguardFile("proguard-android.txt"),
                "proguard-rules.pro"
            )
        }
        getByName("debug") {
            isMinifyEnabled = false
        }
    }
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

    compileOptions {
        sourceCompatibility(JavaVersion.VERSION_1_8)
        targetCompatibility(JavaVersion.VERSION_1_8)
    }
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }

    buildFeatures {
        dataBinding = true
        viewBinding = true
    }

    hilt {
        enableAggregatingTask = true
    }
}

dependencies {
    implementation(project(Module.common))
    implementation(project(Module.widgetCommon))
    implementation(project(Module.domain))
    implementation(project(Module.data))
    implementation(project(Module.share))

    implementation(KotlinLibraries.kotlin)
    implementation(AndroidLibraries.appCompat)
    implementation(AndroidLibraries.materialDesign)
    implementation(AndroidLibraries.constraintLayout)
    implementation(AndroidLibraries.navigation)
    implementation(AndroidLibraries.navigationFrag)
    implementation(AndroidLibraries.multidex)

    implementation(KotlinLibraries.moshiKotlin)
    kapt(KotlinLibraries.moshiKotlinCodegen)

    implementation(Libraries.daggerHilt)
    kapt(Libraries.daggerCompiler)

    implementation(Libraries.sdp)

    testImplementation(TestLibraries.junit)
    androidTestImplementation(TestLibraries.androidTestRunner)
    androidTestImplementation(TestLibraries.espresso)
}