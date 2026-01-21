package Solution.UI;

import Solution.Logic.AuthManager;
import Solution.Logic.TaskManager;
import javax.swing.*;
import java.awt.*;

/**
 * Main application frame styled to resemble classic macOS Aqua look (early 2000s)
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
        setSize(420, 340);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Apple-like window background (very light gray/off-white)
        getContentPane().setBackground(new Color(236, 236, 236));

        // Try to use a macOS-like font
        Font defaultFont = new Font("Lucida Grande", Font.PLAIN, 13);
        if (defaultFont.getFamily().equals("Dialog"))
        {
            defaultFont = new Font("Dialog", Font.PLAIN, 13);
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