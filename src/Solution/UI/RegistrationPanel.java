package Solution.UI;

import Solution.Logic.AuthManager;
import javax.swing.*;
import java.awt.*;

/**
 * Registration interface with Apple design aesthetics
 * Clear visual hierarchy, consistent spacing, subtle interactions
 */
public class RegistrationPanel extends JPanel
{
    private final AuthFrame frame;
    private final AuthManager authManager;
    private final JTextField usernameField;
    private final JPasswordField passwordField;
    private final JTextField firstNameField;
    private final JTextField lastNameField;
    
    public RegistrationPanel(AuthFrame frame, AuthManager authManager)
    {
        this.frame = frame;
        this.authManager = authManager;
        
        setLayout(new GridBagLayout());
        setBackground(new Color(248, 248, 248));
        
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(new Color(248, 248, 248));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 40, 0));
        
        // Title
        JLabel title = new JLabel("Create Account", SwingConstants.CENTER);
        title.setFont(new Font("Helvetica Neue", Font.PLAIN, 28));
        title.setForeground(new Color(28, 28, 28));
        title.setAlignmentX(CENTER_ALIGNMENT);
        title.setBorder(BorderFactory.createEmptyBorder(0, 0, 32, 0));
        contentPanel.add(title);
        
        // Form fields
        contentPanel.add(createLabeledField("Username", usernameField = new JTextField(24)));
        contentPanel.add(Box.createVerticalStrut(12));
        contentPanel.add(createLabeledField("Password", passwordField = new JPasswordField(24)));
        contentPanel.add(Box.createVerticalStrut(12));
        contentPanel.add(createLabeledField("First Name", firstNameField = new JTextField(24)));
        contentPanel.add(Box.createVerticalStrut(12));
        contentPanel.add(createLabeledField("Last Name", lastNameField = new JTextField(24)));
        contentPanel.add(Box.createVerticalStrut(32));
        
        // Requirements hint
        JLabel requirements = new JLabel(
            "<html><div style='text-align: left; font-size: 11px; color: #666;'>" +
            "• Username must contain '_' and be ≤5 characters<br>" +
            "• Password needs 8+ chars with uppercase, number, and special char" +
            "</div></html>"
        );
        requirements.setAlignmentX(LEFT_ALIGNMENT);
        requirements.setBorder(BorderFactory.createEmptyBorder(0, 4, 16, 4));
        contentPanel.add(requirements);
        
        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 16, 0));
        buttonPanel.setBackground(new Color(248, 248, 248));
        
        JButton cancelButton = createSecondaryButton("Cancel");
        cancelButton.addActionListener(e -> frame.showLoginPanel());
        
        JButton createButton = createPrimaryButton("Create Account");
        createButton.addActionListener(e -> attemptRegistration());
        
        buttonPanel.add(cancelButton);
        buttonPanel.add(createButton);
        buttonPanel.setAlignmentX(LEFT_ALIGNMENT);
        contentPanel.add(buttonPanel);
        
        add(contentPanel);
    }
    
    private JPanel createLabeledField(String labelText, JTextField field)
    {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(248, 248, 248));
        panel.setAlignmentX(LEFT_ALIGNMENT);
        
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
        label.setForeground(new Color(100, 100, 100));
        label.setAlignmentX(LEFT_ALIGNMENT);
        
        styleTextField(field);
        
        panel.add(label);
        panel.add(Box.createVerticalStrut(4));
        panel.add(field);
        
        return panel;
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
        button.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(0, 122, 255));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
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
    
    private void attemptRegistration()
    {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();
        String firstName = firstNameField.getText().trim();
        String lastName = lastNameField.getText().trim();
        
        if (username.isEmpty() || password.isEmpty() || 
            firstName.isEmpty() || lastName.isEmpty())
        {
            showMessage("All fields are required", 
                       "Incomplete Form", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        try
        {
            authManager.registerUser(username, password, firstName, lastName);
            showMessage("Account created successfully. Please sign in.", 
                       "Success", JOptionPane.INFORMATION_MESSAGE);
            frame.showLoginPanel();
        }
        catch (IllegalArgumentException ex)
        {
            showMessage(ex.getMessage(), 
                       "Registration Failed", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void showMessage(String message, String title, int messageType)
    {
        JOptionPane.showMessageDialog(this, message, title, messageType);
    }
}