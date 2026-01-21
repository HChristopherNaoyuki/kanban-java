package Solution.UI;

import Solution.Logic.AuthManager;

import javax.swing.*;
import java.awt.*;

/**
 * Minimalist login panel compatible with Java 8
 * - Clear labels above input fields
 * - Underline-style text fields (bottom border only)
 * - Flat buttons with generous padding
 * - High vertical whitespace
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
        vertical.add(Box.createVerticalStrut(24));

        addLabeledField(vertical, "USERNAME:", usernameField = new JTextField(26));
        vertical.add(Box.createVerticalStrut(36));

        addLabeledField(vertical, "PASSWORD:", passwordField = new JPasswordField(26));
        vertical.add(Box.createVerticalStrut(64));

        addButtonRow(vertical);

        JPanel centeringPanel = new JPanel(new GridBagLayout());
        centeringPanel.setOpaque(false);
        centeringPanel.add(vertical);

        add(centeringPanel, BorderLayout.CENTER);
    }

    private void addTitle(Box container)
    {
        JLabel title = new JLabel("Sign In", SwingConstants.CENTER);
        title.setFont(new Font("Helvetica Neue", Font.PLAIN, 30));
        title.setForeground(new Color(28, 28, 30));
        title.setBorder(BorderFactory.createEmptyBorder(0, 0, 60, 0));
        container.add(title);
    }

    private void addLabeledField(Box container, String labelText, JComponent field)
    {
        container.add(createLabeledInput(labelText, field));
    }

    private JPanel createLabeledInput(String labelText, JComponent field)
    {
        JPanel panel = new JPanel(new BorderLayout(0, 8));
        panel.setOpaque(false);

        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Helvetica Neue", Font.PLAIN, 15));
        label.setForeground(new Color(70, 70, 75));
        panel.add(label, BorderLayout.NORTH);

        styleTextComponent(field);
        panel.add(field, BorderLayout.CENTER);

        return panel;
    }

    private void styleTextComponent(JComponent comp)
    {
        comp.setFont(new Font("Helvetica Neue", Font.PLAIN, 17));
        comp.setForeground(new Color(44, 44, 46));

        // Java 8 compatible: only bottom border, no setCaretColor()
        comp.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(190, 190, 200)));

        comp.setBackground(new Color(250, 250, 252));
        comp.setOpaque(false);
    }

    private void addButtonRow(Box container)
    {
        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 0));
        buttons.setOpaque(false);

        JButton signInBtn = createFlatButton("Sign In");
        signInBtn.addActionListener(e -> attemptLogin());
        buttons.add(signInBtn);

        JButton registerBtn = createFlatButton("Register");
        registerBtn.addActionListener(e -> frame.showRegistrationPanel());
        buttons.add(registerBtn);

        container.add(buttons);
    }

    private JButton createFlatButton(String text)
    {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Helvetica Neue", Font.PLAIN, 15));
        btn.setForeground(new Color(28, 28, 30));
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(14, 48, 14, 48));
        btn.setBackground(new Color(238, 238, 245));
        btn.setOpaque(true);
        btn.setBorderPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return btn;
    }

    private void attemptLogin()
    {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();

        if (username.isEmpty() || password.isEmpty())
        {
            JOptionPane.showMessageDialog(this,
                    "Please enter both username and password.",
                    "Required Fields",
                    JOptionPane.WARNING_MESSAGE);
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
}