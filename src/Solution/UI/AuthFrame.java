package Solution.UI;

import Solution.Logic.AuthManager;
import Solution.Logic.TaskManager;

import javax.swing.*;
import java.awt.*;

/**
 * Main application window with minimalist styling:
 *   - Very light neutral background
 *   - Generous padding
 *   - Clean, simple appearance
 */
public final class AuthFrame extends JFrame
{
    private final AuthManager authManager;
    private final TaskManager taskManager;

    public AuthFrame()
    {
        authManager = new AuthManager();
        taskManager = new TaskManager();

        setupFrame();
        showLoginPanel();
    }

    private void setupFrame()
    {
        setTitle("Task Manager");
        setSize(460, 380);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Very light neutral background
        getContentPane().setBackground(new Color(248, 248, 248));

        // Try to use a clean system font stack
        Font defaultFont = new Font("Helvetica Neue", Font.PLAIN, 13);
        if (defaultFont.getFamily().equals("Dialog"))
        {
            defaultFont = new Font("Arial", Font.PLAIN, 13);
        }
        setFont(defaultFont);
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