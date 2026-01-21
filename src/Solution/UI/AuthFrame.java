package Solution.UI;

import Solution.Logic.AuthManager;
import Solution.Logic.TaskManager;

import javax.swing.*;
import java.awt.*;

/**
 * Main application frame styled toward modern macOS light mode:
 *   - Very light background
 *   - Generous padding & centering
 *   - Clean system font preference
 */
public final class AuthFrame extends JFrame
{
    private final AuthManager authManager;
    private final TaskManager taskManager;

    public AuthFrame()
    {
        authManager = new AuthManager();
        taskManager = new TaskManager();

        initAppearance();
        showLoginPanel();
    }

    private void initAppearance()
    {
        setTitle("Task Manager");
        setSize(500, 580);
        setMinimumSize(new Dimension(460, 520));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        getContentPane().setBackground(new Color(250, 250, 252));  // macOS light neutral

        // Prefer SF Pro → Helvetica Neue → Arial (macOS-like hierarchy)
        Font baseFont = new Font("SF Pro Text", Font.PLAIN, 14);
        if (baseFont.getFamily().equals("Dialog") || baseFont.getFamily().contains("SansSerif"))
        {
            baseFont = new Font("Helvetica Neue", Font.PLAIN, 14);
        }
        if (baseFont.getFamily().equals("Dialog"))
        {
            baseFont = new Font("Arial", Font.PLAIN, 14);
        }
        setFont(baseFont);
    }

    public void showLoginPanel()
    {
        getContentPane().removeAll();
        add(new LoginPanel(this, authManager));
        revalidate();
        repaint();
    }

    public void showRegistrationPanel()
    {
        getContentPane().removeAll();
        add(new RegistrationPanel(this, authManager));
        revalidate();
        repaint();
    }

    public void showTaskPanel()
    {
        getContentPane().removeAll();
        add(new TaskPanel(this, taskManager, authManager));
        revalidate();
        repaint();
    }
}