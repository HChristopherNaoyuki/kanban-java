package Solution.UI;

import Solution.Logic.AuthManager;
import javax.swing.*;
import java.awt.*;

/**
 * Login interface optimized for 2160x1440 aspect ratio (3:2)
 * Features consistent button theming and clean layout
 */
public class LoginPanel extends JPanel
{
    private final AuthFrame frame;
    private final AuthManager authManager;
    private JTextField usernameField;
    private JPasswordField passwordField;
    
    public LoginPanel(AuthFrame frame, AuthManager authManager)
    {
        this.frame = frame;
        this.authManager = authManager;
        
        setLayout(new BorderLayout());
        setBackground(new Color(248, 248, 248));
        
        // Create centered content
        JPanel contentPanel = createContentPanel();
        add(contentPanel, BorderLayout.CENTER);
    }
    
    /**
     * Creates the main content panel optimized for 2160x1440 ratio
     */
    private JPanel createContentPanel()
    {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(248, 248, 248));
        panel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        
        JPanel centerPanel = createCenteredContent();
        panel.add(centerPanel, gbc);
        
        return panel;
    }
    
    /**
     * Creates centered content with proper spacing for 2160x1440
     */
    private JPanel createCenteredContent()
    {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(248, 248, 248));
        panel.setMaximumSize(new Dimension(500, 700));
        
        // Title section with generous spacing
        panel.add(createTitleLabel());
        panel.add(Box.createVerticalStrut(60));
        
        // Form section
        panel.add(createFormSection());
        panel.add(Box.createVerticalStrut(40));
        
        // Action buttons
        panel.add(createSignInButton());
        panel.add(Box.createVerticalStrut(25));
        
        // Registration link
        panel.add(createRegistrationLink());
        
        // Add flexible space at bottom
        panel.add(Box.createVerticalGlue());
        
        return panel;
    }
    
    /**
     * Creates the application title label with appropriate sizing
     */
    private JLabel createTitleLabel()
    {
        JLabel label = new JLabel("Task Manager", SwingConstants.CENTER);
        label.setFont(new Font("Helvetica Neue", Font.BOLD, 36));
        label.setForeground(new Color(28, 28, 30));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        return label;
    }
    
    /**
     * Creates the form section with input fields
     */
    private JPanel createFormSection()
    {
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBackground(new Color(248, 248, 248));
        formPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        formPanel.setMaximumSize(new Dimension(400, 200));
        
        // Username field
        formPanel.add(createFormField("Username", usernameField = createTextField()));
        formPanel.add(Box.createVerticalStrut(25));
        
        // Password field
        formPanel.add(createFormField("Password", passwordField = createPasswordField()));
        
        return formPanel;
    }
    
    /**
     * Creates a labeled form field
     */
    private JPanel createFormField(String labelText, JComponent field)
    {
        JPanel fieldPanel = new JPanel();
        fieldPanel.setLayout(new BoxLayout(fieldPanel, BoxLayout.Y_AXIS));
        fieldPanel.setBackground(new Color(248, 248, 248));
        fieldPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        fieldPanel.setMaximumSize(new Dimension(400, 90));
        
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Helvetica Neue", Font.PLAIN, 16));
        label.setForeground(new Color(100, 100, 105));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        field.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        fieldPanel.add(label);
        fieldPanel.add(Box.createVerticalStrut(8));
        fieldPanel.add(field);
        
        return fieldPanel;
    }
    
    /**
     * Creates a styled text field for 2160x1440 ratio
     */
    private JTextField createTextField()
    {
        JTextField field = new JTextField();
        field.setFont(new Font("Helvetica Neue", Font.PLAIN, 18));
        field.setMaximumSize(new Dimension(400, 50));
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(210, 210, 215), 1),
            BorderFactory.createEmptyBorder(12, 15, 12, 15)
        ));
        field.setBackground(Color.WHITE);
        return field;
    }
    
    /**
     * Creates a styled password field for 2160x1440 ratio
     */
    private JPasswordField createPasswordField()
    {
        JPasswordField field = new JPasswordField();
        field.setFont(new Font("Helvetica Neue", Font.PLAIN, 18));
        field.setMaximumSize(new Dimension(400, 50));
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(210, 210, 215), 1),
            BorderFactory.createEmptyBorder(12, 15, 12, 15)
        ));
        field.setBackground(Color.WHITE);
        return field;
    }
    
    /**
     * Creates the primary Sign In button with consistent theming
     */
    private JButton createSignInButton()
    {
        JButton button = new JButton("Sign In");
        stylePrimaryButton(button);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.addActionListener(e -> attemptLogin());
        return button;
    }
    
    /**
     * Creates the registration link section
     */
    private JPanel createRegistrationLink()
    {
        JPanel linkPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 6, 0));
        linkPanel.setBackground(new Color(248, 248, 248));
        linkPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel promptLabel = new JLabel("Don't have an account?");
        promptLabel.setFont(new Font("Helvetica Neue", Font.PLAIN, 15));
        promptLabel.setForeground(new Color(140, 140, 145));
        
        JButton registerButton = createLinkButton("Create Account");
        registerButton.addActionListener(e -> frame.showRegistrationPanel());
        
        linkPanel.add(promptLabel);
        linkPanel.add(registerButton);
        
        return linkPanel;
    }
    
    /**
     * Creates a styled link button
     */
    private JButton createLinkButton(String text)
    {
        JButton button = new JButton(text);
        button.setFont(new Font("Helvetica Neue", Font.PLAIN, 15));
        button.setForeground(new Color(0, 122, 255));
        button.setBorder(BorderFactory.createEmptyBorder(3, 8, 3, 8));
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        return button;
    }
    
    /**
     * Styles a primary button with consistent theming for 2160x1440
     */
    private void stylePrimaryButton(JButton button)
    {
        button.setFont(new Font("Helvetica Neue", Font.BOLD, 16));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(0, 122, 255));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(14, 40, 14, 40));
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
     * Styles a secondary button with consistent theming for 2160x1440
     */
    private void styleSecondaryButton(JButton button)
    {
        button.setFont(new Font("Helvetica Neue", Font.PLAIN, 15));
        button.setForeground(new Color(100, 100, 105));
        button.setBackground(new Color(240, 240, 242));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(210, 210, 215), 1),
            BorderFactory.createEmptyBorder(12, 30, 12, 30)
        ));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }
    
    /**
     * Attempts user login with validation
     */
    private void attemptLogin()
    {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();
        
        // Validate input
        if (username.isEmpty() || password.isEmpty())
        {
            showMessage("Please enter both username and password.", 
                       "Required Fields", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        try
        {
            boolean success = authManager.loginUser(username, password);
            if (success)
            {
                frame.showTaskPanel();
            }
        }
        catch (IllegalArgumentException ex)
        {
            showMessage("Invalid username or password. Please try again.", 
                       "Login Failed", JOptionPane.ERROR_MESSAGE);
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