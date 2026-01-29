package Solution.UI;

import Solution.Logic.AuthManager;
import javax.swing.*;
import java.awt.*;

/**
 * Registration interface optimized for 10x16 aspect ratio
 * Features split layout with form on left and requirements on right
 */
public class RegistrationPanel extends JPanel
{
    private final AuthFrame frame;
    private final AuthManager authManager;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField firstNameField;
    private JTextField lastNameField;
    
    public RegistrationPanel(AuthFrame frame, AuthManager authManager)
    {
        this.frame = frame;
        this.authManager = authManager;
        
        setLayout(new BorderLayout());
        setBackground(new Color(248, 248, 248));
        
        // Create scrollable content for better fit
        JPanel contentPanel = createContentPanel();
        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        
        add(scrollPane, BorderLayout.CENTER);
    }
    
    /**
     * Creates the main content panel for 10x16 ratio
     */
    private JPanel createContentPanel()
    {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(248, 248, 248));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        // Title section
        panel.add(createTitleSection());
        panel.add(Box.createVerticalStrut(20));
        
        // Main content split panel
        panel.add(createSplitContentPanel());
        
        return panel;
    }
    
    /**
     * Creates the title section
     */
    private JPanel createTitleSection()
    {
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        titlePanel.setBackground(new Color(248, 248, 248));
        titlePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel title = new JLabel("Create Account", SwingConstants.CENTER);
        title.setFont(new Font("Helvetica Neue", Font.BOLD, 22));
        title.setForeground(new Color(28, 28, 30));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel subtitle = new JLabel("Join Task Manager", SwingConstants.CENTER);
        subtitle.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
        subtitle.setForeground(new Color(140, 140, 145));
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        subtitle.setBorder(BorderFactory.createEmptyBorder(4, 0, 0, 0));
        
        titlePanel.add(title);
        titlePanel.add(subtitle);
        
        return titlePanel;
    }
    
    /**
     * Creates the split content panel with form and requirements
     */
    private JPanel createSplitContentPanel()
    {
        JPanel splitPanel = new JPanel();
        splitPanel.setLayout(new GridLayout(1, 2, 15, 0));
        splitPanel.setBackground(new Color(248, 248, 248));
        
        // Left panel: Form
        splitPanel.add(createFormPanel());
        
        // Right panel: Requirements
        splitPanel.add(createRequirementsPanel());
        
        return splitPanel;
    }
    
    /**
     * Creates the form panel (left side)
     */
    private JPanel createFormPanel()
    {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 220, 225), 1),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        
        // Form fields
        panel.add(createFormField("First Name", firstNameField = createTextField()));
        panel.add(Box.createVerticalStrut(12));
        panel.add(createFormField("Last Name", lastNameField = createTextField()));
        panel.add(Box.createVerticalStrut(12));
        panel.add(createFormField("Username", usernameField = createTextField()));
        panel.add(Box.createVerticalStrut(12));
        panel.add(createFormField("Password", passwordField = createPasswordField()));
        panel.add(Box.createVerticalStrut(20));
        
        // Action buttons
        panel.add(createActionButtons());
        
        return panel;
    }
    
    /**
     * Creates a labeled form field
     */
    private JPanel createFormField(String labelText, JComponent field)
    {
        JPanel fieldPanel = new JPanel();
        fieldPanel.setLayout(new BoxLayout(fieldPanel, BoxLayout.Y_AXIS));
        fieldPanel.setBackground(Color.WHITE);
        fieldPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
        label.setForeground(new Color(100, 100, 105));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        field.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        fieldPanel.add(label);
        fieldPanel.add(Box.createVerticalStrut(5));
        fieldPanel.add(field);
        
        return fieldPanel;
    }
    
    /**
     * Creates the action buttons with consistent theming
     */
    private JPanel createActionButtons()
    {
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonsPanel.setBackground(Color.WHITE);
        
        JButton cancelButton = new JButton("Cancel");
        styleSecondaryButton(cancelButton);
        cancelButton.addActionListener(e -> frame.showLoginPanel());
        
        JButton createButton = new JButton("Create Account");
        stylePrimaryButton(createButton);
        createButton.addActionListener(e -> attemptRegistration());
        
        buttonsPanel.add(cancelButton);
        buttonsPanel.add(createButton);
        
        return buttonsPanel;
    }
    
    /**
     * Creates the requirements panel (right side)
     */
    private JPanel createRequirementsPanel()
    {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(245, 245, 247));
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 220, 225), 1),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        
        JLabel title = new JLabel("Account Requirements");
        title.setFont(new Font("Helvetica Neue", Font.BOLD, 16));
        title.setForeground(new Color(28, 28, 30));
        title.setAlignmentX(Component.LEFT_ALIGNMENT);
        title.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));
        panel.add(title);
        
        // Requirements content
        panel.add(createRequirementItem(
            "Username must contain '_' and be 5 characters or less"));
        panel.add(Box.createVerticalStrut(8));
        
        panel.add(createRequirementItem(
            "Password must be at least 8 characters"));
        panel.add(Box.createVerticalStrut(8));
        
        panel.add(createRequirementItem(
            "Include uppercase letter, number, and special character"));
        
        // Add flexible space at bottom
        panel.add(Box.createVerticalGlue());
        
        return panel;
    }
    
    /**
     * Creates a requirement item with bullet point
     */
    private JPanel createRequirementItem(String text)
    {
        JPanel itemPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        itemPanel.setBackground(new Color(245, 245, 247));
        
        JLabel bullet = new JLabel("•");
        bullet.setFont(new Font("Helvetica Neue", Font.PLAIN, 12));
        bullet.setForeground(new Color(100, 100, 105));
        
        JLabel textLabel = new JLabel(text);
        textLabel.setFont(new Font("Helvetica Neue", Font.PLAIN, 12));
        textLabel.setForeground(new Color(100, 100, 105));
        
        itemPanel.add(bullet);
        itemPanel.add(textLabel);
        
        return itemPanel;
    }
    
    /**
     * Creates a styled text field
     */
    private JTextField createTextField()
    {
        JTextField field = new JTextField();
        field.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
        field.setMaximumSize(new Dimension(1000, 34));
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(210, 210, 215), 1),
            BorderFactory.createEmptyBorder(7, 9, 7, 9)
        ));
        field.setBackground(Color.WHITE);
        return field;
    }
    
    /**
     * Creates a styled password field
     */
    private JPasswordField createPasswordField()
    {
        JPasswordField field = new JPasswordField();
        field.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
        field.setMaximumSize(new Dimension(1000, 34));
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(210, 210, 215), 1),
            BorderFactory.createEmptyBorder(7, 9, 7, 9)
        ));
        field.setBackground(Color.WHITE);
        return field;
    }
    
    /**
     * Styles a primary button with consistent theming
     */
    private void stylePrimaryButton(JButton button)
    {
        button.setFont(new Font("Helvetica Neue", Font.BOLD, 13));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(0, 122, 255));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(9, 22, 9, 22));
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
    }
    
    /**
     * Styles a secondary button with consistent theming
     */
    private void styleSecondaryButton(JButton button)
    {
        button.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
        button.setForeground(new Color(100, 100, 105));
        button.setBackground(new Color(240, 240, 242));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(210, 210, 215), 1),
            BorderFactory.createEmptyBorder(8, 20, 8, 20)
        ));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }
    
    /**
     * Attempts to register a new user account
     */
    private void attemptRegistration()
    {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();
        String firstName = firstNameField.getText().trim();
        String lastName = lastNameField.getText().trim();
        
        // Validate all fields
        if (username.isEmpty() || password.isEmpty() || 
            firstName.isEmpty() || lastName.isEmpty())
        {
            showMessage("Please fill in all fields to create your account.", 
                       "Incomplete Form", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        try
        {
            authManager.registerUser(username, password, firstName, lastName);
            showMessage("Account created successfully!\nYou can now sign in.", 
                       "Registration Complete", JOptionPane.INFORMATION_MESSAGE);
            frame.showLoginPanel();
        }
        catch (IllegalArgumentException ex)
        {
            showMessage(ex.getMessage(), "Registration Failed", JOptionPane.ERROR_MESSAGE);
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