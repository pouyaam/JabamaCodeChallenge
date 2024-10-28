plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.jabama.core.domain"
    compileSdk = 34

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(":core:model"))
    implementation(project(":core:common"))

    implementation(libs.androidx.core.ktx)
}