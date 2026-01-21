package Solution.UI;

import Solution.Logic.AuthManager;
import Solution.Logic.TaskManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Main working screen of the application
 * Contains:
 * • Welcome header
 * • Task display area
 * • Action buttons (Add, View All, Logout)
 */
public class TaskPanel extends JPanel
{
    private final AuthFrame   parentFrame;
    private final TaskManager taskManager;
    private final AuthManager authManager;
    private JTextArea   taskDisplayArea = null;

    public TaskPanel(AuthFrame frame, TaskManager taskManager, AuthManager authManager)
    {
        this.parentFrame  = frame;
        this.taskManager  = taskManager;
        this.authManager  = authManager;

        setLayout(new BorderLayout(0, 40));
        setBorder(BorderFactory.createEmptyBorder(80, 80, 80, 80));
        setBackground(new Color(250, 250, 252));

        add(createWelcomeHeader(),     BorderLayout.NORTH);
        add(createTaskDisplayScroll(), BorderLayout.CENTER);
        add(createActionButtonsBar(),  BorderLayout.SOUTH);
    }

    private JLabel createWelcomeHeader()
    {
        String fullName = authManager.getStoredFirstName() + " " +
                          authManager.getStoredLastName();

        JLabel label = new JLabel("Welcome, " + fullName, SwingConstants.CENTER);
        label.setFont(new Font("SF Pro Display", Font.PLAIN, 32));
        label.setForeground(new Color(28, 28, 30));
        label.setBorder(BorderFactory.createEmptyBorder(0, 0, 64, 0));
        return label;
    }

    private JScrollPane createTaskDisplayScroll()
    {
        taskDisplayArea = new JTextArea();
        taskDisplayArea.setEditable(false);
        taskDisplayArea.setLineWrap(true);
        taskDisplayArea.setWrapStyleWord(true);
        taskDisplayArea.setFont(new Font("SF Mono", Font.PLAIN, 13));
        taskDisplayArea.setBackground(Color.WHITE);
        taskDisplayArea.setForeground(new Color(40, 40, 45));
        taskDisplayArea.setBorder(BorderFactory.createMatteBorder(
                1, 1, 1, 1, new Color(225, 225, 230)));

        JScrollPane scroll = new JScrollPane(taskDisplayArea);
        scroll.setBorder(BorderFactory.createEmptyBorder());
        scroll.getVerticalScrollBar().setUnitIncrement(16);

        return scroll;
    }

    private JPanel createActionButtonsBar()
    {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 20));
        panel.setOpaque(false);

        String[] actions = {
                "Add New Task",
                "View All Tasks",
                "Logout"
        };

        for (String action : actions)
        {
            JButton button = createFlatButton(action);
            panel.add(button);

            switch (action)
            {
                case "Add New Task"   -> button.addActionListener(e -> showAddTaskDialog());
                case "View All Tasks" -> button.addActionListener(e -> showAllTasks());
                case "Logout"         -> button.addActionListener(e -> parentFrame.showLoginPanel());
            }
        }

        return panel;
    }

    private JButton createFlatButton(String text)
    {
        JButton btn = new JButton(text);
        btn.setFont(new Font("SF Pro Text", Font.PLAIN, 15));
        btn.setForeground(new Color(28, 28, 30));
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(14, 44, 14, 44));
        btn.setBackground(new Color(238, 238, 245));
        btn.setOpaque(true);
        btn.setBorderPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return btn;
    }

    // ─────────────────────────────────────────────────────────────
    //                  User interaction methods
    // ─────────────────────────────────────────────────────────────

    private void showAddTaskDialog()
    {
        String taskName = JOptionPane.showInputDialog(this,
                "Enter task name:", "New Task", JOptionPane.PLAIN_MESSAGE);

        if (taskName == null || taskName.trim().isEmpty()) return;
        taskName = taskName.trim();

        String description = JOptionPane.showInputDialog(this,
                "Enter description (max 50 chars):", "New Task",
                JOptionPane.PLAIN_MESSAGE);

        if (description == null) return;
        description = description.trim();

        if (description.length() > 50)
        {
            showError("Description must be 50 characters or less.");
            return;
        }

        int numDevs = askNumberOfDevelopers();
        if (numDevs < 1) return;

        List<String> developers = askDeveloperNames(numDevs);
        if (developers == null) return;

        float duration = askDuration();
        if (duration <= 0) return;

        String status = askStatus();
        if (status == null) return;

        try
        {
            taskManager.addTask(taskName, description, developers, duration, status);
            JOptionPane.showMessageDialog(this,
                    "Task added successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        catch (IllegalArgumentException ex)
        {
            showError(ex.getMessage());
        }
    }

    private int askNumberOfDevelopers()
    {
        while (true)
        {
            String input = JOptionPane.showInputDialog(this,
                    "Number of developers (1-5):", "New Task",
                    JOptionPane.PLAIN_MESSAGE);

            if (input == null) return -1;

            try
            {
                int n = Integer.parseInt(input.trim());
                if (n >= 1 && n <= 5) return n;
                showWarning("Please enter number between 1 and 5.");
            }
            catch (NumberFormatException e)
            {
                showWarning("Please enter a valid number.");
            }
        }
    }

    private List<String> askDeveloperNames(int count)
    {
        List<String> names = new ArrayList<>();

        for (int i = 1; i <= count; i++)
        {
            String name = JOptionPane.showInputDialog(this,
                    "Developer #" + i + " name:", "New Task",
                    JOptionPane.PLAIN_MESSAGE);

            if (name == null) return null;
            name = name.trim();
            if (name.isEmpty())
            {
                showWarning("Developer name cannot be empty.");
                return null;
            }
            names.add(name);
        }
        return names;
    }

    private float askDuration()
    {
        while (true)
        {
            String input = JOptionPane.showInputDialog(this,
                    "Duration in hours (e.g. 4.5):", "New Task",
                    JOptionPane.PLAIN_MESSAGE);

            if (input == null) return -1;

            try
            {
                float value = Float.parseFloat(input.trim());
                if (value > 0) return value;
                showWarning("Duration must be greater than zero.");
            }
            catch (NumberFormatException e)
            {
                showWarning("Please enter a valid number (e.g. 3.75)");
            }
        }
    }

    private String askStatus()
    {
        String[] options = {"To Do", "Doing", "Done"};

        return (String) JOptionPane.showInputDialog(
                this,
                "Select task status:",
                "Task Status",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );
    }

    private void showAllTasks()
    {
        String content = taskManager.getAllTasks();
        taskDisplayArea.setText(
                content.trim().isEmpty() ?
                        "No tasks have been added yet." :
                        content
        );
    }

    private void showError(String message)
    {
        JOptionPane.showMessageDialog(this,
                message,
                "Error",
                JOptionPane.ERROR_MESSAGE);
    }

    private void showWarning(String message)
    {
        JOptionPane.showMessageDialog(this,
                message,
                "Warning",
                JOptionPane.WARNING_MESSAGE);
    }
}