<!-- AGENTS.md: Guidance for AI coding agents working on this repository -->

# AGENTS — springboot-ci-demo

Purpose: provide concise, repository-specific knowledge that helps an automated coding agent become productive immediately.

- Repo type: Spring Boot (Maven) micro/app — tiny single-module web application.
- Primary language: Java 21 (see `pom.xml`).

Quick checklist for agents
- Ensure JDK 21 is available locally (project uses Java 21 and compiler --enable-preview). See `pom.xml` lines setting <java.version>21</java.version> and maven-compiler-plugin entries.
- Use the included Maven wrapper on Windows: `mvnw.cmd` (or `./mvnw` on UNIX). Example build: `mvnw.cmd clean package`.
- To run locally: `mvnw.cmd spring-boot:run -Dspring-boot.run.jvmArguments="--enable-preview"` or run the built jar: `java --enable-preview -jar target/Simple-0.0.1-SNAPSHOT.jar`.

Big-picture architecture and important files
- Single-module Spring Boot app with two Java classes under `src/main/java/com/example/Simple`:
  - `SimpleApplication.java` — Spring Boot bootstrap (@SpringBootApplication). Entry point for the app.
  - `DemoApplication.java` — annotated with `@RestController`; defines a single endpoint `GET /getData` which returns a plain String.
- Configuration: `src/main/resources/application.properties` contains only `spring.application.name=Simple`.
- Tests:
  - `SimpleApplicationTests.java` — basic `@SpringBootTest` context-load test.
  - `DemoApplicationTest.java` — unit-style test that instantiates `DemoApplication` and asserts the returned string. (Note: this file's formatting appears malformed; be cautious when editing.)

Conventions and repository-specific quirks (do not assume defaults)
- Package name uses an uppercase segment: `com.example.Simple`. This is unusual — be careful with case-sensitivity (Windows tolerates it; UNIX/macOS may not). Always use the exact directory and package casing when modifying files or creating new ones.
- Small codebase where controllers live directly in a top-level class named `DemoApplication` rather than a `controller` package — expect logic to be colocated and minimal.
- Maven compiler plugin sets `--enable-preview` in `pom.xml` (compilerArgs). Even if source doesn't use preview language features, runtime may require `--enable-preview` to execute compiled preview features. When running in the JVM add `--enable-preview` as shown above.

Developer workflows and commands
- Build (with tests):
  - Windows (wrapper): `mvnw.cmd clean package`
  - Unix: `./mvnw clean package`
- Run (development):
  - Spring Boot run with preview JVM arg: `mvnw.cmd spring-boot:run -Dspring-boot.run.jvmArguments="--enable-preview"`
  - Run jar: `java --enable-preview -jar target/Simple-0.0.1-SNAPSHOT.jar`
- Tests:
  - Run unit/integration tests: `mvnw.cmd test`.
  - If you need to skip tests during package: `mvnw.cmd -DskipTests package`.

Patterns to follow when editing or extending
- Keep package casing consistent: add new classes under `src/main/java/com/example/Simple` unless you intentionally reorganize packages and update imports.
- For new REST endpoints follow the pattern in `DemoApplication.java`: annotate with `@RestController` or `@RestController` + `@RequestMapping` and use Spring MVC method-level mappings like `@GetMapping("/path")`.
- Avoid introducing new external integrations without adding configuration to `application.properties` (this repo currently has none).

Potential pitfalls for automated edits
- Tests: `DemoApplicationTest.java` appears to have syntax/formatting issues; modifying it may be necessary but do so carefully and run `mvnw.cmd test` afterwards.
- Case sensitivity: renaming packages or moving files must preserve the uppercase `Simple` token; CI on non-Windows environments may fail if casing changes.
- JVM preview flag: builds may succeed but running code using preview features requires passing `--enable-preview` to the JVM.

Integration points & external dependencies
- Only external dependency is Spring Boot Web MVC (see `pom.xml`). No databases, message brokers, or external services are present.

Where to look first (recommended tour for an agent)
1. `pom.xml` — verify JDK and build plugins (Java 21 + --enable-preview).
2. `src/main/java/com/example/Simple/SimpleApplication.java` — application entry point.
3. `src/main/java/com/example/Simple/DemoApplication.java` — controller and the main HTTP surface (`/getData`).
4. `src/test/java/com/example/Simple/` — tests; fix or mirror patterns when adding tests.

If you change anything, run these commands to validate behavior:
```
mvnw.cmd -DskipTests=false test
mvnw.cmd clean package
java --enable-preview -jar target/Simple-0.0.1-SNAPSHOT.jar
curl http://localhost:8080/getData
```

Contact points in repo for common edits
- To add routes: edit `DemoApplication.java` or add a new `@RestController` in the same package.
- To change app name or add properties: edit `src/main/resources/application.properties`.

Notes
- There are no CI files or agent instruction files present — this AGENTS.md should be the canonical place for quick repo-specific instructions.
- Keep this file minimal and update it if you reorganize the project (especially package casing).

