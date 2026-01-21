# Task Manager: Kanban

A lightweight desktop application for task management with user authentication and basic Kanban-style tracking.

## Disclaimer

**DISCLAIMER**  
UNDER NO CIRCUMSTANCES SHOULD IMAGES OR EMOJIS BE INCLUDED DIRECTLY IN THE README FILE. 
ALL VISUAL MEDIA, INCLUDING SCREENSHOTS AND IMAGES OF THE APPLICATION, MUST BE STORED IN 
A DEDICATED FOLDER WITHIN THE PROJECT DIRECTORY. THIS FOLDER SHOULD BE CLEARLY STRUCTURED 
AND NAMED ACCORDINGLY TO INDICATE THAT IT CONTAINS ALL VISUAL CONTENT RELATED TO THE 
APPLICATION (FOR EXAMPLE, A FOLDER NAMED `images`, `screenshots`, or `media`).

I AM NOT LIABLE OR RESPONSIBLE FOR ANY MALFUNCTIONS, DEFECTS, OR ISSUES THAT MAY OCCUR AS A 
RESULT OF COPYING, MODIFYING, OR USING THIS SOFTWARE. IF YOU ENCOUNTER ANY PROBLEMS OR ERRORS, 
PLEASE DO NOT ATTEMPT TO FIX THEM SILENTLY OR OUTSIDE THE PROJECT. INSTEAD, KINDLY SUBMIT A 
PULL REQUEST OR OPEN AN ISSUE ON THE CORRESPONDING GITHUB REPOSITORY, SO THAT IT CAN BE ADDRESSED 
APPROPRIATELY BY THE MAINTAINERS OR CONTRIBUTORS.

## Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Technology Stack](#technology-stack)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Usage](#usage)
- [Project Structure](#project-structure)
- [Important Notes](#important-notes)
- [Contributing](#contributing)
- [License](#license)

## Overview

This application is a simple task management system built with Java Swing.  
It allows users to register, log in, create tasks, assign them to developers, 
track status, and generate basic reports — all while maintaining a clean, minimalist interface.

Repository:  
https://github.com/HChristopherNaoyuki/kanban-java.git

## Features

- User registration with input validation (username format, password complexity)
- Secure login
- Task creation including:
  - Task name
  - Description (limited to 50 characters)
  - Assignment to 1–5 developers
  - Estimated/completed duration in hours
  - Status selection (To Do, Doing, Done)
- List all tasks
- Filter tasks by developer
- Show task with longest duration
- Logout functionality
- Consistent minimalist user interface

## Technology Stack

| Layer             | Technology              | Version / Note                     |
|-------------------|-------------------------|------------------------------------|
| Language          | Java                    | 8+ (Java 8 compatible)             |
| GUI Framework     | Swing / AWT             | Part of Java SE                    |
| Build system      | None (manual) / Maven recommended | —                                  |
| Data storage      | In-memory               | No persistence (current version)   |

## Prerequisites

- Java Development Kit (JDK) 8 or higher
- Git (recommended for cloning the repository)
- Command line or Java IDE (IntelliJ IDEA, Eclipse, NetBeans, VS Code + Java extensions)

## Installation

1. Clone the repository

   ```bash
   git clone https://github.com/HChristopherNaoyuki/kanban-java.git
   cd kanban-java
   ```

2. Compile the source code

   ```bash
   # Option A – manual compilation
   mkdir -p bin
   javac -d bin src/Solution/**/*.java
   ```

   or use your IDE to build the project.

3. Run the application

   ```bash
   java -cp bin Solution.UI.AuthFrame
   ```

## Usage

1. Start the application
2. Register a new account or sign in
3. After login:
   - Click **Add New Task** to create tasks
   - Click **View All Tasks** to see the full list
   - Click **Logout** to return to the login screen

All critical inputs are validated. Appropriate error messages are shown when rules are violated.

## Project Structure

```
kanban-java/
├── src/
│   └── Solution/
│       ├── Logic/
│       │   ├── AuthManager.java
│       │   ├── TaskManager.java
│       │   └── Validator.java      (optional helper class)
│       └── UI/
│           ├── AuthFrame.java
│           ├── LoginPanel.java
│           ├── RegistrationPanel.java
│           └── TaskPanel.java
└── README.md
```

**Important:** No images should appear directly in this README file. All screenshots and visual assets must be stored in the `images/` folder (or equivalent).

## Important Notes

- The current version stores all data in memory → data is lost when the application closes.
- Passwords are stored in plain text (for demonstration purposes only — not suitable for production).
- No encryption, no database, no file persistence — intended as an educational / academic project.
- Interface styling aims for a clean, modern desktop look (macOS-inspired minimalism).

## Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/add-persistence`)
3. Commit your changes (`git commit -m "Add file-based task persistence"`)
4. Push to your fork (`git push origin feature/add-persistence`)
5. Open a Pull Request against the main repository

Bug reports, feature suggestions, documentation improvements, and code enhancements are welcome via Issues or Pull Requests.

## License

This project is provided as-is for educational and demonstration purposes.  
No warranty is provided. Use at your own risk.

---
