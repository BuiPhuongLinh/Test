plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = 32

    defaultConfig {
        minSdk = 21
        targetSdk = 32

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

    dataBinding {
        isEnabled = true
    }
}

dependencies {

    implementation(project(Module.common))
    implementation(project(Module.widgetCommon))

    implementation(KotlinLibraries.kotlin)
    implementation(AndroidLibraries.appCompat)
    implementation(AndroidLibraries.materialDesign)
    implementation(AndroidLibraries.constraintLayout)
    implementation(AndroidLibraries.navigation)
    implementation(AndroidLibraries.navigationFrag)

    implementation(Libraries.daggerHilt)
    kapt(Libraries.daggerCompiler)

    implementation(Libraries.sdp)
    implementation(Libraries.ssp)

    testImplementation(TestLibraries.junit)
    androidTestImplementation(TestLibraries.androidTestRunner)
    androidTestImplementation(TestLibraries.espresso)
}