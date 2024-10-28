plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.jabama.core.network"
    compileSdk = 34

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(":core:common"))

    implementation(libs.okhttp)
    implementation(libs.okhttp.loggging)
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.gson)
    implementation(libs.retrofit.coroutines.adapter)
    implementation(libs.koin)
}