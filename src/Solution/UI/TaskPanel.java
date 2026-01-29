package Solution.UI;

import Solution.Logic.AuthManager;
import Solution.Logic.TaskManager;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Main task management workspace with structured navigation
 * Implements content grouping and progressive disclosure
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
        setBackground(UIManager.getColor("Panel.background"));
        
        initializeInterface();
    }
    
    /**
     * Initializes main interface components
     */
    private void initializeInterface()
    {
        add(createHeaderPanel(), BorderLayout.NORTH);
        add(createContentPanel(), BorderLayout.CENTER);
        add(createFooterPanel(), BorderLayout.SOUTH);
    }
    
    /**
     * Creates header panel with user context and navigation
     */
    private JPanel createHeaderPanel()
    {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(UIManager.getColor("Panel.background"));
        header.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 1, 0, 
                UIManager.getColor("Separator.foreground")),
            BorderFactory.createEmptyBorder(16, 24, 16, 24)
        ));
        
        // User greeting
        String fullName = authManager.getStoredFirstName() + " " + 
                         authManager.getStoredLastName();
        JLabel welcomeLabel = new JLabel("Welcome, " + fullName);
        welcomeLabel.setFont(new Font("SF Pro Display", Font.BOLD, 18));
        welcomeLabel.setForeground(UIManager.getColor("Label.foreground"));
        
        // Action buttons
        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 12, 0));
        actionPanel.setBackground(UIManager.getColor("Panel.background"));
        
        JButton logoutButton = createTextButton("Sign Out");
        logoutButton.addActionListener(e -> parentFrame.showLoginPanel());
        
        actionPanel.add(logoutButton);
        
        header.add(welcomeLabel, BorderLayout.WEST);
        header.add(actionPanel, BorderLayout.EAST);
        
        return header;
    }
    
    /**
     * Creates main content area with task display
     */
    private JPanel createContentPanel()
    {
        JPanel content = new JPanel(new BorderLayout());
        content.setBackground(UIManager.getColor("Panel.background"));
        content.setBorder(BorderFactory.createEmptyBorder(24, 24, 24, 24));
        
        // Task display area
        taskDisplayArea = createTaskDisplayArea();
        JScrollPane scrollPane = new JScrollPane(taskDisplayArea);
        scrollPane.setBorder(BorderFactory.createLineBorder(
            UIManager.getColor("Separator.foreground"), 1));
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        
        // Status panel
        JPanel statusPanel = createStatusPanel();
        
        content.add(statusPanel, BorderLayout.NORTH);
        content.add(scrollPane, BorderLayout.CENTER);
        
        return content;
    }
    
    /**
     * Creates task display text area with appropriate styling
     */
    private JTextArea createTaskDisplayArea()
    {
        JTextArea area = new JTextArea();
        area.setEditable(false);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setFont(new Font("SF Mono", Font.PLAIN, 12));
        area.setBackground(Color.WHITE);
        area.setForeground(UIManager.getColor("Label.foreground"));
        area.setBorder(new EmptyBorder(16, 16, 16, 16));
        
        // Set initial content
        area.setText("No tasks yet. Add your first task to get started.");
        
        return area;
    }
    
    /**
     * Creates status information panel
     */
    private JPanel createStatusPanel()
    {
        JPanel statusPanel = new JPanel(new BorderLayout());
        statusPanel.setBackground(UIManager.getColor("Panel.background"));
        statusPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 16, 0));
        
        JLabel title = new JLabel("Task Overview");
        title.setFont(new Font("SF Pro Display", Font.BOLD, 16));
        title.setForeground(UIManager.getColor("Label.foreground"));
        
        statusPanel.add(title, BorderLayout.WEST);
        
        return statusPanel;
    }
    
    /**
     * Creates footer panel with action controls
     */
    private JPanel createFooterPanel()
    {
        JPanel footer = new JPanel();
        footer.setLayout(new BoxLayout(footer, BoxLayout.Y_AXIS));
        footer.setBackground(UIManager.getColor("Panel.background"));
        footer.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(1, 0, 0, 0, 
                UIManager.getColor("Separator.foreground")),
            BorderFactory.createEmptyBorder(24, 24, 24, 24)
        ));
        
        // Primary actions
        JPanel primaryActions = new JPanel(new FlowLayout(FlowLayout.CENTER, 16, 0));
        primaryActions.setBackground(UIManager.getColor("Panel.background"));
        
        JButton addTaskButton = createPrimaryButton("Add New Task");
        addTaskButton.addActionListener(e -> showAddTaskDialog());
        
        JButton viewAllButton = createSecondaryButton("View All Tasks");
        viewAllButton.addActionListener(e -> showAllTasks());
        
        primaryActions.add(addTaskButton);
        primaryActions.add(viewAllButton);
        
        // Secondary actions
        JPanel secondaryActions = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 0));
        secondaryActions.setBackground(UIManager.getColor("Panel.background"));
        secondaryActions.setBorder(BorderFactory.createEmptyBorder(16, 0, 0, 0));
        
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
     * Creates primary action button with visual emphasis
     */
    private JButton createPrimaryButton(String text)
    {
        JButton button = new JButton(text);
        button.setFont(new Font("SF Pro Text", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(0, 122, 255));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(12, 24, 12, 24));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        button.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseEntered(java.awt.event.MouseEvent evt)
            {
                button.setBackground(new Color(0, 110, 235));
            }
            
            public void mouseExited(java.awt.event.MouseEvent evt)
            {
                button.setBackground(new Color(0, 122, 255));
            }
        });
        
        return button;
    }
    
    /**
     * Creates secondary action button
     */
    private JButton createSecondaryButton(String text)
    {
        JButton button = new JButton(text);
        button.setFont(new Font("SF Pro Text", Font.PLAIN, 14));
        button.setForeground(UIManager.getColor("Label.foreground"));
        button.setBackground(UIManager.getColor("Button.background"));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(UIManager.getColor("Button.shadow"), 1),
            BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        return button;
    }
    
    /**
     * Creates tertiary text button for less frequent actions
     */
    private JButton createTertiaryButton(String text)
    {
        JButton button = new JButton(text);
        button.setFont(new Font("SF Pro Text", Font.PLAIN, 13));
        button.setForeground(new Color(0, 122, 255));
        button.setBorder(BorderFactory.createEmptyBorder(6, 12, 6, 12));
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        return button;
    }
    
    /**
     * Creates text-only button for navigation actions
     */
    private JButton createTextButton(String text)
    {
        JButton button = new JButton(text);
        button.setFont(new Font("SF Pro Text", Font.PLAIN, 13));
        button.setForeground(new Color(0, 122, 255));
        button.setBorder(BorderFactory.createEmptyBorder(4, 8, 4, 8));
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        return button;
    }
    
    /**
     * Shows dialog for adding new tasks with structured form
     */
    private void showAddTaskDialog()
    {
        JDialog dialog = new JDialog((Frame)SwingUtilities.getWindowAncestor(this), 
                                   "Add New Task", true);
        dialog.setLayout(new BorderLayout());
        dialog.setSize(400, 500);
        dialog.setLocationRelativeTo(this);
        
        // Create form panel
        JPanel formPanel = createTaskFormPanel(dialog);
        
        dialog.add(formPanel, BorderLayout.CENTER);
        dialog.setVisible(true);
    }
    
    /**
     * Creates structured task form panel
     */
    private JPanel createTaskFormPanel(JDialog dialog)
    {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(24, 24, 24, 24));
        panel.setBackground(UIManager.getColor("Panel.background"));
        
        // Form title
        JLabel title = new JLabel("New Task Details", SwingConstants.CENTER);
        title.setFont(new Font("SF Pro Display", Font.BOLD, 18));
        title.setForeground(UIManager.getColor("Label.foreground"));
        title.setAlignmentX(CENTER_ALIGNMENT);
        title.setBorder(BorderFactory.createEmptyBorder(0, 0, 24, 0));
        panel.add(title);
        
        // Form fields
        JTextField nameField = createFormTextField("Task Name");
        panel.add(nameField);
        panel.add(Box.createVerticalStrut(16));
        
        JTextArea descArea = createFormTextArea("Description (≤50 characters)", 3);
        panel.add(new JScrollPane(descArea));
        panel.add(Box.createVerticalStrut(16));
        
        JTextField devField = createFormTextField("Developers (comma-separated)");
        panel.add(devField);
        panel.add(Box.createVerticalStrut(16));
        
        JTextField durationField = createFormTextField("Duration (hours)");
        panel.add(durationField);
        panel.add(Box.createVerticalStrut(16));
        
        // Status selection
        JPanel statusPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        statusPanel.setBackground(UIManager.getColor("Panel.background"));
        statusPanel.setAlignmentX(LEFT_ALIGNMENT);
        
        JLabel statusLabel = new JLabel("Status:");
        statusLabel.setFont(new Font("SF Pro Text", Font.PLAIN, 14));
        statusPanel.add(statusLabel);
        statusPanel.add(Box.createHorizontalStrut(12));
        
        String[] statusOptions = {"To Do", "Doing", "Done"};
        JComboBox<String> statusCombo = new JComboBox<>(statusOptions);
        statusCombo.setFont(new Font("SF Pro Text", Font.PLAIN, 14));
        statusPanel.add(statusCombo);
        
        panel.add(statusPanel);
        panel.add(Box.createVerticalStrut(32));
        
        // Action buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 16, 0));
        buttonPanel.setBackground(UIManager.getColor("Panel.background"));
        
        JButton cancelButton = createSecondaryButton("Cancel");
        cancelButton.addActionListener(e -> dialog.dispose());
        
        JButton saveButton = createPrimaryButton("Add Task");
        saveButton.addActionListener(e -> 
        {
            saveTask(nameField, descArea, devField, durationField, statusCombo, dialog);
        });
        
        buttonPanel.add(cancelButton);
        buttonPanel.add(saveButton);
        
        panel.add(buttonPanel);
        
        return panel;
    }
    
    /**
     * Creates form text field with label
     */
    private JTextField createFormTextField(String placeholder)
    {
        JTextField field = new JTextField();
        field.setFont(new Font("SF Pro Text", Font.PLAIN, 14));
        field.setMaximumSize(new Dimension(1000, 36));
        field.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(UIManager.getColor("TextField.shadow"), 1),
            placeholder, TitledBorder.LEFT, TitledBorder.TOP,
            new Font("SF Pro Text", Font.PLAIN, 11),
            UIManager.getColor("Label.disabledForeground")
        ));
        
        return field;
    }
    
    /**
     * Creates form text area with label
     */
    private JTextArea createFormTextArea(String placeholder, int rows)
    {
        JTextArea area = new JTextArea(rows, 20);
        area.setFont(new Font("SF Pro Text", Font.PLAIN, 14));
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(UIManager.getColor("TextField.shadow"), 1),
            placeholder, TitledBorder.LEFT, TitledBorder.TOP,
            new Font("SF Pro Text", Font.PLAIN, 11),
            UIManager.getColor("Label.disabledForeground")
        ));
        
        return area;
    }
    
    /**
     * Saves task from form data
     */
    private void saveTask(JTextField nameField, JTextArea descArea, 
                         JTextField devField, JTextField durationField,
                         JComboBox<String> statusCombo, JDialog dialog)
    {
        String taskName = nameField.getText().trim();
        String description = descArea.getText().trim();
        String developersStr = devField.getText().trim();
        String durationStr = durationField.getText().trim();
        String status = (String) statusCombo.getSelectedItem();
        
        // Validate required fields
        if (taskName.isEmpty() || description.isEmpty() || 
            developersStr.isEmpty() || durationStr.isEmpty())
        {
            showMessage("Please complete all required fields", 
                       "Incomplete Form", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Validate description length
        if (description.length() > 50)
        {
            showMessage("Description must be 50 characters or less", 
                       "Invalid Description", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Parse developers
        String[] devArray = developersStr.split(",");
        List<String> developers = new ArrayList<>();
        for (String dev : devArray)
        {
            String trimmedDev = dev.trim();
            if (!trimmedDev.isEmpty())
            {
                developers.add(trimmedDev);
            }
        }
        
        // Validate developer count
        if (developers.size() < 1 || developers.size() > 5)
        {
            showMessage("Must have between 1 and 5 developers", 
                       "Invalid Team Size", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Parse and validate duration
        try
        {
            float duration = Float.parseFloat(durationStr);
            if (duration <= 0)
            {
                showMessage("Duration must be a positive number", 
                           "Invalid Duration", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Add task
            taskManager.addTask(taskName, description, developers, duration, status);
            showMessage("Task added successfully", 
                       "Success", JOptionPane.INFORMATION_MESSAGE);
            dialog.dispose();
            showAllTasks();
        }
        catch (NumberFormatException e)
        {
            showMessage("Please enter a valid number for duration", 
                       "Invalid Format", JOptionPane.ERROR_MESSAGE);
        }
        catch (IllegalArgumentException e)
        {
            showMessage(e.getMessage(), "Unable to Add Task", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Displays all tasks in the main display area
     */
    private void showAllTasks()
    {
        String content = taskManager.getAllTasks();
        taskDisplayArea.setText(
            content.trim().isEmpty() ? 
            "No tasks have been added yet." : 
            content
        );
    }
    
    /**
     * Shows dialog for searching tasks by developer
     */
    private void showSearchDialog()
    {
        String developer = JOptionPane.showInputDialog(
            this, 
            "Enter developer name to search:", 
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
     * Shows the task with the longest duration
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
            showMessage("No tasks available to compare", 
                       "Unable to Determine", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    /**
     * Shows user feedback messages
     */
    private void showMessage(String message, String title, int messageType)
    {
        JOptionPane.showMessageDialog(this, message, title, messageType);
    }
}