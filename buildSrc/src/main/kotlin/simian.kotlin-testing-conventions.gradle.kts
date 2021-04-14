import org.gradle.kotlin.dsl.*

plugins {
    id("simian.kotlin-common-conventions")
    id("project-report")
    jacoco
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.test {
    extensions.configure(JacocoTaskExtension::class) {}
    finalizedBy(tasks.jacocoTestReport)
}

jacoco {
    toolVersion = "0.8.6"
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)

    reports {
        xml.isEnabled = true
        html.isEnabled = true
        parallelStream()
    }

    sourceSets(sourceSets.main.get())

    finalizedBy(tasks.jacocoTestCoverageVerification)
}

tasks.jacocoTestCoverageVerification {
    violationRules {
        rule {
            limit {
                minimum = "0.80".toBigDecimal()
            }
        }
        rule {
            element = "CLASS"
            limit {
                counter = "BRANCH"
                value = "COVEREDRATIO"
                minimum = "0.80".toBigDecimal()
            }
            limit {
                counter = "LINE"
                value = "COVEREDRATIO"
                minimum = "0.80".toBigDecimal()
            }
            limit {
                counter = "LINE"
                value = "TOTALCOUNT"
                maximum = "250".toBigDecimal()
            }
        }
    }
}

