plugins {
    java
}

group = "ru.madeira"
version = "unspecified"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
    implementation(project(":jooq_generated"))
}
tasks.getByName<Test>("test") {
    useJUnitPlatform()
}