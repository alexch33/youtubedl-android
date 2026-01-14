plugins {
    id("com.yausername.youtubedl_android")
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("maven-publish")
    id("signing")
}

android {
    namespace = "com.yausername.youtubedl_android"
    compileSdk = 36

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

publishing {
    publications {
        create<MavenPublication>("release") {
            afterEvaluate {
                from(components["release"])
            }
            groupId = "io.github.alexch33"
            artifactId = "youtubedl-android"
            version = "0.18.1-alexch33-2"

            pom {
                name.set("youtubedl-android")
                description.set("A lib for downloading youtube videos in android.")
                url.set("https://github.com/alexch33/youtubedl-android")
                licenses {
                    license {
                        name.set("The Unlicense")
                        url.set("http://unlicense.org/`s")
                    }
                }
                developers {
                    developer {
                        id.set("alexch33")
                        name.set("Alexey")
                        email.set("i3poac@gmail.com")
                    }
                }
                scm {
                    connection.set("scm:git:github.com/alexch33/youtubedl-android.git")
                    developerConnection.set("scm:git:ssh://github.com/alexch33/youtubedl-android.git")
                    url.set("https://github.com/alexch33/youtubedl-android/tree/master")
                }
            }
        }
    }
}

signing {
    val secretKey = project.findProperty("signing.secretKeyRingFile")?.let {
        project.rootProject.file(it).readText()
    }
    val password = project.findProperty("signing.password")?.toString()

    if (secretKey != null && password != null) {
        println("GPG secret key file found. Configuring signing for :library...")
        useInMemoryPgpKeys(secretKey, password)
        sign(publishing.publications["release"])
    } else {
        println("signing.secretKeyRingFile or signing.password not found, skipping signing.")
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation(project(":common"))

    implementation("androidx.appcompat:appcompat:${rootProject.extra["appCompatVer"]}")
    implementation("androidx.core:core-ktx:${rootProject.extra["coreKtxVer"]}")
    testImplementation("junit:junit:${rootProject.extra["junitVer"]}")
    androidTestImplementation("androidx.test.ext:junit:${rootProject.extra["androidJunitVer"]}")
    androidTestImplementation("androidx.test.espresso:espresso-core:${rootProject.extra["espressoVer"]}")

    implementation("com.fasterxml.jackson.core:jackson-databind:${rootProject.extra["jacksonVer"]}")
    implementation("com.fasterxml.jackson.core:jackson-annotations:${rootProject.extra["jacksonVer"]}")
    implementation("commons-io:commons-io:${rootProject.extra["commonsIoVer"]}")
}
