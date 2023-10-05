import java.util.*

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    kotlin("plugin.serialization") version Versions.KOTLIN_VERSION
    id("dagger.hilt.android.plugin")
    id("com.google.gms.google-services")

}

android {
    namespace = Configuration.PACKAGE_NAME
    compileSdk = Configuration.COMPILE_SDK

    defaultConfig {
        applicationId = Configuration.PACKAGE_NAME
        minSdk = Configuration.MIN_SDK
        targetSdk = Configuration.TARGET_SDK
        versionCode = Configuration.VERSION_CODE
        versionName = Configuration.VERSION_NAME

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        debug {
            buildConfigField("String", "CLIENT_ID", Properties().apply {
                load(project.rootProject.file("local.properties").inputStream())
            }["client.id"].toString())
            buildConfigField("String", "BASE_URL", Properties().apply {
                load(project.rootProject.file("local.properties").inputStream())
            }["base.url"].toString())
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
            buildConfigField("String", "BASE_URL", Properties().apply {
                load(project.rootProject.file("local.properties").inputStream())
            }["base.url"].toString())
        }
    }
    compileOptions {
        sourceCompatibility = Versions.JAVA_VERSION
        targetCompatibility = Versions.JAVA_VERSION
    }
    kotlinOptions {
        jvmTarget = Versions.JVM_VERSION
    }
    buildFeatures {
        dataBinding = true
        viewBinding = true
    }
}

dependencies {
    implementation(project(":presentation"))
    implementation(project(":core-ui"))
    implementation(project(":domain"))
    implementation(project(":data"))
    implementation(project(":data-local"))
    implementation(project(":data-remote"))
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
    implementation(ThirdParty.ZXING)

    // shared preference
    implementation("androidx.security:security-crypto-ktx:1.1.0-alpha06")

    //google play
    implementation(Google.GOOGLE_PLAY)

    //google service
    implementation(enforcedPlatform(Google.GOOGLE_FIREBASE_BOM))
    implementation(Google.GOOGLE_FIREBASE_ANALYTICS)
    implementation(Google.GOOGLE_FIREBASE_MESSAGING)
}
