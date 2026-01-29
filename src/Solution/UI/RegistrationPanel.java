package Solution.UI;

import Solution.Logic.AuthManager;
import javax.swing.*;
import java.awt.*;

/**
 * Registration interface with structured form layout
 * Clear progression through account creation steps
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
        
        setLayout(new GridBagLayout());
        setBackground(UIManager.getColor("Panel.background"));
        
        JScrollPane scrollPane = createScrollableContent();
        add(scrollPane);
    }
    
    /**
     * Creates scrollable content for better form management
     */
    private JScrollPane createScrollableContent()
    {
        JPanel contentPanel = createContentPanel();
        
        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        
        return scrollPane;
    }
    
    /**
     * Creates main content panel with form structure
     */
    private JPanel createContentPanel()
    {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(UIManager.getColor("Panel.background"));
        panel.setBorder(BorderFactory.createEmptyBorder(32, 64, 32, 64));
        panel.setMaximumSize(new Dimension(400, 700));
        
        // Header section
        panel.add(createHeaderSection());
        panel.add(Box.createVerticalStrut(32));
        
        // Form section
        panel.add(createFormSection());
        panel.add(Box.createVerticalStrut(24));
        
        // Requirements section
        panel.add(createRequirementsSection());
        panel.add(Box.createVerticalStrut(32));
        
        // Action section
        panel.add(createActionSection());
        
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
        header.setAlignmentX(CENTER_ALIGNMENT);
        
        JLabel title = new JLabel("Create Account", SwingConstants.CENTER);
        title.setFont(new Font("SF Pro Display", Font.BOLD, 24));
        title.setForeground(UIManager.getColor("Label.foreground"));
        
        JLabel subtitle = new JLabel("Get started with Task Manager", SwingConstants.CENTER);
        subtitle.setFont(new Font("SF Pro Text", Font.PLAIN, 14));
        subtitle.setForeground(UIManager.getColor("Label.disabledForeground"));
        subtitle.setBorder(BorderFactory.createEmptyBorder(8, 0, 0, 0));
        
        header.add(title);
        header.add(subtitle);
        
        return header;
    }
    
    /**
     * Creates structured form with input validation
     */
    private JPanel createFormSection()
    {
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBackground(UIManager.getColor("Panel.background"));
        formPanel.setAlignmentX(CENTER_ALIGNMENT);
        
        // Personal information fields
        formPanel.add(createFormField("First Name", firstNameField = createTextField()));
        formPanel.add(Box.createVerticalStrut(16));
        formPanel.add(createFormField("Last Name", lastNameField = createTextField()));
        formPanel.add(Box.createVerticalStrut(16));
        
        // Account credentials fields
        formPanel.add(createFormField("Username", usernameField = createTextField()));
        formPanel.add(Box.createVerticalStrut(16));
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
        fieldPanel.setAlignmentX(LEFT_ALIGNMENT);
        
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("SF Pro Text", Font.PLAIN, 14));
        label.setForeground(UIManager.getColor("Label.foreground"));
        
        fieldPanel.add(label);
        fieldPanel.add(Box.createVerticalStrut(6));
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
        reqPanel.setAlignmentX(LEFT_ALIGNMENT);
        reqPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(1, 0, 0, 0, 
                UIManager.getColor("Separator.foreground")),
            BorderFactory.createEmptyBorder(16, 0, 0, 0)
        ));
        
        JLabel title = new JLabel("Account Requirements");
        title.setFont(new Font("SF Pro Text", Font.BOLD, 13));
        title.setForeground(UIManager.getColor("Label.foreground"));
        
        JTextArea requirements = new JTextArea(
            "• Username must contain '_' and be 5 characters or less\n" +
            "• Password must be at least 8 characters\n" +
            "• Include uppercase letter, number, and special character"
        );
        requirements.setFont(new Font("SF Pro Text", Font.PLAIN, 12));
        requirements.setForeground(UIManager.getColor("Label.disabledForeground"));
        requirements.setBackground(UIManager.getColor("Panel.background"));
        requirements.setEditable(false);
        requirements.setBorder(BorderFactory.createEmptyBorder(8, 0, 0, 0));
        
        reqPanel.add(title);
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
        actionPanel.setAlignmentX(CENTER_ALIGNMENT);
        
        // Primary action button
        JButton createButton = createPrimaryButton("Create Account");
        createButton.addActionListener(e -> attemptRegistration());
        actionPanel.add(createButton);
        
        actionPanel.add(Box.createVerticalStrut(16));
        
        // Secondary navigation
        JButton cancelButton = createSecondaryButton("Cancel");
        cancelButton.addActionListener(e -> frame.showLoginPanel());
        actionPanel.add(cancelButton);
        
        return actionPanel;
    }
    
    /**
     * Creates standardized text field
     */
    private JTextField createTextField()
    {
        JTextField field = new JTextField(24);
        field.setFont(new Font("SF Pro Text", Font.PLAIN, 16));
        field.setMaximumSize(new Dimension(300, 40));
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 1, 0, 
                UIManager.getColor("TextField.shadow")),
            BorderFactory.createEmptyBorder(8, 8, 8, 8)
        ));
        return field;
    }
    
    /**
     * Creates secure password field
     */
    private JPasswordField createPasswordField()
    {
        JPasswordField field = new JPasswordField(24);
        field.setFont(new Font("SF Pro Text", Font.PLAIN, 16));
        field.setMaximumSize(new Dimension(300, 40));
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 1, 0, 
                UIManager.getColor("TextField.shadow")),
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
        button.setFont(new Font("SF Pro Text", Font.BOLD, 15));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(0, 122, 255));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(12, 32, 12, 32));
        button.setAlignmentX(CENTER_ALIGNMENT);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        button.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseEntered(java.awt.event.MouseEvent evt)
            {
                button.setBackground(new Color(0, 110, 235));
            }
            
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
        button.setFont(new Font("SF Pro Text", Font.PLAIN, 14));
        button.setForeground(UIManager.getColor("Label.foreground"));
        button.setBackground(UIManager.getColor("Button.background"));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(UIManager.getColor("Button.shadow"), 1),
            BorderFactory.createEmptyBorder(10, 24, 10, 24)
        ));
        button.setAlignmentX(CENTER_ALIGNMENT);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        return button;
    }
    
    /**
     * Handles registration attempt with validation and feedback
     */
    private void attemptRegistration()
    {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();
        String firstName = firstNameField.getText().trim();
        String lastName = lastNameField.getText().trim();
        
        // Validate required fields
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
     * Shows user feedback messages
     */
    private void showMessage(String message, String title, int messageType)
    {
        JOptionPane.showMessageDialog(this, message, title, messageType);
    }
}