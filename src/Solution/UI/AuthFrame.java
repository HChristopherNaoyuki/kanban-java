package Solution.UI;

import Solution.Logic.AuthManager;
import Solution.Logic.TaskManager;
import javax.swing.*;
import java.awt.*;

/**
 * Main application window with Apple-like minimalism
 * Controls navigation between authentication and task panels
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
        
        // Apple-like light gray background
        getContentPane().setBackground(new Color(248, 248, 248));
        
        // Use system font stack for best compatibility
        setUIFont();
    }
    
    /**
     * Sets consistent Apple-like fonts across the application
     */
    private void setUIFont()
    {
        Font baseFont;
        
        // Try preferred Apple fonts first
        String[] fontCandidates = {
            "SF Pro Display",
            "SF Pro Text", 
            "Helvetica Neue",
            "Segoe UI",
            "Arial"
        };
        
        for (String fontName : fontCandidates)
        {
            Font testFont = new Font(fontName, Font.PLAIN, 13);
            if (!testFont.getFamily().equals(Font.DIALOG))
            {
                baseFont = testFont;
                setFont(baseFont);
                return;
            }
        }
        
        // Fallback to system font
        baseFont = new Font("Dialog", Font.PLAIN, 13);
        setFont(baseFont);
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