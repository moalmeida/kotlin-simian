import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("simian.kotlin-common-conventions")
    `java-library`
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = JavaVersion.VERSION_11.toString()
        javaParameters = true
    }
}
