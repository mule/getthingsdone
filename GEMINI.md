Of course. Here is the raw Markdown text directly in the chat for you to copy.

# Project: Personal Task Manager (Android Client)

## Project Overview

This project is the native Android client for a personal task manager application. Inspired by productivity methodologies like **Getting Things Done (GTD)**, **Building a Second Brain**, and the **Zettelkasten** system, this app aims to be more than a simple to-do list. It is designed as an integrated system for capturing, organizing, and acting on tasks, ideas, and knowledge.

The core interface is a **Kanban board** with "swim lanes" for high-level categories and columns for task status. The long-term vision includes an **intelligent assistant** to help prioritize and manage workflows.

The application will utilize **Firebase Realtime Database** for data persistence and **Firebase Authentication** for user management. This repository focuses exclusively on the native **Kotlin** Android client.

## Key Features

### Core Task & Board Management
* **Kanban Board View:** Drag-and-drop tasks between customizable status columns (e.g., "To Do", "In Progress", "Done").
* 
* **Swim Lanes:** Group tasks into customizable high-level categories (e.g., "Work", "Personal", "Study").
* **Task Management:** Create, edit, and delete tasks with titles, detailed descriptions, due dates, and priority levels.
* **Sub-tasks:** Add checklists or sub-tasks within a parent task for granular tracking.
* **User Authentication:** Secure sign-up, log-in, and log-out using Firebase Authentication.
* **Real-time Sync:** All changes are instantly synchronized across devices via Firebase Realtime Database.

### Productivity & Knowledge System Features
* **Inbox (GTD):** A dedicated area for quickly capturing tasks and ideas before they are processed and organized.
* **Contexts & Tagging (GTD):** Assign contexts (e.g., `@computer`, `@home`) and flexible tags to tasks to filter for what can be done now.
* **Inter-Task Linking (Zettelkasten):** Create bi-directional links between tasks to build a network of related ideas and projects.
* **Notes & Resources (Second Brain):** Attach rich notes, documents, images, or web links directly to tasks, turning them into comprehensive knowledge hubs.
* **Search & Filtering:** Powerful search to find tasks by title, description, or tag. Advanced filtering by priority, due date, context, or swim lane.

### Task Lifecycle & Analytics
* **Task Event History:** An immutable log for each task that automatically records events like status changes, priority updates, and comments with timestamps.
* **Time Tracking:** A simple start/stop timer on each task to log the time spent, with a view of total time per task or project.
* **Notifications:** Local push notifications to remind users of upcoming or overdue task deadlines.

### UI/UX
* A clean, intuitive user interface built with Material Design 3.
* Support for both light and dark themes.

### Future Aspirations & Roadmap
* **Intelligent Assistant:** An AI-powered assistant to:
  * Suggest "Next Actions" based on context, priority, and energy level.
  * Identify patterns in your workflow.
  * Help with weekly reviews and planning.
* **Review & Reflection (GTD):** Dedicated features to facilitate weekly and monthly reviews of progress and open loops.

## Project Foundation and Tooling

This project is bootstrapped using the [**cortinico/kotlin-android-template**](https://github.com/cortinico/kotlin-android-template). This template provides a modern, robust foundation for native Android development in Kotlin and comes pre-configured with the following tools and conventions:

* **Continuous Integration**: Automated workflows are managed via **GitHub Actions**.
* **Code Quality**: Enforced via **ktlint** (formatting) and **Detekt** (static analysis).
* **Build System**: **Gradle Kotlin DSL** with centralized build logic in `buildSrc`.

## Tech Stack

* **Platform:** Android (Native)
* **Language:** Kotlin
* **Database:** Firebase Realtime Database
* **Authentication:** Firebase Authentication
* **Architecture:** MVVM (Model-View-ViewModel), Repository Pattern
* **Build System:** Gradle with Kotlin DSL
* **CI/CD:** GitHub Actions
* **Code Quality & Formatting:** Detekt, ktlint

## Specific Instructions for Gemini

* **Architecture**: When generating new features, adhere to the MVVM and Repository patterns. Create ViewModels to hold UI state and a Repository to manage data operations with Firebase.
* **Data Models**: When creating data models for tasks, ensure they can support a list of `TaskEvent` objects for the history log and a list of links/references to other tasks.
* **UI**: Follow Material Design 3 guidelines for all new UI components.
* **Testing**: For any new ViewModel, please generate corresponding unit tests using JUnit and Mockito/MockK.
* **Dependencies**: When suggesting new libraries, prefer those from the Android Jetpack suite or other widely-adopted, well-maintained open-source projects.
