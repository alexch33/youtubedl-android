import com.yausername.youtubedl_android.PublishConfigurationExtension

plugins {
    id("com.yausername.youtubedl_android")
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("maven-publish")
    id("signing")
}

project.extensions.configure<PublishConfigurationExtension>("configurePublishing") {
    isPublished = true
    artifactId = "youtubedl-android"
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

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("release") {
                from(components["release"])

                groupId = "io.github.alexch33"
                artifactId = "youtubedl-android"
                version = "0.18.1-alexch33-1"

                pom {
                    name.set("youtubedl-android")
                    description.set("A forked version of youtubedl-android.")
                    url.set("https://github.com/alexch33/youtubedl-android")
                    licenses {
                        license {
                            name.set("GPL-3.0 license")
                            url.set("https://www.gnu.org/licenses/gpl-3.0.en.html")
                        }
                    }
                    developers {
                        developer {
                            id.set("alexch33")
                            name.set("alexch33")
                        }
                    }
                    scm {
                        connection.set("scm:git:https://github.com/alexch33/youtubedl-android.git")
                        url.set("https://github.com/alexch33/youtubedl-android")
                    }
                }
            }
        }

        repositories {
            maven {
                name = "SonatypeCentral"
                url = uri("https://central.sonatype.com/api/v1/publisher/upload")
                credentials {
                    username = project.property("ossrhUsername").toString()
                    password = project.property("ossrhPassword").toString()
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
            println("GPG secret key file found. Configuring signing...")
            useInMemoryPgpKeys(secretKey, password)
            sign(publishing.publications["release"])
        } else {
            println("signing.secretKeyRingFile or signing.password not found, skipping signing.")
        }
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
