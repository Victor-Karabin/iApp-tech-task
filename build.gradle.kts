// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.google.dagger.hilt.android") version "2.47" apply false
}

buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(libs.gradle.tools)
        classpath(libs.kotlin.gradle)
    }
}

// merge test-reports
val testWithReport = tasks.register<TestReport>("testWithReport") {
    destinationDirectory.set(file("$buildDir/reports/tests/test"))

    //for kotlin modules
    val fromKotlin = subprojects.mapNotNull { project ->
        project.tasks.findByPath("test")
    }
    testResults.from(fromKotlin)

    //for android modules
    val fromAndroid = subprojects.mapNotNull { project ->
        project.tasks.findByPath("testDevelopmentDebugUnitTest")
    }
    testResults.from(fromAndroid)
}

subprojects {
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = "11"
        }
    }


    // reports for tests
    tasks.withType<Test> {
        useJUnit()
        testLogging {
            events("passed", "skipped", "failed")
        }
        reports.html.required.set(false)
    }

    // reports and metrics for jetpack compose
    // @see https://github.com/androidx/androidx/blob/androidx-main/compose/compiler/design/compiler-metrics.md#enabling-metrics
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
        kotlinOptions {
            if (project.findProperty("composeCompilerReports") == "true") {
                freeCompilerArgs += listOf(
                    "-P",
                    "plugin:androidx.compose.compiler.plugins.kotlin:reportsDestination=${project.buildDir.absolutePath}/compose_compiler"
                )
            }
            if (project.findProperty("composeCompilerMetrics") == "true") {
                freeCompilerArgs += listOf(
                    "-P",
                    "plugin:androidx.compose.compiler.plugins.kotlin:metricsDestination=${project.buildDir.absolutePath}/compose_compiler"
                )
            }
        }
    }
}

tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}