import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    id("java")
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

dependencies {
    //Project
    implementation(rootProject)

    //TdLight
    val tdlightVersion = project.property("tdlight_version")
    implementation(platform("it.tdlight:tdlight-java-bom:${tdlightVersion}"))
    implementation("it.tdlight:tdlight-java:${tdlightVersion}}")
    implementation("it.tdlight:tdlight-natives:4.0.506:windows_amd64")
}


tasks.withType<ShadowJar> {
    manifest.attributes.apply {
        put("Main-Class", "org.teleight.teleightbots.demo.Main")
    }

    archiveFileName.set("demo.jar")
}
