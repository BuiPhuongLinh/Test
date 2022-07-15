plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
}

android {
    compileSdk = AndroidVersion.compileSdk
    defaultConfig {
        minSdk = AndroidVersion.minSdk
        targetSdk = AndroidVersion.compileSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(AndroidLibraries.coreKtx)
    implementation(KotlinLibraries.kotlin)

    implementation(AndroidLibraries.lifecycleLivedata)
    implementation(AndroidLibraries.lifecycleExtensions)
    kapt(AndroidLibraries.lifecycleCompile)

    implementation(KotlinLibraries.moshiKotlin)
    kapt(KotlinLibraries.moshiKotlinCodegen)
}