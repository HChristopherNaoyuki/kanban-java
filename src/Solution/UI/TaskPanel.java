package Solution.UI;

import Solution.Logic.AuthManager;
import Solution.Logic.TaskManager;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Main task management interface with clean, organized layout
 * Provides task creation, viewing, and management features
 */
public class TaskPanel extends JPanel
{
    private final AuthFrame parentFrame;
    private final TaskManager taskManager;
    private final AuthManager authManager;
    private JTextArea taskDisplayArea;
    
    /**
     * Constructs the task management panel
     */
    public TaskPanel(AuthFrame frame, TaskManager taskManager, AuthManager authManager)
    {
        this.parentFrame = frame;
        this.taskManager = taskManager;
        this.authManager = authManager;
        
        setLayout(new BorderLayout());
        setBackground(UIManager.getColor("Panel.background"));
        
        initializeComponents();
    }
    
    /**
     * Initializes all panel components
     */
    private void initializeComponents()
    {
        add(createHeaderPanel(), BorderLayout.NORTH);
        add(createContentPanel(), BorderLayout.CENTER);
        add(createFooterPanel(), BorderLayout.SOUTH);
    }
    
    /**
     * Creates the header panel with user information
     */
    private JPanel createHeaderPanel()
    {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(UIManager.getColor("Panel.background"));
        header.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(220, 220, 220)),
            BorderFactory.createEmptyBorder(15, 20, 15, 20)
        ));
        
        // Welcome message
        String firstName = authManager.getStoredFirstName();
        String lastName = authManager.getStoredLastName();
        String fullName = firstName + " " + lastName;
        
        JLabel welcomeLabel = new JLabel("Welcome, " + fullName);
        welcomeLabel.setFont(new Font("Helvetica Neue", Font.BOLD, 18));
        welcomeLabel.setForeground(new Color(28, 28, 28));
        
        // Logout button
        JButton logoutButton = createTextButton("Sign Out");
        logoutButton.addActionListener(e -> parentFrame.showLoginPanel());
        
        header.add(welcomeLabel, BorderLayout.WEST);
        header.add(logoutButton, BorderLayout.EAST);
        
        return header;
    }
    
    /**
     * Creates the main content panel
     */
    private JPanel createContentPanel()
    {
        JPanel content = new JPanel(new BorderLayout());
        content.setBackground(UIManager.getColor("Panel.background"));
        content.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Task display area
        taskDisplayArea = createTaskDisplayArea();
        JScrollPane scrollPane = new JScrollPane(taskDisplayArea);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220), 1));
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        
        content.add(scrollPane, BorderLayout.CENTER);
        
        return content;
    }
    
    /**
     * Creates the task display text area
     */
    private JTextArea createTaskDisplayArea()
    {
        JTextArea area = new JTextArea();
        area.setEditable(false);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setFont(new Font("Monaco", Font.PLAIN, 13));
        area.setBackground(Color.WHITE);
        area.setForeground(new Color(50, 50, 50));
        area.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        // Set initial message
        area.setText("No tasks available. Add your first task to get started.");
        
        return area;
    }
    
    /**
     * Creates the footer panel with action buttons
     */
    private JPanel createFooterPanel()
    {
        JPanel footer = new JPanel();
        footer.setLayout(new BoxLayout(footer, BoxLayout.Y_AXIS));
        footer.setBackground(UIManager.getColor("Panel.background"));
        footer.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(220, 220, 220)),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        
        // Primary actions
        JPanel primaryActions = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        primaryActions.setBackground(UIManager.getColor("Panel.background"));
        
        JButton addTaskButton = createPrimaryButton("Add Task");
        addTaskButton.addActionListener(e -> showAddTaskDialog());
        
        JButton viewAllButton = createSecondaryButton("View All");
        viewAllButton.addActionListener(e -> showAllTasks());
        
        primaryActions.add(addTaskButton);
        primaryActions.add(viewAllButton);
        
        // Secondary actions
        JPanel secondaryActions = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        secondaryActions.setBackground(UIManager.getColor("Panel.background"));
        secondaryActions.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));
        
        JButton searchButton = createTertiaryButton("Search by Developer");
        searchButton.addActionListener(e -> showSearchDialog());
        
        JButton longestButton = createTertiaryButton("Longest Task");
        longestButton.addActionListener(e -> showLongestTask());
        
        secondaryActions.add(searchButton);
        secondaryActions.add(longestButton);
        
        footer.add(primaryActions);
        footer.add(secondaryActions);
        
        return footer;
    }
    
    /**
     * Creates a primary action button
     */
    private JButton createPrimaryButton(String text)
    {
        JButton button = new JButton(text);
        button.setFont(new Font("Helvetica Neue", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(0, 122, 255));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(12, 30, 12, 30));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        // Hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter()
        {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt)
            {
                button.setBackground(new Color(0, 100, 230));
            }
            
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt)
            {
                button.setBackground(new Color(0, 122, 255));
            }
        });
        
        return button;
    }
    
    /**
     * Creates a secondary action button
     */
    private JButton createSecondaryButton(String text)
    {
        JButton button = new JButton(text);
        button.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
        button.setForeground(new Color(80, 80, 80));
        button.setBackground(new Color(240, 240, 240));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
            BorderFactory.createEmptyBorder(10, 25, 10, 25)
        ));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        return button;
    }
    
    /**
     * Creates a tertiary action button
     */
    private JButton createTertiaryButton(String text)
    {
        JButton button = new JButton(text);
        button.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
        button.setForeground(new Color(0, 122, 255));
        button.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        return button;
    }
    
    /**
     * Creates a text button for navigation
     */
    private JButton createTextButton(String text)
    {
        JButton button = new JButton(text);
        button.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
        button.setForeground(new Color(0, 122, 255));
        button.setBorder(BorderFactory.createEmptyBorder(6, 12, 6, 12));
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        return button;
    }
    
    /**
     * Shows dialog for adding a new task
     */
    private void showAddTaskDialog()
    {
        // Create input dialog
        String taskName = JOptionPane.showInputDialog(
            this,
            "Enter task name:",
            "New Task",
            JOptionPane.PLAIN_MESSAGE
        );
        
        if (taskName == null || taskName.trim().isEmpty())
        {
            return; // User cancelled or entered empty
        }
        
        taskName = taskName.trim();
        
        // Get description
        String description = JOptionPane.showInputDialog(
            this,
            "Enter description (max 50 characters):",
            "Task Description",
            JOptionPane.PLAIN_MESSAGE
        );
        
        if (description == null)
        {
            return; // User cancelled
        }
        
        description = description.trim();
        
        // Validate description length
        if (description.length() > 50)
        {
            showMessage("Description must be 50 characters or less.", 
                       "Invalid Description", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Get number of developers
        int numDevs = askNumberOfDevelopers();
        if (numDevs < 1)
        {
            return; // User cancelled
        }
        
        // Get developer names
        List<String> developers = askDeveloperNames(numDevs);
        if (developers == null)
        {
            return; // User cancelled
        }
        
        // Get duration
        float duration = askDuration();
        if (duration <= 0)
        {
            return; // User cancelled or invalid
        }
        
        // Get status
        String status = askStatus();
        if (status == null)
        {
            return; // User cancelled
        }
        
        try
        {
            // Add the task
            taskManager.addTask(taskName, description, developers, duration, status);
            showMessage("Task added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            showAllTasks(); // Refresh task display
        }
        catch (IllegalArgumentException ex)
        {
            showMessage(ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Asks for number of developers with validation
     */
    private int askNumberOfDevelopers()
    {
        while (true)
        {
            String input = JOptionPane.showInputDialog(
                this,
                "Number of developers (1-5):",
                "Team Size",
                JOptionPane.PLAIN_MESSAGE
            );
            
            if (input == null)
            {
                return -1; // User cancelled
            }
            
            try
            {
                int count = Integer.parseInt(input.trim());
                if (count >= 1 && count <= 5)
                {
                    return count;
                }
                showMessage("Please enter a number between 1 and 5.", 
                           "Invalid Input", JOptionPane.WARNING_MESSAGE);
            }
            catch (NumberFormatException e)
            {
                showMessage("Please enter a valid number.", 
                           "Invalid Input", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
    
    /**
     * Collects developer names
     */
    private List<String> askDeveloperNames(int count)
    {
        List<String> developers = new ArrayList<>();
        
        for (int i = 1; i <= count; i++)
        {
            String name = JOptionPane.showInputDialog(
                this,
                "Developer #" + i + " name:",
                "Team Member",
                JOptionPane.PLAIN_MESSAGE
            );
            
            if (name == null)
            {
                return null; // User cancelled
            }
            
            name = name.trim();
            if (name.isEmpty())
            {
                showMessage("Developer name cannot be empty.", 
                           "Invalid Input", JOptionPane.WARNING_MESSAGE);
                return null;
            }
            
            developers.add(name);
        }
        
        return developers;
    }
    
    /**
     * Asks for task duration with validation
     */
    private float askDuration()
    {
        while (true)
        {
            String input = JOptionPane.showInputDialog(
                this,
                "Duration in hours (e.g., 4.5):",
                "Task Duration",
                JOptionPane.PLAIN_MESSAGE
            );
            
            if (input == null)
            {
                return -1; // User cancelled
            }
            
            try
            {
                float duration = Float.parseFloat(input.trim());
                if (duration > 0)
                {
                    return duration;
                }
                showMessage("Duration must be greater than 0.", 
                           "Invalid Input", JOptionPane.WARNING_MESSAGE);
            }
            catch (NumberFormatException e)
            {
                showMessage("Please enter a valid number (e.g., 3.75).", 
                           "Invalid Input", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
    
    /**
     * Asks for task status
     */
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
    
    /**
     * Displays all tasks
     */
    private void showAllTasks()
    {
        String content = taskManager.getAllTasks();
        if (content.trim().isEmpty() || content.equals("No tasks available"))
        {
            taskDisplayArea.setText("No tasks available. Add your first task to get started.");
        }
        else
        {
            taskDisplayArea.setText(content);
        }
    }
    
    /**
     * Shows dialog to search tasks by developer
     */
    private void showSearchDialog()
    {
        String developer = JOptionPane.showInputDialog(
            this,
            "Enter developer name:",
            "Search Tasks",
            JOptionPane.QUESTION_MESSAGE
        );
        
        if (developer != null && !developer.trim().isEmpty())
        {
            try
            {
                String content = taskManager.getTasksByDeveloper(developer.trim());
                taskDisplayArea.setText(content);
            }
            catch (IllegalArgumentException e)
            {
                showMessage("No tasks found for developer: " + developer, 
                           "Search Results", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
    
    /**
     * Shows the task with longest duration
     */
    private void showLongestTask()
    {
        try
        {
            String content = taskManager.getTaskWithLongestDuration();
            taskDisplayArea.setText(content);
        }
        catch (IllegalStateException e)
        {
            showMessage("No tasks available.", 
                       "Cannot Determine", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    /**
     * Displays a message dialog
     */
    private void showMessage(String message, String title, int messageType)
    {
        JOptionPane.showMessageDialog(this, message, title, messageType);
    }
}