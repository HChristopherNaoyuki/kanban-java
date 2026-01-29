package Solution.UI;

import Solution.Logic.AuthManager;
import Solution.Logic.TaskManager;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Enhanced task management interface with improved visual layout
 * Provides clean task display and intuitive controls
 */
public class TaskPanel extends JPanel
{
    private final AuthFrame parentFrame;
    private final TaskManager taskManager;
    private final AuthManager authManager;
    private JTextArea taskDisplayArea;
    
    public TaskPanel(AuthFrame frame, TaskManager taskManager, AuthManager authManager)
    {
        this.parentFrame = frame;
        this.taskManager = taskManager;
        this.authManager = authManager;
        
        setLayout(new BorderLayout());
        setBackground(new Color(248, 248, 248));
        
        initializeComponents();
    }
    
    /**
     * Initializes all components
     */
    private void initializeComponents()
    {
        add(createHeaderPanel(), BorderLayout.NORTH);
        add(createContentPanel(), BorderLayout.CENTER);
        add(createFooterPanel(), BorderLayout.SOUTH);
    }
    
    /**
     * Creates the header panel with user info and navigation
     */
    private JPanel createHeaderPanel()
    {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(Color.WHITE);
        header.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(220, 220, 225)),
            BorderFactory.createEmptyBorder(15, 25, 15, 25)
        ));
        
        // Welcome message
        String firstName = authManager.getStoredFirstName();
        String lastName = authManager.getStoredLastName();
        String fullName = firstName + " " + lastName;
        
        JLabel welcomeLabel = new JLabel("Welcome, " + fullName);
        welcomeLabel.setFont(new Font("Helvetica Neue", Font.BOLD, 20));
        welcomeLabel.setForeground(new Color(28, 28, 30));
        
        // Logout button
        JButton logoutButton = createTextButton("Sign Out");
        logoutButton.addActionListener(e -> parentFrame.showLoginPanel());
        
        header.add(welcomeLabel, BorderLayout.WEST);
        header.add(logoutButton, BorderLayout.EAST);
        
        return header;
    }
    
    /**
     * Creates the main content panel with task display
     */
    private JPanel createContentPanel()
    {
        JPanel content = new JPanel(new BorderLayout());
        content.setBackground(new Color(248, 248, 248));
        content.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
        
        // Task display area
        taskDisplayArea = createTaskDisplayArea();
        JScrollPane scrollPane = new JScrollPane(taskDisplayArea);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 225), 1));
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setBackground(Color.WHITE);
        
        content.add(scrollPane, BorderLayout.CENTER);
        
        return content;
    }
    
    /**
     * Creates the task display text area with formatting
     */
    private JTextArea createTaskDisplayArea()
    {
        JTextArea area = new JTextArea();
        area.setEditable(false);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setFont(new Font("Menlo", Font.PLAIN, 13));
        area.setBackground(Color.WHITE);
        area.setForeground(new Color(50, 50, 55));
        area.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Set initial text with formatting
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
        footer.setBackground(new Color(248, 248, 248));
        footer.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(220, 220, 225)),
            BorderFactory.createEmptyBorder(20, 25, 20, 25)
        ));
        
        // Primary actions section
        JPanel primarySection = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        primarySection.setBackground(new Color(248, 248, 248));
        
        JButton addTaskButton = createPrimaryButton("Add New Task");
        addTaskButton.addActionListener(e -> showAddTaskDialog());
        
        JButton viewAllButton = createSecondaryButton("View All Tasks");
        viewAllButton.addActionListener(e -> showAllTasks());
        
        primarySection.add(addTaskButton);
        primarySection.add(viewAllButton);
        
        // Divider
        JSeparator divider = new JSeparator(JSeparator.HORIZONTAL);
        divider.setForeground(new Color(220, 220, 225));
        divider.setMaximumSize(new Dimension(400, 1));
        
        // Secondary actions section
        JPanel secondarySection = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        secondarySection.setBackground(new Color(248, 248, 248));
        secondarySection.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));
        
        JButton searchButton = createTertiaryButton("Search by Developer");
        searchButton.addActionListener(e -> showSearchDialog());
        
        JButton longestButton = createTertiaryButton("Longest Task");
        longestButton.addActionListener(e -> showLongestTask());
        
        secondarySection.add(searchButton);
        secondarySection.add(longestButton);
        
        footer.add(primarySection);
        footer.add(Box.createVerticalStrut(15));
        footer.add(divider);
        footer.add(Box.createVerticalStrut(15));
        footer.add(secondarySection);
        
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
        button.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        // Hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter()
        {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt)
            {
                button.setBackground(new Color(0, 110, 235));
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
        button.setForeground(new Color(100, 100, 105));
        button.setBackground(new Color(240, 240, 242));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(210, 210, 215), 1),
            BorderFactory.createEmptyBorder(9, 22, 9, 22)
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
        button.setBorder(BorderFactory.createEmptyBorder(6, 15, 6, 15));
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        return button;
    }
    
    /**
     * Creates a text-only button
     */
    private JButton createTextButton(String text)
    {
        JButton button = new JButton(text);
        button.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
        button.setForeground(new Color(0, 122, 255));
        button.setBorder(BorderFactory.createEmptyBorder(4, 10, 4, 10));
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
        // Task name
        String taskName = JOptionPane.showInputDialog(
            this,
            "Enter task name:",
            "New Task",
            JOptionPane.PLAIN_MESSAGE
        );
        
        if (taskName == null || taskName.trim().isEmpty())
        {
            return;
        }
        
        taskName = taskName.trim();
        
        // Description
        String description = JOptionPane.showInputDialog(
            this,
            "Enter description (max 50 characters):",
            "Task Description",
            JOptionPane.PLAIN_MESSAGE
        );
        
        if (description == null)
        {
            return;
        }
        
        description = description.trim();
        
        if (description.length() > 50)
        {
            showMessage("Description must be 50 characters or less.", 
                       "Invalid Description", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Number of developers
        int numDevs = askNumberOfDevelopers();
        if (numDevs < 1)
        {
            return;
        }
        
        // Developer names
        List<String> developers = askDeveloperNames(numDevs);
        if (developers == null)
        {
            return;
        }
        
        // Duration
        float duration = askDuration();
        if (duration <= 0)
        {
            return;
        }
        
        // Status
        String status = askStatus();
        if (status == null)
        {
            return;
        }
        
        try
        {
            taskManager.addTask(taskName, description, developers, duration, status);
            showMessage("Task added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            showAllTasks();
        }
        catch (IllegalArgumentException ex)
        {
            showMessage(ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Asks for number of developers
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
                return -1;
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
                return null;
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
     * Asks for task duration
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
                return -1;
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
                showMessage("Please enter a valid number.", 
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
     * Shows search dialog
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
     * Shows the longest task
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
     * Shows a message dialog
     */
    private void showMessage(String message, String title, int messageType)
    {
        JOptionPane.showMessageDialog(this, message, title, messageType);
    }
}