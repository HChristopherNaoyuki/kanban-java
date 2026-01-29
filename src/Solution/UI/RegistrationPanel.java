package Solution.UI;

import Solution.Logic.AuthManager;
import javax.swing.*;
import java.awt.*;

/**
 * Registration interface with structured form layout
 * Clear progression through account creation steps
 * Fixed: Blank screen issue by properly managing scroll pane
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
        
        // Use BorderLayout instead of GridBagLayout for better control
        setLayout(new BorderLayout());
        setBackground(UIManager.getColor("Panel.background"));
        
        // Create scrollable content with proper constraints
        JScrollPane scrollPane = createScrollableContent();
        add(scrollPane, BorderLayout.CENTER);
    }
    
    /**
     * Creates scrollable content for better form management
     * Fixed: Ensure proper viewport sizing and constraints
     */
    private JScrollPane createScrollableContent()
    {
        JPanel contentPanel = createContentPanel();
        
        JScrollPane scrollPane = new JScrollPane(
            contentPanel,
            ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER
        );
        
        // Remove unnecessary borders for cleaner appearance
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getViewport().setBackground(UIManager.getColor("Panel.background"));
        
        // Configure smooth scrolling
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        
        return scrollPane;
    }
    
    /**
     * Creates main content panel with form structure
     * Fixed: Ensure proper sizing and layout constraints
     */
    private JPanel createContentPanel()
    {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(UIManager.getColor("Panel.background"));
        
        // Use consistent padding for better visual balance
        panel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        
        // Header section
        panel.add(createHeaderSection());
        panel.add(Box.createVerticalStrut(24));
        
        // Form section
        panel.add(createFormSection());
        panel.add(Box.createVerticalStrut(16));
        
        // Requirements section
        panel.add(createRequirementsSection());
        panel.add(Box.createVerticalStrut(24));
        
        // Action section
        panel.add(createActionSection());
        
        // Add flexible glue to push content to top when needed
        panel.add(Box.createVerticalGlue());
        
        return panel;
    }
    
    /**
     * Creates header with clear purpose indication
     */
    private JPanel createHeaderSection()
    {
        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));
        header.setBackground(UIManager.getColor("Panel.background"));
        header.setAlignmentX(Component.CENTER_ALIGNMENT);
        header.setMaximumSize(new Dimension(400, 100));
        
        JLabel title = new JLabel("Create Account", SwingConstants.CENTER);
        title.setFont(new Font("Helvetica Neue", Font.BOLD, 24));
        title.setForeground(UIManager.getColor("Label.foreground"));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel subtitle = new JLabel("Get started with Task Manager", SwingConstants.CENTER);
        subtitle.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
        subtitle.setForeground(UIManager.getColor("Label.disabledForeground"));
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        subtitle.setBorder(BorderFactory.createEmptyBorder(4, 0, 0, 0));
        
        header.add(title);
        header.add(subtitle);
        
        return header;
    }
    
    /**
     * Creates structured form with input validation
     * Fixed: Ensure proper field initialization and layout
     */
    private JPanel createFormSection()
    {
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBackground(UIManager.getColor("Panel.background"));
        formPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        formPanel.setMaximumSize(new Dimension(400, 300));
        
        // Personal information fields
        formPanel.add(createFormField("First Name", firstNameField = createTextField()));
        formPanel.add(Box.createVerticalStrut(12));
        formPanel.add(createFormField("Last Name", lastNameField = createTextField()));
        formPanel.add(Box.createVerticalStrut(12));
        
        // Account credentials fields
        formPanel.add(createFormField("Username", usernameField = createTextField()));
        formPanel.add(Box.createVerticalStrut(12));
        formPanel.add(createFormField("Password", passwordField = createPasswordField()));
        
        return formPanel;
    }
    
    /**
     * Creates labeled form field with consistent styling
     */
    private JPanel createFormField(String labelText, JComponent field)
    {
        JPanel fieldPanel = new JPanel();
        fieldPanel.setLayout(new BoxLayout(fieldPanel, BoxLayout.Y_AXIS));
        fieldPanel.setBackground(UIManager.getColor("Panel.background"));
        fieldPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        fieldPanel.setMaximumSize(new Dimension(400, 80));
        
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
        label.setForeground(UIManager.getColor("Label.foreground"));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        // Set constraints on the field component
        field.setMaximumSize(new Dimension(400, 36));
        field.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        fieldPanel.add(label);
        fieldPanel.add(Box.createVerticalStrut(4));
        fieldPanel.add(field);
        
        return fieldPanel;
    }
    
    /**
     * Creates requirements information section
     */
    private JPanel createRequirementsSection()
    {
        JPanel reqPanel = new JPanel();
        reqPanel.setLayout(new BoxLayout(reqPanel, BoxLayout.Y_AXIS));
        reqPanel.setBackground(UIManager.getColor("Panel.background"));
        reqPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        reqPanel.setMaximumSize(new Dimension(400, 120));
        
        // Visual separator
        reqPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(1, 0, 0, 0, 
                UIManager.getColor("Separator.foreground")),
            BorderFactory.createEmptyBorder(12, 0, 0, 0)
        ));
        
        JLabel title = new JLabel("Account Requirements");
        title.setFont(new Font("Helvetica Neue", Font.BOLD, 13));
        title.setForeground(UIManager.getColor("Label.foreground"));
        title.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        // Use JTextArea with proper wrapping for requirements
        JTextArea requirements = new JTextArea(
            "• Username must contain '_' and be 5 characters or less\n" +
            "• Password must be at least 8 characters\n" +
            "• Include uppercase letter, number, and special character"
        );
        requirements.setFont(new Font("Helvetica Neue", Font.PLAIN, 12));
        requirements.setForeground(UIManager.getColor("Label.disabledForeground"));
        requirements.setBackground(UIManager.getColor("Panel.background"));
        requirements.setEditable(false);
        requirements.setLineWrap(true);
        requirements.setWrapStyleWord(true);
        requirements.setBorder(BorderFactory.createEmptyBorder(6, 0, 0, 0));
        requirements.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        reqPanel.add(title);
        reqPanel.add(Box.createVerticalStrut(4));
        reqPanel.add(requirements);
        
        return reqPanel;
    }
    
    /**
     * Creates action buttons section with clear progression
     */
    private JPanel createActionSection()
    {
        JPanel actionPanel = new JPanel();
        actionPanel.setLayout(new BoxLayout(actionPanel, BoxLayout.Y_AXIS));
        actionPanel.setBackground(UIManager.getColor("Panel.background"));
        actionPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        actionPanel.setMaximumSize(new Dimension(400, 100));
        
        // Primary action button
        JButton createButton = createPrimaryButton("Create Account");
        createButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        createButton.addActionListener(e -> attemptRegistration());
        actionPanel.add(createButton);
        
        actionPanel.add(Box.createVerticalStrut(12));
        
        // Secondary navigation
        JButton cancelButton = createSecondaryButton("Cancel");
        cancelButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        cancelButton.addActionListener(e -> frame.showLoginPanel());
        actionPanel.add(cancelButton);
        
        return actionPanel;
    }
    
    /**
     * Creates standardized text field with proper styling
     */
    private JTextField createTextField()
    {
        JTextField field = new JTextField();
        field.setFont(new Font("Helvetica Neue", Font.PLAIN, 16));
        field.setMaximumSize(new Dimension(400, 36));
        
        // Clean border styling
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(UIManager.getColor("TextField.shadow"), 1),
            BorderFactory.createEmptyBorder(8, 8, 8, 8)
        ));
        
        return field;
    }
    
    /**
     * Creates secure password field with proper styling
     */
    private JPasswordField createPasswordField()
    {
        JPasswordField field = new JPasswordField();
        field.setFont(new Font("Helvetica Neue", Font.PLAIN, 16));
        field.setMaximumSize(new Dimension(400, 36));
        
        // Match text field styling
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(UIManager.getColor("TextField.shadow"), 1),
            BorderFactory.createEmptyBorder(8, 8, 8, 8)
        ));
        
        return field;
    }
    
    /**
     * Creates primary action button with visual emphasis
     */
    private JButton createPrimaryButton(String text)
    {
        JButton button = new JButton(text);
        button.setFont(new Font("Helvetica Neue", Font.BOLD, 15));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(0, 122, 255)); // System blue
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(12, 32, 12, 32));
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
        
        return button;
    }
    
    /**
     * Creates secondary action button with appropriate visual weight
     */
    private JButton createSecondaryButton(String text)
    {
        JButton button = new JButton(text);
        button.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
        button.setForeground(UIManager.getColor("Label.foreground"));
        button.setBackground(UIManager.getColor("Button.background"));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(UIManager.getColor("Button.shadow"), 1),
            BorderFactory.createEmptyBorder(10, 24, 10, 24)
        ));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        return button;
    }
    
    /**
     * Handles registration attempt with validation and feedback
     * Fixed: Ensure all fields are properly initialized before use
     */
    private void attemptRegistration()
    {
        // Validate that all fields are initialized
        if (usernameField == null || passwordField == null || 
            firstNameField == null || lastNameField == null)
        {
            showMessage("System error: Form fields not properly initialized", 
                       "Initialization Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();
        String firstName = firstNameField.getText().trim();
        String lastName = lastNameField.getText().trim();
        
        // Validate required fields are not empty
        if (username.isEmpty() || password.isEmpty() || 
            firstName.isEmpty() || lastName.isEmpty())
        {
            showMessage("All fields are required to create your account", 
                       "Incomplete Information", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        try
        {
            authManager.registerUser(username, password, firstName, lastName);
            showMessage("Your account has been created successfully.\nPlease sign in to continue.", 
                       "Account Created", JOptionPane.INFORMATION_MESSAGE);
            frame.showLoginPanel();
        }
        catch (IllegalArgumentException ex)
        {
            showMessage(ex.getMessage(), 
                       "Unable to Create Account", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Shows user feedback messages with proper parent reference
     */
    private void showMessage(String message, String title, int messageType)
    {
        // Use the frame as parent for proper dialog positioning
        JOptionPane.showMessageDialog(frame, message, title, messageType);
    }
}