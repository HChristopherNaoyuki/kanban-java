package Solution.UI;

import javax.swing.*;
import java.awt.*;
import Solution.Logic.AuthManager;

/**
 * Classic 2000s style login panel
 */
public class LoginPanel extends JPanel
{
    private final JTextField usernameField;
    private final JPasswordField passwordField;

    /**
     * Constructs login panel
     * @param frame
     * @param authManager
     */
    public LoginPanel(AuthFrame frame, AuthManager authManager)
    {
        setLayout(new GridLayout(4, 1, 5, 5));
        setBackground(SystemColor.control);

        // Title
        JLabel title = new JLabel("Login", SwingConstants.CENTER);
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

        // Buttons
        JPanel buttonPanel = new JPanel();
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(e -> attemptLogin(frame, authManager));
        buttonPanel.add(loginButton);

        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(e -> frame.showRegistrationPanel());
        buttonPanel.add(registerButton);
        add(buttonPanel);
    }

    /**
     * Attempts user login
     */
    private void attemptLogin(AuthFrame frame, AuthManager authManager)
    {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        try
        {
            if (authManager.loginUser(username, password))
            {
                frame.showTaskPanel();
            }
        }
        catch (IllegalArgumentException ex)
        {
            JOptionPane.showMessageDialog(this, ex.getMessage(), 
                "Login Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}