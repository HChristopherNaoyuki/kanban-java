package Solution.UI;

import Solution.Logic.AuthManager;
import Solution.Logic.TaskManager;
import javax.swing.*;
import java.awt.*;

/**
 * Main application window with optimized window sizes
 * Registration panel opens in full screen mode
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
     * Initializes window properties with optimal sizing
     */
    private void initializeWindow()
    {
        setTitle("Task Manager");
        
        // Set default size for login and task panels
        setSize(900, 600);
        setMinimumSize(new Dimension(800, 533));
        
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
        
        // Center the window on screen
        setLocationRelativeTo(null);
    }
    
    /**
     * Shows the login panel for user authentication
     */
    public void showLoginPanel()
    {
        // Reset to windowed mode
        setExtendedState(JFrame.NORMAL);
        setSize(900, 600);
        setLocationRelativeTo(null);
        
        getContentPane().removeAll();
        add(new LoginPanel(this, authManager));
        revalidate();
        repaint();
    }
    
    /**
     * Shows the registration panel in full screen mode
     */
    public void showRegistrationPanel()
    {
        // Set to full screen mode
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        
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
        // Reset to windowed mode
        setExtendedState(JFrame.NORMAL);
        setSize(900, 600);
        setLocationRelativeTo(null);
        
        getContentPane().removeAll();
        add(new TaskPanel(this, taskManager, authManager));
        revalidate();
        repaint();
    }
}