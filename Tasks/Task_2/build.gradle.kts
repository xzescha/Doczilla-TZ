plugins {
    java
    war
}

group = "com.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.postgresql:postgresql:42.7.4")
    implementation("jakarta.servlet:jakarta.servlet-api:6.1.0")
    implementation("org.json:json:20231013")

    providedCompile("javax.servlet:javax.servlet-api:4.0.1")
}


tasks.war {
    archiveFileName.set("Task_2.war")
}
