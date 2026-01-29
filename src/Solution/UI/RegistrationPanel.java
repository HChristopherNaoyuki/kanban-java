package Solution.UI;

import Solution.Logic.AuthManager;
import javax.swing.*;
import java.awt.*;

/**
 * Registration interface optimized for 2160x1440 aspect ratio (3:2)
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
        
        // Create main content panel
        JPanel contentPanel = createContentPanel();
        add(contentPanel, BorderLayout.CENTER);
    }
    
    /**
     * Creates the main content panel for 2160x1440 ratio
     */
    private JPanel createContentPanel()
    {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(248, 248, 248));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(0, 0, 0, 20);
        
        // Left panel: Form
        JPanel formPanel = createFormPanel();
        panel.add(formPanel, gbc);
        
        gbc.gridx = 1;
        gbc.insets = new Insets(0, 20, 0, 0);
        
        // Right panel: Requirements
        JPanel requirementsPanel = createRequirementsPanel();
        panel.add(requirementsPanel, gbc);
        
        return panel;
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
            BorderFactory.createEmptyBorder(40, 40, 40, 40)
        ));
        
        // Title
        JLabel title = new JLabel("Create Account", SwingConstants.LEFT);
        title.setFont(new Font("Helvetica Neue", Font.BOLD, 28));
        title.setForeground(new Color(28, 28, 30));
        title.setAlignmentX(Component.LEFT_ALIGNMENT);
        title.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));
        panel.add(title);
        
        // Form fields
        panel.add(createFormField("First Name", firstNameField = createTextField()));
        panel.add(Box.createVerticalStrut(20));
        panel.add(createFormField("Last Name", lastNameField = createTextField()));
        panel.add(Box.createVerticalStrut(20));
        panel.add(createFormField("Username", usernameField = createTextField()));
        panel.add(Box.createVerticalStrut(20));
        panel.add(createFormField("Password", passwordField = createPasswordField()));
        panel.add(Box.createVerticalStrut(40));
        
        // Action buttons
        panel.add(createActionButtons());
        
        return panel;
    }
    
    /**
     * Creates a labeled form field for 2160x1440
     */
    private JPanel createFormField(String labelText, JComponent field)
    {
        JPanel fieldPanel = new JPanel();
        fieldPanel.setLayout(new BoxLayout(fieldPanel, BoxLayout.Y_AXIS));
        fieldPanel.setBackground(Color.WHITE);
        fieldPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
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
     * Creates the action buttons with consistent theming
     */
    private JPanel createActionButtons()
    {
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        buttonsPanel.setBackground(Color.WHITE);
        buttonsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
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
            BorderFactory.createEmptyBorder(40, 35, 40, 35)
        ));
        
        JLabel title = new JLabel("Account Requirements");
        title.setFont(new Font("Helvetica Neue", Font.BOLD, 22));
        title.setForeground(new Color(28, 28, 30));
        title.setAlignmentX(Component.LEFT_ALIGNMENT);
        title.setBorder(BorderFactory.createEmptyBorder(0, 0, 25, 0));
        panel.add(title);
        
        // Requirements content
        panel.add(createRequirementItem(
            "Username must contain '_' and be 5 characters or less"));
        panel.add(Box.createVerticalStrut(15));
        
        panel.add(createRequirementItem(
            "Password must be at least 8 characters"));
        panel.add(Box.createVerticalStrut(15));
        
        panel.add(createRequirementItem(
            "Include uppercase letter, number, and special character"));
        
        // Add flexible space at bottom
        panel.add(Box.createVerticalGlue());
        
        return panel;
    }
    
    /**
     * Creates a requirement item with bullet point for 2160x1440
     */
    private JPanel createRequirementItem(String text)
    {
        JPanel itemPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        itemPanel.setBackground(new Color(245, 245, 247));
        
        JLabel bullet = new JLabel("•");
        bullet.setFont(new Font("Helvetica Neue", Font.BOLD, 16));
        bullet.setForeground(new Color(0, 122, 255));
        
        JTextArea textArea = new JTextArea(text);
        textArea.setFont(new Font("Helvetica Neue", Font.PLAIN, 15));
        textArea.setForeground(new Color(100, 100, 105));
        textArea.setBackground(new Color(245, 245, 247));
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        
        itemPanel.add(bullet);
        itemPanel.add(textArea);
        
        return itemPanel;
    }
    
    /**
     * Creates a styled text field for 2160x1440
     */
    private JTextField createTextField()
    {
        JTextField field = new JTextField();
        field.setFont(new Font("Helvetica Neue", Font.PLAIN, 17));
        field.setMaximumSize(new Dimension(1000, 50));
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(210, 210, 215), 1),
            BorderFactory.createEmptyBorder(12, 15, 12, 15)
        ));
        field.setBackground(Color.WHITE);
        return field;
    }
    
    /**
     * Creates a styled password field for 2160x1440
     */
    private JPasswordField createPasswordField()
    {
        JPasswordField field = new JPasswordField();
        field.setFont(new Font("Helvetica Neue", Font.PLAIN, 17));
        field.setMaximumSize(new Dimension(1000, 50));
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(210, 210, 215), 1),
            BorderFactory.createEmptyBorder(12, 15, 12, 15)
        ));
        field.setBackground(Color.WHITE);
        return field;
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
        button.setBorder(BorderFactory.createEmptyBorder(14, 35, 14, 35));
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