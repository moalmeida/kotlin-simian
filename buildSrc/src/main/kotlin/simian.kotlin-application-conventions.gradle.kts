plugins {
    id("simian.kotlin-library-conventions")
    id("simian.kotlin-testing-conventions")
    application
}

tasks.getByName<Jar>("jar") {
    enabled = true
    manifest {
        attributes["Main-Class"] = "simian.app.ApplicationKt"
    }
    from({
        configurations.runtimeClasspath.get().filter { it.name.endsWith("jar") }.map { zipTree(it) }
    })
}

group = "simian"
version = "1.0.0-SNAPSHOT"

application {
    mainClass.set("simian.app.ApplicationKt")
}
