package Solution.UI;

import Solution.Logic.AuthManager;
import javax.swing.*;
import java.awt.*;

/**
 * Minimalist login interface with Apple design principles
 * Clean layout, ample whitespace, subtle interactions
 */
public class LoginPanel extends JPanel
{
    private final AuthFrame frame;
    private final AuthManager authManager;
    private final JTextField usernameField;
    private final JPasswordField passwordField;
    
    public LoginPanel(AuthFrame frame, AuthManager authManager)
    {
        this.frame = frame;
        this.authManager = authManager;
        
        setLayout(new GridBagLayout());
        setBackground(new Color(248, 248, 248));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(4, 40, 4, 40);
        
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(new Color(248, 248, 248));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(40, 0, 40, 0));
        
        // Title
        JLabel title = new JLabel("Sign In", SwingConstants.CENTER);
        title.setFont(new Font("Helvetica Neue", Font.PLAIN, 32));
        title.setForeground(new Color(28, 28, 28));
        title.setAlignmentX(CENTER_ALIGNMENT);
        title.setBorder(BorderFactory.createEmptyBorder(0, 0, 48, 0));
        contentPanel.add(title);
        
        // Username field
        JLabel userLabel = new JLabel("Username");
        userLabel.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
        userLabel.setForeground(new Color(100, 100, 100));
        userLabel.setAlignmentX(LEFT_ALIGNMENT);
        contentPanel.add(userLabel);
        contentPanel.add(Box.createVerticalStrut(4));
        
        usernameField = new JTextField(24);
        styleTextField(usernameField);
        usernameField.setAlignmentX(LEFT_ALIGNMENT);
        contentPanel.add(usernameField);
        contentPanel.add(Box.createVerticalStrut(20));
        
        // Password field
        JLabel passLabel = new JLabel("Password");
        passLabel.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
        passLabel.setForeground(new Color(100, 100, 100));
        passLabel.setAlignmentX(LEFT_ALIGNMENT);
        contentPanel.add(passLabel);
        contentPanel.add(Box.createVerticalStrut(4));
        
        passwordField = new JPasswordField(24);
        styleTextField(passwordField);
        passwordField.setAlignmentX(LEFT_ALIGNMENT);
        contentPanel.add(passwordField);
        contentPanel.add(Box.createVerticalStrut(32));
        
        // Sign In button
        JButton signInButton = createPrimaryButton("Sign In");
        signInButton.setAlignmentX(LEFT_ALIGNMENT);
        signInButton.addActionListener(e -> attemptLogin());
        contentPanel.add(signInButton);
        contentPanel.add(Box.createVerticalStrut(16));
        
        // Registration link
        JPanel linkPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        linkPanel.setBackground(new Color(248, 248, 248));
        JLabel question = new JLabel("Don't have an account? ");
        question.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
        question.setForeground(new Color(100, 100, 100));
        
        JButton registerLink = new JButton("Register");
        styleLinkButton(registerLink);
        registerLink.addActionListener(e -> frame.showRegistrationPanel());
        
        linkPanel.add(question);
        linkPanel.add(registerLink);
        linkPanel.setAlignmentX(LEFT_ALIGNMENT);
        contentPanel.add(linkPanel);
        
        add(contentPanel);
    }
    
    private void styleTextField(JTextField field)
    {
        field.setFont(new Font("Helvetica Neue", Font.PLAIN, 16));
        field.setForeground(new Color(28, 28, 28));
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(200, 200, 200)),
            BorderFactory.createEmptyBorder(8, 8, 8, 8)
        ));
        field.setBackground(new Color(248, 248, 248));
        field.setMaximumSize(new Dimension(400, 40));
    }
    
    private JButton createPrimaryButton(String text)
    {
        JButton button = new JButton(text);
        button.setFont(new Font("Helvetica Neue", Font.PLAIN, 15));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(0, 122, 255));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(12, 24, 12, 24));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        // Simple hover effect
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
    
    private void styleLinkButton(JButton button)
    {
        button.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
        button.setForeground(new Color(0, 122, 255));
        button.setBorder(BorderFactory.createEmptyBorder(2, 4, 2, 4));
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }
    
    private void attemptLogin()
    {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();
        
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
            showMessage("Invalid username or password", 
                       "Login Failed", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void showMessage(String message, String title, int messageType)
    {
        JOptionPane.showMessageDialog(this, message, title, messageType);
    }
}