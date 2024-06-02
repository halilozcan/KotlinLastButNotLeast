plugins {
    kotlin("jvm") version "1.9.23"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation ("org.jetbrains.kotlin:kotlin-reflect:1.9.10")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}