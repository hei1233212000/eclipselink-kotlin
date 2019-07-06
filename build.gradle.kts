import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    val kotlinVersion = "1.3.41"
    kotlin("jvm") version kotlinVersion
    id("org.jetbrains.kotlin.plugin.jpa") version kotlinVersion
    jacoco
}

group = "poc"
version = "1.0-SNAPSHOT"

repositories {
    jcenter()
}

val jpaVersion = "2.2"
val eclipselinkVersion = "2.7.1" // 2.7.1 has issue but the issue is fixed in 2.7.2
val junitJupiterVersion = "5.2.0"
val spekVersion = "2.0.5"
val kluentVersion = "1.49"
val h2Version = "1.4.199"
dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("reflect"))
    implementation("org.eclipse.persistence:eclipselink:$eclipselinkVersion") {
        exclude(group = "org.eclipse.persistence")
    }
    implementation("javax.persistence:javax.persistence-api:$jpaVersion")

    testImplementation(kotlin("test"))
    testImplementation("com.h2database:h2:$h2Version")
    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitJupiterVersion")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitJupiterVersion")

    testImplementation("org.spekframework.spek2:spek-dsl-jvm:$spekVersion")
    testRuntimeOnly("org.spekframework.spek2:spek-runner-junit5:$spekVersion")

    testImplementation("org.amshove.kluent:kluent:$kluentVersion")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

tasks.withType<Test> {
    useJUnitPlatform {
        includeEngines("spek2")
    }

    finalizedBy("jacocoTestReport")
}
