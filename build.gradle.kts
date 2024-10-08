plugins {
    kotlin("jvm") version "2.0.20" apply false
}

repositories {
    mavenCentral()
}

buildscript {
    dependencies {
        classpath(kotlin("gradle-plugin", version = "2.0.20"))
    }
}
