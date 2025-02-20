plugins {
    `java-library`
}

val edcVersion = libs.versions.edc

allprojects {
    apply(plugin = "java")

    repositories {
        mavenCentral()
    }

    testing {
        suites {
            val test by getting(JvmTestSuite::class) {
                useJUnitJupiter()
            }
        }
    }

    // needed for E2E tests
    tasks.register("printClasspath") {
        dependsOn(tasks.compileJava)
        doLast {
            println(sourceSets["main"].runtimeClasspath.asPath)
        }
    }

}
