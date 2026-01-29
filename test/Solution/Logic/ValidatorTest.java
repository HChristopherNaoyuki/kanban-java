package Solution.Logic;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;

/**
 * Unit tests for Validator class
 * Tests various input validation methods
 */
public class ValidatorTest
{
    private Validator validator;
    
    /**
     * Sets up test fixture before each test
     */
    @Before
    public void setUp()
    {
        validator = new Validator();
    }
    
    /**
     * Cleans up test fixture after each test
     */
    @After
    public void tearDown()
    {
        validator = null;
    }
    
    /**
     * Tests valid username with underscore
     */
    @Test
    public void testIsValidUsernameValid()
    {
        // Arrange
        String username = "john_";
        
        // Act
        boolean result = validator.isValidUsername(username);
        
        // Assert
        assertTrue("Username with underscore and ≤5 chars should be valid", result);
    }
    
    /**
     * Tests username without underscore
     */
    @Test
    public void testIsValidUsernameNoUnderscore()
    {
        // Arrange
        String username = "john";
        
        // Act
        boolean result = validator.isValidUsername(username);
        
        // Assert
        assertFalse("Username without underscore should be invalid", result);
    }
    
    /**
     * Tests username exceeding 5 characters
     */
    @Test
    public void testIsValidUsernameTooLong()
    {
        // Arrange
        String username = "john_smith";
        
        // Act
        boolean result = validator.isValidUsername(username);
        
        // Assert
        assertFalse("Username with >5 chars should be invalid", result);
    }
    
    /**
     * Tests null username
     */
    @Test
    public void testIsValidUsernameNull()
    {
        // Act
        boolean result = validator.isValidUsername(null);
        
        // Assert
        assertFalse("Null username should be invalid", result);
    }
    
    /**
     * Tests empty username
     */
    @Test
    public void testIsValidUsernameEmpty()
    {
        // Arrange
        String username = "";
        
        // Act
        boolean result = validator.isValidUsername(username);
        
        // Assert
        assertFalse("Empty username should be invalid", result);
    }
    
    /**
     * Tests username exactly 5 characters with underscore
     */
    @Test
    public void testIsValidUsernameExactly5Chars()
    {
        // Arrange
        String username = "ab_cd";
        
        // Act
        boolean result = validator.isValidUsername(username);
        
        // Assert
        assertTrue("Username exactly 5 chars with underscore should be valid", result);
    }
    
    /**
     * Tests valid password with all requirements
     */
    @Test
    public void testIsValidPasswordValid()
    {
        // Arrange
        String password = "Password123!";
        
        // Act
        boolean result = validator.isValidPassword(password);
        
        // Assert
        assertTrue("Valid password should pass validation", result);
    }
    
    /**
     * Tests password too short
     */
    @Test
    public void testIsValidPasswordTooShort()
    {
        // Arrange
        String password = "Pass1!";
        
        // Act
        boolean result = validator.isValidPassword(password);
        
        // Assert
        assertFalse("Password with <8 chars should be invalid", result);
    }
    
    /**
     * Tests password exactly 8 characters
     */
    @Test
    public void testIsValidPasswordExactly8Chars()
    {
        // Arrange
        String password = "Pass1!@#";
        
        // Act
        boolean result = validator.isValidPassword(password);
        
        // Assert
        assertTrue("Password exactly 8 chars with all requirements should be valid", result);
    }
    
    /**
     * Tests null password
     */
    @Test
    public void testIsValidPasswordNull()
    {
        // Act
        boolean result = validator.isValidPassword(null);
        
        // Assert
        assertFalse("Null password should be invalid", result);
    }
    
    /**
     * Tests password without uppercase
     */
    @Test
    public void testIsValidPasswordNoUppercase()
    {
        // Arrange
        String password = "password123!";
        
        // Act
        boolean result = validator.isValidPassword(password);
        
        // Assert
        assertFalse("Password without uppercase should be invalid", result);
    }
    
    /**
     * Tests password without number
     */
    @Test
    public void testIsValidPasswordNoNumber()
    {
        // Arrange
        String password = "Password!";
        
        // Act
        boolean result = validator.isValidPassword(password);
        
        // Assert
        assertFalse("Password without number should be invalid", result);
    }
    
