package Solution.UI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import Solution.Logic.TaskManager;
import Solution.Logic.AuthManager;

/**
 * Main task management panel with macOS Aqua (early 2000s) inspired appearance.
 * Displays welcome message, task list view, and control buttons.
 * All task creation, viewing, and reporting logic remains unchanged from original.
 */
public class TaskPanel extends JPanel
{
    private final JTextArea taskDisplay;

    /**
     * Creates the task management panel with welcome message and controls.
     *
     * @param frame        Reference to the main AuthFrame for navigation
     * @param taskManager  Handles all task-related operations
     * @param authManager  Provides access to current user's name
     */
    public TaskPanel(AuthFrame frame, TaskManager taskManager, AuthManager authManager)
    {
        setLayout(new BorderLayout(14, 14));
        setBorder(BorderFactory.createEmptyBorder(20, 24, 20, 24));

        // Classic macOS window background color (very light gray / "Aluminum" feel)
        setBackground(new Color(237, 237, 237));

        // --------------------------------------------------------------------
        // Welcome header
        // --------------------------------------------------------------------
        String welcomeText = String.format(
            "Hello %s %s",
            authManager.getStoredFirstName(),
            authManager.getStoredLastName()
        );

        JLabel welcomeLabel = new JLabel(welcomeText, SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Lucida Grande", Font.BOLD, 17));
        welcomeLabel.setForeground(new Color(40, 40, 40));
        welcomeLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 18, 0));

        add(welcomeLabel, BorderLayout.NORTH);

