package Solution.UI;

import Solution.Logic.AuthManager;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.LineBorder;

/**
 * Full screen registration interface
 * Features expanded layout with form on left and detailed requirements on right
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
     * Creates the main content panel optimized for full screen
     */
    private JPanel createContentPanel()
    {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(248, 248, 248));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(40, 60, 40, 30);
        
        // Left panel: Form (40% width)
        JPanel formPanel = createFormPanel();
        gbc.weightx = 0.4;
        panel.add(formPanel, gbc);
        
        gbc.gridx = 1;
        gbc.insets = new Insets(40, 30, 40, 60);
        
        // Right panel: Requirements (60% width)
        JPanel requirementsPanel = createRequirementsPanel();
        gbc.weightx = 0.6;
        panel.add(requirementsPanel, gbc);
        
        return panel;
    }
    
    /**
     * Creates the form panel (left side, 40% of screen) with soft edges
     */
    private JPanel createFormPanel()
    {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(new Color(220, 220, 225), 1, true),
            BorderFactory.createEmptyBorder(50, 50, 50, 50)
        ));
        
        // Title
        JLabel title = new JLabel("Create Account", SwingConstants.LEFT);
        title.setFont(new Font("Helvetica Neue", Font.BOLD, 32));
        title.setForeground(new Color(28, 28, 30));
        title.setAlignmentX(Component.LEFT_ALIGNMENT);
        title.setBorder(BorderFactory.createEmptyBorder(0, 0, 40, 0));
        panel.add(title);
        
        // Subtitle
        JLabel subtitle = new JLabel("Join Task Manager", SwingConstants.LEFT);
        subtitle.setFont(new Font("Helvetica Neue", Font.PLAIN, 18));
        subtitle.setForeground(new Color(140, 140, 145));
        subtitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        subtitle.setBorder(BorderFactory.createEmptyBorder(0, 0, 40, 0));
        panel.add(subtitle);
        
        // Form fields
        panel.add(createFormField("First Name", firstNameField = createTextField()));
        panel.add(Box.createVerticalStrut(25));
        panel.add(createFormField("Last Name", lastNameField = createTextField()));
        panel.add(Box.createVerticalStrut(25));
        panel.add(createFormField("Username", usernameField = createTextField()));
        panel.add(Box.createVerticalStrut(25));
        panel.add(createFormField("Password", passwordField = createPasswordField()));
        panel.add(Box.createVerticalStrut(50));
        
        // Action buttons - Center aligned
        panel.add(createActionButtons());
        
        // Add flexible space at bottom
        panel.add(Box.createVerticalGlue());
        
        return panel;
    }
    
    /**
     * Creates a labeled form field for full screen
     */
    private JPanel createFormField(String labelText, JComponent field)
    {
        JPanel fieldPanel = new JPanel();
        fieldPanel.setLayout(new BoxLayout(fieldPanel, BoxLayout.Y_AXIS));
        fieldPanel.setBackground(Color.WHITE);
        fieldPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        // REMOVED maximum size constraint to prevent cutting off
        fieldPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
        
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Helvetica Neue", Font.PLAIN, 18));
        label.setForeground(new Color(100, 100, 105));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        field.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        fieldPanel.add(label);
        fieldPanel.add(Box.createVerticalStrut(10));
        fieldPanel.add(field);
        
        return fieldPanel;
    }
    
    /**
     * Creates the action buttons with consistent theming
     * Now center-aligned and properly sized
     */
    private JPanel createActionButtons()
    {
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));
        buttonsPanel.setBackground(Color.WHITE);
        buttonsPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Create a sub-panel to center the buttons
        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        centerPanel.setBackground(Color.WHITE);
        
        RoundedButton cancelButton = new RoundedButton("Cancel");
        styleSecondaryButton(cancelButton);
        cancelButton.addActionListener(e -> frame.showLoginPanel());
        
        RoundedButton createButton = new RoundedButton("Create Account");
        stylePrimaryButton(createButton);
        createButton.addActionListener(e -> attemptRegistration());
        
        centerPanel.add(cancelButton);
        centerPanel.add(createButton);
        
        buttonsPanel.add(Box.createHorizontalGlue());
        buttonsPanel.add(centerPanel);
        buttonsPanel.add(Box.createHorizontalGlue());
        
        return buttonsPanel;
    }
    
    /**
     * Creates the requirements panel (right side, 60% of screen) with soft edges
     */
    private JPanel createRequirementsPanel()
    {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(245, 245, 247));
        panel.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(new Color(220, 220, 225), 1, true),
            BorderFactory.createEmptyBorder(50, 50, 50, 50)
        ));
        
        // Title
        JLabel title = new JLabel("Account Requirements & Guidelines", SwingConstants.LEFT);
        title.setFont(new Font("Helvetica Neue", Font.BOLD, 28));
        title.setForeground(new Color(28, 28, 30));
        title.setAlignmentX(Component.LEFT_ALIGNMENT);
        title.setBorder(BorderFactory.createEmptyBorder(0, 0, 40, 0));
        panel.add(title);
        
        // Introduction text
        JTextArea introText = new JTextArea(
            "To ensure security and consistency across the platform, please follow " +
            "these guidelines when creating your account. These requirements help " +
            "protect your account and ensure a smooth experience for all users."
        );
        introText.setFont(new Font("Helvetica Neue", Font.PLAIN, 16));
        introText.setForeground(new Color(100, 100, 105));
        introText.setBackground(new Color(245, 245, 247));
        introText.setEditable(false);
        introText.setLineWrap(true);
        introText.setWrapStyleWord(true);
        introText.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));
        panel.add(introText);
        
        // Requirements sections
        panel.add(createRequirementSection(
            "Username Requirements",
            new String[]{
                "Must contain an underscore (_) character",
                "Maximum 5 characters in length",
                "No spaces allowed",
                "Case-sensitive (usernames are exact match)",
                "Cannot be changed after account creation"
            }
        ));
        panel.add(Box.createVerticalStrut(25));
        panel.add(createRequirementSection(
            "Password Security",
            new String[]{
                "Minimum 8 characters in length",
                "At least one uppercase letter (A-Z)",
                "At least one number (0-9)",
                "At least one special character (!@#$%^&*()_+-=[]{}|;:,.<>?)",
                "Avoid common passwords and personal information",
                "Recommended: Use a passphrase or password manager"
            }
        ));
        panel.add(Box.createVerticalStrut(25));
        panel.add(createRequirementSection(
            "Personal Information",
            new String[]{
                "First and last name are required",
                "Use your real, legal name",
                "Names will be displayed in your profile",
                "Privacy: Your name is visible to other users",
                "Names can be updated in account settings"
            }
        ));
        panel.add(Box.createVerticalStrut(25));
        panel.add(createRequirementSection(
            "Account Guidelines",
            new String[]{
                "One account per person",
                "Keep your credentials secure",
                "Contact support for account recovery",
                "Terms of service apply to all accounts",
                "Regular security updates are recommended"
            }
        ));
        
        // Help text at bottom
        panel.add(Box.createVerticalStrut(40));
        JTextArea helpText = new JTextArea(
            "Need help? Contact our support team at support@taskmanager.com " +
            "or visit our help center for additional assistance."
        );
        helpText.setFont(new Font("Helvetica Neue", Font.ITALIC, 14));
        helpText.setForeground(new Color(140, 140, 145));
        helpText.setBackground(new Color(245, 245, 247));
        helpText.setEditable(false);
        helpText.setLineWrap(true);
        helpText.setWrapStyleWord(true);
        helpText.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        panel.add(helpText);
        
        // Add flexible space at bottom
        panel.add(Box.createVerticalGlue());
        
        return panel;
    }
    
    /**
     * Creates a requirement section with title and bullet points
     */
    private JPanel createRequirementSection(String titleText, String[] requirements)
    {
        JPanel sectionPanel = new JPanel();
        sectionPanel.setLayout(new BoxLayout(sectionPanel, BoxLayout.Y_AXIS));
        sectionPanel.setBackground(new Color(245, 245, 247));
        sectionPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        // Section title
        JLabel title = new JLabel(titleText);
        title.setFont(new Font("Helvetica Neue", Font.BOLD, 20));
        title.setForeground(new Color(28, 28, 30));
        title.setAlignmentX(Component.LEFT_ALIGNMENT);
        title.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));
        sectionPanel.add(title);
        
        // Requirements list
        for (String requirement : requirements)
        {
            JPanel itemPanel = createRequirementItem(requirement);
            sectionPanel.add(itemPanel);
            sectionPanel.add(Box.createVerticalStrut(8));
        }
        
        return sectionPanel;
    }
    
    /**
     * Creates a single requirement item with bullet point
     */
    private JPanel createRequirementItem(String text)
    {
        JPanel itemPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        itemPanel.setBackground(new Color(245, 245, 247));
        itemPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JLabel bullet = new JLabel("•");
        bullet.setFont(new Font("Helvetica Neue", Font.BOLD, 18));
        bullet.setForeground(new Color(0, 122, 255));
        
        JTextArea textArea = new JTextArea(text);
        textArea.setFont(new Font("Helvetica Neue", Font.PLAIN, 16));
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
     * Creates a styled text field for full screen with soft edges
     * Removed maximum width constraint to allow full expansion
     */
    private JTextField createTextField()
    {
        JTextField field = new JTextField();
        field.setFont(new Font("Helvetica Neue", Font.PLAIN, 18));
        // Allow field to expand horizontally
        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 55));
        field.setPreferredSize(new Dimension(400, 55));
        field.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(new Color(210, 210, 215), 1, true),
            BorderFactory.createEmptyBorder(15, 18, 15, 18)
        ));
        field.setBackground(Color.WHITE);
        return field;
    }
    
    /**
     * Creates a styled password field for full screen with soft edges
     * Removed maximum width constraint to allow full expansion
     */
    private JPasswordField createPasswordField()
    {
        JPasswordField field = new JPasswordField();
        field.setFont(new Font("Helvetica Neue", Font.PLAIN, 18));
        // Allow field to expand horizontally
        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 55));
        field.setPreferredSize(new Dimension(400, 55));
        field.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(new Color(210, 210, 215), 1, true),
            BorderFactory.createEmptyBorder(15, 18, 15, 18)
        ));
        field.setBackground(Color.WHITE);
        return field;
    }
    
    /**
     * Styles a primary button with consistent theming for full screen
     * Ensures proper visibility and sizing
     */
    private void stylePrimaryButton(JButton button)
    {
        button.setFont(new Font("Helvetica Neue", Font.BOLD, 18));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(0, 122, 255));
        button.setBorder(BorderFactory.createEmptyBorder(16, 45, 16, 45));
        button.setBorderPainted(false);  // No border for primary buttons
        // Set minimum and preferred size for better visibility
        button.setMinimumSize(new Dimension(180, 55));
        button.setPreferredSize(new Dimension(180, 55));
        
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
     * Styles a secondary button with consistent theming for full screen with soft edges
     * Ensures proper visibility and sizing
     */
    private void styleSecondaryButton(JButton button)
    {
        button.setFont(new Font("Helvetica Neue", Font.PLAIN, 17));
        button.setForeground(new Color(100, 100, 105));
        button.setBackground(new Color(240, 240, 242));
        // Set minimum and preferred size for better visibility
        button.setMinimumSize(new Dimension(120, 55));
        button.setPreferredSize(new Dimension(120, 55));
        button.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(new Color(210, 210, 215), 1, true),
            BorderFactory.createEmptyBorder(14, 40, 14, 40)
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