plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)

    kotlin("kapt")

    // Hilt
    id("com.google.dagger.hilt.android")

    // Kotlin Serialization
    kotlin("plugin.serialization") version "2.0.0"
    alias(libs.plugins.google.gms.google.services)

}

android {
    namespace = "dev.mshajkarami.fs.kt.bookstack"
    compileSdk = 36

    defaultConfig {
        applicationId = "dev.mshajkarami.fs.kt.bookstack"
        minSdk = 28
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.firebase.database)
    implementation(libs.firebase.storage)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    // My Library

    // Additional icons Compose
    implementation("androidx.compose.material:material-icons-extended:1.7.8")

    // Dependency injection Hilt
    implementation("com.google.dagger:hilt-android:2.57.2")
    kapt("com.google.dagger:hilt-android-compiler:2.57.2")

    // Using hiltViewModel in Compose
    implementation("androidx.hilt:hilt-navigation-compose:1.3.0")
    kapt("androidx.hilt:hilt-compiler:1.3.0")

    // Navigation in Compose
    implementation("androidx.navigation:navigation-compose:2.9.6") //

    // (Serialization)
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.9.0")

    // Coil
    implementation("io.coil-kt:coil-compose:2.7.0")

    // PDF
//    implementation("io.github.grizz191:bouquet:1.1.2")
}