package Solution.UI;

import Solution.Logic.AuthManager;
import Solution.Logic.TaskManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Main task management panel with minimalist styling.
 * Features:
 *  - Generous whitespace and padding
 *  - Very light neutral color palette
 *  - Flat buttons without heavy borders
 *  - Clean typography (system-preferred sans-serif)
 *  - Monospaced task output for readability
 *
 * All user interaction is handled through simple dialogs.
 * Business logic remains in TaskManager.
 */
public class TaskPanel extends JPanel
{
    private final AuthFrame     parentFrame;
    private final TaskManager   taskManager;
    private final AuthManager   authManager;
    private JTextArea     taskDisplayArea;

    /**
     * Creates the main task management panel.
     *
     * @param frame        reference to the main application frame (for navigation)
     * @param taskManager  business logic component for task operations
     * @param authManager  provides access to current user information
     */
    public TaskPanel(AuthFrame frame, TaskManager taskManager, AuthManager authManager)
    {
        this.parentFrame   = frame;
        this.taskManager   = taskManager;
        this.authManager   = authManager;

        setLayout(new BorderLayout(0, 44));
        setBorder(BorderFactory.createEmptyBorder(64, 64, 64, 64));
        setBackground(new Color(248, 248, 250));

        add(createWelcomeHeader(),   BorderLayout.NORTH);
        add(createTaskDisplayArea(), BorderLayout.CENTER);
        add(createActionButtonBar(), BorderLayout.SOUTH);
    }

    // -------------------------------------------------------------------------
    // Layout construction methods
    // -------------------------------------------------------------------------

