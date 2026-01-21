package Solution;

import Solution.UI.AuthFrame;

/**
 * Main entry point for the Task Management System
 */
public class Solution
{
    /**
     * Application entry point
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args)
    {
        javax.swing.SwingUtilities.invokeLater(() -> 
        {
            AuthFrame frame = new AuthFrame();
            frame.setVisible(true);
        });
    }
}