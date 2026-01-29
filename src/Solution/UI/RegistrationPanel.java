package Solution.UI;

import Solution.Logic.AuthManager;
import javax.swing.*;
import java.awt.*;

/**
 * Clean registration interface with structured form layout
 * Provides clear guidance and validation feedback
 */
public class RegistrationPanel extends JPanel
{
    private final AuthFrame frame;
    private final AuthManager authManager;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField firstNameField;
    private JTextField lastNameField;
    
    /**
     * Constructs the registration panel
     */
    public RegistrationPanel(AuthFrame frame, AuthManager authManager)
    {
        this.frame = frame;
        this.authManager = authManager;
        
        setLayout(new BorderLayout());
        setBackground(UIManager.getColor("Panel.background"));
        
        // Create content panel inside scroll pane
        JPanel contentPanel = createContentPanel();
        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        
        add(scrollPane, BorderLayout.CENTER);
    }
    
    /**
     * Creates the main content panel
     */
    private JPanel createContentPanel()
    {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(UIManager.getColor("Panel.background"));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));
        
        // Header section
        panel.add(createHeaderSection());
        panel.add(Box.createVerticalStrut(30));
        
        // Form section
        panel.add(createFormSection());
        panel.add(Box.createVerticalStrut(20));
        
        // Requirements section
        panel.add(createRequirementsSection());
        panel.add(Box.createVerticalStrut(30));
        
        // Action buttons section
        panel.add(createActionSection());
        
        return panel;
    }
    
    /**
     * Creates the header section with title
     */
    private JPanel createHeaderSection()
    {
        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));
        header.setBackground(UIManager.getColor("Panel.background"));
        header.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel title = new JLabel("Create Account", SwingConstants.CENTER);
        title.setFont(new Font("Helvetica Neue", Font.BOLD, 24));
        title.setForeground(new Color(28, 28, 28));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel subtitle = new JLabel("Join Task Manager", SwingConstants.CENTER);
        subtitle.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
        subtitle.setForeground(new Color(120, 120, 120));
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        subtitle.setBorder(BorderFactory.createEmptyBorder(4, 0, 0, 0));
        
        header.add(title);
        header.add(subtitle);
        
        return header;
    }
    
    /**
     * Creates the form section with input fields
     */
    private JPanel createFormSection()
    {
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBackground(UIManager.getColor("Panel.background"));
        formPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Personal information
        formPanel.add(createFormField("First Name", firstNameField = createTextField()));
        formPanel.add(Box.createVerticalStrut(12));
        formPanel.add(createFormField("Last Name", lastNameField = createTextField()));
        formPanel.add(Box.createVerticalStrut(12));
        
        // Account credentials
        formPanel.add(createFormField("Username", usernameField = createTextField()));
        formPanel.add(Box.createVerticalStrut(12));
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
        fieldPanel.setBackground(UIManager.getColor("Panel.background"));
        fieldPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
        label.setForeground(new Color(80, 80, 80));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        fieldPanel.add(label);
        fieldPanel.add(Box.createVerticalStrut(6));
        fieldPanel.add(field);
        
        return fieldPanel;
    }
    
    /**
     * Creates a styled text field
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
     * Creates a styled password field
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
     * Creates the requirements information section
     */
    private JPanel createRequirementsSection()
    {
        JPanel reqPanel = new JPanel();
        reqPanel.setLayout(new BoxLayout(reqPanel, BoxLayout.Y_AXIS));
        reqPanel.setBackground(UIManager.getColor("Panel.background"));
        reqPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        reqPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(220, 220, 220)),
            BorderFactory.createEmptyBorder(12, 0, 0, 0)
        ));
        
        JLabel title = new JLabel("Account Requirements");
        title.setFont(new Font("Helvetica Neue", Font.BOLD, 13));
        title.setForeground(new Color(80, 80, 80));
        title.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JTextArea requirements = new JTextArea(
            "• Username must contain '_' and be 5 characters or less\n" +
            "• Password must be at least 8 characters\n" +
            "• Include uppercase letter, number, and special character"
        );
        requirements.setFont(new Font("Helvetica Neue", Font.PLAIN, 12));
        requirements.setForeground(new Color(120, 120, 120));
        requirements.setBackground(UIManager.getColor("Panel.background"));
        requirements.setEditable(false);
        requirements.setLineWrap(true);
        requirements.setWrapStyleWord(true);
        requirements.setBorder(BorderFactory.createEmptyBorder(6, 0, 0, 0));
        
        reqPanel.add(title);
        reqPanel.add(Box.createVerticalStrut(4));
        reqPanel.add(requirements);
        
        return reqPanel;
    }
    
    /**
     * Creates the action buttons section
     */
    private JPanel createActionSection()
    {
        JPanel actionPanel = new JPanel();
        actionPanel.setLayout(new BoxLayout(actionPanel, BoxLayout.Y_AXIS));
        actionPanel.setBackground(UIManager.getColor("Panel.background"));
        actionPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Primary action button
        JButton createButton = createPrimaryButton("Create Account");
        createButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        createButton.addActionListener(e -> attemptRegistration());
        actionPanel.add(createButton);
        
        actionPanel.add(Box.createVerticalStrut(12));
        
        // Secondary action button
        JButton cancelButton = createSecondaryButton("Cancel");
        cancelButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        cancelButton.addActionListener(e -> frame.showLoginPanel());
        actionPanel.add(cancelButton);
        
        return actionPanel;
    }
    
    /**
     * Creates a primary action button
     */
    private JButton createPrimaryButton(String text)
    {
        JButton button = new JButton(text);
        button.setFont(new Font("Helvetica Neue", Font.BOLD, 15));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(0, 122, 255));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(12, 40, 12, 40));
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
        
        return button;
    }
    
    /**
     * Creates a secondary action button
     */
    private JButton createSecondaryButton(String text)
    {
        JButton button = new JButton(text);
        button.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
        button.setForeground(new Color(80, 80, 80));
        button.setBackground(new Color(240, 240, 240));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
            BorderFactory.createEmptyBorder(10, 30, 10, 30)
        ));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        return button;
    }
    
    /**
     * Attempts to register a new user account
     */
    private void attemptRegistration()
    {
        // Get input values
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();
        String firstName = firstNameField.getText().trim();
        String lastName = lastNameField.getText().trim();
        
        // Validate all fields are filled
        if (username.isEmpty() || password.isEmpty() || 
            firstName.isEmpty() || lastName.isEmpty())
        {
            showMessage("Please fill in all fields to create your account.", 
                       "Incomplete Form", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        try
        {
            // Attempt registration
            authManager.registerUser(username, password, firstName, lastName);
            showMessage("Account created successfully!\nYou can now sign in.", 
                       "Registration Complete", JOptionPane.INFORMATION_MESSAGE);
            frame.showLoginPanel();
        }
        catch (IllegalArgumentException ex)
        {
            // Show specific error message
            showMessage(ex.getMessage(), "Registration Failed", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Displays a message dialog
     */
    private void showMessage(String message, String title, int messageType)
    {
        JOptionPane.showMessageDialog(this, message, title, messageType);
    }
}