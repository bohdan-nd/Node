import com.google.protobuf.gradle.*
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

repositories {
    mavenCentral()
}

plugins {
    id("org.springframework.boot") version "2.4.0"
    id("io.spring.dependency-management") version "1.0.10.RELEASE"
    id("com.google.protobuf") version "0.8.14"
    kotlin("jvm") version "1.4.10"
    kotlin("plugin.spring") version "1.4.10"
    kotlin("plugin.jpa") version "1.4.10"
}

group = "com.trspo"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_14

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.grpc:grpc-kotlin-stub:1.0.0")
    implementation("net.devh:grpc-server-spring-boot-starter:2.5.1.RELEASE")
    implementation("com.google.protobuf:protobuf-java:3.6.1")
    implementation("io.grpc:grpc-protobuf:1.15.1")
    implementation("io.grpc:grpc-stub:1.15.1")
//    implementation("io.grpc:grpc-netty-shaded:1.15.1")
    implementation("com.google.guava:guava:30.0-jre")
    api("com.google.guava:guava:30.0-jre")
//    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.amqp:spring-rabbit")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    compileOnly("org.projectlombok:lombok")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

//kotlin{
//    sourceSets["main"].apply{
//        kotlin.srcDir("./build/generated/source/proto/main/grpckt")
//    }
//}

sourceSets {
    main {
        java.srcDirs("src/main/kotlin","./build/generated/source/proto/main/java",
                "./build/generated/source/proto/main/grpc", "./build/generated/source/proto/main/grpckt")
        resources.srcDir("src/main/resources")
        proto.srcDir("src/proto")
    }
}

protobuf {
//    generatedFilesBaseDir = "$projectDir/src/main/kotlin"
    protoc {
        // The artifact spec for the Protobuf Compiler
        artifact = "com.google.protobuf:protoc:3.6.1"
    }
    plugins {
        id("grpc") {
            artifact = "io.grpc:protoc-gen-grpc-java:1.15.1"
        }
        id("grpckt") {
            artifact = "io.grpc:protoc-gen-grpc-kotlin:0.2.0:jdk7@jar"
        }
    }
    generateProtoTasks {
        ofSourceSet("main").forEach {
            it.plugins {
                id("grpc")
                id("grpckt")
            }

        }
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "14"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
