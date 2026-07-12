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

    // ---------------------------
    // JUnit
    // ---------------------------
    testImplementation(platform("org.junit:junit-bom:5.10.2"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.junit.platform:junit-platform-suite-api")

    testRuntimeOnly("org.junit.platform:junit-platform-suite-engine")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    // ---------------------------
    // Cucumber
    // ---------------------------
    testImplementation("io.cucumber:cucumber-java:7.14.0")
    testImplementation("io.cucumber:cucumber-junit-platform-engine:7.14.0")
    testImplementation("io.cucumber:cucumber-picocontainer:7.14.0")

    // ---------------------------
    // Selenide
    // ---------------------------
    testImplementation("com.codeborne:selenide:7.9.3")

    // ---------------------------
    // REST Assured
    // ---------------------------
    testImplementation("io.rest-assured:rest-assured:5.5.6")

    // ---------------------------
    // Dotenv
    // ---------------------------
    testImplementation("io.github.cdimascio:dotenv-java:3.2.0")

    // ---------------------------
    // Allure
    // ---------------------------
    testImplementation("io.qameta.allure:allure-cucumber7-jvm:2.35.1")
    testImplementation("io.qameta.allure:allure-rest-assured:2.35.1")
    testImplementation("io.qameta.allure:allure-selenide:2.35.1")

    // ---------------------------
    // MySQL
    // ---------------------------
    testImplementation("com.mysql:mysql-connector-j:9.4.0")

    // ---------------------------
    // Testcontainers
    // Keep only if you will use them later
    // ---------------------------
    testImplementation("org.testcontainers:junit-jupiter:1.21.3")
    testImplementation("org.testcontainers:mysql:1.21.3")

    // ---------------------------
    // Flyway
    // Keep only if you will use Flyway later
    // ---------------------------
    testImplementation("org.flywaydb:flyway-core:11.12.0")
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

val smoke by tasks.registering(Test::class) {

    description = "Runs Smoke scenarios"

    group = "verification"

    useJUnitPlatform()

    systemProperty("cucumber.filter.tags", "@smoke")
}

val api by tasks.registering(Test::class) {

    description = "Runs API scenarios"

    group = "verification"

    useJUnitPlatform()

    systemProperty("cucumber.filter.tags", "@api")
}

val ui by tasks.registering(Test::class) {

    description = "Runs UI scenarios"

    group = "verification"

    useJUnitPlatform()

    systemProperty("cucumber.filter.tags", "@ui")
}

val db by tasks.registering(Test::class) {

    description = "Runs Database scenarios"

    group = "verification"

    useJUnitPlatform()

    systemProperty("cucumber.filter.tags", "@db")
}

val negative by tasks.registering(Test::class) {

    description = "Runs Negative scenarios"

    group = "verification"

    useJUnitPlatform()

    systemProperty("cucumber.filter.tags", "@negative")
}

val security by tasks.registering(Test::class) {

    description = "Runs Security scenarios"

    group = "verification"

    useJUnitPlatform()

    systemProperty("cucumber.filter.tags", "@security")
}

val e2e by tasks.registering(Test::class) {

    description = "Runs End-to-End scenarios"

    group = "verification"

    useJUnitPlatform()

    systemProperty("cucumber.filter.tags", "@e2e")
}

val reporting by tasks.registering(Test::class) {

    description = "Runs reporting framework tests"

    group = "verification"

    useJUnitPlatform()

    include("**/ReportingConfigurationTest.class")
}