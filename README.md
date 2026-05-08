# Task Management System

A professional Java Swing application for managing development tasks with team
collaboration features. This system provides secure user authentication and
comprehensive task management capabilities.

## Table of Contents

1.  [Features](#features)
2.  [System Requirements](#system-requirements)
3.  [Installation and Clone](#installation-and-clone)
4.  [Quick Start](#quick-start)
5.  [User Guide](#user-guide)
    1.  [Account Registration](#account-registration)
    2.  [User Authentication](#user-authentication)
    3.  [Task Management](#task-management)
    4.  [Task Operations](#task-operations)
6.  [Technical Architecture](#technical-architecture)
7.  [Project Structure](#project-structure)
8.  [Validation Rules](#validation-rules)
9.  [Development Guide](#development-guide)
10. [Contributing](#contributing)
11. [License](#license)

## Features

-   **Secure Authentication**: Username and password validation with security
    requirements.
-   **Team Task Management**: Support for multiple developers per task (1-5
    team members).
-   **Task Status Tracking**: Kanban-style workflow with To Do, Doing, and
    Done statuses.
-   **Professional UI**: Clean, Apple-inspired interface with responsive
    design.
-   **Comprehensive Search**: Filter tasks by developer or find the longest
    task.
-   **Data Validation**: Strict input validation for data integrity.
-   **Automatic Task ID Generation**: Intelligent ID creation based on task
    details.

## System Requirements

-   **Java Development Kit (JDK)**: Version 8 or higher.
-   **Operating System**: Windows, macOS, or Linux.
-   **Memory**: Minimum 512 MB RAM.
-   **Storage**: Minimum 50 MB free disk space.

## Installation and Clone

To clone the repository and install the application, follow these steps.

1.  Clone the repository using the command below:
    ```bash
    git clone https://github.com/HChristopherNaoyuki/kanban-java.git
    ```
2.  Navigate into the project directory:
    ```bash
    cd kanban-java
    ```
3.  Compile the application source code:
    ```bash
    javac -d bin src/Solution/*.java src/Solution/UI/*.java src/Solution/Logic/*.java
    ```
4.  Alternatively, you can import the project directly into any standard Java
    IDE such as IntelliJ IDEA, Eclipse, or NetBeans. The IDE will typically
    handle the build process automatically.

## Quick Start

After successful compilation, you can launch the application immediately.

1.  Run the application using the Java command:
    ```bash
    java -cp bin Solution.Solution
    ```
2.  Create a new user account by following the on-screen registration
    guidelines.
3.  Log in with your newly created credentials.
4.  Begin adding and managing your development tasks without delay.

## User Guide

### Account Registration

1.  Launch the application and click the "Create Account" button.
2.  Complete all required fields in the registration form:
    -   First and Last Name for your profile.
    -   Username (must contain an underscore `_` and be 5 characters or less).
    -   Password (8+ characters, with an uppercase letter, a number, and a
        special character).
3.  Review the detailed requirements panel for guidance on valid inputs.
4.  Click "Create Account" to finalize the registration process.

### User Authentication

1.  On the main login screen, enter your registered username and password.
2.  Click the "Sign In" button to access the task management dashboard.
3.  Use the "Sign Out" button from within the dashboard to securely end your
    session.
4.  You may return to the login screen at any time to switch user accounts.

### Task Management

The main task management interface provides a complete workspace with these
areas:

-   **Welcome Display**: Shows your registered full name for personalization.
-   **Task List Area**: A formatted text area that displays all tasks in a
    readable layout.
-   **Action Buttons**: Primary controls for adding, viewing, and managing
    tasks.
-   **Search Functions**: Quick access to filtering and search tools.

### Task Operations

#### Adding a New Task

1.  Click the "Add Task" button in the main interface.
2.  Provide the required details in the sequence of dialog boxes:
    -   Task Name: A descriptive title for the work item.
    -   Description: A detailed explanation, limited to 50 characters.
    -   Number of Developers: Select between 1 and 5 team members.
    -   Developer Names: Enter the full name for each selected developer.
    -   Duration: The estimated hours, entered as a positive decimal number.
    -   Status: Choose from "To Do", "Doing", or "Done".
3.  The system will validate all inputs before creating the new task.

#### Viewing Tasks

-   **View All**: Click the "View All" button to display every task currently
    stored in the system.
-   **Task Details**: Each task entry shows its name, description, assigned
    developers, duration, status, and unique ID.
-   **Format**: All tasks are presented in a clean, well-formatted text area
    for easy reading.

#### Searching Tasks

-   **By Developer**: Click "Search by Developer", enter a name, and the
    system will show all tasks assigned to that person.
-   **Longest Task**: Click the "Longest Task" button to identify the single
    most time-consuming task in the system.
-   **Results Display**: All search results appear directly in the main task
    display area.

#### Task ID Generation

The system automatically creates a unique task ID using this format:

```
[First 2 letters of task name]:[Last 3 letters of first developer's name]
```

For example, a task named "Database" with a developer named "Christopher"
would generate the ID "DA:HER".

## Technical Architecture

The application follows the Model-View-Controller (MVC) architectural pattern.

-   **Model**: The `AuthManager`, `TaskManager`, and `Validator` classes
    contain all business logic and data management rules.
-   **View**: Swing-based UI components provide the graphical user interface.
-   **Controller**: The `AuthFrame` class coordinates the interactions between
    the views and the models.

### Key Design Principles

-   **Separation of Concerns**: A clear division between UI presentation,
    application logic, and validation rules.
-   **Error Handling**: Comprehensive exception handling with user-friendly
    error messages.
-   **Thread Safety**: Proper Swing thread management ensures a responsive
    user interface.
-   **Memory Efficiency**: Optimized data structures and careful resource
    management.

## Project Structure

```
kanban-java/
├── src/
│   └── Solution/
│       ├── Solution.java
│       ├── Documentation/
│       │   ├── Documentation.md
│       │   └── Disclaimer.md
│       ├── Logic/
│       │   ├── AuthManager.java
│       │   ├── TaskManager.java
│       │   └── Validator.java
│       └── UI/
│           ├── AuthFrame.java
│           ├── RoundedButton.java
│           ├── LoginPanel.java
│           ├── RegistrationPanel.java
│           └── TaskPanel.java
├── test/
│   └── Solution/
│       ├── Logic/
│       │   ├── AuthManagerTest.java
│       │   ├── TaskManagerTest.java
│       │   └── ValidatorTest.java
│       └── UI/
│           └── RoundedButtonTest.java
├── lib/
│   ├── junit-4.13.2.jar
│   └── hamcrest-core-1.3.jar
├── build.xml
└── README.md
```

## Validation Rules

### Username Requirements

-   Must contain at least one underscore character (`_`).
-   Maximum total length is 5 characters.
-   Spaces are not permitted.
-   Validation is case-sensitive.
-   The username cannot be changed after account creation.

### Password Security

-   Minimum length is 8 characters.
-   Must include at least one uppercase letter (A-Z).
-   Must include at least one digit (0-9).
-   Must include at least one special character (e.g., !@#$%^&*).
-   Avoid using common passwords or personal information for security.

### Task Validation

-   **Description**: Maximum length of 50 characters.
-   **Developers**: Each task must have between 1 and 5 developers.
-   **Duration**: Must be a positive decimal number.
-   **Status**: Must be exactly "To Do", "Doing", or "Done".

## Development Guide

### Code Style

-   **Brace Style**: Allman (BSD) style, with opening braces placed on new
    lines.
-   **Naming Conventions**: Use camelCase for variables and methods, and
    PascalCase for class names.
-   **Comments**: Provide comprehensive JavaDoc comments for all public
    methods.
-   **Indentation**: Use 4 spaces per indentation level. Do not use tabs.

### Building from Source

1.  Ensure that JDK 8 or a newer version is installed and configured on your
    system.
2.  Clone the repository as shown in the installation section.
3.  Compile all source files using the following command:
    ```bash
    javac -d bin src/Solution/*.java src/Solution/UI/*.java src/Solution/Logic/*.java
    ```

### Running Tests

The application includes comprehensive testing through its user interface.
Key test scenarios include:

-   User registration with both valid and invalid credentials.
-   Task creation using boundary value cases.
-   Search functionality verified with various inputs.
-   Error handling for invalid operations.

### Extending the Application

To add new features to the system, please follow these guidelines:

1.  Follow the existing coding patterns and architectural decisions.
2.  Maintain a strict separation between UI, logic, and validation layers.
3.  Add appropriate validation rules for any new input fields.
4.  Update the project documentation to reflect your changes.
5.  Test your additions thoroughly before deployment.

## Contributing

Contributions to improve the Task Management System are welcome. Please follow
this process:

1.  Fork the repository on GitHub.
2.  Create a new feature branch for your intended changes.
3.  Follow the existing code style and naming conventions strictly.
4.  Add or update documentation for any new features you introduce.
5.  Test your changes thoroughly using the defined scenarios.
6.  Submit a pull request with a clear description of your work.

## License

This project is provided for educational and demonstration purposes only.
Please contact the repository owner for specific licensing information
regarding your intended use case.

---

End of Document

---
