import java.util.*

plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    kotlin("plugin.serialization") version Versions.KOTLIN_VERSION
    id("com.google.gms.google-services")
}

android {
    namespace = "com.velogm.presentation"
    compileSdk = Configuration.COMPILE_SDK

    defaultConfig {
        minSdk = Configuration.MIN_SDK
        targetSdk = Configuration.TARGET_SDK

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        debug {
            buildConfigField("String", "CLIENT_ID", Properties().apply {
                load(project.rootProject.file("local.properties").inputStream())
            }["client.id"].toString())
            buildConfigField("String", "VERSION_NAMES", Properties().apply {
                load(project.rootProject.file("local.properties").inputStream())
            }["versions.name"].toString())
        }
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String", "CLIENT_ID", Properties().apply {
                load(project.rootProject.file("local.properties").inputStream())
            }["client.id"].toString())
            buildConfigField("String", "VERSION_NAMES", Properties().apply {
                load(project.rootProject.file("local.properties").inputStream())
            }["versions.name"].toString())
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        dataBinding = true
        viewBinding = true
    }
}

dependencies {

    implementation(project(":core-ui"))
    implementation(project(":domain"))
    // Kotlin
    implementation(KotlinX.KOTLINX_SERIALIZATION)
    // AndroidX
    implementation(AndroidX.ACTIVITY)
    implementation(AndroidX.APP_COMPAT)
    implementation(AndroidX.CORE_KTX)
    implementation(AndroidX.LIFECYCLE_RUNTIME)
    implementation(AndroidX.PAGING)
    implementation(AndroidX.LIFECYCLE_VIEWMODEL_KTX)
    implementation(AndroidX.NAVIGATION_CONPONENT_FRAGMENT)
    implementation(AndroidX.NAVIGATION_CONPONENT_UI)
    implementation(AndroidX.CONSTRAINT_LAYOUT)

    // Matrial Design
    implementation(Google.MATERIAL)

    // Test Dependency
    androidTestImplementation(TestDependencies.EXT_JUNIT)
    androidTestImplementation(TestDependencies.ESPRESSO_CORE)
    testImplementation(TestDependencies.JUNIT)

    //Hilt
    implementation(Google.HILT_ANDROID)
    kapt(Google.HILT_ANDROID_COMPILER)

    // Third-Party
    implementation(SquareUp.RETROFIT2)
    implementation(SquareUp.RETROFIT2_CONVERTER_GSON)
    implementation(SquareUp.OKHTTP3)
    implementation(SquareUp.OKHTTP3_LOGGING)
    implementation(SquareUp.OKHTTP3_BOM)
    implementation(Jakewharton.TIMBER)
    implementation(Jakewharton.CONVERTER)
    implementation(ThirdParty.COIL)

    //google play
    implementation(Google.GOOGLE_PLAY)

    //google service
    implementation(enforcedPlatform(Google.GOOGLE_FIREBASE_BOM))
    implementation(Google.GOOGLE_FIREBASE_ANALYTICS)
    implementation(Google.GOOGLE_FIREBASE_MESSAGING)

    implementation("com.google.firebase:firebase-config-ktx")

}