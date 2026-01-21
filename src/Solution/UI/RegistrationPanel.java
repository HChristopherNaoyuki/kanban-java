package Solution.UI;

import Solution.Logic.AuthManager;

import javax.swing.*;
import java.awt.*;

/**
 * macOS-inspired registration panel with labeled fields
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

        JLabel title = new JLabel("Create Account", SwingConstants.CENTER);
        title.setFont(new Font("SF Pro Display", Font.PLAIN, 26));
        title.setForeground(new Color(28, 28, 30));
        title.setBorder(BorderFactory.createEmptyBorder(0, 0, 60, 0));
        vertical.add(title);
        vertical.add(Box.createVerticalStrut(20));

        vertical.add(createLabeledField("USERNAME:", usernameField = new JTextField(28)));
        vertical.add(Box.createVerticalStrut(28));

        vertical.add(createLabeledField("PASSWORD:", passwordField = new JPasswordField(28)));
        vertical.add(Box.createVerticalStrut(28));

        vertical.add(createLabeledField("FIRST NAME:", firstNameField = new JTextField(28)));
        vertical.add(Box.createVerticalStrut(28));

        vertical.add(createLabeledField("LAST NAME:", lastNameField = new JTextField(28)));
        vertical.add(Box.createVerticalStrut(50));

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 48, 0));
        btnPanel.setOpaque(false);

        JButton createBtn = createFlatButton("Create Account");
        createBtn.addActionListener(e -> attemptRegistration());
        btnPanel.add(createBtn);

        JButton cancelBtn = createFlatButton("Cancel");
        cancelBtn.addActionListener(e -> frame.showLoginPanel());
        btnPanel.add(cancelBtn);

        vertical.add(btnPanel);

        JPanel centerWrapper = new JPanel(new GridBagLayout());
        centerWrapper.setOpaque(false);
        centerWrapper.add(vertical);

        add(centerWrapper, BorderLayout.CENTER);
    }

    private JPanel createLabeledField(String labelText, JComponent field)
    {
        JPanel container = new JPanel(new BorderLayout(0, 6));
        container.setOpaque(false);

        JLabel lbl = new JLabel(labelText);
        lbl.setFont(new Font("SF Pro Text", Font.PLAIN, 15));
        lbl.setForeground(new Color(70, 70, 75));
        container.add(lbl, BorderLayout.NORTH);

        field.setFont(new Font("SF Pro Text", Font.PLAIN, 17));
        field.setForeground(new Color(44, 44, 46));
        field.setCaretColor(new Color(10, 132, 255));
        field.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(190, 190, 200)));
        field.setBackground(new Color(250, 250, 252));
        field.setOpaque(false);

        container.add(field, BorderLayout.CENTER);
        return container;
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

    private void attemptRegistration()
    {
        String u  = usernameField.getText().trim();
        String p  = new String(passwordField.getPassword()).trim();
        String fn = firstNameField.getText().trim();
        String ln = lastNameField.getText().trim();

        if (u.isEmpty() || p.isEmpty() || fn.isEmpty() || ln.isEmpty())
        {
            JOptionPane.showMessageDialog(this,
                    "All fields are required.",
                    "Incomplete",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        try
        {
            authManager.registerUser(u, p, fn, ln);
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
                    "Registration Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}