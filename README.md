# Task Management System

A professional Java Swing application for managing development tasks with team collaboration features. 
This system provides secure user authentication and comprehensive task management capabilities.

## Table of Contents

1.  [Features](#features)
2.  [System Requirements](#system-requirements)
3.  [Installation](#installation)
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

-   **Secure Authentication**: Username and password validation with security requirements
-   **Team Task Management**: Support for multiple developers per task (1-5 team members)
-   **Task Status Tracking**: Kanban-style workflow with To Do, Doing, Done statuses
-   **Professional UI**: Clean, Apple-inspired interface with responsive design
-   **Comprehensive Search**: Filter tasks by developer or find the longest task
-   **Data Validation**: Strict input validation for data integrity
-   **Automatic Task ID Generation**: Intelligent ID creation based on task details

## System Requirements

-   **Java Development Kit (JDK)**: Version 8 or higher
-   **Operating System**: Windows, macOS, or Linux
-   **Memory**: Minimum 512 MB RAM
-   **Storage**: Minimum 50 MB free disk space

## Installation

### Method 1: Using Git Clone

1.  Clone the repository:
    ```bash
    git clone https://github.com/HChristopherNaoyuki/kanban-java.git
    ```
2.  Navigate to the project directory:
    ```bash
    cd kanban-java
    ```
3.  Compile the application:
    ```bash
    javac -d bin src/Solution/*.java src/Solution/UI/*.java src/Solution/Logic/*.java
    ```

### Method 2: Using an IDE

1.  Open your preferred Java IDE (IntelliJ IDEA, Eclipse, or NetBeans)
2.  Import the project from the cloned repository
3.  The IDE should automatically detect the project structure
4.  Build the project using the IDE's build tools

## Quick Start

1.  After compilation, run the application:
    ```bash
    java -cp bin Solution.Solution
    ```
2.  Create a new account following the registration guidelines
3.  Log in with your credentials
4.  Start adding and managing tasks immediately

## User Guide

### Account Registration

1.  Launch the application and click "Create Account"
2.  Fill in all required personal information:
    -   First and Last Name (required for profile)
    -   Username (must contain `_` and be ≤5 characters)
    -   Password (8+ characters with uppercase, number, special character)
3.  Review the detailed requirements panel for guidance
4.  Click "Create Account" to complete registration

### User Authentication

1.  Enter your registered username and password
2.  Click "Sign In" to access the task management system
3.  Use "Sign Out" to securely exit your session
4.  Return to login at any time to switch accounts

### Task Management

The task management interface provides:

-   **Welcome Display**: Shows your registered name
-   **Task List Area**: Displays all tasks in readable format
-   **Action Buttons**: Primary controls for task operations
-   **Search Functions**: Quick access to filtering tools

### Task Operations

#### Adding a New Task

1.  Click "Add Task" in the main interface
2.  Provide task details in the dialog sequence:
    -   Task Name: Descriptive title for the task
    -   Description: Detailed explanation (50 characters maximum)
    -   Number of Developers: Select 1-5 team members
    -   Developer Names: Enter each developer's name
    -   Duration: Estimated hours (positive decimal number)
    -   Status: Select To Do, Doing, or Done
3.  The system validates all inputs and creates the task

#### Viewing Tasks

-   **View All**: Click "View All" to display every task in the system
-   **Task Details**: Each task shows name, description, developers, duration, status, and ID
-   **Format**: Tasks display in a clean, formatted text area for easy reading

#### Searching Tasks

-   **By Developer**: Click "Search by Developer" and enter a name to see assigned tasks
-   **Longest Task**: Click "Longest Task" to identify the most time-consuming task
-   **Results**: Search results appear in the main display area

#### Task ID Generation

The system automatically generates task IDs using this format:

```
[First 2 letters of task name]:[Last 3 letters of first developer's name]
```

Example: Task "Database" with developer "Christopher" generates "DA:HER"

## Technical Architecture

The application follows Model-View-Controller (MVC) architecture:

-   **Model**: `AuthManager`, `TaskManager`, `Validator` classes handle business logic
-   **View**: Swing-based UI components provide the user interface
-   **Controller**: `AuthFrame` coordinates between views and models

### Key Design Principles

-   **Separation of Concerns**: Clear division between UI, logic, and validation
-   **Error Handling**: Comprehensive exception handling with user-friendly messages
-   **Thread Safety**: Proper Swing thread management for responsive UI
-   **Memory Efficiency**: Optimized data structures and resource management

## Project Structure

```
kanban-java/
├── src/
│   └── Solution/
│       ├── Solution.java             # Main application entry point
│       ├── Logic/
│       │   ├── AuthManager.java      # User authentication and registration
│       │   ├── TaskManager.java      # Task creation and management
│       │   └── Validator.java        # Input validation utilities
│       └── UI/
│           ├── AuthFrame.java        # Main window controller
│           ├── RoundedButton.java    # Custom rounded button component
│           ├── LoginPanel.java       # User authentication interface
│           ├── RegistrationPanel.java # User registration interface
│           └── TaskPanel.java        # Task management interface
└── README.md                         # This documentation file
```

## Validation Rules

### Username Requirements

-   Must contain at least one underscore character (`_`)
-   Maximum length: 5 characters
-   No spaces allowed
-   Case-sensitive matching
-   Cannot be changed after account creation

### Password Security

-   Minimum length: 8 characters
-   At least one uppercase letter (A-Z)
-   At least one digit (0-9)
-   At least one special character (!@#$%^&*()_+-=[]{}|;:,.<>?)
-   Avoid common passwords and personal information

### Task Validation

-   **Description**: Maximum 50 characters
-   **Developers**: 1-5 developers per task
-   **Duration**: Positive decimal number
-   **Status**: Must be "To Do", "Doing", or "Done"

## Development Guide

### Code Style

-   **Brace Style**: Allman (BSD) style with opening braces on new lines
-   **Naming Conventions**: camelCase for variables, PascalCase for classes
-   **Comments**: Comprehensive JavaDoc comments for all public methods
-   **Indentation**: 4 spaces (no tabs)

### Building from Source

1.  Ensure JDK 8+ is installed and configured
2.  Clone the repository
3.  Compile all source files:
    ```bash
    javac -d bin src/Solution/*.java src/Solution/UI/*.java src/Solution/Logic/*.java
    ```

### Running Tests

The application includes comprehensive validation testing through user interface interactions. Test scenarios include:

-   User registration with valid and invalid credentials
-   Task creation with boundary cases
-   Search functionality with various inputs
-   Error handling for invalid operations

### Extending the Application

To add new features:

1.  Follow existing patterns in the codebase
2.  Maintain separation between UI, logic, and validation
3.  Add appropriate validation for new inputs
4.  Update documentation to reflect changes
5.  Test thoroughly before deployment

## Contributing

Contributions to improve the Task Management System are welcome. Please follow these guidelines:

1.  Fork the repository
2.  Create a feature branch for your changes
3.  Follow the existing code style and conventions
4.  Add appropriate documentation for new features
5.  Test your changes thoroughly
6.  Submit a pull request with a clear description

## License

This project is available for educational and demonstration purposes. Please contact the repository owner for licensing information specific to your use case.

---