        // --------------------------------------------------------------------
        // Main task display area (monospaced font like classic Terminal/Monaco)
        // --------------------------------------------------------------------
        taskDisplay = new JTextArea();
        taskDisplay.setEditable(false);
        taskDisplay.setFont(new Font("Monaco", Font.PLAIN, 12));
        taskDisplay.setLineWrap(true);
        taskDisplay.setWrapStyleWord(true);
        taskDisplay.setBackground(new Color(255, 255, 255));
        taskDisplay.setForeground(new Color(30, 30, 30));
        taskDisplay.setBorder(
            BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(140, 140, 140)),
                BorderFactory.createEmptyBorder(8, 10, 8, 10)
            )
        );

        JScrollPane scrollPane = new JScrollPane(taskDisplay);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        add(scrollPane, BorderLayout.CENTER);

        // --------------------------------------------------------------------
        // Bottom toolbar-style button row
        // --------------------------------------------------------------------
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 12, 12));
        buttonPanel.setOpaque(false);

        String[] buttonLabels = {
            "Add Task…",
            "View All Tasks",
            "Reports…",
            "Log Out"
        };

        for (String label : buttonLabels)
        {
            JButton button = new JButton(label);
            styleAquaButton(button);

            if (label.contains("Add Task"))
            {
                button.addActionListener(e -> showAddTaskDialog(taskManager));
            }
            else if (label.contains("View All"))
            {
                button.addActionListener(e -> displayTasks(taskManager));
            }
            else if (label.contains("Reports"))
            {
                button.addActionListener(e -> showReportOptions(taskManager));
            }
            else if (label.contains("Log Out"))
            {
                button.addActionListener(e -> frame.showLoginPanel());
            }

            buttonPanel.add(button);
        }

        add(buttonPanel, BorderLayout.SOUTH);
    }

    /**
     * Applies classic macOS Aqua-style appearance to buttons
     * (light gray background, subtle border, no focus ring by default)
     *
     * @param button The JButton to style
     */
    private void styleAquaButton(JButton button)
    {
        button.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
        button.setFocusPainted(false);
        button.setBorder(
            BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(128, 128, 128, 180)),
                BorderFactory.createEmptyBorder(8, 18, 8, 18)
            )
        );
        button.setBackground(new Color(245, 245, 245));
        button.setOpaque(true);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    /**
     * Sequential dialog flow for adding a new task (original behavior preserved).
     * Collects: name, description, number of developers, developer names,
     * duration (float), and status.
     *
     * @param taskManager The TaskManager instance to add the task to
     */
    private void showAddTaskDialog(TaskManager taskManager)
    {
        // Task Name
        String taskName = JOptionPane.showInputDialog(
            this,
            "Enter task name:",
            "New Task",
            JOptionPane.QUESTION_MESSAGE
        );
        if (taskName == null || taskName.trim().isEmpty())
        {
            return;
        }

        // Description (≤ 50 characters)
        String description = JOptionPane.showInputDialog(
            this,
            "Enter task description (max 50 characters):",
            "New Task",
            JOptionPane.QUESTION_MESSAGE
        );
        if (description == null || description.trim().isEmpty())
        {
            return;
        }
        if (description.length() > 50)
        {
            JOptionPane.showMessageDialog(
                this,
                "Description must be 50 characters or less.",
                "Input Error",
                JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        // Number of developers (1–5)
        int numDevelopers = 0;
        while (numDevelopers < 1 || numDevelopers > 5)
        {
            String input = JOptionPane.showInputDialog(
                this,
                "Number of developers (1–5):",
                "New Task",
                JOptionPane.QUESTION_MESSAGE
            );
            if (input == null)
            {
                return;
            }

            try
            {
                numDevelopers = Integer.parseInt(input.trim());
                if (numDevelopers < 1 || numDevelopers > 5)
                {
                    JOptionPane.showMessageDialog(
                        this,
                        "Please enter a number between 1 and 5.",
                        "Input Error",
                        JOptionPane.ERROR_MESSAGE
                    );
                }
            }
            catch (NumberFormatException ex)
            {
                JOptionPane.showMessageDialog(
                    this,
                    "Please enter a valid number.",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE
                );
            }
        }

        // Developer names
        List<String> developers = new ArrayList<>();
        for (int i = 0; i < numDevelopers; i++)
        {
            String devName = JOptionPane.showInputDialog(
                this,
                String.format("Developer #%d name:", i + 1),
                "New Task",
                JOptionPane.QUESTION_MESSAGE
            );
            if (devName == null || devName.trim().isEmpty())
            {
                return;
            }
            developers.add(devName.trim());
        }

        // Duration in hours (float, up to 2 decimal places)
        float duration = 0f;
        boolean valid = false;
        while (!valid)
        {
            String input = JOptionPane.showInputDialog(
                this,
                "Task duration (hours, e.g. 12.5):",
                "New Task",
                JOptionPane.QUESTION_MESSAGE
            );
            if (input == null)
            {
                return;
            }

            try
            {
                duration = Float.parseFloat(input.trim());
                // Basic check for reasonable decimal places
                String[] parts = input.trim().split("\\.");
                if (parts.length > 1 && parts[1].length() > 2)
                {
                    throw new NumberFormatException("Too many decimal places");
                }
                valid = true;
            }
            catch (NumberFormatException ex)
            {
                JOptionPane.showMessageDialog(
                    this,
                    "Please enter a valid number (max 2 decimal places).",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE
                );
            }
        }

        // Status selection
        String[] statusOptions = {"To Do", "Doing", "Done"};
        String status = (String) JOptionPane.showInputDialog(
            this,
            "Select task status:",
            "New Task – Status",
            JOptionPane.QUESTION_MESSAGE,
            null,
            statusOptions,
            statusOptions[0]
        );
        if (status == null)
        {
            return;
        }

        // Final attempt to add task
        try
        {
            taskManager.addTask(taskName.trim(), description.trim(), developers, duration, status);
            JOptionPane.showMessageDialog(
                this,
                "Task added successfully.",
                "Success",
                JOptionPane.INFORMATION_MESSAGE
            );
        }
        catch (IllegalArgumentException ex)
        {
            JOptionPane.showMessageDialog(
                this,
                ex.getMessage(),
                "Error Adding Task",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    /**
     * Displays all tasks in the text area using TaskManager's formatted output.
     *
     * @param taskManager The TaskManager containing all tasks
     */
    private void displayTasks(TaskManager taskManager)
    {
        try
        {
            taskDisplay.setText(taskManager.getAllTasks());
        }
        catch (Exception ex)
        {
            JOptionPane.showMessageDialog(
                this,
                ex.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    /**
     * Shows report selection dialog and displays chosen report in text area.
     *
     * @param taskManager The TaskManager used to generate reports
     */
    private void showReportOptions(TaskManager taskManager)
    {
        String[] options = {"Tasks by Developer", "Longest Duration Task", "Cancel"};

        int choice = JOptionPane.showOptionDialog(
            this,
            "Select report to generate:",
            "Reports",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            options,
            options[0]
        );

        if (choice < 0 || choice == 2) // Cancel or close
        {
            return;
        }

        try
        {
            if (choice == 0)
            {
                String developer = JOptionPane.showInputDialog(
                    this,
                    "Enter developer name:",
                    "Tasks by Developer",
                    JOptionPane.QUESTION_MESSAGE
                );
                if (developer != null && !developer.trim().isEmpty())
                {
                    taskDisplay.setText(taskManager.getTasksByDeveloper(developer.trim()));
                }
            }
            else if (choice == 1)
            {
                taskDisplay.setText(taskManager.getTaskWithLongestDuration());
            }
        }
        catch (IllegalArgumentException | IllegalStateException ex)
        {
            JOptionPane.showMessageDialog(
                this,
                ex.getMessage(),
                "Report Error",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }
}