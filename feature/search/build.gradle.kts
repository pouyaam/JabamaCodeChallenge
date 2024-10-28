plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.jabama.feature.search"
    compileSdk = 34

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(":core:domain"))
    implementation(project(":core:designsystem"))
    implementation(project(":core:common"))
    implementation(project(":core:data"))
    implementation(project(":core:network"))
    implementation(project(":core:model"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(platform(libs.androidx.compose.bom))
    api(libs.androidx.ui)
    api(libs.androidx.ui.graphics)
    implementation(libs.androidx.foundation.android)
    debugApi(libs.androidx.ui.tooling)
    api(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.material.icons.core)
    implementation(libs.androidx.compose.material.icons.extended)
    implementation(libs.koin)
    api(libs.koin.compose)
    implementation(libs.immutable.list)
}