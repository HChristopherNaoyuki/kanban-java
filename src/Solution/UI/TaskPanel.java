package Solution.UI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import Solution.Logic.TaskManager;
import Solution.Logic.AuthManager;

/**
 * Classic 2000s style task management panel with welcome message
 */
public class TaskPanel extends JPanel
{
    private final JTextArea taskDisplay;

    /**
     * Constructs task panel with welcome message
     * @param frame
     * @param taskManager
     * @param authManager
     */
    public TaskPanel(AuthFrame frame, TaskManager taskManager, AuthManager authManager)
    {
        setLayout(new BorderLayout(5, 5));
        setBackground(SystemColor.control);

        // Welcome message
        String welcomeMsg = String.format("Hello %s %s. Welcome back.\n\n",
            authManager.getStoredFirstName(),
            authManager.getStoredLastName());
        
        JLabel welcomeLabel = new JLabel(welcomeMsg, SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
        add(welcomeLabel, BorderLayout.NORTH);

        // Task display
        taskDisplay = new JTextArea();
        taskDisplay.setEditable(false);
        taskDisplay.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(taskDisplay);
        add(scrollPane, BorderLayout.CENTER);

        // Buttons
        JPanel buttonPanel = new JPanel(new GridLayout(1, 4, 5, 5));
        
        JButton addButton = new JButton("Add");
        addButton.addActionListener(e -> showAddTaskDialog(taskManager));
        buttonPanel.add(addButton);

        JButton viewButton = new JButton("View");
        viewButton.addActionListener(e -> displayTasks(taskManager));
        buttonPanel.add(viewButton);

        JButton reportButton = new JButton("Report");
        reportButton.addActionListener(e -> showReportOptions(taskManager));
        buttonPanel.add(reportButton);

        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(e -> frame.showLoginPanel());
        buttonPanel.add(logoutButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    /**
     * Shows sequential dialogs for adding a task with multiple developers
     */
    private void showAddTaskDialog(TaskManager taskManager)
    {
        // Task Name
        String taskName = JOptionPane.showInputDialog(this, "Enter task name:");
        if (taskName == null || taskName.trim().isEmpty())
        {
            return;
        }

        // Description
        String description = JOptionPane.showInputDialog(this, "Enter task description (max 50 chars):");
        if (description == null || description.trim().isEmpty())
        {
            return;
        }
        if (description.length() > 50)
        {
            JOptionPane.showMessageDialog(this, "Description must be 50 characters or less");
            return;
        }

        // Number of Developers (1-5)
        int numDevelopers = 0;
        while (numDevelopers < 1 || numDevelopers > 5)
        {
            String numStr = JOptionPane.showInputDialog(this, "Enter number of developers:");
            if (numStr == null)
            {
                return;
            }
            
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

        // Collect Developer Names
        List<String> developers = new ArrayList<>();
        for (int i = 0; i < numDevelopers; i++)
        {
            String devName = JOptionPane.showInputDialog(this, String.format("Enter developer #%d name:", i+1));
            if (devName == null || devName.trim().isEmpty())
            {
                return;
            }
            developers.add(devName.trim());
        }

        // Duration (float with 2 decimal places)
        float duration = 0;
        boolean validDuration = false;
        while (!validDuration)
        {
            String durationStr = JOptionPane.showInputDialog(this, "Enter task duration (hours, max 2 decimal places):");
            if (durationStr == null)
            {
                return;
            }
            
            try
            {
                duration = Float.parseFloat(durationStr);
                if (String.valueOf(duration).split("\\.")[1].length() > 2)
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

        // Status
        String[] statusOptions = {"To Do", "Doing", "Done"};
        String status = (String)JOptionPane.showInputDialog(this, 
            "Select task status:", "Status", 
            JOptionPane.QUESTION_MESSAGE, null, 
            statusOptions, statusOptions[0]);
        if (status == null)
        {
            return;
        }

        // Add the task
        try
        {
            taskManager.addTask(taskName, description, developers, duration, status);
            JOptionPane.showMessageDialog(this, "Task added successfully!");
        }
        catch (HeadlessException | IllegalArgumentException ex)
        {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Displays all tasks
     */
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

    /**
     * Shows report generation options
     */
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
                case 0 ->
                {
                    String dev = JOptionPane.showInputDialog("Developer name:");
                    if (dev != null)
                    {
                        taskDisplay.setText(taskManager.getTasksByDeveloper(dev));
                    }
                }
                case 1 -> taskDisplay.setText(taskManager.getTaskWithLongestDuration());
            }
        }
        catch (HeadlessException | IllegalArgumentException | IllegalStateException ex)
        {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}