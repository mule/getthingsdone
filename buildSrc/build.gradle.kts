import org.jetbrains.kotlin.gradle.dsl.JvmTarget
plugins {
    `kotlin-dsl`
}
repositories {
    google()
    mavenCentral()
}

dependencies {
    implementation(libs.kgp)
    implementation(libs.agp)
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_17) // Use the new compilerOptions DSL
    }
}

kotlin {
    jvmToolchain(17)
}