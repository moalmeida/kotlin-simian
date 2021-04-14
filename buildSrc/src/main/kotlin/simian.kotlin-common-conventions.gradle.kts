import org.gradle.api.JavaVersion

plugins {
    kotlin("jvm")
    kotlin("kapt")
}

java.sourceCompatibility = JavaVersion.VERSION_11
java.targetCompatibility = JavaVersion.VERSION_11

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    constraints {
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    }

    implementation("javax.inject:javax.inject:1")
    implementation("javax.annotation:javax.annotation-api:1.3.2")

    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-noarg")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.7.1")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.7.1")

    testImplementation("org.mockito:mockito-core:3.8.0")
    testImplementation("org.mockito:mockito-junit-jupiter:3.8.0")
    testImplementation("org.mockito.kotlin:mockito-kotlin:2.2.8")

}

