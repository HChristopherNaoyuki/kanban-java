package Solution.UI;

import Solution.Logic.AuthManager;
import javax.swing.*;
import java.awt.*;

/**
 * Clean, minimal login interface following Apple design guidelines
 * Provides clear navigation and intuitive user experience
 */
public class LoginPanel extends JPanel
{
    private final AuthFrame frame;
    private final AuthManager authManager;
    private JTextField usernameField;
    private JPasswordField passwordField;
    
    /**
     * Constructs the login panel with authentication components
     */
    public LoginPanel(AuthFrame frame, AuthManager authManager)
    {
        this.frame = frame;
        this.authManager = authManager;
        
        setLayout(new BorderLayout());
        setBackground(UIManager.getColor("Panel.background"));
        
        // Create centered content panel
        JPanel contentPanel = createContentPanel();
        add(contentPanel, BorderLayout.CENTER);
    }
    
    /**
     * Creates the main content panel with all UI components
     */
    private JPanel createContentPanel()
    {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(UIManager.getColor("Panel.background"));
        panel.setBorder(BorderFactory.createEmptyBorder(40, 50, 40, 50));
        
        // Add application title
        panel.add(createTitleLabel());
        panel.add(Box.createVerticalStrut(40));
        
        // Add form fields
        panel.add(createFormSection());
        panel.add(Box.createVerticalStrut(30));
        
        // Add primary action button
        panel.add(createSignInButton());
        panel.add(Box.createVerticalStrut(20));
        
        // Add registration link
        panel.add(createRegistrationLink());
        
        return panel;
    }
    
    /**
     * Creates the application title label
     */
    private JLabel createTitleLabel()
    {
        JLabel label = new JLabel("Task Manager", SwingConstants.CENTER);
        label.setFont(new Font("Helvetica Neue", Font.BOLD, 28));
        label.setForeground(new Color(28, 28, 28));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        return label;
    }
    
    /**
     * Creates the form section with username and password fields
     */
    private JPanel createFormSection()
    {
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBackground(UIManager.getColor("Panel.background"));
        formPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Username field
        formPanel.add(createFieldLabel("Username"));
        formPanel.add(Box.createVerticalStrut(8));
        usernameField = createTextField();
        formPanel.add(usernameField);
        formPanel.add(Box.createVerticalStrut(20));
        
        // Password field
        formPanel.add(createFieldLabel("Password"));
        formPanel.add(Box.createVerticalStrut(8));
        passwordField = createPasswordField();
        formPanel.add(passwordField);
        
        return formPanel;
    }
    
    /**
     * Creates a form field label with consistent styling
     */
    private JLabel createFieldLabel(String text)
    {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
        label.setForeground(new Color(80, 80, 80));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        return label;
    }
    
    /**
     * Creates a styled text field for user input
     */
    private JTextField createTextField()
    {
        JTextField field = new JTextField();
        field.setFont(new Font("Helvetica Neue", Font.PLAIN, 16));
        field.setMaximumSize(new Dimension(320, 40));
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
            BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        field.setBackground(Color.WHITE);
        return field;
    }
    
    /**
     * Creates a styled password field for secure input
     */
    private JPasswordField createPasswordField()
    {
        JPasswordField field = new JPasswordField();
        field.setFont(new Font("Helvetica Neue", Font.PLAIN, 16));
        field.setMaximumSize(new Dimension(320, 40));
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
            BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        field.setBackground(Color.WHITE);
        return field;
    }
    
    /**
     * Creates the primary sign-in button
     */
    private JButton createSignInButton()
    {
        JButton button = new JButton("Sign In");
        button.setFont(new Font("Helvetica Neue", Font.BOLD, 15));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(0, 122, 255));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(12, 40, 12, 40));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        // Add hover effect
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
        
        button.addActionListener(e -> attemptLogin());
        
        return button;
    }
    
    /**
     * Creates the registration navigation link
     */
    private JPanel createRegistrationLink()
    {
        JPanel linkPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 4, 0));
        linkPanel.setBackground(UIManager.getColor("Panel.background"));
        linkPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel promptLabel = new JLabel("Don't have an account?");
        promptLabel.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
        promptLabel.setForeground(new Color(120, 120, 120));
        
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
        button.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
        button.setForeground(new Color(0, 122, 255));
        button.setBorder(BorderFactory.createEmptyBorder(2, 6, 2, 6));
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return button;
    }
    
    /**
     * Attempts user login with validation
     */
    private void attemptLogin()
    {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();
        
        // Validate input fields
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
     * Displays a message dialog to the user
     */
    private void showMessage(String message, String title, int messageType)
    {
        JOptionPane.showMessageDialog(
            this, 
            message, 
            title, 
            messageType
        );
    }
}