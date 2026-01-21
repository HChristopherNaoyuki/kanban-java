package Solution.UI;

import Solution.Logic.AuthManager;

import javax.swing.*;
import java.awt.*;

/**
 * Clean, minimalistic login screen with:
 * • Clear labels above fields
 * • Underline style input fields
 * • Flat modern buttons
 * • Generous whitespace
 */
public class LoginPanel extends JPanel
{
    private final AuthFrame      frame;
    private final AuthManager    authManager;
    private final JTextField     usernameField;
    private final JPasswordField passwordField;

    public LoginPanel(AuthFrame frame, AuthManager authManager)
    {
        this.frame       = frame;
        this.authManager = authManager;

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(120, 80, 120, 80));
        setBackground(new Color(250, 250, 252));

        Box vertical = Box.createVerticalBox();
        vertical.setAlignmentX(CENTER_ALIGNMENT);

        addTitle(vertical);
        addSpacing(vertical, 24);

        addLabeledField(vertical, "USERNAME:", usernameField = new JTextField(26));
        addSpacing(vertical, 36);

        addLabeledField(vertical, "PASSWORD:", passwordField = new JPasswordField(26));
        addSpacing(vertical, 64);

        addButtonsRow(vertical);

        JPanel centeringWrapper = new JPanel(new GridBagLayout());
        centeringWrapper.setOpaque(false);
        centeringWrapper.add(vertical);

        add(centeringWrapper, BorderLayout.CENTER);
    }

    private void addTitle(Box container)
    {
        JLabel title = new JLabel("Sign In", SwingConstants.CENTER);
        title.setFont(new Font("SF Pro Display", Font.PLAIN, 30));
        title.setForeground(new Color(28, 28, 30));
        title.setBorder(BorderFactory.createEmptyBorder(0, 0, 60, 0));
        container.add(title);
    }

    private void addLabeledField(Box container, String labelText, JComponent field)
    {
        container.add(createLabeledField(labelText, field));
    }

    private JPanel createLabeledField(String labelText, JComponent field)
    {
        JPanel panel = new JPanel(new BorderLayout(0, 8));
        panel.setOpaque(false);

        JLabel label = new JLabel(labelText);
        label.setFont(new Font("SF Pro Text", Font.PLAIN, 15));
        label.setForeground(new Color(70, 70, 75));
        panel.add(label, BorderLayout.NORTH);

        styleInputField(field);
        panel.add(field, BorderLayout.CENTER);

        return panel;
    }

    private void styleInputField(JComponent field)
    {
        field.setFont(new Font("SF Pro Text", Font.PLAIN, 17));
        field.setForeground(new Color(44, 44, 46));
        field.setCaretColor(new Color(10, 132, 255)); // nice accent color
        field.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(190, 190, 200)));
        field.setBackground(new Color(250, 250, 252));
        field.setOpaque(false);
    }

    private void addButtonsRow(Box container)
    {
        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 0));
        buttons.setOpaque(false);

        JButton signIn = createFlatButton("Sign In");
        signIn.addActionListener(e -> tryLogin());
        buttons.add(signIn);

        JButton register = createFlatButton("Register");
        register.addActionListener(e -> frame.showRegistrationPanel());
        buttons.add(register);

        container.add(buttons);
    }

    private JButton createFlatButton(String text)
    {
        JButton btn = new JButton(text);
        btn.setFont(new Font("SF Pro Text", Font.PLAIN, 15));
        btn.setForeground(new Color(28, 28, 30));
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(14, 48, 14, 48));
        btn.setBackground(new Color(238, 238, 245));
        btn.setOpaque(true);
        btn.setBorderPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        return btn;
    }

    private void addSpacing(Box container, int pixels)
    {
        container.add(Box.createVerticalStrut(pixels));
    }

    private void tryLogin()
    {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();

        if (username.isBlank() || password.isBlank())
        {
            showWarning("Please fill in both username and password.");
            return;
        }

        try
        {
            authManager.loginUser(username, password);
            frame.showTaskPanel();
        }
        catch (IllegalArgumentException ex)
        {
            JOptionPane.showMessageDialog(this,
                    ex.getMessage(),
                    "Login Failed",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showWarning(String message)
    {
        JOptionPane.showMessageDialog(this,
                message,
                "Missing Information",
                JOptionPane.WARNING_MESSAGE);
    }
}