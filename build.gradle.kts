plugins {
	java
	id("org.springframework.boot") version "3.0.2"
	id("io.spring.dependency-management") version "1.1.0"
	id("jacoco")
	id("maven-publish")
}

group = "com.harbourspace"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}


tasks.withType<Test> {
	useJUnitPlatform()
}

val jacocoTestReport = tasks.named<JacocoReport>("jacocoTestReport") {
	executionData.from(fileTree(project.buildDir.absolutePath).include("jacoco/*.exec"))
	classDirectories.setFrom(files(project.sourceSets.main.get().output))
	sourceDirectories.setFrom(files(project.sourceSets.main.get().allSource.srcDirs))

	reports {
		xml.required.set(true)
		csv.required.set(false)
		html.required.set(true)
	}
}

publishing {
	repositories {
		maven {
			name = "GitHubPackages"
			url = uri("https://maven.pkg.github.com/Ernestico98/SpringBoot-GithubWorkflow")
//			credentials {
//				username = project.findProperty("gpr.user") ?: System.getenv("USERNAME")
//				password = project.findProperty("gpr.key") ?: System.getenv("TOKEN")
//			}
		}
	}
}

tasks.register("publishToGitHubPackages") {
	group = "publishing"
	dependsOn("publish")

	doLast {
		println("Publishing to GitHub Packages...")
	}
}