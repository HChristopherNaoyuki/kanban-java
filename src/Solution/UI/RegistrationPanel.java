package Solution.UI;

import Solution.Logic.AuthManager;
import javax.swing.*;
import java.awt.*;

/**
 * Registration panel styled to resemble classic macOS Aqua appearance
 */
public class RegistrationPanel extends JPanel
{
    private final JTextField usernameField;
    private final JPasswordField passwordField;
    private final JTextField firstNameField;
    private final JTextField lastNameField;

    public RegistrationPanel(AuthFrame frame, AuthManager authManager)
    {
        setLayout(new BorderLayout(12, 12));
        setBorder(BorderFactory.createEmptyBorder(20, 24, 20, 24));
        setBackground(new Color(236, 236, 236));

        JPanel content = new JPanel(new GridBagLayout());
        content.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 0, 8, 0);
        gbc.anchor = GridBagConstraints.WEST;

        // Title
        JLabel title = new JLabel("Create Account", SwingConstants.CENTER);
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
        content.add(new JLabel("Username:"), gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        usernameField = new JTextField(18);
        styleTextField(usernameField);
        content.add(usernameField, gbc);

        // Password
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.weightx = 0;
        content.add(new JLabel("Password:"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        passwordField = new JPasswordField(18);
        styleTextField(passwordField);
        content.add(passwordField, gbc);

        // First Name
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.weightx = 0;
        content.add(new JLabel("First Name:"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        firstNameField = new JTextField(18);
        styleTextField(firstNameField);
        content.add(firstNameField, gbc);

        // Last Name
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.weightx = 0;
        content.add(new JLabel("Last Name:"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        lastNameField = new JTextField(18);
        styleTextField(lastNameField);
        content.add(lastNameField, gbc);

        // Buttons
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 16, 0));
        buttonPanel.setOpaque(false);

        JButton registerButton = new JButton("Create Account");
        styleButton(registerButton);
        registerButton.addActionListener(e -> attemptRegistration(frame, authManager));
        buttonPanel.add(registerButton);

        JButton cancelButton = new JButton("Cancel");
        styleButton(cancelButton);
        cancelButton.addActionListener(e -> frame.showLoginPanel());
        buttonPanel.add(cancelButton);

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

    private void attemptRegistration(AuthFrame frame, AuthManager authManager)
    {
        try
        {
            authManager.registerUser(
                    usernameField.getText().trim(),
                    new String(passwordField.getPassword()),
                    firstNameField.getText().trim(),
                    lastNameField.getText().trim()
            );
            JOptionPane.showMessageDialog(this,
                    "Account created successfully.",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);
            frame.showLoginPanel();
        }
        catch (IllegalArgumentException ex)
        {
            JOptionPane.showMessageDialog(this,
                    ex.getMessage(),
                    "Registration Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}