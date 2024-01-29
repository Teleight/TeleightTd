import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    id("java")
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

repositories {
    mavenCentral()
    mavenLocal()

    //TdLight
    maven("https://mvn.mchv.eu/repository/mchv/")
}

dependencies {
    //Project
    implementation(rootProject)

    //TdLight
    val tdlightVersion = project.property("tdlight_version");
    implementation(platform("it.tdlight:tdlight-java-bom:${tdlightVersion}"))
    implementation("it.tdlight:tdlight-java:${tdlightVersion}}")


    // AutoGenerator
    implementation("com.squareup:javapoet:1.13.0")
    implementation("com.github.javaparser:javaparser-symbol-solver-core:3.25.8")
    implementation("com.github.javaparser:javaparser-core:3.25.8")
    implementation("com.github.javaparser:javaparser-core-serialization:3.25.8")


    //Commons Lang
    implementation("org.apache.commons:commons-lang3:3.14.0")
    implementation("commons-io:commons-io:2.15.1")
}


tasks.withType<ShadowJar> {
    manifest.attributes.apply {
        put("Main-Class", "org.teleight.td.codegenerator.CodeGenerator")
    }

    archiveFileName.set("CodeGenerator.jar")
}
