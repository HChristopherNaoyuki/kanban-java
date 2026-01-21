package Solution.UI;

import Solution.Logic.AuthManager;

import javax.swing.*;
import java.awt.*;

/**
 * Modern macOS-inspired minimalist login screen
 *   - Underline-style fields
 *   - Flat rounded buttons
 *   - Centered vertical layout with breathing room
 *   - No heavy borders / visual noise
 */
public class LoginPanel extends JPanel
{
    private final AuthFrame      parent;
    private final AuthManager    auth;
    private final JTextField     username;
    private final JPasswordField password;

    public LoginPanel(AuthFrame parent, AuthManager auth)
    {
        this.parent = parent;
        this.auth   = auth;

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(100, 60, 100, 60));
        setBackground(new Color(250, 250, 252));

        Box vertical = Box.createVerticalBox();
        vertical.setAlignmentX(CENTER_ALIGNMENT);

        // Title
        JLabel title = new JLabel("Sign In", SwingConstants.CENTER);
        title.setFont(new Font("SF Pro Display", Font.PLAIN, 28));
        fallbackFont(title, "Helvetica Neue", Font.PLAIN, 28);
        title.setForeground(new Color(28, 28, 30));
        title.setBorder(BorderFactory.createEmptyBorder(0, 0, 80, 0));
        vertical.add(title);
        vertical.add(Box.createVerticalStrut(20));

        // Fields
        username = createUnderlineField("Username", 26);
        vertical.add(username);
        vertical.add(Box.createVerticalStrut(36));

        password = createUnderlinePasswordField("Password", 26);
        vertical.add(password);
        vertical.add(Box.createVerticalStrut(60));

        // Buttons
        JPanel btnRow = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 0));
        btnRow.setOpaque(false);

        JButton signIn = createMacButton("Sign In");
        signIn.addActionListener(e -> tryLogin());
        btnRow.add(signIn);

        JButton register = createMacButton("Register");
        register.addActionListener(e -> parent.showRegistrationPanel());
        btnRow.add(register);

        vertical.add(btnRow);

        // Center the whole stack
        JPanel center = new JPanel(new GridBagLayout());
        center.setOpaque(false);
        center.add(vertical);

        add(center, BorderLayout.CENTER);
    }

    private JTextField createUnderlineField(String hint, int columns)
    {
        JTextField f = new JTextField(columns);
        styleUnderlineField(f, hint);
        return f;
    }

    private JPasswordField createUnderlinePasswordField(String hint, int columns)
    {
        JPasswordField f = new JPasswordField(columns);
        styleUnderlineField(f, hint);
        return f;
    }

    private void styleUnderlineField(JTextField f, String hint)
    {
        f.setFont(new Font("SF Pro Text", Font.PLAIN, 17));
        fallbackFont(f, "Helvetica Neue", Font.PLAIN, 17);
        f.setForeground(new Color(44, 44, 46));
        f.setCaretColor(new Color(10, 132, 255));  // macOS accent
        f.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(180, 180, 190)));
        f.setBackground(new Color(250, 250, 252));
        f.setOpaque(false);
        f.setToolTipText(hint);
    }

    private JButton createMacButton(String label)
    {
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

        // Slight rounding simulation (macOS-like)
        b.setBorder(new javax.swing.border.AbstractBorder()
        {
            @Override
            public void paintBorder(Component c, Graphics g, int x, int y, int w, int h)
            {
                // optional very light rounded rectangle if desired
            }

            @Override
            public Insets getBorderInsets(Component c) { return new Insets(0,0,0,0); }

            @Override
            public boolean isBorderOpaque() { return false; }
        });

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

    private void tryLogin()
    {
        String u = username.getText().trim();
        String p = new String(password.getPassword()).trim();

        if (u.isEmpty() || p.isEmpty())
        {
            JOptionPane.showMessageDialog(this,
                    "Username and password are required.",
                    "Missing Fields",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        try
        {
            auth.loginUser(u, p);
            parent.showTaskPanel();
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