import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    id("simian.kotlin-application-conventions")
    id("com.github.johnrengelman.shadow")
    id("io.micronaut.application")
}

micronaut {
    enableNativeImage(true)
    runtime("netty")
    testRuntime("junit5")
    processing {
        incremental(true)
        annotations("simian.*")
    }
}

tasks.getByName<ShadowJar>("shadowJar") {
    mergeServiceFiles()
}

dependencies {
    implementation(project(":usecases"))
    implementation(project(":in-memory-repository"))

    kapt(platform("io.micronaut:micronaut-bom:2.4.1"))
    kapt("io.micronaut:micronaut-inject-java")
    kapt("io.micronaut:micronaut-validation")
    kapt("io.micronaut.data:micronaut-data-processor")

    implementation(platform("io.micronaut:micronaut-bom:2.4.1"))
    implementation("io.micronaut.kotlin:micronaut-kotlin-runtime")

    implementation("io.micronaut:micronaut-inject")
    implementation("io.micronaut:micronaut-validation")
    implementation("javax.annotation:javax.annotation-api")

    implementation("io.micronaut:micronaut-runtime")
    implementation("io.micronaut:micronaut-http-client")
    implementation("io.micronaut:micronaut-http-server-netty")

    runtimeOnly("ch.qos.logback:logback-classic")
    runtimeOnly("com.fasterxml.jackson.module:jackson-module-kotlin")

    kaptTest(platform("io.micronaut:micronaut-bom:2.4.1"))
    kaptTest("io.micronaut:micronaut-inject-java")
    testImplementation(platform("io.micronaut:micronaut-bom:2.4.1"))
}
