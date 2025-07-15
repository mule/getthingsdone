
# Project: Personal Task Manager (Android Client)

## Overview
This repository contains the native Android client for a personal task manager. It is built with Kotlin and Jetpack Compose and relies on Firebase for authentication and realtime data. The project structure comes from the cortinico/kotlin-android-template and uses Gradle Kotlin DSL.

## Directory Layout
- `app/` – Main Android application sources.
- `library-android/` – Shared Android components.
- `library-compose/` – Compose UI library.
- `library-kotlin/` – Non-UI Kotlin code.
- `buildSrc/` – Shared Gradle build logic.

## Tooling
- **Build System:** Gradle with Kotlin DSL and a version catalog.
- **Code Style:** ktlint formatting and Detekt static analysis.
- **CI:** GitHub Actions.
- **Testing:** JUnit and MockK.

## Instructions for Agents
- Follow the MVVM and Repository patterns when adding new features.
- Place new ViewModel tests under the appropriate `*test` source sets.
- Prefer libraries from Jetpack and other well maintained open-source projects.
- Keep Jetifier disabled and respect the existing gradle configuration.
- Compose UI should adhere to Material Design 3 guidelines.


## Best Practices
- Write concise commit messages in the imperative mood (e.g., "Add feature" not "Added" or "Adding").
- Run `./gradlew check` before submitting changes to ensure tests and static analysis pass.
- Use `./gradlew ktlintFormat` to automatically format Kotlin sources.
- Avoid leaving `TODO` or `STOPSHIP` comments; Detekt forbids them.
- Keep pull requests focused on a single topic.
