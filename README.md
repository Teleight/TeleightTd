<h1>Teleight Td 
<sub><sub><sub><sub><sub>The most lightweight java telegram td wrapper</sub></sub></sub></sub></sub>
</h1>

TeleightTd is a java telegram td wrapper written with performance and ease of use at mind. It fully utilizes the modern java APIs in order to have a more flexible environment for developers

TeleightTd depends on TdLight natives to add more features and to add more compatibility with other platforms.

**The project is still in an alpha phase, and most bot api methods are not yet available. Please see [how to contribute](CONTRIBUTING.md)**

## Features
TeleightTd is written towards the latest Java 21 LTS, thus providing the latest features provided by this version.

- It provides a new api design that is more flexible and easier to use
- It mostly uses native calls to reduce overhead to the minimum

## Installation

Read carefully the [tdlight guide](https://github.com/tdlight-team/tdlight-java) to their native library.

Clone this repo, and run this command
```bash
./gradlew publishToMavenLocal
```

Then, in your project, add the dependency
```kotlin
repositories {
    mavenLocal()
    
    //TdLight
    maven("https://mvn.mchv.eu/repository/mchv/")
}

dependencies {
    implementation("org.teleight:TeleightTd:1.0")
    val tdlightVersion = project.property("tdlight_version")
    implementation(platform("it.tdlight:tdlight-java-bom:${tdlightVersion}"))
    implementation("it.tdlight:tdlight-java:${tdlightVersion}}")
    //Use the appropriate native for your system
    implementation("it.tdlight:tdlight-natives:4.0.374:windows_amd64")
}
```
# Usage

An example of how to use the TeleightTd library is available [here](/demo).

## Contributing
We welcome all submissions to this project, but please follow the contributing guidelines situated in [CONTRIBUTING.md](CONTRIBUTING.md)

# Credits
* The [contributors](https://github.com/Teleight/TeleightTd/graphs/contributors) of the project.
* [TdLight](https://github.com/tdlight-team/tdlight-java) for their native tdlib wrapper.
* [Minestom](https://github.com/Minestom/Minestom) for the amazing api design.

# License
This project is licensed under the [GNU General Public License v3.0](LICENSE).
