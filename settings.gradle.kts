pluginManagement {
    plugins {
        id("com.github.johnrengelman.shadow") version "6.1.0"
        id("io.micronaut.application") version "1.4.2"
    }
    repositories {
        gradlePluginPortal()
        maven {
            url = uri("https://plugins.gradle.org/m2/")
        }
    }
}

rootProject.name = "example-simian"
include("usecases", "entities", ":in-memory-repository", ":micronaut-app")

project(":in-memory-repository").projectDir = file("adapter/in-memory-repository")
project(":micronaut-app").projectDir = file("app/micronaut-app")
