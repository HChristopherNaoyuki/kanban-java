package Solution.UI;

import javax.swing.*;
import java.awt.*;
import Solution.Logic.AuthManager;
import Solution.Logic.TaskManager;

/**
 * Main application frame with classic 2000s UI style
 */
public final class AuthFrame extends JFrame
{
    private final AuthManager authManager;
    private final TaskManager taskManager;

    /**
     * Constructs the main application frame
     */
    public AuthFrame()
    {
        authManager = new AuthManager();
        taskManager = new TaskManager();
        setupFrame();
        showLoginPanel();
    }

    /**
     * Configures frame properties
     */
    private void setupFrame()
    {
        setTitle("Task Manager 2002");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(SystemColor.control);
    }

    /**
     * Shows login panel
     */
    public void showLoginPanel()
    {
        getContentPane().removeAll();
        add(new LoginPanel(this, authManager));
        revalidate();
    }

    /**
     * Shows registration panel
     */
    public void showRegistrationPanel()
    {
        getContentPane().removeAll();
        add(new RegistrationPanel(this, authManager));
        revalidate();
    }

    /**
     * Shows task management panel with welcome message
     */
    public void showTaskPanel()
    {
        getContentPane().removeAll();
        add(new TaskPanel(this, taskManager, authManager));
        revalidate();
    }
}