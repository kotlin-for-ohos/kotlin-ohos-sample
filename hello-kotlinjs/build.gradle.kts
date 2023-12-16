import org.jetbrains.kotlin.gradle.targets.js.npm.npmProject
import org.jetbrains.kotlin.incremental.deleteDirectoryContents

plugins {
    kotlin("multiplatform") version "1.9.21"
    kotlin("plugin.serialization") version "1.9.21"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://s01.oss.sonatype.org/content/repositories/snapshots")
}

kotlin {
    js(IR) {
        generateTypeScriptDefinitions()
        useEsModules()
        nodejs()
        binaries.executable()

        tasks.register<Copy>("copyOutput") {
            group = "nodejs"
            dependsOn("jsPackageJson")
            dependsOn("jsDevelopmentExecutableCompileSync")
            dependsOn("compileDevelopmentExecutableKotlinJs")

            val sourceDir = File(compilations.getByName("main").npmProject.dir, "kotlin")
            val destDir = File("../HelloHarmony/entry/src/main/js")

            from(sourceDir)
            into(destDir)

            doFirst {
                destDir.deleteDirectoryContents()
            }

            doLast {
                destDir.listFiles()?.forEach {
                    if(it.name.endsWith(".mjs")) {
                        val lines = it.readLines()
                        val newLines = ArrayList<String>(lines.size)
                        var importDone = false
                        for (line in lines) {
                            if (line.startsWith("//# sourceMappingURL=")) {
                                newLines += line.replace(".mjs.map", ".js.map")
                            } else if (importDone) {
                                newLines += line
                            } else if (line.startsWith("//region block")) {
                                importDone = true
                                newLines += line
                            } else {
                                newLines += line.replace(Regex("""'(\./.+)\.mjs'""")) { "'${it.groupValues[1]}.js'" }
                            }
                        }
                        it.writeText(newLines.joinToString("\n"))

                        it.renameTo(File(destDir, it.name.replace(".mjs", ".js")))
                    } else if(it.name.endsWith(".mjs.map")) {
                        it.renameTo(File(destDir, it.name.replace(".mjs.map", ".js.map")))
                    }
                }
            }
        }
    }

    sourceSets {
        jsMain {
            val ktorVersion = "2.3.7-SNAPSHOT"
            dependencies {
                implementation("com.bennyhuo.ktor:ktor-client-core:$ktorVersion")
                implementation("com.bennyhuo.ktor:ktor-client-content-negotiation:$ktorVersion")
                implementation("com.bennyhuo.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
                implementation("com.bennyhuo.ktor:ktor-client-js:$ktorVersion")
            }
        }
    }
}
