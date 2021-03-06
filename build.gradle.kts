import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
	val kotlinVersion = "1.4.0"
	val springBootVersion = "2.3.4.RELEASE"
	repositories {
		mavenLocal()
		mavenCentral()
	}
	dependencies {
		classpath("org.springframework.boot:spring-boot-gradle-plugin:$springBootVersion")
		classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
	}
}

plugins {
	id("org.springframework.boot") version "2.3.4.RELEASE"
	id("io.spring.dependency-management") version "1.0.9.RELEASE"
	id("maven-publish")
	kotlin("jvm") version "1.4.0"
	kotlin("plugin.spring") version "1.4.0"
}

apply(plugin = "maven")
apply(plugin = "org.springframework.boot")
apply(plugin = "kotlin")

tasks.withType<JavaCompile> {
	options.encoding = "UTF-8"
}

repositories {
	mavenLocal()
	mavenCentral()
	maven(url = "https://jitpack.io")
}

group = "ie.daithi.yeelight"
version = "1.1.2-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_14

description = "api"

val springBootVersion: String = "2.3.4.RELEASE"
val swaggerVersion: String = "2.9.2"

dependencies {

	//External Dependancies
	compile("com.mollin.yapi:yapi:1.0")

	//Kotlin dependencies
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

	//Spring dependencies
	implementation("org.springframework.boot:spring-boot-starter:$springBootVersion")
	implementation("org.springframework.boot:spring-boot-starter-web:$springBootVersion")
	testImplementation("org.springframework.boot:spring-boot-starter-test") {
		exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
	}

	//Springfox
	implementation("io.springfox:springfox-swagger2:$swaggerVersion")
	implementation("io.springfox:springfox-swagger-ui:$swaggerVersion")


	implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.9.+")

}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "14"
	}
}