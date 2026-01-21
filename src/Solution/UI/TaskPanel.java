package Solution.UI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import Solution.Logic.TaskManager;
import Solution.Logic.AuthManager;

/**
 * Task management panel with minimalist design:
 *   - High whitespace
 *   - Neutral palette (light grays, off-white)
 *   - Thin / no borders
 *   - Clean typography
 *   - Flat buttons
 * All logic (task addition, display, reports) remains unchanged.
 */
public class TaskPanel extends JPanel
{
    private final JTextArea taskDisplay;

    public TaskPanel(AuthFrame frame, TaskManager taskManager, AuthManager authManager)
    {
        setLayout(new BorderLayout(20, 20));
        setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        setBackground(new Color(250, 250, 250));  // very light neutral background

        // Welcome label – minimalist, no extra decoration
        String welcome = String.format("Hello %s %s",
                authManager.getStoredFirstName(),
                authManager.getStoredLastName());

        JLabel welcomeLabel = new JLabel(welcome, SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Helvetica", Font.PLAIN, 18));
        welcomeLabel.setForeground(new Color(33, 33, 33));
        welcomeLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 32, 0));
        add(welcomeLabel, BorderLayout.NORTH);

        // Task display – clean, minimal border, generous padding
        taskDisplay = new JTextArea();
        taskDisplay.setEditable(false);
        taskDisplay.setFont(new Font("Menlo", Font.PLAIN, 13));  // clean monospaced
        taskDisplay.setLineWrap(true);
        taskDisplay.setWrapStyleWord(true);
        taskDisplay.setBackground(Color.WHITE);
        taskDisplay.setForeground(new Color(40, 40, 40));
        taskDisplay.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220), 1));

        JScrollPane scroll = new JScrollPane(taskDisplay);
        scroll.setBorder(BorderFactory.createEmptyBorder());
        scroll.getVerticalScrollBar().setUnitIncrement(14);
        add(scroll, BorderLayout.CENTER);

        // Button bar – flat, minimal, centered
        JPanel buttonBar = new JPanel(new FlowLayout(FlowLayout.CENTER, 16, 16));
        buttonBar.setOpaque(false);

        String[] labels = {"Add Task", "View All", "Reports", "Logout"};

        for (String label : labels)
        {
            JButton btn = new JButton(label);
            styleMinimalButton(btn);

            if (label.equals("Add Task"))
                btn.addActionListener(e -> showAddTaskDialog(taskManager));
            else if (label.equals("View All"))
                btn.addActionListener(e -> displayTasks(taskManager));
            else if (label.equals("Reports"))
                btn.addActionListener(e -> showReportOptions(taskManager));
            else if (label.equals("Logout"))
                btn.addActionListener(e -> frame.showLoginPanel());

            buttonBar.add(btn);
        }

        add(buttonBar, BorderLayout.SOUTH);
    }

    /**
     * Applies flat/minimalist button style:
     *   - no heavy border
     *   - subtle hover effect possible via UIDefaults (not implemented here)
     *   - neutral background
     */
    private void styleMinimalButton(JButton btn)
    {
        btn.setFont(new Font("Helvetica", Font.PLAIN, 14));
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(210, 210, 210), 1),
                BorderFactory.createEmptyBorder(10, 24, 10, 24)
        ));
        btn.setBackground(new Color(245, 245, 245));
        btn.setOpaque(true);
        btn.setContentAreaFilled(true);
        btn.setBorderPainted(true);
    }

    // ──────────────────────────────────────────────
    // The following methods remain 100% unchanged:
    // ──────────────────────────────────────────────

    private void showAddTaskDialog(TaskManager taskManager)
    {
        String taskName = JOptionPane.showInputDialog(this, "Enter task name:");
        if (taskName == null || taskName.trim().isEmpty()) return;

        String description = JOptionPane.showInputDialog(this, "Enter task description (max 50 chars):");
        if (description == null || description.trim().isEmpty()) return;
        if (description.length() > 50)
        {
            JOptionPane.showMessageDialog(this, "Description must be 50 characters or less");
            return;
        }

        int numDevelopers = 0;
        while (numDevelopers < 1 || numDevelopers > 5)
        {
            String numStr = JOptionPane.showInputDialog(this, "Enter number of developers:");
            if (numStr == null) return;
            try
            {
                numDevelopers = Integer.parseInt(numStr);
                if (numDevelopers < 1 || numDevelopers > 5)
                {
                    JOptionPane.showMessageDialog(this, "Please enter between 1 and 5 developers");
                }
            }
            catch (NumberFormatException ex)
            {
                JOptionPane.showMessageDialog(this, "Please enter a valid number");
            }
        }

        List<String> developers = new ArrayList<>();
        for (int i = 0; i < numDevelopers; i++)
        {
            String devName = JOptionPane.showInputDialog(this, String.format("Enter developer #%d name:", i+1));
            if (devName == null || devName.trim().isEmpty()) return;
            developers.add(devName.trim());
        }

        float duration = 0;
        boolean validDuration = false;
        while (!validDuration)
        {
            String durationStr = JOptionPane.showInputDialog(this, "Enter task duration (hours, max 2 decimal places):");
            if (durationStr == null) return;
            try
            {
                duration = Float.parseFloat(durationStr);
                String[] parts = durationStr.split("\\.");
                if (parts.length > 1 && parts[1].length() > 2)
                {
                    throw new NumberFormatException();
                }
                validDuration = true;
            }
            catch (NumberFormatException ex)
            {
                JOptionPane.showMessageDialog(this, "Please enter a valid number with max 2 decimal places");
            }
        }

        String[] statusOptions = {"To Do", "Doing", "Done"};
        String status = (String) JOptionPane.showInputDialog(this,
                "Select task status:", "Status",
                JOptionPane.QUESTION_MESSAGE, null,
                statusOptions, statusOptions[0]);
        if (status == null) return;

        try
        {
            taskManager.addTask(taskName.trim(), description.trim(), developers, duration, status);
            JOptionPane.showMessageDialog(this, "Task added successfully!");
        }
        catch (IllegalArgumentException ex)
        {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void displayTasks(TaskManager taskManager)
    {
        try
        {
            taskDisplay.setText(taskManager.getAllTasks());
        }
        catch (Exception ex)
        {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showReportOptions(TaskManager taskManager)
    {
        String[] options = {"By Developer", "Longest Task", "Cancel"};
        int choice = JOptionPane.showOptionDialog(
                this, "Generate Report:", "Reports",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, options, options[0]);

        try
        {
            switch (choice)
            {
                case 0:
                    String dev = JOptionPane.showInputDialog("Developer name:");
                    if (dev != null)
                    {
                        taskDisplay.setText(taskManager.getTasksByDeveloper(dev.trim()));
                    }
                    break;
                case 1:
                    taskDisplay.setText(taskManager.getTaskWithLongestDuration());
                    break;
            }
        }
        catch (IllegalArgumentException | IllegalStateException ex)
        {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}