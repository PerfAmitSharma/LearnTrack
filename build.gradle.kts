plugins {
    id("java")
    id("application")
}

group = "com.airtribe.learntrack"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

application {
    mainClass.set("com.airtribe.learntrack.ui.Main")
}

tasks.test {
    useJUnitPlatform()
}

