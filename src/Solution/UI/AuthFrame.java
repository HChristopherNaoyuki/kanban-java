package Solution.UI;

import Solution.Logic.AuthManager;
import Solution.Logic.TaskManager;
import javax.swing.*;
import java.awt.*;

/**
 * Main application window with Apple-inspired design
 * Manages navigation between authentication and task management panels
 */
public final class AuthFrame extends JFrame
{
    private final AuthManager authManager;
    private final TaskManager taskManager;
    
    /**
     * Constructs the main application window
     */
    public AuthFrame()
    {
        this.authManager = new AuthManager();
        this.taskManager = new TaskManager();
        initializeWindow();
        showLoginPanel();
    }
    
    /**
     * Initializes window properties with Apple design principles
     */
    private void initializeWindow()
    {
        setTitle("Task Manager");
        setSize(500, 550);
        setMinimumSize(new Dimension(480, 500));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Use system colors for consistent appearance across platforms
        getContentPane().setBackground(UIManager.getColor("Panel.background"));
        
        // Set system look and feel for native appearance
        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception e)
        {
            // Use default look and feel if system L&F fails
            System.err.println("Unable to set system look and feel: " + e.getMessage());
        }
        
        // Center window on screen
        setLocationRelativeTo(null);
    }
    
    /**
     * Displays the login panel for user authentication
     */
    public void showLoginPanel()
    {
        getContentPane().removeAll();
        add(new LoginPanel(this, authManager));
        revalidate();
        repaint();
    }
    
    /**
     * Displays the registration panel for new account creation
     */
    public void showRegistrationPanel()
    {
        getContentPane().removeAll();
        add(new RegistrationPanel(this, authManager));
        revalidate();
        repaint();
    }
    
    /**
     * Displays the main task management panel
     */
    public void showTaskPanel()
    {
        getContentPane().removeAll();
        add(new TaskPanel(this, taskManager, authManager));
        revalidate();
        repaint();
    }
}