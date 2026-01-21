package Solution.UI;

import Solution.Logic.AuthManager;
import Solution.Logic.TaskManager;

import javax.swing.*;
import java.awt.*;

/**
 * Main application frame with macOS-inspired light appearance
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
        setSize(520, 620);
        setMinimumSize(new Dimension(480, 540));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        getContentPane().setBackground(new Color(250, 250, 252));

        // Prefer SF Pro family → fallbacks
        Font base = new Font("SF Pro Display", Font.PLAIN, 15);
        if (base.getFamily().equals("Dialog") || base.getFamily().contains("SansSerif"))
        {
            base = new Font("Helvetica Neue", Font.PLAIN, 15);
        }
        if (base.getFamily().equals("Dialog"))
        {
            base = new Font("Arial", Font.PLAIN, 15);
        }
        setFont(base);
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