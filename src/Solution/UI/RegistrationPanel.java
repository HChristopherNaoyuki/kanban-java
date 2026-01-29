package Solution.UI;

import Solution.Logic.AuthManager;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.LineBorder;

/**
 * Clean, minimal registration interface optimized for 900x600 window
 * Features clear form presentation with visible action buttons
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
     * Creates the main content panel optimized for 900x600 window
     */
    private JPanel createContentPanel()
    {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(248, 248, 248));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        
        // Main form panel
        JPanel formPanel = createFormPanel();
        panel.add(formPanel, gbc);
        
        return panel;
    }
    
    /**
     * Creates the form panel with clearly visible buttons
     */
    private JPanel createFormPanel()
    {
        // Main form panel with BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout(0, 20));
        mainPanel.setBackground(new Color(248, 248, 248));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        
        // Title section at the top
        JPanel titlePanel = createTitlePanel();
        mainPanel.add(titlePanel, BorderLayout.NORTH);
        
        // Form fields in the center
        JPanel fieldsPanel = createFieldsPanel();
        mainPanel.add(fieldsPanel, BorderLayout.CENTER);
        
        // Requirements info
        JPanel requirementsPanel = createRequirementsPanel();
        mainPanel.add(requirementsPanel, BorderLayout.EAST);
        
        // Buttons at the bottom - always visible
        JPanel buttonsPanel = createButtonsPanel();
        mainPanel.add(buttonsPanel, BorderLayout.SOUTH);
        
        return mainPanel;
    }
    
    /**
     * Creates the title panel with registration heading
     */
    private JPanel createTitlePanel()
    {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(248, 248, 248));
        panel.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        
        JLabel title = new JLabel("Create Account");
        title.setFont(new Font("Helvetica Neue", Font.BOLD, 28));
        title.setForeground(new Color(28, 28, 30));
        title.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JLabel subtitle = new JLabel("Join Task Manager");
        subtitle.setFont(new Font("Helvetica Neue", Font.PLAIN, 16));
        subtitle.setForeground(new Color(140, 140, 145));
        subtitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        subtitle.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
        
        panel.add(title);
        panel.add(subtitle);
        
        return panel;
    }
    
    /**
     * Creates the form fields panel
     */
    private JPanel createFieldsPanel()
    {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(248, 248, 248));
        panel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets = new Insets(0, 0, 15, 0);
        
        // First Name field
        panel.add(createFormField("First Name", firstNameField = createTextField()), gbc);
        
        gbc.gridy++;
        gbc.insets = new Insets(0, 0, 15, 0);
        
        // Last Name field
        panel.add(createFormField("Last Name", lastNameField = createTextField()), gbc);
        
        gbc.gridy++;
        gbc.insets = new Insets(0, 0, 15, 0);
        
        // Username field
        panel.add(createFormField("Username", usernameField = createTextField()), gbc);
        
        gbc.gridy++;
        gbc.insets = new Insets(0, 0, 0, 0);
        
        // Password field
        panel.add(createFormField("Password", passwordField = createPasswordField()), gbc);
        
        return panel;
    }
    
    /**
     * Creates a single form field with label
     */
    private JPanel createFormField(String labelText, JComponent field)
    {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(248, 248, 248));
        panel.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.setMaximumSize(new Dimension(300, 70));
        
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
        label.setForeground(new Color(100, 100, 105));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        field.setAlignmentX(Component.LEFT_ALIGNMENT);
        field.setMaximumSize(new Dimension(300, 40));
        
        panel.add(label);
        panel.add(Box.createVerticalStrut(6));
        panel.add(field);
        
        return panel;
    }
    
    /**
     * Creates the buttons panel - FIXED to be always visible
     */
    private JPanel createButtonsPanel()
    {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        panel.setBackground(new Color(248, 248, 248));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        
        // Cancel button
        RoundedButton cancelButton = new RoundedButton("Cancel");
        styleSecondaryButton(cancelButton);
        cancelButton.addActionListener(e -> frame.showLoginPanel());
        
        // Create Account button
        RoundedButton createButton = new RoundedButton("Create Account");
        stylePrimaryButton(createButton);
        createButton.addActionListener(e -> attemptRegistration());
        
        panel.add(cancelButton);
        panel.add(createButton);
        
        return panel;
    }
    
    /**
     * Creates the requirements panel
     */
    private JPanel createRequirementsPanel()
    {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(245, 245, 247));
        panel.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(new Color(220, 220, 225), 1, true),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        
        JTextArea requirementsText = new JTextArea(
            "Password Security:\n" +
            "- Minimum 8 characters\n" +
            "- At least one uppercase letter (A-Z)\n" +
            "- At least one number (0-9)\n" +
            "- At least one special character (!@#$%^&*()_+-=[{}]:;.<>?)\n\n" +
            "Personal Information:\n" +
            "- First and last name are required\n" +
            "- Use your real, legal name\n" +
            "- Names will be displayed in your profile"
        );
        
        requirementsText.setFont(new Font("Helvetica Neue", Font.PLAIN, 12));
        requirementsText.setForeground(new Color(100, 100, 105));
        requirementsText.setBackground(new Color(245, 245, 247));
        requirementsText.setEditable(false);
        requirementsText.setLineWrap(true);
        requirementsText.setWrapStyleWord(true);
        requirementsText.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        panel.add(requirementsText, BorderLayout.CENTER);
        
        return panel;
    }
    
    /**
     * Creates a styled text field
     */
    private JTextField createTextField()
    {
        JTextField field = new JTextField();
        field.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
        field.setMaximumSize(new Dimension(300, 40));
        field.setPreferredSize(new Dimension(300, 40));
        field.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(new Color(210, 210, 215), 1, true),
            BorderFactory.createEmptyBorder(10, 12, 10, 12)
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
        field.setMaximumSize(new Dimension(300, 40));
        field.setPreferredSize(new Dimension(300, 40));
        field.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(new Color(210, 210, 215), 1, true),
            BorderFactory.createEmptyBorder(10, 12, 10, 12)
        ));
        field.setBackground(Color.WHITE);
        return field;
    }
    
    /**
     * Styles the primary button (Create Account)
     */
    private void stylePrimaryButton(JButton button)
    {
        button.setFont(new Font("Helvetica Neue", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(0, 122, 255));
        button.setBorder(BorderFactory.createEmptyBorder(12, 30, 12, 30));
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        // Set explicit sizes for visibility
        button.setPreferredSize(new Dimension(140, 45));
        button.setMinimumSize(new Dimension(140, 45));
        
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
     * Styles the secondary button (Cancel)
     */
    private void styleSecondaryButton(JButton button)
    {
        button.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
        button.setForeground(new Color(100, 100, 105));
        button.setBackground(new Color(240, 240, 242));
        button.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(new Color(210, 210, 215), 1, true),
            BorderFactory.createEmptyBorder(10, 25, 10, 25)
        ));
        button.setFocusPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        // Set explicit sizes for visibility
        button.setPreferredSize(new Dimension(100, 45));
        button.setMinimumSize(new Dimension(100, 45));
        
        // Hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter()
        {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt)
            {
                button.setBackground(new Color(230, 230, 232));
            }
            
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt)
            {
                button.setBackground(new Color(240, 240, 242));
            }
        });
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