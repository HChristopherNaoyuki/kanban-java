package Solution.UI;

import Solution.Logic.AuthManager;
import javax.swing.*;
import java.awt.*;

/**
 * Authentication interface following progressive disclosure principles
 * Clear structure with focused content layout
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
        
        setLayout(new GridBagLayout());
        setBackground(UIManager.getColor("Panel.background"));
        
        // Main content container with proper spacing
        JPanel contentPanel = createContentPanel();
        add(contentPanel);
    }
    
    /**
     * Creates main content panel with structured layout
     * Implements visual hierarchy and clear action paths
     */
    private JPanel createContentPanel()
    {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(UIManager.getColor("Panel.background"));
        panel.setBorder(BorderFactory.createEmptyBorder(48, 64, 48, 64));
        panel.setMaximumSize(new Dimension(400, 600));
        
        // Application title
        panel.add(createTitleLabel());
        panel.add(Box.createVerticalStrut(48));
        
        // Form fields with clear labels
        panel.add(createFormSection());
        panel.add(Box.createVerticalStrut(32));
        
        // Primary action button
        panel.add(createSignInButton());
        panel.add(Box.createVerticalStrut(24));
        
        // Secondary navigation option
        panel.add(createRegistrationLink());
        
        return panel;
    }
    
    /**
     * Creates application title with visual hierarchy
     */
    private JLabel createTitleLabel()
    {
        JLabel label = new JLabel("Task Manager", SwingConstants.CENTER);
        label.setFont(new Font("SF Pro Display", Font.BOLD, 28));
        label.setForeground(UIManager.getColor("Label.foreground"));
        label.setAlignmentX(CENTER_ALIGNMENT);
        return label;
    }
    
    /**
     * Creates structured form section with input fields
     */
    private JPanel createFormSection()
    {
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBackground(UIManager.getColor("Panel.background"));
        formPanel.setAlignmentX(CENTER_ALIGNMENT);
        
        // Username field with label
        formPanel.add(createFieldLabel("Username"));
        formPanel.add(Box.createVerticalStrut(8));
        usernameField = createTextField();
        formPanel.add(usernameField);
        formPanel.add(Box.createVerticalStrut(24));
        
        // Password field with label
        formPanel.add(createFieldLabel("Password"));
        formPanel.add(Box.createVerticalStrut(8));
        passwordField = createPasswordField();
        formPanel.add(passwordField);
        
        return formPanel;
    }
    
    /**
     * Creates consistent field labels with typographic hierarchy
     */
    private JLabel createFieldLabel(String text)
    {
        JLabel label = new JLabel(text);
        label.setFont(new Font("SF Pro Text", Font.PLAIN, 14));
        label.setForeground(UIManager.getColor("Label.foreground"));
        label.setAlignmentX(LEFT_ALIGNMENT);
        return label;
    }
    
    /**
     * Creates standardized text input field
     */
    private JTextField createTextField()
    {
        JTextField field = new JTextField(24);
        field.setFont(new Font("SF Pro Text", Font.PLAIN, 16));
        field.setMaximumSize(new Dimension(300, 40));
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 1, 0, 
                UIManager.getColor("TextField.shadow")),
            BorderFactory.createEmptyBorder(8, 8, 8, 8)
        ));
        return field;
    }
    
    /**
     * Creates secure password input field
     */
    private JPasswordField createPasswordField()
    {
        JPasswordField field = new JPasswordField(24);
        field.setFont(new Font("SF Pro Text", Font.PLAIN, 16));
        field.setMaximumSize(new Dimension(300, 40));
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 1, 0, 
                UIManager.getColor("TextField.shadow")),
            BorderFactory.createEmptyBorder(8, 8, 8, 8)
        ));
        return field;
    }
    
    /**
     * Creates primary action button with visual emphasis
     */
    private JButton createSignInButton()
    {
        JButton button = new JButton("Sign In");
        button.setFont(new Font("SF Pro Text", Font.BOLD, 15));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(0, 122, 255)); // System blue
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(12, 32, 12, 32));
        button.setAlignmentX(CENTER_ALIGNMENT);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        // Add hover effect for better interaction feedback
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
        
        button.addActionListener(e -> attemptLogin());
        
        return button;
    }
    
    /**
     * Creates secondary navigation link with clear affordance
     */
    private JPanel createRegistrationLink()
    {
        JPanel linkPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 4, 0));
        linkPanel.setBackground(UIManager.getColor("Panel.background"));
        linkPanel.setAlignmentX(CENTER_ALIGNMENT);
        
        JLabel promptLabel = new JLabel("New to Task Manager?");
        promptLabel.setFont(new Font("SF Pro Text", Font.PLAIN, 13));
        promptLabel.setForeground(UIManager.getColor("Label.disabledForeground"));
        
        JButton registerButton = new JButton("Create Account");
        styleLinkButton(registerButton);
        registerButton.addActionListener(e -> frame.showRegistrationPanel());
        
        linkPanel.add(promptLabel);
        linkPanel.add(registerButton);
        
        return linkPanel;
    }
    
    /**
     * Styles link buttons for clear navigation affordance
     */
    private void styleLinkButton(JButton button)
    {
        button.setFont(new Font("SF Pro Text", Font.PLAIN, 13));
        button.setForeground(new Color(0, 122, 255));
        button.setBorder(BorderFactory.createEmptyBorder(2, 4, 2, 4));
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }
    
    /**
     * Handles login attempt with user feedback
     */
    private void attemptLogin()
    {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();
        
        // Validate required fields
        if (username.isEmpty() || password.isEmpty())
        {
            showMessage("Please enter both username and password", 
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
            showMessage("The username or password is incorrect", 
                       "Unable to Sign In", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Shows user feedback messages with consistent styling
     */
    private void showMessage(String message, String title, int messageType)
    {
        JOptionPane.showMessageDialog(this, message, title, messageType);
    }
}