    private JPanel createWelcomeHeader()
    {
        String fullName = authManager.getStoredFirstName() + " " +
                          authManager.getStoredLastName();

        JLabel welcomeLabel = new JLabel("Welcome, " + fullName, SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Helvetica Neue", Font.PLAIN, 20));
        if (welcomeLabel.getFont().getFamily().contains("Dialog"))
        {
            welcomeLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        }
        welcomeLabel.setForeground(new Color(33, 33, 38));
        welcomeLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 56, 0));

        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);
        header.add(welcomeLabel, BorderLayout.CENTER);

        return header;
    }

    private JScrollPane createTaskDisplayArea()
    {
        taskDisplayArea = new JTextArea();
        taskDisplayArea.setEditable(false);
        taskDisplayArea.setLineWrap(true);
        taskDisplayArea.setWrapStyleWord(true);
        taskDisplayArea.setFont(getMonospaceFont());
        taskDisplayArea.setBackground(Color.WHITE);
        taskDisplayArea.setForeground(new Color(40, 40, 45));
        taskDisplayArea.setBorder(BorderFactory.createMatteBorder(
                1, 1, 1, 1, new Color(228, 228, 235)));

        JScrollPane scrollPane = new JScrollPane(taskDisplayArea);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setBackground(Color.WHITE);

        return scrollPane;
    }

    private JPanel createActionButtonBar()
    {
        JPanel bar = new JPanel(new FlowLayout(FlowLayout.CENTER, 32, 0));
        bar.setOpaque(false);
        bar.setBorder(BorderFactory.createEmptyBorder(48, 0, 0, 0));

        String[] actions = {
                "Add New Task",
                "Show All Tasks",
                "View Reports",
                "Sign Out"
        };

        for (String label : actions)
        {
            JButton button = createFlatButton(label);
            bar.add(button);

            button.addActionListener(e -> {
                switch (label)
                {
                    case "Add New Task"    -> showAddTaskDialog();
                    case "Show All Tasks"  -> showAllTasks();
                    case "View Reports"    -> showReportSelectionDialog();
                    case "Sign Out"        -> parentFrame.showLoginPanel();
                }
            });
        }

        return bar;
    }

    private JButton createFlatButton(String text)
    {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
        if (btn.getFont().getFamily().contains("Dialog"))
        {
            btn.setFont(new Font("Arial", Font.PLAIN, 14));
        }
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(12, 32, 12, 32));
        btn.setBackground(new Color(242, 242, 247));
        btn.setForeground(new Color(35, 35, 40));
        btn.setOpaque(true);
        btn.setBorderPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        return btn;
    }

    private Font getMonospaceFont()
    {
        Font candidate = new Font("SF Mono", Font.PLAIN, 13);
        if (!candidate.getFamily().contains("SF Mono"))
        {
            candidate = new Font("Menlo", Font.PLAIN, 13);
        }
        if (candidate.getFamily().contains("Dialog"))
        {
            candidate = new Font("Consolas", Font.PLAIN, 13);
        }
        return candidate;
    }

    // -------------------------------------------------------------------------
    // User interaction / dialog logic
    // -------------------------------------------------------------------------

    private void showAddTaskDialog()
    {
        String taskName = JOptionPane.showInputDialog(
                this, "Task name:", "New Task", JOptionPane.PLAIN_MESSAGE);

        if (taskName == null || taskName.trim().isEmpty()) return;
        taskName = taskName.trim();

        String description = JOptionPane.showInputDialog(
                this, "Description (max 50 characters):", "New Task",
                JOptionPane.PLAIN_MESSAGE);

        if (description == null) return;
        description = description.trim();

        if (description.length() > 50)
        {
            JOptionPane.showMessageDialog(this,
                    "Description must not exceed 50 characters.",
                    "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int numDevs = askForNumberOfDevelopers();
        if (numDevs < 1) return;

        List<String> developers = collectDeveloperNames(numDevs);
        if (developers == null || developers.isEmpty()) return;

        float durationHours = askForTaskDuration();
        if (durationHours <= 0) return;

        String status = askForTaskStatus();
        if (status == null) return;

        try
        {
            taskManager.addTask(taskName, description, developers, durationHours, status);
            JOptionPane.showMessageDialog(this,
                    "Task created successfully.",
                    "Success", JOptionPane.INFORMATION_MESSAGE);
        }
        catch (IllegalArgumentException ex)
        {
            JOptionPane.showMessageDialog(this,
                    ex.getMessage(), "Cannot Create Task", JOptionPane.ERROR_MESSAGE);
        }
    }

    private int askForNumberOfDevelopers()
    {
        while (true)
        {
            String input = JOptionPane.showInputDialog(
                    this, "Number of developers (1–5):", "New Task",
                    JOptionPane.PLAIN_MESSAGE);

            if (input == null) return -1;

            try
            {
                int n = Integer.parseInt(input.trim());
                if (n >= 1 && n <= 5) return n;
                JOptionPane.showMessageDialog(this,
                        "Please enter a number between 1 and 5.",
                        "Invalid Input", JOptionPane.WARNING_MESSAGE);
            }
            catch (NumberFormatException ex)
            {
                JOptionPane.showMessageDialog(this,
                        "Please enter a valid number.",
                        "Invalid Input", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    private List<String> collectDeveloperNames(int count)
    {
        List<String> names = new ArrayList<>();

        for (int i = 1; i <= count; i++)
        {
            String name = JOptionPane.showInputDialog(
                    this, "Developer #" + i + " name:",
                    "New Task", JOptionPane.PLAIN_MESSAGE);

            if (name == null) return null;
            name = name.trim();
            if (name.isEmpty())
            {
                JOptionPane.showMessageDialog(this,
                        "Developer name cannot be empty.",
                        "Invalid Input", JOptionPane.WARNING_MESSAGE);
                return null;
            }
            names.add(name);
        }
        return names;
    }

    private float askForTaskDuration()
    {
        while (true)
        {
            String input = JOptionPane.showInputDialog(
                    this, "Duration in hours (e.g. 4.5):", "New Task",
                    JOptionPane.PLAIN_MESSAGE);

            if (input == null) return -1;

            try
            {
                float hours = Float.parseFloat(input.trim());
                if (hours > 0) return hours;

                JOptionPane.showMessageDialog(this,
                        "Duration must be greater than zero.",
                        "Invalid Duration", JOptionPane.WARNING_MESSAGE);
            }
            catch (NumberFormatException ex)
            {
                JOptionPane.showMessageDialog(this,
                        "Please enter a valid number (e.g. 3.75).",
                        "Invalid Format", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    private String askForTaskStatus()
    {
        String[] options = {"To Do", "Doing", "Done"};

        return (String) JOptionPane.showInputDialog(
                this,
                "Select task status:",
                "Task Status",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);
    }

    private void showAllTasks()
    {
        String content = taskManager.getAllTasks();
        taskDisplayArea.setText(content.isEmpty() ? "No tasks have been created yet." : content);
    }

    private void showReportSelectionDialog()
    {
        String[] choices = {
                "Tasks assigned to a developer",
                "Task with longest duration",
                "Cancel"
        };

        int option = JOptionPane.showOptionDialog(
                this,
                "Which report would you like to see?",
                "Reports",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                choices,
                choices[0]);

        if (option < 0 || option == 2) return;

        if (option == 0)
        {
            String developer = JOptionPane.showInputDialog(
                    this, "Enter developer name:", "Report Filter",
                    JOptionPane.PLAIN_MESSAGE);

            if (developer == null || developer.trim().isEmpty()) return;

            try
            {
                String report = taskManager.getTasksByDeveloper(developer.trim());
                taskDisplayArea.setText(report);
            }
            catch (IllegalArgumentException ex)
            {
                taskDisplayArea.setText("No tasks found for developer: " + developer.trim());
            }
        }
        else if (option == 1)
        {
            try
            {
                String report = taskManager.getTaskWithLongestDuration();
                taskDisplayArea.setText(report);
            }
            catch (IllegalStateException ex)
            {
                taskDisplayArea.setText("No tasks exist yet.");
            }
        }
    }
}