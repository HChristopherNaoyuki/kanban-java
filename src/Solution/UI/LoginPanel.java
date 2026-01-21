package Solution.UI;

import Solution.Logic.AuthManager;
import javax.swing.*;
import java.awt.*;

/**
 * Login panel styled to resemble classic macOS Aqua appearance
 */
public class LoginPanel extends JPanel
{
    private final JTextField usernameField;
    private final JPasswordField passwordField;

    public LoginPanel(AuthFrame frame, AuthManager authManager)
    {
        setLayout(new BorderLayout(12, 12));
        setBorder(BorderFactory.createEmptyBorder(20, 24, 20, 24));
        setBackground(new Color(236, 236, 236));

        // Main content panel with grid
        JPanel content = new JPanel(new GridBagLayout());
        content.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 0, 8, 0);
        gbc.anchor = GridBagConstraints.WEST;

        // Title
        JLabel title = new JLabel("Sign In", SwingConstants.CENTER);
        title.setFont(new Font("Lucida Grande", Font.BOLD, 18));
        title.setForeground(new Color(0, 0, 0, 220));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        content.add(title, gbc);

        // Username
        gbc.gridy++;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        content.add(new JLabel("Username:"), gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        usernameField = new JTextField(18);
        styleTextField(usernameField);
        content.add(usernameField, gbc);

        // Password
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.weightx = 0;
        gbc.anchor = GridBagConstraints.LINE_END;
        content.add(new JLabel("Password:"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.LINE_START;
        passwordField = new JPasswordField(18);
        styleTextField(passwordField);
        content.add(passwordField, gbc);

        // Buttons
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 16, 0));
        buttonPanel.setOpaque(false);

        JButton loginButton = new JButton("Sign In");
        styleButton(loginButton);
        loginButton.addActionListener(e -> attemptLogin(frame, authManager));
        buttonPanel.add(loginButton);

        JButton registerButton = new JButton("Register…");
        styleButton(registerButton);
        registerButton.addActionListener(e -> frame.showRegistrationPanel());
        buttonPanel.add(registerButton);

        content.add(buttonPanel, gbc);

        add(content, BorderLayout.CENTER);
    }

    private void styleTextField(JTextField field)
    {
        field.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(160, 160, 160)),
                BorderFactory.createEmptyBorder(6, 8, 6, 8)));
        field.setBackground(Color.WHITE);
    }

    private void styleButton(JButton button)
    {
        button.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(120, 120, 120)),
                BorderFactory.createEmptyBorder(6, 16, 6, 16)));
        button.setBackground(new Color(240, 240, 240));
        button.setOpaque(true);
    }

    private void attemptLogin(AuthFrame frame, AuthManager authManager)
    {
        String username = usernameField.getText().trim();
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
            JOptionPane.showMessageDialog(this,
                    ex.getMessage(),
                    "Authentication Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}