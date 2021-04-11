plugins {
    id("example.simian.kotlin-library-conventions")
    application
}

tasks.getByName<Jar>("jar") {
    enabled = true
    manifest {
        attributes["Main-Class"] = "example.simian.app.ApplicationKt"
    }
    from({
        configurations.runtimeClasspath.get().filter { it.name.endsWith("jar") }.map { zipTree(it) }
    })
}

group = "example.simian"
version = "1.0.0-SNAPSHOT"

application {
    mainClass.set("example.simian.app.ApplicationKt")
}
