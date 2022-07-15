plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    compileSdk = 32

    defaultConfig {
        minSdk = 21
        targetSdk = 32

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    implementation(AndroidLibraries.appCompat)
    implementation(AndroidLibraries.materialDesign)
    implementation(AndroidLibraries.constraintLayout)

    implementation(AndroidLibraries.navigation)
    implementation(AndroidLibraries.navigationFrag)
    implementation(Libraries.sdp)

    testImplementation(TestLibraries.junit)
    androidTestImplementation(TestLibraries.junit)
    androidTestImplementation(TestLibraries.espresso)
}