# Task Management System Documentation

## Table of Contents

1.  [Introduction](#introduction)
2.  [System Architecture](#system-architecture)
3.  [Getting Started](#getting-started)
    1.  [Prerequisites](#prerequisites)
    2.  [Installation](#installation)
4.  [User Guide](#user-guide)
    1.  [Authentication](#authentication)
        1.  [Registration](#registration)
        2.  [Login](#login)
    2.  [Task Management](#task-management)
        1.  [Adding a Task](#adding-a-task)
        2.  [Viewing Tasks](#viewing-tasks)
        3.  [Searching Tasks](#searching-tasks)
5.  [Technical Specifications](#technical-specifications)
    1.  [Data Validation Rules](#data-validation-rules)
    2.  [User Interface Design](#user-interface-design)
6.  [Code Structure](#code-structure)
    1.  [Solution Package](#solution-package)
    2.  [Logic Package](#logic-package)
    3.  [UI Package](#ui-package)
7.  [Development Notes](#development-notes)

## Introduction

The Task Management System is a Java Swing application designed for managing 
development tasks. 

It provides a clean, intuitive interface for user authentication, task creation, 
and team-based task management. The system enforces strict validation rules for 
data integrity and security, supporting multiple developers per task with 
comprehensive tracking capabilities.

This application implements a Kanban-style workflow with task status tracking 
(To Do, Doing, Done), making it suitable for small to medium-sized development 
teams seeking a lightweight, desktop-based task management solution.

## System Architecture

The system follows a Model-View-Controller (MVC) architectural pattern:

-   **Model**: `AuthManager`, `TaskManager`, and `Validator` classes in the `Solution.Logic` package handle business logic, data validation, and state management.
-   **View**: Swing-based UI components in the `Solution.UI` package provide the user interface.
-   **Controller**: The `AuthFrame` class acts as the main controller, coordinating between views and models.

The application uses a single-window approach with panel swapping for different 
views (Login, Registration, Task Management), ensuring a seamless user 
experience.

## Getting Started

### Prerequisites

-   Java Development Kit (JDK) 8 or higher
-   A Java-compatible IDE (IntelliJ IDEA, Eclipse, NetBeans) or command-line tools
-   Basic understanding of Java programming

### Installation

1.  Clone the repository:
    ```
    git clone https://github.com/HChristopherNaoyuki/kanban-java.git
    ```
2.  Navigate to the project directory:
    ```
    cd kanban-java
    ```
3.  Compile the source code:
    ```
    javac -d bin src/Solution/*.java src/Solution/UI/*.java src/Solution/Logic/*.java
    ```
4.  Run the application:
    ```
    java -cp bin Solution.Solution
    ```

Alternatively, import the project into your preferred Java IDE and run the 
`Solution.java` file as the main class.

## User Guide

### Authentication

#### Registration

1.  Launch the application to see the Login screen.
2.  Click "Create Account" to navigate to the Registration screen.
3.  Fill in all required fields:
    -   **First Name** and **Last Name**: Your real name (required)
    -   **Username**: Must contain an underscore (`_`) and be 5 characters or less
    -   **Password**: Must be at least 8 characters with:
        -   One uppercase letter (A-Z)
        -   One digit (0-9)
        -   One special character (!@#$%^&*(), etc.)
4.  Review the requirements panel for detailed guidelines.
5.  Click "Create Account" to register or "Cancel" to return to Login.

#### Login

1.  Enter your registered username and password.
2.  Click "Sign In" to authenticate.
3.  If credentials are invalid, an error message will appear.
4.  Successful login redirects to the main Task Management interface.

### Task Management

#### Adding a Task

1.  Click "Add Task" in the Task Management interface.
2.  Provide the following information:
    -   **Task Name**: Descriptive title for the task
    -   **Description**: Detailed explanation (50 characters maximum)
    -   **Number of Developers**: Between 1 and 5 team members
    -   **Developer Names**: Names of assigned developers
    -   **Duration**: Estimated hours to complete (e.g., 4.5)
    -   **Status**: Current progress (To Do, Doing, Done)
3.  Click OK to create the task. The system validates all inputs and displays confirmation.

#### Viewing Tasks

-   **View All**: Click "View All" to display all created tasks with complete details including task ID, description, assigned developers, duration, and status.
-   **Task Display**: Tasks appear in a formatted text area showing:
    -   Task name and description
    -   Comma-separated developer list
    -   Duration in hours (formatted to 2 decimal places)
    -   Current status
    -   Automatically generated task ID

#### Searching Tasks

-   **Search by Developer**: Click "Search by Developer" and enter a developer's name to filter tasks assigned to that specific team member.
-   **Longest Task**: Click "Longest Task" to identify the task with the highest duration estimate across all tasks.

## Technical Specifications

### Data Validation Rules

#### User Account Validation

-   **Username**:
    -   Must contain at least one underscore character (`_`)
    -   Maximum length: 5 characters
    -   Case-sensitive matching
-   **Password**:
    -   Minimum length: 8 characters
    -   Must contain at least one uppercase letter (A-Z)
    -   Must contain at least one digit (0-9)
    -   Must contain at least one special character (non-alphanumeric)
-   **Personal Information**:
    -   First and last name are required fields
    -   No specific format restrictions

#### Task Validation

-   **Description**: Maximum 50 characters
-   **Developers**: Minimum 1, maximum 5 developers per task
-   **Duration**: Must be a positive floating-point number
-   **Status**: Must be one of: "To Do", "Doing", "Done"

#### Task ID Generation

Task IDs follow a specific format: `[First 2 letters of task name]:[Last 3 letters of first developer's name]` (both converted to uppercase). For example, a task named "Database" assigned to "Christopher" generates ID: "DA:HER".

### User Interface Design

The application features a clean, professional interface with Apple-inspired design principles:

-   **Color Scheme**: Light gray backgrounds (#F8F8F8) with blue accent colors (#007AFF)
-   **Typography**: System fonts (Helvetica Neue preferred) with appropriate hierarchy
-   **Layout**: Responsive design with consistent spacing and alignment
-   **Components**: Rounded buttons with soft edges, clear visual hierarchy
-   **Window Management**:
    -   Login/Task panels: 900×600 windowed mode
    -   Registration panel: Full-screen mode for better form experience

## Code Structure

### Solution Package

**Main Class**: `Solution.java`
-   Entry point for the application
-   Initializes the Swing interface on the Event Dispatch Thread
-   Creates and displays the main `AuthFrame` window

### Logic Package

**AuthManager.java**
-   Handles user registration and authentication
-   Stores user credentials and personal information
-   Validates username and password against business rules
-   Provides access to stored user data

**TaskManager.java**
-   Manages task creation, storage, and retrieval
-   Supports operations: add task, view all tasks, search by developer, find longest task
-   Contains inner `Task` class representing individual task objects
-   Implements task ID generation algorithm

**Validator.java**
-   Provides reusable validation methods for various input types
-   Contains methods for username, password, and task description validation
-   Used by both authentication and task management components

### UI Package

**AuthFrame.java**
-   Main application window controller
-   Manages panel transitions between Login, Registration, and Task views
-   Handles window sizing and state management
-   Coordinates between UI panels and logic components

**LoginPanel.java**
-   Authentication interface for existing users
-   Features username/password input fields with validation
-   Includes "Create Account" link for new users
-   Optimized for 900×600 window dimensions

**RegistrationPanel.java**
-   Full-screen user registration interface
-   Divided into form section (left) and requirements guide (right)
-   Comprehensive input validation with real-time feedback
-   Clean, spacious layout for optimal user experience

**TaskPanel.java**
-   Primary task management interface
-   Displays welcome message with user's full name
-   Provides task creation, viewing, and search functionality
-   Features "Sign Out" button for returning to login screen

**RoundedButton.java**
-   Custom Swing component extending JButton
-   Implements rounded corners with 20-pixel radius
-   Supports hover effects and visual states
-   Maintains accessibility and standard button functionality

## Development Notes

-   **Code Style**: All code follows the Allman (BSD) style with opening braces on new lines
-   **Comments**: Comprehensive JavaDoc comments explain functionality, parameters, and return values
-   **Error Handling**: Robust exception handling with user-friendly error messages
-   **Thread Safety**: Swing components properly initialized on Event Dispatch Thread
-   **Memory Management**: Efficient data structures with appropriate scoping and cleanup
-   **Extensibility**: Modular design allows for easy addition of new features
-   **Maintainability**: Clear separation of concerns between UI, logic, and validation components

The system demonstrates professional Java Swing development practices with 
emphasis on usability, security, and maintainability. All validation rules are 
strictly enforced to ensure data integrity, while the intuitive interface 
provides a smooth user experience for task management workflows.

---