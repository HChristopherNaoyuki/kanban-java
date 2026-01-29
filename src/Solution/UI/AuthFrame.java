package Solution.UI;

import Solution.Logic.AuthManager;
import Solution.Logic.TaskManager;
import javax.swing.*;
import java.awt.*;

/**
 * Main application window implementing Apple's Human Interface Guidelines
 * Provides consistent navigation foundation and visual structure
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
    
    /**
     * Initializes window with Apple-like design principles
     * Emphasizes clarity, consistency, and visual hierarchy
     */
    private void initializeWindow()
    {
        setTitle("Task Manager");
        setSize(520, 600);
        setMinimumSize(new Dimension(480, 520));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // System background color for consistent appearance
        getContentPane().setBackground(UIManager.getColor("Panel.background"));
        
        // Use system look and feel for native appearance
        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception e)
        {
            // Fallback to default look and feel
        }
    }
    
    /**
     * Shows login panel - entry point for user authentication
     */
    public void showLoginPanel()
    {
        getContentPane().removeAll();
        add(new LoginPanel(this, authManager));
        revalidate();
        repaint();
    }
    
    /**
     * Shows registration panel for new account creation
     */
    public void showRegistrationPanel()
    {
        getContentPane().removeAll();
        add(new RegistrationPanel(this, authManager));
        revalidate();
        repaint();
    }
    
    /**
     * Shows main task management interface
     * Core workspace for task operations
     */
    public void showTaskPanel()
    {
        getContentPane().removeAll();
        add(new TaskPanel(this, taskManager, authManager));
        revalidate();
        repaint();
    }
}