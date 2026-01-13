plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
}

dependencies {
    implementation("com.android.tools.build:gradle:8.11.2")
}

gradlePlugin {
    plugins {
        create("youtubedl-android") {
            id = "com.yausername.youtubedl_android" // The ID of root project
            implementationClass = "com.yausername.youtubedl_android.PublishPlugin"
        }
    }
}
