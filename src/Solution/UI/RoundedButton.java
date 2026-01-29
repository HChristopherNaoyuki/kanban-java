package Solution.UI;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

/**
 * Custom button with rounded corners for soft edges.
 * This class extends JButton to provide a rounded appearance while maintaining standard button functionality.
 * The background is filled with a rounded rectangle if content area is filled, supporting soft edges.
 * Anti-aliasing is enabled for smoother rendering.
 */
public class RoundedButton extends JButton
{
    // Radius for the rounded corners to achieve soft edges
    private static final int RADIUS = 20;

    /**
     * Constructs a rounded button with the specified text.
     * @param text The text to display on the button.
     */
    public RoundedButton(String text)
    {
        super(text);
        // Set opaque to false to allow transparent corners for rounded effect
        setOpaque(false);
        // Disable focus painting to maintain clean appearance
        setFocusPainted(false);
    }

    /**
     * Overrides paintComponent to draw a rounded background if content area is filled.
     * Applies anti-aliasing for soft edges and handles button states for visual feedback.
     * @param g The graphics context.
     */
    @Override
    protected void paintComponent(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Determine background color based on button state
        Color bg = getBackground();
        if (getModel().isArmed())
        {
            bg = bg.darker();
        }

        int width = getWidth();
        int height = getHeight();

        // Fill rounded background only if content area is filled
        if (isContentAreaFilled())
        {
            g2.setColor(bg);
            g2.fillRoundRect(0, 0, width, height, RADIUS, RADIUS);
        }

        // Paint the text and icon (clipped implicitly by padding)
        super.paintComponent(g2);

        g2.dispose();
    }
}