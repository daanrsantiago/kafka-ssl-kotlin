plugins {
    kotlin("jvm") version "2.1.0"
}

group = "dev.danielsantiago.kafka.ssl.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("org.apache.kafka:kafka-clients:3.9.0")
    implementation("org.slf4j:slf4j-simple:1.7.36")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.18.2")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.18.2")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.18.2")
}

tasks.register<Jar>("buildProducerJar") {
    archiveFileName.set("producer.jar")
    group = "build" // OR, for example, "build"
    description = "Creates a self-contained fat JAR of the application that can be run."
    manifest.attributes["Main-Class"] = "dev.danielsantiago.kafka.ssl.example.ProducerApplicationKt"
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    val dependencies = configurations
        .runtimeClasspath
        .get()
        .map(::zipTree)
    from(dependencies)
    with(tasks.jar.get())
}

tasks.register<Jar>("buildConsumerJar") {
    archiveFileName.set("consumer.jar")
    group = "build" // OR, for example, "build"
    description = "Creates a self-contained fat JAR of the application that can be run."
    manifest.attributes["Main-Class"] = "dev.danielsantiago.kafka.ssl.example.ConsumerApplicationKt"
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    val dependencies = configurations
        .runtimeClasspath
        .get()
        .map(::zipTree)
    from(dependencies)
    with(tasks.jar.get())
}

tasks.named("build") {
    dependsOn("buildConsumerJar")
    dependsOn("buildProducerJar")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(18)
}