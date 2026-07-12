plugins {
    java
    id("io.qameta.allure") version "2.12.0"
}

group = "com.shopkart"
version = "1.0-SNAPSHOT"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(22))
    }
}

repositories {
    mavenCentral()
}

dependencies {

    // JUnit
    testImplementation(platform("org.junit:junit-bom:5.10.2"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.junit.platform:junit-platform-suite-api")

    testRuntimeOnly("org.junit.platform:junit-platform-suite-engine")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    // Cucumber
    testImplementation("io.cucumber:cucumber-java:7.14.0")
    testImplementation("io.cucumber:cucumber-junit-platform-engine:7.14.0")
    testImplementation("io.cucumber:cucumber-picocontainer:7.14.0")

    // Selenide
    testImplementation("com.codeborne:selenide:7.9.3")

    // REST Assured
    testImplementation("io.rest-assured:rest-assured:5.5.6")

    // Dotenv
    testImplementation("io.github.cdimascio:dotenv-java:3.2.0")

    // Allure
    testImplementation("io.qameta.allure:allure-cucumber7-jvm:2.35.1")
    testImplementation("io.qameta.allure:allure-rest-assured:2.35.1")
    testImplementation("io.qameta.allure:allure-selenide:2.35.1")
    testImplementation("io.qameta.allure:allure-junit5:2.35.1")

    // MySQL
    testImplementation("com.mysql:mysql-connector-j:9.4.0")


}

allure {
    version.set("2.35.1")
}

tasks.test {

    useJUnitPlatform()

    systemProperty(
        "cucumber.plugin",
        "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
    )

    testLogging {

        events(
            org.gradle.api.tasks.testing.logging.TestLogEvent.PASSED,
            org.gradle.api.tasks.testing.logging.TestLogEvent.FAILED,
            org.gradle.api.tasks.testing.logging.TestLogEvent.SKIPPED
        )

        exceptionFormat =
            org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
    }
}

// Shared test source set

val testSourceSet = sourceSets.test.get()

fun Test.configureTestTask() {

    testClassesDirs = testSourceSet.output.classesDirs
    classpath = testSourceSet.runtimeClasspath

    useJUnitPlatform()

    systemProperty(
        "cucumber.plugin",
        "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
    )
}

// Smoke

val smoke by tasks.registering(Test::class) {

    description = "Runs Smoke scenarios"

    group = "verification"

    configureTestTask()

    systemProperty("cucumber.filter.tags", "@smoke")
}

// API

val api by tasks.registering(Test::class) {

    description = "Runs API scenarios"

    group = "verification"

    configureTestTask()

    systemProperty("cucumber.filter.tags", "@api")
}

// UI

val ui by tasks.registering(Test::class) {

    description = "Runs UI scenarios"

    group = "verification"

    configureTestTask()

    systemProperty("cucumber.filter.tags", "@ui")
}

// Database

val db by tasks.registering(Test::class) {

    description = "Runs Database scenarios"

    group = "verification"

    configureTestTask()

    systemProperty("cucumber.filter.tags", "@db")
}

// Negative

val negative by tasks.registering(Test::class) {

    description = "Runs Negative scenarios"

    group = "verification"

    configureTestTask()

    systemProperty("cucumber.filter.tags", "@negative")
}

// Security

val security by tasks.registering(Test::class) {

    description = "Runs Security scenarios"

    group = "verification"

    configureTestTask()

    systemProperty("cucumber.filter.tags", "@security")
}

// E2E

val e2e by tasks.registering(Test::class) {

    description = "Runs End-to-End scenarios"

    group = "verification"

    configureTestTask()

    systemProperty("cucumber.filter.tags", "@e2e")
}

// Reporting

val reporting by tasks.registering(Test::class) {

    description = "Runs Reporting framework tests"

    group = "verification"

    configureTestTask()

    include("**/ReportingConfigurationTest.class")
}