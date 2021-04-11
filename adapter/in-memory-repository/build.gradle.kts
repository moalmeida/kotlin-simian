plugins {
    id("example.simian.kotlin-library-conventions")
}

dependencies {
    api(project(":entities"))

    implementation("org.jetbrains.kotlin:kotlin-reflect:1.4.30")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.4.30")

}
