package Solution.UI;

import Solution.Logic.AuthManager;

import javax.swing.*;
import java.awt.*;

/**
 * Minimalist registration screen
 * Same visual language as LoginPanel
 */
public class RegistrationPanel extends JPanel
{
    private final AuthFrame      parent;
    private final AuthManager    auth;
    private final JTextField     username;
    private final JPasswordField password;
    private final JTextField     firstName;
    private final JTextField     lastName;

    public RegistrationPanel(AuthFrame parent, AuthManager auth)
    {
        this.parent = parent;
        this.auth   = auth;

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(80, 60, 80, 60));
        setBackground(new Color(250, 250, 252));

        Box box = Box.createVerticalBox();
        box.setAlignmentX(CENTER_ALIGNMENT);

        JLabel title = new JLabel("Create Account", SwingConstants.CENTER);
        title.setFont(new Font("SF Pro Display", Font.PLAIN, 26));
        fallbackFont(title, "Helvetica Neue", Font.PLAIN, 26);
        title.setForeground(new Color(28, 28, 30));
        title.setBorder(BorderFactory.createEmptyBorder(0, 0, 60, 0));
        box.add(title);
        box.add(Box.createVerticalStrut(16));

        username   = createField("Username");
        box.add(username);   box.add(Box.createVerticalStrut(28));

        password   = createPasswordField("Password");
        box.add(password);   box.add(Box.createVerticalStrut(28));

        firstName  = createField("First Name");
        box.add(firstName);  box.add(Box.createVerticalStrut(28));

        lastName   = createField("Last Name");
        box.add(lastName);   box.add(Box.createVerticalStrut(48));

        JPanel btns = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 0));
        btns.setOpaque(false);

        JButton create = createMacButton("Create Account");
        create.addActionListener(e -> tryRegister());
        btns.add(create);

        JButton cancel = createMacButton("Cancel");
        cancel.addActionListener(e -> parent.showLoginPanel());
        btns.add(cancel);

        box.add(btns);

        JPanel center = new JPanel(new GridBagLayout());
        center.setOpaque(false);
        center.add(box);

        add(center, BorderLayout.CENTER);
    }

    private JTextField createField(String hint)
    {
        JTextField f = new JTextField(28);
        styleUnderlineField(f, hint);
        return f;
    }

    private JPasswordField createPasswordField(String hint)
    {
        JPasswordField f = new JPasswordField(28);
        styleUnderlineField(f, hint);
        return f;
    }

    private void styleUnderlineField(JTextField f, String hint)
    {
        f.setFont(new Font("SF Pro Text", Font.PLAIN, 16));
        fallbackFont(f, "Helvetica Neue", Font.PLAIN, 16);
        f.setForeground(new Color(44, 44, 46));
        f.setCaretColor(new Color(10, 132, 255));
        f.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(180, 180, 190)));
        f.setBackground(new Color(250, 250, 252));
        f.setOpaque(false);
        f.setToolTipText(hint);
    }

    // Reuse same button style as LoginPanel
    private JButton createMacButton(String label)
    {
        // (copy from LoginPanel – same implementation)
        JButton b = new JButton(label);
        b.setFont(new Font("SF Pro Text", Font.PLAIN, 15));
        fallbackFont(b, "Helvetica Neue", Font.PLAIN, 15);
        b.setForeground(new Color(28, 28, 30));
        b.setFocusPainted(false);
        b.setBorder(BorderFactory.createEmptyBorder(12, 36, 12, 36));
        b.setBackground(new Color(240, 240, 245));
        b.setOpaque(true);
        b.setBorderPainted(false);
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return b;
    }

    private void fallbackFont(JComponent c, String name, int style, int size)
    {
        Font f = c.getFont();
        if (f.getFamily().equals("Dialog") || f.getFamily().contains("SansSerif"))
        {
            c.setFont(new Font(name, style, size));
        }
    }

    private void tryRegister()
    {
        String u  = username.getText().trim();
        String p  = new String(password.getPassword()).trim();
        String fn = firstName.getText().trim();
        String ln = lastName.getText().trim();

        if (u.isEmpty() || p.isEmpty() || fn.isEmpty() || ln.isEmpty())
        {
            JOptionPane.showMessageDialog(this,
                    "All fields are required.",
                    "Incomplete Form",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        try
        {
            auth.registerUser(u, p, fn, ln);
            JOptionPane.showMessageDialog(this,
                    "Account created. Please sign in.",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);
            parent.showLoginPanel();
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