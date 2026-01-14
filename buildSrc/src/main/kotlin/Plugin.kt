package com.yausername.youtubedl_android

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.extra
import java.util.Properties

open class PublishConfigurationExtension {
    var isPublished: Boolean = false
    var artifactId: String = ""
}


open class PublishPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        project.extensions.create<PublishConfigurationExtension>(
            "configurePublishing", PublishConfigurationExtension::class.java
        )
        val localProperties = Properties()
        val localPropertiesFile = project.rootProject.file("local.properties")
        if (localPropertiesFile.exists()) {
            localProperties.load(localPropertiesFile.inputStream())
            localProperties.forEach { (key, value) ->
                project.extra.set(key.toString(), value)
            }
        }
    }
}
