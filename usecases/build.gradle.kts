plugins {
    id("simian.kotlin-library-conventions")
}

dependencies {
    api(project(":entities"))

    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    implementation("com.fasterxml.jackson.core:jackson-annotations:2.12.2")
    implementation("com.fasterxml.jackson.core:jackson-core:2.12.2")
    implementation("org.slf4j:slf4j-api:1.7.30")
    implementation("ch.qos.logback:logback-core:1.2.3")

    runtimeOnly("com.fasterxml.jackson.module:jackson-module-kotlin:2.12.2")
    runtimeOnly("ch.qos.logback:logback-classic:1.2.3")

}
