package Solution.UI;

import Solution.Logic.AuthManager;

import javax.swing.*;
import java.awt.*;

/**
 * Minimalist registration panel – Java 8 compatible version
 * - Labels above every field
 * - Underline style input (bottom border only)
 * - Flat buttons, generous spacing
 * - No methods added after Java 8
 */
public class RegistrationPanel extends JPanel
{
    private final AuthFrame      frame;
    private final AuthManager    authManager;
    private final JTextField     usernameField;
    private final JPasswordField passwordField;
    private final JTextField     firstNameField;
    private final JTextField     lastNameField;

    public RegistrationPanel(AuthFrame frame, AuthManager authManager)
    {
        this.frame       = frame;
        this.authManager = authManager;

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(100, 70, 100, 70));
        setBackground(new Color(250, 250, 252));

        Box vertical = Box.createVerticalBox();
        vertical.setAlignmentX(CENTER_ALIGNMENT);

        addTitle(vertical);
        vertical.add(Box.createVerticalStrut(24));

        addLabeledField(vertical, "USERNAME:", usernameField = new JTextField(28));
        vertical.add(Box.createVerticalStrut(32));

        addLabeledField(vertical, "PASSWORD:", passwordField = new JPasswordField(28));
        vertical.add(Box.createVerticalStrut(32));

        addLabeledField(vertical, "FIRST NAME:", firstNameField = new JTextField(28));
        vertical.add(Box.createVerticalStrut(32));

        addLabeledField(vertical, "LAST NAME:", lastNameField = new JTextField(28));
        vertical.add(Box.createVerticalStrut(56));

        addButtonRow(vertical);

        JPanel center = new JPanel(new GridBagLayout());
        center.setOpaque(false);
        center.add(vertical);

        add(center, BorderLayout.CENTER);
    }

    private void addTitle(Box container)
    {
        JLabel title = new JLabel("Create Account", SwingConstants.CENTER);
        title.setFont(new Font("Helvetica Neue", Font.PLAIN, 28));
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

        // Java 8 compatible: underline effect via bottom border only
        comp.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(190, 190, 200)));

        comp.setBackground(new Color(250, 250, 252));
        comp.setOpaque(false);
    }

    private void addButtonRow(Box container)
    {
        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER, 48, 0));
        buttons.setOpaque(false);

        JButton createBtn = createFlatButton("Create Account");
        createBtn.addActionListener(e -> attemptRegistration());
        buttons.add(createBtn);

        JButton cancelBtn = createFlatButton("Cancel");
        cancelBtn.addActionListener(e -> frame.showLoginPanel());
        buttons.add(cancelBtn);

        container.add(buttons);
    }

    private JButton createFlatButton(String text)
    {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Helvetica Neue", Font.PLAIN, 15));
        btn.setForeground(new Color(28, 28, 30));
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(14, 44, 14, 44));
        btn.setBackground(new Color(240, 240, 245));
        btn.setOpaque(true);
        btn.setBorderPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return btn;
    }

    private void attemptRegistration()
    {
        String username   = usernameField.getText().trim();
        String password   = new String(passwordField.getPassword()).trim();
        String firstName  = firstNameField.getText().trim();
        String lastName   = lastNameField.getText().trim();

        if (username.isEmpty() || password.isEmpty() ||
            firstName.isEmpty() || lastName.isEmpty())
        {
            JOptionPane.showMessageDialog(this,
                    "All fields are required.",
                    "Incomplete Form",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        try
        {
            authManager.registerUser(username, password, firstName, lastName);
            JOptionPane.showMessageDialog(this,
                    "Account created successfully.\nPlease sign in.",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);
            frame.showLoginPanel();
        }
        catch (IllegalArgumentException ex)
        {
            JOptionPane.showMessageDialog(this,
                    ex.getMessage(),
                    "Registration Failed",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}