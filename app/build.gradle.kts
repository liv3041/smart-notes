plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
//    alias(libs.plugins.kotlin.kapt)
    id("org.jetbrains.kotlin.kapt")
    id ("dagger.hilt.android.plugin") // ✅ Required for Hilt



}

android {
    namespace = "com.example.smartnotes"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.smartnotes"
        minSdk = 24
        targetSdk = 35
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
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    // Core
    implementation ("androidx.core:core-ktx:1.12.0")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation ("androidx.activity:activity-compose:1.8.2")

// Compose
    implementation ("androidx.compose.ui:ui:1.6.4")
    implementation ("androidx.compose.material3:material3:1.2.1")
    implementation ("androidx.navigation:navigation-compose:2.7.7")

// Room
    implementation ("androidx.room:room-runtime:2.6.1")
    kapt ("androidx.room:room-compiler:2.6.1")
    implementation ("androidx.room:room-ktx:2.6.1")

// Hilt
    implementation ("com.google.dagger:hilt-android:2.50")
    kapt ("com.google.dagger:hilt-compiler:2.50")
    implementation ("androidx.hilt:hilt-navigation-compose:1.1.0")

// WorkManager (Optional)
    implementation ("androidx.work:work-runtime-ktx:2.9.0")

// DataStore (for theme prefs)
    implementation ("androidx.datastore:datastore-preferences:1.0.0")

//    implementation("com.github.jeziellago:compose-markdown-text:0.3.0")
    implementation ("com.github.jeziellago:compose-markdown:0.5.7")

    implementation("io.coil-kt:coil-compose:2.4.0")
    implementation("androidx.activity:activity-compose:1.7.2")


//    Rich Text Editor\
//    implementation("com.halilibo.richtext:richtext-ui-material3:0.16.0")
//    implementation("com.halilibo.richtext:richtext-ui:0.16.0")






}