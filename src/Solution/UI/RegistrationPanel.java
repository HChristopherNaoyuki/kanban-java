package Solution.UI;

import Solution.Logic.AuthManager;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.LineBorder;

/**
 * Full screen registration interface
 * Features expanded layout with form on left and detailed requirements on right
 * Polished with improved button visibility, consistent styling, and better layout management
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
        panel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(0, 0, 0, 20); // Right margin between panels
        
        // Left panel: Form (45% width)
        JPanel formPanel = createFormPanel();
        gbc.weightx = 0.45;
        panel.add(formPanel, gbc);
        
        gbc.gridx = 1;
        gbc.insets = new Insets(0, 20, 0, 0); // Left margin between panels
        
        // Right panel: Requirements (55% width)
        JPanel requirementsPanel = createRequirementsPanel();
        gbc.weightx = 0.55;
        panel.add(requirementsPanel, gbc);
        
        return panel;
    }
    
    /**
     * Creates the form panel (left side, 45% of screen) with soft edges
     * Improved layout management for better button visibility
     */
    private JPanel createFormPanel()
    {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout()); // Changed to BorderLayout for better control
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(new Color(220, 220, 225), 1, true),
            BorderFactory.createEmptyBorder(40, 40, 40, 40)
        ));
        
        // Main content container
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(Color.WHITE);
        
        // Title section
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        titlePanel.setBackground(Color.WHITE);
        titlePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JLabel title = new JLabel("Create Account", SwingConstants.LEFT);
        title.setFont(new Font("Helvetica Neue", Font.BOLD, 32));
        title.setForeground(new Color(28, 28, 30));
        title.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JLabel subtitle = new JLabel("Join Task Manager", SwingConstants.LEFT);
        subtitle.setFont(new Font("Helvetica Neue", Font.PLAIN, 18));
        subtitle.setForeground(new Color(140, 140, 145));
        subtitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        subtitle.setBorder(BorderFactory.createEmptyBorder(5, 0, 30, 0));
        
        titlePanel.add(title);
        titlePanel.add(subtitle);
        
        // Form fields container
        JPanel fieldsPanel = new JPanel();
        fieldsPanel.setLayout(new BoxLayout(fieldsPanel, BoxLayout.Y_AXIS));
        fieldsPanel.setBackground(Color.WHITE);
        fieldsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        // Form fields
        fieldsPanel.add(createFormField("First Name", firstNameField = createTextField()));
        fieldsPanel.add(Box.createVerticalStrut(20));
        fieldsPanel.add(createFormField("Last Name", lastNameField = createTextField()));
        fieldsPanel.add(Box.createVerticalStrut(20));
        fieldsPanel.add(createFormField("Username", usernameField = createTextField()));
        fieldsPanel.add(Box.createVerticalStrut(20));
        fieldsPanel.add(createFormField("Password", passwordField = createPasswordField()));
        
        // Button panel - Now properly sized and positioned
        JPanel buttonPanel = createButtonPanel();
        buttonPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        buttonPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
        
        // Add all components to content panel
        contentPanel.add(titlePanel);
        contentPanel.add(fieldsPanel);
        contentPanel.add(Box.createVerticalStrut(40));
        contentPanel.add(buttonPanel);
        contentPanel.add(Box.createVerticalGlue());
        
        // Add content panel to main panel with proper constraints
        panel.add(contentPanel, BorderLayout.CENTER);
        
        return panel;
    }
    
    /**
     * Creates a labeled form field for full screen
     * Fixed to prevent cutting off content
     */
    private JPanel createFormField(String labelText, JComponent field)
    {
        JPanel fieldPanel = new JPanel();
        fieldPanel.setLayout(new BoxLayout(fieldPanel, BoxLayout.Y_AXIS));
        fieldPanel.setBackground(Color.WHITE);
        fieldPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        fieldPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 85));
        
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Helvetica Neue", Font.PLAIN, 16));
        label.setForeground(new Color(100, 100, 105));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        field.setAlignmentX(Component.LEFT_ALIGNMENT);
        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
        
        fieldPanel.add(label);
        fieldPanel.add(Box.createVerticalStrut(8));
        fieldPanel.add(field);
        
        return fieldPanel;
    }
    
    /**
     * Creates the action buttons with consistent theming
     * Fixed visibility and sizing issues
     */
    private JPanel createButtonPanel()
    {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 0));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        // Create Cancel button
        RoundedButton cancelButton = new RoundedButton("Cancel");
        styleSecondaryButton(cancelButton);
        cancelButton.addActionListener(e -> frame.showLoginPanel());
        
        // Create Account button
        RoundedButton createButton = new RoundedButton("Create Account");
        stylePrimaryButton(createButton);
        createButton.addActionListener(e -> attemptRegistration());
        
        buttonPanel.add(cancelButton);
        buttonPanel.add(createButton);
        
        return buttonPanel;
    }
    
    /**
     * Creates the requirements panel (right side, 55% of screen) with soft edges
     */
    private JPanel createRequirementsPanel()
    {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(245, 245, 247));
        panel.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(new Color(220, 220, 225), 1, true),
            BorderFactory.createEmptyBorder(40, 40, 40, 40)
        ));
        
        // Create scrollable content area
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(new Color(245, 245, 247));
        
        // Title
        JLabel title = new JLabel("Account Requirements", SwingConstants.LEFT);
        title.setFont(new Font("Helvetica Neue", Font.BOLD, 28));
        title.setForeground(new Color(28, 28, 30));
        title.setAlignmentX(Component.LEFT_ALIGNMENT);
        title.setBorder(BorderFactory.createEmptyBorder(0, 0, 25, 0));
        contentPanel.add(title);
        
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
        contentPanel.add(introText);
        
        // Requirements sections
        contentPanel.add(createRequirementSection(
            "Username Requirements",
            new String[]{
                "Must contain an underscore (_) character",
                "Maximum 5 characters in length",
                "No spaces allowed",
                "Case-sensitive (usernames are exact match)",
                "Cannot be changed after account creation"
            }
        ));
        contentPanel.add(Box.createVerticalStrut(20));
        
        contentPanel.add(createRequirementSection(
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
        contentPanel.add(Box.createVerticalStrut(20));
        
        contentPanel.add(createRequirementSection(
            "Personal Information",
            new String[]{
                "First and last name are required",
                "Use your real, legal name",
                "Names will be displayed in your profile",
                "Privacy: Your name is visible to other users",
                "Names can be updated in account settings"
            }
        ));
        
        // Add scroll pane for requirements content
        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        
        panel.add(scrollPane, BorderLayout.CENTER);
        
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
        title.setBorder(BorderFactory.createEmptyBorder(0, 0, 12, 0));
        sectionPanel.add(title);
        
        // Requirements list
        for (String requirement : requirements)
        {
            JPanel itemPanel = createRequirementItem(requirement);
            sectionPanel.add(itemPanel);
            sectionPanel.add(Box.createVerticalStrut(6));
        }
        
        return sectionPanel;
    }
    
    /**
     * Creates a single requirement item with bullet point
     */
    private JPanel createRequirementItem(String text)
    {
        JPanel itemPanel = new JPanel(new BorderLayout(10, 0));
        itemPanel.setBackground(new Color(245, 245, 247));
        itemPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JLabel bullet = new JLabel("•");
        bullet.setFont(new Font("Helvetica Neue", Font.BOLD, 16));
        bullet.setForeground(new Color(0, 122, 255));
        bullet.setPreferredSize(new Dimension(20, 20));
        
        JTextArea textArea = new JTextArea(text);
        textArea.setFont(new Font("Helvetica Neue", Font.PLAIN, 15));
        textArea.setForeground(new Color(100, 100, 105));
        textArea.setBackground(new Color(245, 245, 247));
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        
        itemPanel.add(bullet, BorderLayout.WEST);
        itemPanel.add(textArea, BorderLayout.CENTER);
        
        return itemPanel;
    }
    
    /**
     * Creates a styled text field for full screen with soft edges
     */
    private JTextField createTextField()
    {
        JTextField field = new JTextField();
        field.setFont(new Font("Helvetica Neue", Font.PLAIN, 16));
        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
        field.setPreferredSize(new Dimension(400, 45));
        field.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(new Color(210, 210, 215), 1, true),
            BorderFactory.createEmptyBorder(12, 15, 12, 15)
        ));
        field.setBackground(Color.WHITE);
        return field;
    }
    
    /**
     * Creates a styled password field for full screen with soft edges
     */
    private JPasswordField createPasswordField()
    {
        JPasswordField field = new JPasswordField();
        field.setFont(new Font("Helvetica Neue", Font.PLAIN, 16));
        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
        field.setPreferredSize(new Dimension(400, 45));
        field.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(new Color(210, 210, 215), 1, true),
            BorderFactory.createEmptyBorder(12, 15, 12, 15)
        ));
        field.setBackground(Color.WHITE);
        return field;
    }
    
    /**
     * Styles a primary button with consistent theming for full screen
     * Improved sizing and visibility for Create Account button
     */
    private void stylePrimaryButton(JButton button)
    {
        button.setFont(new Font("Helvetica Neue", Font.BOLD, 16));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(0, 122, 255));
        button.setBorder(BorderFactory.createEmptyBorder(14, 35, 14, 35));
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        // Set explicit minimum and preferred sizes for better visibility
        button.setMinimumSize(new Dimension(160, 50));
        button.setPreferredSize(new Dimension(160, 50));
        button.setMaximumSize(new Dimension(160, 50));
        
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
     * Styles a secondary button with consistent theming for full screen
     * Improved sizing and visibility for Cancel button
     */
    private void styleSecondaryButton(JButton button)
    {
        button.setFont(new Font("Helvetica Neue", Font.PLAIN, 15));
        button.setForeground(new Color(100, 100, 105));
        button.setBackground(new Color(240, 240, 242));
        button.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(new Color(210, 210, 215), 1, true),
            BorderFactory.createEmptyBorder(12, 30, 12, 30)
        ));
        button.setFocusPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        // Set explicit minimum and preferred sizes for better visibility
        button.setMinimumSize(new Dimension(120, 50));
        button.setPreferredSize(new Dimension(120, 50));
        button.setMaximumSize(new Dimension(120, 50));
        
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