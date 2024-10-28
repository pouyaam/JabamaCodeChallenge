plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.jabama.core.common"
    compileSdk = 34

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(libs.coroutines.android)
    implementation(libs.androidx.core.ktx)
    implementation(libs.koin)
    implementation(libs.retrofit.converter.gson)
}