package Solution.UI;

import Solution.Logic.AuthManager;
import Solution.Logic.TaskManager;
import javax.swing.*;
import java.awt.*;

/**
 * Main application window with optimized 10x16 aspect ratio
 * Provides consistent navigation with Apple-inspired design
 */
public final class AuthFrame extends JFrame
{
    private final AuthManager authManager;
    private final TaskManager taskManager;
    
    /**
     * Constructs the main application window with 10x16 aspect ratio
     */
    public AuthFrame()
    {
        this.authManager = new AuthManager();
        this.taskManager = new TaskManager();
        initializeWindow();
        showLoginPanel();
    }
    
    /**
     * Initializes window properties with 10x16 aspect ratio
     * Base size: 400x640 (10:16 aspect ratio)
     */
    private void initializeWindow()
    {
        setTitle("Task Manager");
        
        // 10:16 aspect ratio - base size 400x640
        setSize(400, 640);
        setMinimumSize(new Dimension(350, 560));
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Use light gray background for Apple-like appearance
        getContentPane().setBackground(new Color(248, 248, 248));
        
        // Set system look and feel for consistent appearance
        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception e)
        {
            // Fall back to default look and feel
            System.err.println("Unable to set system look and feel: " + e.getMessage());
        }
    }
    
    /**
     * Shows the login panel for user authentication
     */
    public void showLoginPanel()
    {
        getContentPane().removeAll();
        add(new LoginPanel(this, authManager));
        revalidate();
        repaint();
    }
    
    /**
     * Shows the registration panel for new account creation
     */
    public void showRegistrationPanel()
    {
        getContentPane().removeAll();
        add(new RegistrationPanel(this, authManager));
        revalidate();
        repaint();
    }
    
    /**
     * Shows the main task management panel
     */
    public void showTaskPanel()
    {
        getContentPane().removeAll();
        add(new TaskPanel(this, taskManager, authManager));
        revalidate();
        repaint();
    }
}