package com.yausername.youtubedl_android

import org.gradle.api.Project
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPom
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.create
import org.gradle.kotlin.dsl.get

internal fun Project.configurePublishingToGithubPackages() {
    afterEvaluate {
        configure<PublishingExtension> {
            publications {
                create<MavenPublication>("release") {
                    groupId = "com.github.alexch33"
                    artifactId = project.name // Use the module's name as the artifactId
                    version = project.version.toString()

                    from(components["release"])

                    pom {
                        commonPomConfiguration(project)
                    }
                }
            }

            repositories {
                maven {
                    name = "GitHubPackages"
                    url = uri("https://maven.pkg.github.com/alexch33/youtubedl-android")
                    credentials {
                        username = System.getenv("GITHUB_ACTOR")
                        password = System.getenv("GITHUB_TOKEN")
                    }
                }
            }
        }
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
