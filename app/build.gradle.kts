plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")

    // Firebase
    id("com.google.gms.google-services")
}

android {
    namespace = "com.yunjung.runningday"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.yunjung.runningday"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    signingConfigs {
        getByName("debug"){
            storeFile = file("../keystore/debug.keystore")
            storePassword = "runningday0921!!"
            keyAlias = "runningday_debug.keystore"
            keyPassword = "runningday0921!!"
        }
        create("release"){
            storeFile = file("../keystore/release.keystore")
            storePassword = "runningday0921!!"
            keyAlias = "runningday_release.keystore"
            keyPassword = "runningday0921!!"
        }
    }

    buildTypes {
        getByName("debug") {
            isDebuggable = true
            isMinifyEnabled = false
            signingConfig = signingConfigs.getByName("debug")
        }
        getByName("release") {
            isDebuggable = false
            isMinifyEnabled = false
            signingConfig = signingConfigs.getByName("release")
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    flavorDimensions += "version"
    productFlavors {
        create("dev"){
            dimension = "version"
            buildConfigField("String", "VERSION", "\"dev\"")
        }
        create("stage"){
            dimension = "version"
            buildConfigField("String", "VERSION", "\"stage\"")
        }
        create("prod"){
            dimension = "version"
            buildConfigField("String", "VERSION", "\"prod\"")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    dataBinding{
        enable = true
    }
    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    // https://dl.google.com/dl/android/maven2/com/google/android/material/material/1.10.0/material-1.10.0.pom
    // 도메인: dl.google.com, IP 주소: 142.250.196.110
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    // Test
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // Ext
    implementation("androidx.fragment:fragment-ktx:1.6.1")
    implementation("androidx.activity:activity-ktx:1.8.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")

    // Firebase
    implementation(platform("com.google.firebase:firebase-bom:32.6.0"))
    implementation("com.google.firebase:firebase-analytics")
}