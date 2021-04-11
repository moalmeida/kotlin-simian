import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("example.simian.kotlin-common-conventions")
    id("project-report")
    id("jacoco")
    `java-library`
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = JavaVersion.VERSION_1_8.toString()
        javaParameters = true
    }

}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.test {
    extensions.configure(JacocoTaskExtension::class) {}
    finalizedBy("jacocoTestReport")
}

jacoco {
    toolVersion = "0.8.6"
}

tasks.jacocoTestReport {
    reports {
        html.isEnabled = true
        xml.isEnabled = false
        csv.isEnabled = false
    }
    finalizedBy("jacocoTestCoverageVerification")
}

tasks.jacocoTestCoverageVerification {
    violationRules {
        rule {
            enabled = false
            limit {
                minimum = "0.30".toBigDecimal()
            }
        }
        rule {
            enabled = false
            element = "CLASS"
            limit {
                counter = "BRANCH"
                value = "COVEREDRATIO"
                minimum = "0.60".toBigDecimal()
            }
            limit {
                counter = "LINE"
                value = "COVEREDRATIO"
                minimum = "0.60".toBigDecimal()
            }
            limit {
                counter = "LINE"
                value = "TOTALCOUNT"
                maximum = "200".toBigDecimal()
            }
            excludes = listOf()
        }
    }
}

val testCoverage by tasks.registering {
    group = "verification"
    description = "Runs the unit tests with coverage"

    dependsOn(
        ":test",
        ":jacocoTestReport",
        ":jacocoTestCoverageVerification"
    )

    tasks["jacocoTestReport"].mustRunAfter(tasks["test"])
    tasks["jacocoTestCoverageVerification"].mustRunAfter(tasks["jacocoTestReport"])
}

tasks.register("stage") {
    dependsOn(
        "build",
        "clean"
    )
    tasks["build"].mustRunAfter(tasks["clean"])
}