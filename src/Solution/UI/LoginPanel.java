package Solution.UI;

import Solution.Logic.AuthManager;

import javax.swing.*;
import java.awt.*;

/**
 * Login panel with labeled fields and flat buttons
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
        setBorder(BorderFactory.createEmptyBorder(100, 70, 100, 70));
        setBackground(new Color(250, 250, 252));

        Box verticalStack = Box.createVerticalBox();
        verticalStack.setAlignmentX(CENTER_ALIGNMENT);

        // Title
        JLabel titleLabel = new JLabel("Sign In", SwingConstants.CENTER);
        titleLabel.setFont(new Font("SF Pro Display", Font.PLAIN, 28));
        titleLabel.setForeground(new Color(28, 28, 30));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 70, 0));
        verticalStack.add(titleLabel);
        verticalStack.add(Box.createVerticalStrut(20));

        // Username row
        verticalStack.add(createLabeledField("USERNAME:", usernameField = new JTextField(26)));
        verticalStack.add(Box.createVerticalStrut(32));

        // Password row
        verticalStack.add(createLabeledField("PASSWORD:", passwordField = new JPasswordField(26)));
        verticalStack.add(Box.createVerticalStrut(60));

        // Buttons
        JPanel buttonRow = new JPanel(new FlowLayout(FlowLayout.CENTER, 48, 0));
        buttonRow.setOpaque(false);

        JButton signInBtn = createFlatButton("Sign In");
        signInBtn.addActionListener(e -> attemptLogin());
        buttonRow.add(signInBtn);

        JButton registerBtn = createFlatButton("Register");
        registerBtn.addActionListener(e -> frame.showRegistrationPanel());
        buttonRow.add(registerBtn);

        verticalStack.add(buttonRow);

        // Center the stack vertically
        JPanel centeringPanel = new JPanel(new GridBagLayout());
        centeringPanel.setOpaque(false);
        centeringPanel.add(verticalStack);

        add(centeringPanel, BorderLayout.CENTER);
    }

    private JPanel createLabeledField(String labelText, JComponent field)
    {
        JPanel row = new JPanel(new BorderLayout(0, 6));
        row.setOpaque(false);

        JLabel label = new JLabel(labelText);
        label.setFont(new Font("SF Pro Text", Font.PLAIN, 15));
        label.setForeground(new Color(70, 70, 75));
        row.add(label, BorderLayout.NORTH);

        field.setFont(new Font("SF Pro Text", Font.PLAIN, 17));
        field.setForeground(new Color(44, 44, 46));
        field.setCaretColor(new Color(10, 132, 255));
        field.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(190, 190, 200)));
        field.setBackground(new Color(250, 250, 252));
        field.setOpaque(false);

        row.add(field, BorderLayout.CENTER);
        return row;
    }

    private JButton createFlatButton(String text)
    {
        JButton btn = new JButton(text);
        btn.setFont(new Font("SF Pro Text", Font.PLAIN, 15));
        btn.setForeground(new Color(28, 28, 30));
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(12, 40, 12, 40));
        btn.setBackground(new Color(240, 240, 245));
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
                    "Please fill in both fields.",
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
                    "Sign In Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}