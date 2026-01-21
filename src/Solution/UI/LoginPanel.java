package Solution.UI;

import Solution.Logic.AuthManager;

import javax.swing.*;
import java.awt.*;

/**
 * Minimalist login screen:
 *   - Very high whitespace
 *   - Almost invisible fields (underline style)
 *   - Flat, borderless buttons
 *   - Centered vertical layout
 *   - No visual noise
 */
public class LoginPanel extends JPanel
{
    private final JTextField     usernameField;
    private final JPasswordField passwordField;
    private final AuthFrame      parentFrame;
    private final AuthManager    authManager;

    public LoginPanel(AuthFrame frame, AuthManager authManager)
    {
        this.parentFrame = frame;
        this.authManager = authManager;

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(80, 60, 80, 60));
        setBackground(new Color(250, 250, 252));

        // Main vertical stack
        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setOpaque(false);
        content.setAlignmentX(Component.CENTER_ALIGNMENT);

        // ── Title ────────────────────────────────────────
        JLabel title = new JLabel("Sign In", SwingConstants.CENTER);
        title.setFont(new Font("Helvetica Neue", Font.PLAIN, 26));
        if (title.getFont().getFamily().equals("Dialog"))
        {
            title.setFont(new Font("Arial", Font.PLAIN, 26));
        }
        title.setForeground(new Color(30, 30, 36));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setBorder(BorderFactory.createEmptyBorder(0, 0, 60, 0));
        content.add(title);

        // ── Form fields ──────────────────────────────────
        content.add(Box.createVerticalStrut(12));
        usernameField = createUnderlineTextField("Username", 24);
        content.add(usernameField);
        content.add(Box.createVerticalStrut(32));

        passwordField = createUnderlinePasswordField("Password", 24);
        content.add(passwordField);
        content.add(Box.createVerticalStrut(60));

        // ── Buttons ──────────────────────────────────────
        JPanel buttonRow = new JPanel(new FlowLayout(FlowLayout.CENTER, 32, 0));
        buttonRow.setOpaque(false);
        buttonRow.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton signInBtn = createFlatButton("Sign In");
        signInBtn.addActionListener(e -> attemptLogin());
        buttonRow.add(signInBtn);

        JButton registerBtn = createFlatButton("Register");
        registerBtn.addActionListener(e -> parentFrame.showRegistrationPanel());
        buttonRow.add(registerBtn);

        content.add(buttonRow);

        // Center everything vertically
        JPanel wrapper = new JPanel(new GridBagLayout());
        wrapper.setOpaque(false);
        wrapper.add(content);

        add(wrapper, BorderLayout.CENTER);
    }

    private JTextField createUnderlineTextField(String placeholder, int columns)
    {
        JTextField field = new JTextField(columns);
        field.setFont(new Font("Helvetica Neue", Font.PLAIN, 15));
        field.setForeground(new Color(40, 40, 45));
        field.setCaretColor(new Color(50, 115, 220));
        field.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(190, 190, 200)));
        field.setBackground(new Color(250, 250, 252));
        field.setOpaque(false);
        field.setText("");
        field.setToolTipText(placeholder);
        return field;
    }

    private JPasswordField createUnderlinePasswordField(String placeholder, int columns)
    {
        JPasswordField field = new JPasswordField(columns);
        field.setFont(new Font("Helvetica Neue", Font.PLAIN, 15));
        field.setForeground(new Color(40, 40, 45));
        field.setCaretColor(new Color(50, 115, 220));
        field.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(190, 190, 200)));
        field.setBackground(new Color(250, 250, 252));
        field.setOpaque(false);
        field.setToolTipText(placeholder);
        return field;
    }

    private JButton createFlatButton(String text)
    {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Helvetica Neue", Font.PLAIN, 15));
        btn.setForeground(new Color(38, 38, 44));
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(12, 36, 12, 36));
        btn.setBackground(new Color(240, 240, 245));
        btn.setOpaque(true);
        btn.setBorderPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
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
                    "Missing Information",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        try
        {
            authManager.loginUser(username, password);
            parentFrame.showTaskPanel();
        }
        catch (IllegalArgumentException ex)
        {
            JOptionPane.showMessageDialog(this,
                    ex.getMessage(),
                    "Sign In Failed",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}