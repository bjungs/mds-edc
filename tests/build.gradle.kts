dependencies {
    testImplementation(libs.edc.boot.lib)
    testImplementation(libs.edc.junit)
    testImplementation(testFixtures(libs.edc.management.api.test.fixtures))

    testImplementation(libs.assertj)
    testImplementation(libs.awaitility)
    testImplementation(libs.junit.jupiter)
    testImplementation(libs.mockserver.netty)
    testImplementation(libs.rest.assured)

    testRuntimeOnly(project(":launchers:connector"))
}
