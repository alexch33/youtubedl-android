package com.yausername.youtubedl_android

import org.gradle.api.Project
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPom
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.create
import org.gradle.kotlin.dsl.get
import org.gradle.plugins.signing.SigningExtension

internal fun Project.configurePublishingToMavenCentral() {
    val isReleaseVersion = !version.toString().endsWith("SNAPSHOT")

    configure<PublishingExtension> {
        publications {
            create<MavenPublication>("release") {
                groupId = "com.github.alexch33"
                artifactId = project.name
                version = project.version.toString()

                from(components["release"])

                pom {
                    commonPomConfiguration(project)
                }
            }
        }

        repositories {
            maven {
                name = "Sonatype"
                url = uri("https://central.sonatype.com/api/v1/publisher/upload")
                credentials {
                    username = project.property("ossrhUsername").toString()
                    password = project.property("ossrhPassword").toString()
                }
            }
        }
    }

    // Configure GPG Signing
    configure<SigningExtension> {
        sign(project.extensions.getByType(PublishingExtension::class.java).publications)
    }
}


internal fun MavenPom.commonPomConfiguration(project: Project) {
    name.set(project.name)
    description.set("A forked version of youtubedl-android.")
    url.set("https://github.com/alexch33/youtubedl-android") // Link to fork
    inceptionYear.set("2024")
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
