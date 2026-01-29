package Solution.UI;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import java.awt.*;
import javax.swing.*;

/**
 * Unit tests for RoundedButton class
 * Tests custom button rendering and functionality
 */
public class RoundedButtonTest
{
    private RoundedButton button;
    
    /**
     * Sets up test fixture before each test
     */
    @Before
    public void setUp()
    {
        button = new RoundedButton("Test Button");
    }
    
    /**
     * Tests button construction with text
     */
    @Test
    public void testRoundedButtonConstruction()
    {
        // Assert
        assertEquals("Button text should match constructor argument", 
                    "Test Button", button.getText());
        assertFalse("Button should not be opaque for rounded corners", 
                   button.isOpaque());
        assertFalse("Focus painting should be disabled", 
                   button.isFocusPainted());
    }
    
    /**
     * Tests button text setting
     */
    @Test
    public void testSetButtonText()
    {
        // Act
        button.setText("New Text");
        
        // Assert
        assertEquals("Button text should be updated", "New Text", button.getText());
    }
    
    /**
     * Tests button background color
     */
    @Test
    public void testSetBackgroundColor()
    {
        // Arrange
        Color testColor = Color.RED;
        
        // Act
        button.setBackground(testColor);
        
        // Assert
        assertEquals("Background color should be set", testColor, button.getBackground());
    }
    
    /**
     * Tests button foreground color
     */
    @Test
    public void testSetForegroundColor()
    {
        // Arrange
        Color testColor = Color.BLUE;
        
        // Act
        button.setForeground(testColor);
        
        // Assert
        assertEquals("Foreground color should be set", testColor, button.getForeground());
    }
    
    /**
     * Tests button enabled state
     */
    @Test
    public void testSetEnabled()
    {
        // Act
        button.setEnabled(false);
        
        // Assert
        assertFalse("Button should be disabled", button.isEnabled());
        
        // Act
        button.setEnabled(true);
        
        // Assert
        assertTrue("Button should be enabled", button.isEnabled());
    }
    
    /**
     * Tests button visibility
     */
    @Test
    public void testSetVisible()
    {
        // Act
        button.setVisible(false);
        
        // Assert
        assertFalse("Button should be invisible", button.isVisible());
        
        // Act
        button.setVisible(true);
        
        // Assert
        assertTrue("Button should be visible", button.isVisible());
    }
    
    /**
     * Tests button preferred size
     */
    @Test
    public void testGetPreferredSize()
    {
        // Act
        Dimension preferredSize = button.getPreferredSize();
        
        // Assert
        assertNotNull("Preferred size should not be null", preferredSize);
        assertTrue("Preferred size should have positive width", 
                  preferredSize.width > 0);
        assertTrue("Preferred size should have positive height", 
                  preferredSize.height > 0);
    }
    
    /**
     * Tests button content area filled property
     */
    @Test
    public void testSetContentAreaFilled()
    {
        // Act
        button.setContentAreaFilled(false);
        
        // Assert
        assertFalse("Content area should not be filled", button.isContentAreaFilled());
        
        // Act
        button.setContentAreaFilled(true);
        
        // Assert
        assertTrue("Content area should be filled", button.isContentAreaFilled());
    }
    
    /**
     * Tests button border painting
     */
    @Test
    public void testSetBorderPainted()
    {
        // Act
        button.setBorderPainted(false);
        
        // Assert
        assertFalse("Border should not be painted", button.isBorderPainted());
        
        // Act
        button.setBorderPainted(true);
        
        // Assert
        assertTrue("Border should be painted", button.isBorderPainted());
    }
    
    /**
     * Tests button action listener addition
     */
    @Test
    public void testAddActionListener()
    {
        // Arrange
        boolean[] listenerCalled = {false};
        
        // Act
        button.addActionListener(e -> listenerCalled[0] = true);
        
        // Simulate button click
        button.doClick();
        
        // Assert
        assertTrue("Action listener should be called on click", listenerCalled[0]);
    }
    
    /**
     * Tests button with different text lengths
     */
    @Test
    public void testButtonWithDifferentTextLengths()
    {
        // Test short text
        RoundedButton shortButton = new RoundedButton("OK");
        assertEquals("Short text should be preserved", "OK", shortButton.getText());
        
        // Test long text
        RoundedButton longButton = new RoundedButton("This is a very long button text");
        assertEquals("Long text should be preserved", 
                    "This is a very long button text", longButton.getText());
        
        // Test empty text
        RoundedButton emptyButton = new RoundedButton("");
        assertEquals("Empty text should be preserved", "", emptyButton.getText());
    }
    
    /**
     * Tests button font
     */
    @Test
    public void testSetFont()
    {
        // Arrange
        Font testFont = new Font("Arial", Font.BOLD, 14);
        
        // Act
        button.setFont(testFont);
        
        // Assert
        assertEquals("Font should be set correctly", testFont, button.getFont());
    }
    
    /**
     * Tests button tooltip text
     */
    @Test
    public void testSetToolTipText()
    {
        // Arrange
        String tooltip = "Click to perform action";
        
        // Act
        button.setToolTipText(tooltip);
        
        // Assert
        assertEquals("Tooltip text should be set", tooltip, button.getToolTipText());
    }
    
    /**
     * Tests button name property
     */
    @Test
    public void testSetName()
    {
        // Arrange
        String buttonName = "submitButton";
        
        // Act
        button.setName(buttonName);
        
        // Assert
        assertEquals("Button name should be set", buttonName, button.getName());
    }
}