    /**
     * Tests password without special character
     */
    @Test
    public void testIsValidPasswordNoSpecialChar()
    {
        // Arrange
        String password = "Password123";
        
        // Act
        boolean result = validator.isValidPassword(password);
        
        // Assert
        assertFalse("Password without special char should be invalid", result);
    }
    
    /**
     * Tests password with only uppercase, missing other requirements
     */
    @Test
    public void testIsValidPasswordOnlyUppercase()
    {
        // Arrange
        String password = "PASSWORD";
        
        // Act
        boolean result = validator.isValidPassword(password);
        
        // Assert
        assertFalse("Password with only uppercase should be invalid", result);
    }
    
    /**
     * Tests password with only numbers
     */
    @Test
    public void testIsValidPasswordOnlyNumbers()
    {
        // Arrange
        String password = "12345678";
        
        // Act
        boolean result = validator.isValidPassword(password);
        
        // Assert
        assertFalse("Password with only numbers should be invalid", result);
    }
    
    /**
     * Tests password with only special characters
     */
    @Test
    public void testIsValidPasswordOnlySpecialChars()
    {
        // Arrange
        String password = "!@#$%^&*";
        
        // Act
        boolean result = validator.isValidPassword(password);
        
        // Assert
        assertFalse("Password with only special chars should be invalid", result);
    }
    
    /**
     * Tests various special characters in password
     */
    @Test
    public void testIsValidPasswordVariousSpecialChars()
    {
        // Test different special characters
        String[] specialChars = {"!", "@", "#", "$", "%", "^", "&", "*", "(", ")", "_", "+", "-", "=", "[", "]", "{", "}", ":", ";", ".", "<", ">", "?"};
        
        for (String specialChar : specialChars)
        {
            String password = "Password1" + specialChar;
            boolean result = validator.isValidPassword(password);
            assertTrue("Password with special char '" + specialChar + "' should be valid", result);
        }
    }
    
    /**
     * Tests valid task description
     */
    @Test
    public void testIsValidTaskDescriptionValid()
    {
        // Arrange
        String description = "Implement user login functionality";
        
        // Act
        boolean result = validator.isValidTaskDescription(description);
        
        // Assert
        assertTrue("Valid description should pass validation", result);
    }
    
    /**
     * Tests task description exactly 50 characters
     */
    @Test
    public void testIsValidTaskDescriptionExactly50Chars()
    {
        // Arrange
        String description = "This is exactly fifty characters long description!";
        // Verify it's 50 chars
        assertEquals(50, description.length());
        
        // Act
        boolean result = validator.isValidTaskDescription(description);
        
        // Assert
        assertTrue("Description exactly 50 chars should be valid", result);
    }
    
    /**
     * Tests task description exceeding 50 characters
     */
    @Test
    public void testIsValidTaskDescriptionTooLong()
    {
        // Arrange
        String description = "This description is way too long because it has more than fifty characters in total length";
        
        // Act
        boolean result = validator.isValidTaskDescription(description);
        
        // Assert
        assertFalse("Description >50 chars should be invalid", result);
    }
    
    /**
     * Tests null task description
     */
    @Test
    public void testIsValidTaskDescriptionNull()
    {
        // Act
        boolean result = validator.isValidTaskDescription(null);
        
        // Assert
        assertFalse("Null description should be invalid", result);
    }
    
    /**
     * Tests empty task description
     */
    @Test
    public void testIsValidTaskDescriptionEmpty()
    {
        // Arrange
        String description = "";
        
        // Act
        boolean result = validator.isValidTaskDescription(description);
        
        // Assert
        assertTrue("Empty description should be valid", result);
    }
    
    /**
     * Tests description with 49 characters
     */
    @Test
    public void testIsValidTaskDescription49Chars()
    {
        // Arrange
        String description = "This is forty-nine characters long description";
        
        // Act
        boolean result = validator.isValidTaskDescription(description);
        
        // Assert
        assertTrue("Description with 49 chars should be valid", result);
    }
}