plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.jabama.core.data"
    compileSdk = 34

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(":core:network"))
    implementation(project(":core:model"))
    implementation(project(":core:common"))
    implementation(project(":core:domain"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.coroutines.core)
    implementation(libs.koin)
    implementation(libs.preference.ktx)
}