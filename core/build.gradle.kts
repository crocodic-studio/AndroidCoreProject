plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
    id("maven-publish")
}

android {
    compileSdk = 32

    defaultConfig {
        minSdk = 23
        targetSdk = 32

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
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

    buildFeatures {
        viewBinding = true
        dataBinding = true
    }

    packagingOptions {
        resources.excludes.add("META-INF/DEPENDENCIES")
    }
}

dependencies {

    /* Basic Android */
    api("androidx.appcompat:appcompat:1.4.1")
    api("androidx.core:core-ktx:1.7.0")
    api("androidx.constraintlayout:constraintlayout:2.1.3")
    api("androidx.exifinterface:exifinterface:1.3.3")
    api("androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.1")
    api("androidx.security:security-crypto:1.1.0-alpha01")

    /* Basic Component */
    api("com.github.tapadoo:alerter:7.1.0")
    api("id.zelory:compressor:3.0.1")
    api("org.greenrobot:eventbus:3.2.0")
    api("com.github.stfalcon-studio:StfalconImageViewer:1.0.1")

    /* Glide */
    api("com.github.bumptech.glide:glide:4.12.0")
    kapt("com.github.bumptech.glide:compiler:4.12.0")
    api("com.github.bumptech.glide:okhttp3-integration:4.12.0") {
        exclude(group = "glide-parent")
    }

    /* Basic Google */
    api("com.google.android.material:material:1.5.0")
    api("com.google.code.gson:gson:2.8.8")

    /* Location */
    api("com.google.android.gms:play-services-location:19.0.1")
    api("com.google.android.gms:play-services-maps:18.0.2")
    api("com.google.maps.android:android-maps-utils:1.3.3")

    /* Networking */
    api("com.squareup.retrofit2:retrofit:2.9.0")
    api("com.squareup.retrofit2:converter-scalars:2.9.0")
    api("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.2")

    /* Room */
    api("androidx.room:room-runtime:2.4.2")
    api("androidx.room:room-ktx:2.4.2")
    kapt("androidx.room:room-compiler:2.4.2")

    /* Firebase */
    api(platform("com.google.firebase:firebase-bom:29.2.0"))
    api("com.google.firebase:firebase-analytics-ktx")
    api("com.google.firebase:firebase-messaging-ktx")
    api("com.google.firebase:firebase-crashlytics-ktx")
    api("com.google.firebase:firebase-perf-ktx")

    /* Base64 Crypt */
    api(group = "commons-codec", name = "commons-codec", version = "1.11")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
}

afterEvaluate {
    publishing {
        publications {
            // Creates a Maven publication called "release".
            create<MavenPublication>("release") {
                from(components["release"])
                groupId = "com.github.crocodic-studio"
                artifactId = "AndroidCoreProject"
                version = "4.0.1"
            }
        }
    }
}