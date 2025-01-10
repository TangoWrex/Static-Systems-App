plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
//    alias(libs.plugins.kotlin.kapt)  // Add this line for kapt
}

android {
    namespace = "com.example.wardragon_app"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.wardragon_app"
        minSdk = 21
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Mapbox SDK for Android
    implementation("com.mapbox.maps:android:11.4.1")

    // API call
    implementation("com.squareup.okhttp3:okhttp:4.9.1")

//    // Room dependencies
//    implementation("androidx.room:room-runtime:2.4.3")
//    kapt("androidx.room:room-compiler:2.4.3")
//    implementation("androidx.room:room-ktx:2.4.3")
}
