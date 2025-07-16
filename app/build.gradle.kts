plugins {
    id("com.android.application")
    kotlin("android")
    alias(libs.plugins.google.gms.google.services)
    alias(libs.plugins.hilt) // Apply Hilt plugin using the alias from libs.versions.toml
    kotlin("kapt") // Add kotlin-kapt plugin for annotation processing
}

android {
    compileSdk = libs.versions.compile.sdk.version.get().toInt()

    defaultConfig {
        minSdk = libs.versions.min.sdk.version.get().toInt()
        namespace = "dev.muuli.gtd.app.app"

        applicationId = AppCoordinates.APP_ID
        versionCode = AppCoordinates.APP_VERSION_CODE
        versionName = AppCoordinates.APP_VERSION_NAME
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildFeatures {
        viewBinding = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    lint {
        warningsAsErrors = false
        abortOnError = true
        disable.add("GradleDependency")
        disable.add("AndroidGradlePluginVersion")

    }

    packaging {
        resources {
            excludes.add("/META-INF/LICENSE.md")
            excludes.add("/META-INF/LICENSE-notice.md")
            excludes.add("/META-INF/NOTICE.md")
            // You might also need to exclude other common conflicting files
            // For example, if you see similar errors for NOTICE.txt or other license files:
            // excludes += '/META-INF/LICENSE.txt'
            // excludes += '/META-INF/NOTICE.txt'
            // excludes += '/META-INF/DEPENDENCIES'
            // excludes += '/META-INF/AL2.0'
            // excludes += '/META-INF/LGPL2.1'
        }
    }
    // Use this block to configure different flavors
//    flavorDimensions("version")
//    productFlavors {
//        create("full") {
//            dimension = "version"
//            applicationIdSuffix = ".full"
//        }
//        create("demo") {
//            dimension = "version"
//            applicationIdSuffix = ".demo"
//        }
//    }
}



dependencies {
    implementation(projects.libraryAndroid)
    implementation(projects.libraryCompose)
    implementation(projects.libraryKotlin)

    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.constraint.layout)
    implementation(libs.androidx.core.ktx)
    implementation(libs.firebase.database)
    implementation(libs.firebase.auth)
    
    // Hilt Dependencies
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    testImplementation(libs.junit)

    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.androidx.test.ext.junit.ktx)
    androidTestImplementation(libs.androidx.test.rules)
    androidTestImplementation(libs.espresso.core)
}

hilt {
    enableAggregatingTask = false
}

kapt {
    correctErrorTypes = true
}
