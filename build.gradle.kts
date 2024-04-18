plugins {
    kotlin("jvm") version "1.9.23"
    id("org.jetbrains.kotlinx.dataframe") version "0.13.1"
    id ("edu.sc.seis.launch4j") version "3.0.5"

}

launch4j {
    mainClassName = "MainKt"
    icon = "${projectDir}/icons/SALogo.ico"
    bundledJrePath = "C:/Users/dauss/.jdks/corretto-11.0.22"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    google()
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    implementation("org.jetbrains.kotlinx:dataframe:0.13.1")
    implementation("com.github.doyaaaaaken:kotlin-csv-jvm:1.9.3")
    implementation ("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.9.23")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(11)
}