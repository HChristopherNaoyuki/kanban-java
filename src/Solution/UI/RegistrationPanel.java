package Solution.UI;

import Solution.Logic.AuthManager;

import javax.swing.*;
import java.awt.*;

/**
 * Modern, clean registration form following same style as LoginPanel
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
        setBorder(BorderFactory.createEmptyBorder(90, 70, 90, 70));
        setBackground(new Color(250, 250, 252));

        Box vertical = Box.createVerticalBox();
        vertical.setAlignmentX(CENTER_ALIGNMENT);

        addTitle(vertical);
        addSpacing(vertical, 24);

        addLabeledField(vertical, "USERNAME:", usernameField = new JTextField(28));
        addSpacing(vertical, 32);

        addLabeledField(vertical, "PASSWORD:", passwordField = new JPasswordField(28));
        addSpacing(vertical, 32);

        addLabeledField(vertical, "FIRST NAME:", firstNameField = new JTextField(28));
        addSpacing(vertical, 32);

        addLabeledField(vertical, "LAST NAME:", lastNameField = new JTextField(28));
        addSpacing(vertical, 56);

        addButtonsRow(vertical);

        JPanel center = new JPanel(new GridBagLayout());
        center.setOpaque(false);
        center.add(vertical);

        add(center, BorderLayout.CENTER);
    }

    private void addTitle(Box container)
    {
        JLabel title = new JLabel("Create Account", SwingConstants.CENTER);
        title.setFont(new Font("SF Pro Display", Font.PLAIN, 28));
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
        field.setCaretColor(ne/*w*/ Color(10, 132, 255));
        field.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(190, 190, 200)));
        field.setBackground(new Color(250, 250, 252));
        field.setOpaque(false);
    }

    private void addButtonsRow(Box container)
    {
        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER, 48, 0));
        buttons.setOpaque(false);

        JButton create = createFlatButton("Create Account");
        create.addActionListener(e -> tryRegister());
        buttons.add(create);

        JButton cancel = createFlatButton("Cancel");
        cancel.addActionListener(e -> frame.showLoginPanel());
        buttons.add(cancel);

        container.add(buttons);
    }

    private JButton createFlatButton(String text)
    {
        JButton btn = new JButton(text);
        btn.setFont(new Font("SF Pro Text", Font.PLAIN, 15));
        btn.setForeground(new Color(28, 28, 30));
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(14, 44, 14, 44));
        btn.setBackground(new Color(240, 240, 245));
        btn.setOpaque(true);
        btn.setBorderPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return btn;
    }

    private void addSpacing(Box container, int pixels)
    {
        container.add(Box.createVerticalStrut(pixels));
    }

    private void tryRegister()
    {
        String username   = usernameField.getText().trim();
        String password   = new String(passwordField.getPassword()).trim();
        String firstName  = firstNameField.getText().trim();
        String lastName   = lastNameField.getText().trim();

        if (username.isBlank() || password.isBlank() ||
            firstName.isBlank() || lastName.isBlank())
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
                    "Account created successfully!\nPlease sign in.",
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