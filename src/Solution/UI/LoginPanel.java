package Solution.UI;

import Solution.Logic.AuthManager;

import javax.swing.*;
import java.awt.*;

/**
 * Clean, minimalist login screen:
 *   - Lots of whitespace
 *   - Subtle borders only where functionally necessary
 *   - Flat buttons
 *   - Neutral color palette
 */
public class LoginPanel extends JPanel
{
    private final JTextField usernameField;
    private final JPasswordField passwordField;

    public LoginPanel(AuthFrame frame, AuthManager authManager)
    {
        setLayout(new BorderLayout(0, 40));
        setBorder(BorderFactory.createEmptyBorder(60, 60, 60, 60));
        setBackground(new Color(248, 248, 248));

        // Center content vertically and horizontally
        JPanel content = new JPanel(new GridBagLayout());
        content.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 0, 12, 0);
        gbc.anchor = GridBagConstraints.CENTER;

        // Title
        JLabel title = new JLabel("Sign In", SwingConstants.CENTER);
        title.setFont(new Font("Helvetica Neue", Font.PLAIN, 22));
        title.setForeground(new Color(30, 30, 30));
        gbc.gridy = 0;
        content.add(title, gbc);

        // Form fields container
        JPanel form = new JPanel(new GridBagLayout());
        form.setOpaque(false);
        GridBagConstraints fbc = new GridBagConstraints();
        fbc.insets = new Insets(10, 0, 10, 0);
        fbc.fill = GridBagConstraints.HORIZONTAL;
        fbc.weightx = 1.0;

        // Username
        fbc.gridy = 0;
        usernameField = createMinimalTextField(20);
        form.add(createLabeledField("Username", usernameField), fbc);

        // Password
        fbc.gridy = 1;
        passwordField = createMinimalPasswordField(20);
        form.add(createLabeledField("Password", passwordField), fbc);

        gbc.gridy = 1;
        gbc.insets = new Insets(32, 0, 32, 0);
        content.add(form, gbc);

        // Buttons
        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER, 24, 0));
        buttons.setOpaque(false);

        JButton loginBtn = createMinimalButton("Sign In");
        loginBtn.addActionListener(e -> attemptLogin(frame, authManager));
        buttons.add(loginBtn);

        JButton registerBtn = createMinimalButton("Register");
        registerBtn.addActionListener(e -> frame.showRegistrationPanel());
        buttons.add(registerBtn);

        gbc.gridy = 2;
        gbc.insets = new Insets(0, 0, 0, 0);
        content.add(buttons, gbc);

        add(content, BorderLayout.CENTER);
    }

    private JPanel createLabeledField(String labelText, JComponent field)
    {
        JPanel panel = new JPanel(new BorderLayout(0, 6));
        panel.setOpaque(false);

        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
        label.setForeground(new Color(70, 70, 70));
        panel.add(label, BorderLayout.NORTH);
        panel.add(field, BorderLayout.CENTER);

        return panel;
    }

    private JTextField createMinimalTextField(int columns)
    {
        JTextField field = new JTextField(columns);
        field.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
        field.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(180, 180, 180)));
        field.setBackground(new Color(248, 248, 248));
        field.setOpaque(false);
        field.setCaretColor(new Color(40, 40, 40));
        return field;
    }

    private JPasswordField createMinimalPasswordField(int columns)
    {
        JPasswordField field = new JPasswordField(columns);
        field.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
        field.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(180, 180, 180)));
        field.setBackground(new Color(248, 248, 248));
        field.setOpaque(false);
        field.setCaretColor(new Color(40, 40, 40));
        return field;
    }

    private JButton createMinimalButton(String text)
    {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(10, 28, 10, 28));
        btn.setBackground(new Color(235, 235, 235));
        btn.setOpaque(true);
        btn.setBorderPainted(false);
        return btn;
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
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}