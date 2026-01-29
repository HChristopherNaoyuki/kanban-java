package Solution.UI;

import Solution.Logic.AuthManager;
import javax.swing.*;
import java.awt.*;

/**
 * Registration interface with split layout: form on left, requirements on right
 * Provides clear visual separation and guidance during account creation
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
        
        // Create main content with split layout
        JPanel contentPanel = createSplitContentPanel();
        add(contentPanel, BorderLayout.CENTER);
    }
    
    /**
     * Creates the split content panel with form on left and requirements on right
     */
    private JPanel createSplitContentPanel()
    {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(248, 248, 248));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        
        // Left side: Form
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 0, 20);
        JPanel formPanel = createFormSection();
        panel.add(formPanel, gbc);
        
        // Right side: Requirements
        gbc.gridx = 1;
        gbc.insets = new Insets(0, 20, 0, 0);
        JPanel requirementsPanel = createRequirementsSection();
        panel.add(requirementsPanel, gbc);
        
        return panel;
    }
    
    /**
     * Creates the form section (left side)
     */
    private JPanel createFormSection()
    {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 220, 225), 1),
            BorderFactory.createEmptyBorder(30, 30, 30, 30)
        ));
        panel.setMaximumSize(new Dimension(350, 600));
        
        // Header
        panel.add(createFormHeader());
        panel.add(Box.createVerticalStrut(30));
        
        // Form fields
        panel.add(createFormFields());
        panel.add(Box.createVerticalStrut(30));
        
        // Action buttons
        panel.add(createActionButtons());
        
        return panel;
    }
    
    /**
     * Creates the form header
     */
    private JPanel createFormHeader()
    {
        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));
        header.setBackground(Color.WHITE);
        header.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JLabel title = new JLabel("Create Account", SwingConstants.LEFT);
        title.setFont(new Font("Helvetica Neue", Font.BOLD, 24));
        title.setForeground(new Color(28, 28, 30));
        title.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JLabel subtitle = new JLabel("Join Task Manager", SwingConstants.LEFT);
        subtitle.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
        subtitle.setForeground(new Color(140, 140, 145));
        subtitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        subtitle.setBorder(BorderFactory.createEmptyBorder(4, 0, 0, 0));
        
        header.add(title);
        header.add(subtitle);
        
        return header;
    }
    
    /**
     * Creates all form fields
     */
    private JPanel createFormFields()
    {
        JPanel fieldsPanel = new JPanel();
        fieldsPanel.setLayout(new BoxLayout(fieldsPanel, BoxLayout.Y_AXIS));
        fieldsPanel.setBackground(Color.WHITE);
        fieldsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        // Personal information
        fieldsPanel.add(createFormField("First Name", firstNameField = createTextField()));
        fieldsPanel.add(Box.createVerticalStrut(15));
        fieldsPanel.add(createFormField("Last Name", lastNameField = createTextField()));
        fieldsPanel.add(Box.createVerticalStrut(15));
        
        // Account credentials
        fieldsPanel.add(createFormField("Username", usernameField = createTextField()));
        fieldsPanel.add(Box.createVerticalStrut(15));
        fieldsPanel.add(createFormField("Password", passwordField = createPasswordField()));
        
        return fieldsPanel;
    }
    
    /**
     * Creates a single form field with label
     */
    private JPanel createFormField(String labelText, JComponent field)
    {
        JPanel fieldPanel = new JPanel();
        fieldPanel.setLayout(new BoxLayout(fieldPanel, BoxLayout.Y_AXIS));
        fieldPanel.setBackground(Color.WHITE);
        fieldPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        fieldPanel.setMaximumSize(new Dimension(300, 70));
        
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
        label.setForeground(new Color(100, 100, 105));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        field.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        fieldPanel.add(label);
        fieldPanel.add(Box.createVerticalStrut(6));
        fieldPanel.add(field);
        
        return fieldPanel;
    }
    
    /**
     * Creates the action buttons
     */
    private JPanel createActionButtons()
    {
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 0));
        buttonsPanel.setBackground(Color.WHITE);
        buttonsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JButton cancelButton = createSecondaryButton("Cancel");
        cancelButton.addActionListener(e -> frame.showLoginPanel());
        
        JButton createButton = createPrimaryButton("Create Account");
        createButton.addActionListener(e -> attemptRegistration());
        
        buttonsPanel.add(cancelButton);
        buttonsPanel.add(createButton);
        
        return buttonsPanel;
    }
    
    /**
     * Creates the requirements section (right side)
     */
    private JPanel createRequirementsSection()
    {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(245, 245, 247));
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 220, 225), 1),
            BorderFactory.createEmptyBorder(30, 25, 30, 25)
        ));
        panel.setMaximumSize(new Dimension(300, 600));
        
        // Title
        JLabel title = new JLabel("Account Requirements", SwingConstants.LEFT);
        title.setFont(new Font("Helvetica Neue", Font.BOLD, 18));
        title.setForeground(new Color(28, 28, 30));
        title.setAlignmentX(Component.LEFT_ALIGNMENT);
        title.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        panel.add(title);
        
        // Requirements list
        panel.add(createRequirementItem("Username Requirements", 
            "• Must contain an underscore (_)\n" +
            "• Maximum 5 characters\n" +
            "• No spaces allowed"));
        
        panel.add(Box.createVerticalStrut(20));
        
        panel.add(createRequirementItem("Password Requirements", 
            "• Minimum 8 characters\n" +
            "• At least one uppercase letter\n" +
            "• At least one number (0-9)\n" +
            "• At least one special character\n" +
            "  (!@#$%^&*()_+-=[]{}|;:,.<>?)"));
        
        panel.add(Box.createVerticalStrut(20));
        
        panel.add(createRequirementItem("Personal Information", 
            "• First and last name required\n" +
            "• Use your real name\n" +
            "• Names will be displayed in the app"));
        
        // Spacer to push content to top
        panel.add(Box.createVerticalGlue());
        
        return panel;
    }
    
    /**
     * Creates a requirement item with icon and text
     */
    private JPanel createRequirementItem(String title, String content)
    {
        JPanel itemPanel = new JPanel();
        itemPanel.setLayout(new BoxLayout(itemPanel, BoxLayout.Y_AXIS));
        itemPanel.setBackground(new Color(245, 245, 247));
        itemPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        // Title with icon
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        titlePanel.setBackground(new Color(245, 245, 247));
        
        JLabel icon = new JLabel("•");
        icon.setFont(new Font("Helvetica Neue", Font.BOLD, 16));
        icon.setForeground(new Color(0, 122, 255));
        icon.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 8));
        
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Helvetica Neue", Font.BOLD, 14));
        titleLabel.setForeground(new Color(28, 28, 30));
        
        titlePanel.add(icon);
        titlePanel.add(titleLabel);
        
        // Content
        JTextArea contentArea = new JTextArea(content);
        contentArea.setFont(new Font("Helvetica Neue", Font.PLAIN, 12));
        contentArea.setForeground(new Color(100, 100, 105));
        contentArea.setBackground(new Color(245, 245, 247));
        contentArea.setEditable(false);
        contentArea.setLineWrap(true);
        contentArea.setWrapStyleWord(true);
        contentArea.setBorder(BorderFactory.createEmptyBorder(5, 20, 0, 0));
        
        itemPanel.add(titlePanel);
        itemPanel.add(contentArea);
        
        return itemPanel;
    }
    
    /**
     * Creates a styled text field
     */
    private JTextField createTextField()
    {
        JTextField field = new JTextField();
        field.setFont(new Font("Helvetica Neue", Font.PLAIN, 15));
        field.setMaximumSize(new Dimension(280, 38));
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(210, 210, 215), 1),
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
        field.setFont(new Font("Helvetica Neue", Font.PLAIN, 15));
        field.setMaximumSize(new Dimension(280, 38));
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(210, 210, 215), 1),
            BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        field.setBackground(Color.WHITE);
        return field;
    }
    
    /**
     * Creates a primary action button
     */
    private JButton createPrimaryButton(String text)
    {
        JButton button = new JButton(text);
        button.setFont(new Font("Helvetica Neue", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(0, 122, 255));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));
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
        
        return button;
    }
    
    /**
     * Creates a secondary action button
     */
    private JButton createSecondaryButton(String text)
    {
        JButton button = new JButton(text);
        button.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
        button.setForeground(new Color(100, 100, 105));
        button.setBackground(new Color(240, 240, 242));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(210, 210, 215), 1),
            BorderFactory.createEmptyBorder(9, 22, 9, 22)
        ));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        return button;
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