package Solution.UI;

import javax.swing.*;
import java.awt.*;
import Solution.Logic.AuthManager;

/**
 * Classic 2000s style registration panel
 */
public class RegistrationPanel extends JPanel
{
    private final JTextField usernameField;
    private final JPasswordField passwordField;
    private final JTextField firstNameField;
    private final JTextField lastNameField;

    /**
     * Constructs registration panel
     * @param frame
     * @param authManager
     */
    public RegistrationPanel(AuthFrame frame, AuthManager authManager)
    {
        setLayout(new GridLayout(6, 1, 5, 5));
        setBackground(SystemColor.control);

        // Title
        JLabel title = new JLabel("Register New User", SwingConstants.CENTER);
        title.setFont(new Font("Tahoma", Font.BOLD, 14));
        add(title);

        // Username
        JPanel usernamePanel = new JPanel();
        usernamePanel.add(new JLabel("Username:"));
        usernameField = new JTextField(15);
        usernamePanel.add(usernameField);
        add(usernamePanel);

        // Password
        JPanel passwordPanel = new JPanel();
        passwordPanel.add(new JLabel("Password:"));
        passwordField = new JPasswordField(15);
        passwordPanel.add(passwordField);
        add(passwordPanel);

        // First Name
        JPanel firstNamePanel = new JPanel();
        firstNamePanel.add(new JLabel("First Name:"));
        firstNameField = new JTextField(15);
        firstNamePanel.add(firstNameField);
        add(firstNamePanel);

        // Last Name
        JPanel lastNamePanel = new JPanel();
        lastNamePanel.add(new JLabel("Last Name:"));
        lastNameField = new JTextField(15);
        lastNamePanel.add(lastNameField);
        add(lastNamePanel);

        // Buttons
        JPanel buttonPanel = new JPanel();
        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(e -> 
            attemptRegistration(frame, authManager));
        buttonPanel.add(registerButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> frame.showLoginPanel());
        buttonPanel.add(cancelButton);
        add(buttonPanel);
    }

    /**
     * Attempts user registration
     */
    private void attemptRegistration(AuthFrame frame, AuthManager authManager)
    {
        try
        {
            authManager.registerUser(
                usernameField.getText(),
                new String(passwordField.getPassword()),
                firstNameField.getText(),
                lastNameField.getText()
            );
            JOptionPane.showMessageDialog(this, "Registration successful!");
            frame.showLoginPanel();
        }
        catch (HeadlessException | IllegalArgumentException ex)
        {
            JOptionPane.showMessageDialog(this, ex.getMessage(), 
                "Registration Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}