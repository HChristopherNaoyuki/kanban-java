package Solution.UI;

import Solution.Logic.AuthManager;
import Solution.Logic.TaskManager;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Main task management interface with Apple-like design
 * Clean layout, intuitive controls, and consistent visual language
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
        
        setLayout(new BorderLayout(0, 0));
        setBackground(new Color(248, 248, 248));
        
        add(createHeaderPanel(), BorderLayout.NORTH);
        add(createContentPanel(), BorderLayout.CENTER);
        add(createFooterPanel(), BorderLayout.SOUTH);
    }
    
    private JPanel createHeaderPanel()
    {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(248, 248, 248));
        header.setBorder(BorderFactory.createEmptyBorder(24, 32, 24, 32));
        
        // Welcome message
        String fullName = authManager.getStoredFirstName() + " " + 
                         authManager.getStoredLastName();
        JLabel welcomeLabel = new JLabel("Welcome, " + fullName);
        welcomeLabel.setFont(new Font("Helvetica Neue", Font.PLAIN, 22));
        welcomeLabel.setForeground(new Color(28, 28, 28));
        
        // Logout button
        JButton logoutButton = createTextButton("Logout");
        logoutButton.addActionListener(e -> parentFrame.showLoginPanel());
        
        header.add(welcomeLabel, BorderLayout.WEST);
        header.add(logoutButton, BorderLayout.EAST);
        
        return header;
    }
    
    private JPanel createContentPanel()
    {
        JPanel content = new JPanel(new BorderLayout());
        content.setBackground(new Color(248, 248, 248));
        content.setBorder(BorderFactory.createEmptyBorder(0, 32, 0, 32));
        
        // Task display area
        taskDisplayArea = new JTextArea();
        taskDisplayArea.setEditable(false);
        taskDisplayArea.setLineWrap(true);
        taskDisplayArea.setWrapStyleWord(true);
        taskDisplayArea.setFont(new Font("Monaco", Font.PLAIN, 12));
        taskDisplayArea.setBackground(Color.WHITE);
        taskDisplayArea.setForeground(new Color(50, 50, 50));
        taskDisplayArea.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));
        
        JScrollPane scrollPane = new JScrollPane(taskDisplayArea);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220), 1));
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        
        content.add(scrollPane, BorderLayout.CENTER);
        
        return content;
    }
    
    private JPanel createFooterPanel()
    {
        JPanel footer = new JPanel(new FlowLayout(FlowLayout.CENTER, 16, 16));
        footer.setBackground(new Color(248, 248, 248));
        footer.setBorder(BorderFactory.createEmptyBorder(16, 32, 32, 32));
        
        // Action buttons
        JButton addTaskButton = createPrimaryButton("Add Task");
        addTaskButton.addActionListener(e -> showAddTaskDialog());
        
        JButton viewAllButton = createSecondaryButton("View All");
        viewAllButton.addActionListener(e -> showAllTasks());
        
        JButton searchButton = createSecondaryButton("Search by Developer");
        searchButton.addActionListener(e -> showSearchDialog());
        
        footer.add(addTaskButton);
        footer.add(viewAllButton);
        footer.add(searchButton);
        
        return footer;
    }
    
    private JButton createPrimaryButton(String text)
    {
        JButton button = new JButton(text);
        button.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(0, 122, 255));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 24, 10, 24));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        button.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseEntered(java.awt.event.MouseEvent evt)
            {
                button.setBackground(new Color(0, 110, 230));
            }
            
            public void mouseExited(java.awt.event.MouseEvent evt)
            {
                button.setBackground(new Color(0, 122, 255));
            }
        });
        
        return button;
    }
    
    private JButton createSecondaryButton(String text)
    {
        JButton button = new JButton(text);
        button.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
        button.setForeground(new Color(100, 100, 100));
        button.setBackground(new Color(235, 235, 235));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        return button;
    }
    
    private JButton createTextButton(String text)
    {
        JButton button = new JButton(text);
        button.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
        button.setForeground(new Color(0, 122, 255));
        button.setBorder(BorderFactory.createEmptyBorder(4, 8, 4, 8));
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        return button;
    }
    
    private void showAddTaskDialog()
    {
        // Simplified task addition - all in one dialog
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(4, 4, 12, 4);
        
        // Task name
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Task Name:"), gbc);
        gbc.gridx = 1;
        JTextField nameField = new JTextField(20);
        panel.add(nameField, gbc);
        
        // Description
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Description (≤50 chars):"), gbc);
        gbc.gridx = 1;
        JTextArea descArea = new JTextArea(3, 20);
        descArea.setLineWrap(true);
        descArea.setWrapStyleWord(true);
        panel.add(new JScrollPane(descArea), gbc);
        
        // Developers
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Developers (comma-separated):"), gbc);
        gbc.gridx = 1;
        JTextField devField = new JTextField(20);
        panel.add(devField, gbc);
        
        // Duration
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("Duration (hours):"), gbc);
        gbc.gridx = 1;
        JTextField durationField = new JTextField(10);
        panel.add(durationField, gbc);
        
        // Status
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(new JLabel("Status:"), gbc);
        gbc.gridx = 1;
        String[] statusOptions = {"To Do", "Doing", "Done"};
        JComboBox<String> statusCombo = new JComboBox<>(statusOptions);
        panel.add(statusCombo, gbc);
        
        int result = JOptionPane.showConfirmDialog(
            this, panel, "Add New Task", 
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE
        );
        
        if (result == JOptionPane.OK_OPTION)
        {
            String taskName = nameField.getText().trim();
            String description = descArea.getText().trim();
            String developersStr = devField.getText().trim();
            String durationStr = durationField.getText().trim();
            String status = (String) statusCombo.getSelectedItem();
            
            // Validation
            if (taskName.isEmpty() || description.isEmpty() || 
                developersStr.isEmpty() || durationStr.isEmpty())
            {
                showMessage("All fields are required", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if (description.length() > 50)
            {
                showMessage("Description must be 50 characters or less", 
                           "Error", JOptionPane.ERROR_MESSAGE);
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
            
            if (developers.size() < 1 || developers.size() > 5)
            {
                showMessage("Must have 1-5 developers", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Parse duration
            try
            {
                float duration = Float.parseFloat(durationStr);
                if (duration <= 0)
                {
                    showMessage("Duration must be positive", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                taskManager.addTask(taskName, description, developers, duration, status);
                showMessage("Task added successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                showAllTasks();
            }
            catch (NumberFormatException e)
            {
                showMessage("Invalid duration format", "Error", JOptionPane.ERROR_MESSAGE);
            }
            catch (IllegalArgumentException e)
            {
                showMessage(e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
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
                showMessage(e.getMessage(), "Search Results", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
    
    private void showMessage(String message, String title, int messageType)
    {
        JOptionPane.showMessageDialog(this, message, title, messageType);
    }
}