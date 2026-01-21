package Solution.UI;

import Solution.Logic.AuthManager;
import Solution.Logic.TaskManager;

import javax.swing.*;
import java.awt.*;

/**
 * Main application frame – kept very light and spacious
 */
public final class AuthFrame extends JFrame
{
    private final AuthManager authManager;
    private final TaskManager taskManager;

    public AuthFrame()
    {
        authManager = new AuthManager();
        taskManager = new TaskManager();

        initWindow();
        showLoginPanel();
    }

    private void initWindow()
    {
        setTitle("Task Manager");
        // ── Increased size so content is never clipped ──
        setSize(480, 520);
        setMinimumSize(new Dimension(460, 480));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Very light modern neutral background
        getContentPane().setBackground(new Color(250, 250, 252));

        // Prefer clean system fonts
        Font uiFont = new Font("Helvetica Neue", Font.PLAIN, 14);
        if (uiFont.getFamily().equals("Dialog"))
        {
            uiFont = new Font("Arial", Font.PLAIN, 14);
        }
        setFont(uiFont);
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