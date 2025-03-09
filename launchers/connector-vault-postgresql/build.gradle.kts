plugins {
    application
    distribution
}

val edcGroupId = "org.eclipse.edc"

dependencies {
    runtimeOnly(project(":launchers:connector-inmemory"))

    runtimeOnly(libs.edc.oauth2.daps)
    runtimeOnly(libs.edc.oauth2.service)
    runtimeOnly(libs.edc.vault.hashicorp)

    runtimeOnly(libs.edc.controlplane.feature.sql.bom)
    runtimeOnly(libs.edc.dataplane.feature.sql.bom)

    // Tractusx EDC migrations libraries
    implementation(libs.tractusx.edc.postgresql.migration)
    implementation(libs.tractusx.edc.data.plane.migration)
    implementation(libs.tractusx.edc.control.plane.migration)
}

application {
    mainClass = "$edcGroupId.boot.system.runtime.BaseRuntime"
}
