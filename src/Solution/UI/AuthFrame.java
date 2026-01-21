package Solution.UI;

import Solution.Logic.AuthManager;
import Solution.Logic.TaskManager;

import javax.swing.*;
import java.awt.*;

/**
 * Main application window (JFrame) that controls navigation between panels
 */
public final class AuthFrame extends JFrame
{
    private final AuthManager authManager;
    private final TaskManager taskManager;

    public AuthFrame()
    {
        this.authManager = new AuthManager();
        this.taskManager = new TaskManager();

        initializeWindow();
        showLoginPanel();
    }

    private void initializeWindow()
    {
        setTitle("Task Manager");
        setSize(540, 640);
        setMinimumSize(new Dimension(500, 560));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Very light modern / macOS-like background
        getContentPane().setBackground(new Color(250, 250, 252));

        // Try to use modern system font stack
        Font font = new Font("SF Pro Text", Font.PLAIN, 15);
        if ("Dialog".equals(font.getFamily()) || font.getFamily().contains("SansSerif"))
        {
            font = new Font("Helvetica Neue", Font.PLAIN, 15);
        }
        if ("Dialog".equals(font.getFamily()))
        {
            font = new Font("Arial", Font.PLAIN, 15);
        }
        setFont(font);
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