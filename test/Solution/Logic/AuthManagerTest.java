package Solution.Logic;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import java.lang.reflect.Method;

/**
 * Unit tests for AuthManager class
 * Tests user authentication and registration functionality
 */
public class AuthManagerTest
{
    private AuthManager authManager;
    
    /**
     * Sets up test fixture before each test
     */
    @Before
    public void setUp()
    {
        authManager = new AuthManager();
    }
    
    /**
     * Cleans up test fixture after each test
     */
    @After
    public void tearDown()
    {
        authManager = null;
    }
    
    /**
     * Tests successful user registration with valid credentials
     */
    @Test
    public void testRegisterUserSuccess()
    {
        // Arrange
        String username = "john_";
        String password = "Password123!";
        String firstName = "John";
        String lastName = "Doe";
        
        // Act
        authManager.registerUser(username, password, firstName, lastName);
        
        // Assert - use reflection to access stored values
        assertEquals("John", getStoredFirstName(authManager));
        assertEquals("Doe", getStoredLastName(authManager));
    }
    
    /**
     * Tests registration with invalid username format
     */
    @Test(expected = IllegalArgumentException.class)
    public void testRegisterUserInvalidUsernameNoUnderscore()
    {
        // Arrange
        String username = "john"; // No underscore
        String password = "Password123!";
        String firstName = "John";
        String lastName = "Doe";
        
        // Act & Assert
        authManager.registerUser(username, password, firstName, lastName);
    }
    
    /**
     * Tests registration with username exceeding 5 characters
     */
    @Test(expected = IllegalArgumentException.class)
    public void testRegisterUserUsernameTooLong()
    {
        // Arrange
        String username = "john_smith"; // 10 characters > 5
        String password = "Password123!";
        String firstName = "John";
        String lastName = "Doe";
        
        // Act & Assert
        authManager.registerUser(username, password, firstName, lastName);
    }
    
    /**
     * Tests registration with invalid password (no uppercase)
     */
    @Test(expected = IllegalArgumentException.class)
    public void testRegisterUserPasswordNoUppercase()
    {
        // Arrange
        String username = "john_";
        String password = "password123!"; // No uppercase
        String firstName = "John";
        String lastName = "Doe";
        
        // Act & Assert
        authManager.registerUser(username, password, firstName, lastName);
    }
    
    /**
     * Tests registration with invalid password (too short)
     */
    @Test(expected = IllegalArgumentException.class)
    public void testRegisterUserPasswordTooShort()
    {
        // Arrange
        String username = "john_";
        String password = "Pass1!"; // 6 characters < 8
        String firstName = "John";
        String lastName = "Doe";
        
        // Act & Assert
        authManager.registerUser(username, password, firstName, lastName);
    }
    
    /**
     * Tests registration with invalid password (no number)
     */
    @Test(expected = IllegalArgumentException.class)
    public void testRegisterUserPasswordNoNumber()
    {
        // Arrange
        String username = "john_";
        String password = "Password!"; // No number
        String firstName = "John";
        String lastName = "Doe";
        
        // Act & Assert
        authManager.registerUser(username, password, firstName, lastName);
    }
    
    /**
     * Tests registration with invalid password (no special character)
     */
    @Test(expected = IllegalArgumentException.class)
    public void testRegisterUserPasswordNoSpecialChar()
    {
        // Arrange
        String username = "john_";
        String password = "Password123"; // No special char
        String firstName = "John";
        String lastName = "Doe";
        
        // Act & Assert
        authManager.registerUser(username, password, firstName, lastName);
    }
    
    /**
     * Tests successful login with valid credentials
     */
    @Test
    public void testLoginUserSuccess()
    {
        // Arrange
        String username = "john_";
        String password = "Password123!";
        String firstName = "John";
        String lastName = "Doe";
        
        authManager.registerUser(username, password, firstName, lastName);
        
        // Act
        boolean result = authManager.loginUser(username, password);
        
        // Assert
        assertTrue("Login should succeed with valid credentials", result);
    }
    
    /**
     * Tests login with incorrect username
     */
    @Test(expected = IllegalArgumentException.class)
    public void testLoginUserInvalidUsername()
    {
        // Arrange
        String username = "john_";
        String password = "Password123!";
        String firstName = "John";
        String lastName = "Doe";
        
        authManager.registerUser(username, password, firstName, lastName);
        
        // Act & Assert
        authManager.loginUser("wrong_user", password);
    }
    
    /**
     * Tests login with incorrect password
     */
    @Test(expected = IllegalArgumentException.class)
    public void testLoginUserInvalidPassword()
    {
        // Arrange
        String username = "john_";
        String password = "Password123!";
        String firstName = "John";
        String lastName = "Doe";
        
        authManager.registerUser(username, password, firstName, lastName);
        
        // Act & Assert
        authManager.loginUser(username, "WrongPassword123!");
    }
    
    /**
     * Tests login without registration first
     */
    @Test(expected = IllegalArgumentException.class)
    public void testLoginUserNoRegistration()
    {
        // Act & Assert
        authManager.loginUser("nonexistent", "password");
    }
    
    /**
     * Tests getStoredFirstName after registration
     */
    @Test
    public void testGetStoredFirstName()
    {
        // Arrange
        String username = "john_";
        String password = "Password123!";
        String firstName = "John";
        String lastName = "Doe";
        
        authManager.registerUser(username, password, firstName, lastName);
        
        // Act
        String storedFirstName = authManager.getStoredFirstName();
        
        // Assert
        assertEquals("First name should match registered value", firstName, storedFirstName);
    }
    
    /**
     * Tests getStoredLastName after registration
     */
    @Test
    public void testGetStoredLastName()
    {
        // Arrange
        String username = "john_";
        String password = "Password123!";
        String firstName = "John";
        String lastName = "Doe";
        
        authManager.registerUser(username, password, firstName, lastName);
        
        // Act
        String storedLastName = authManager.getStoredLastName();
        
        // Assert
        assertEquals("Last name should match registered value", lastName, storedLastName);
    }
    
    /**
     * Tests username validation with valid username
     */
    @Test
    public void testIsValidUsernameValid()
    {
        // Arrange
        String username = "john_";
        
        // Act
        boolean result = callPrivateIsValidUsername(authManager, username);
        
        // Assert
        assertTrue("Username with underscore and ≤5 chars should be valid", result);
    }
    
    /**
     * Tests username validation with invalid username (no underscore)
     */
    @Test
    public void testIsValidUsernameNoUnderscore()
    {
        // Arrange
        String username = "john";
        
        // Act
        boolean result = callPrivateIsValidUsername(authManager, username);
        
        // Assert
        assertFalse("Username without underscore should be invalid", result);
    }
    
    /**
     * Tests username validation with invalid username (too long)
     */
    @Test
    public void testIsValidUsernameTooLong()
    {
        // Arrange
        String username = "john_smith";
        
        // Act
        boolean result = callPrivateIsValidUsername(authManager, username);
        
        // Assert
        assertFalse("Username with >5 chars should be invalid", result);
    }
    
    /**
     * Tests username validation with null username
     */
    @Test
    public void testIsValidUsernameNull()
    {
        // Act
        boolean result = callPrivateIsValidUsername(authManager, null);
        
        // Assert
        assertFalse("Null username should be invalid", result);
    }
    
    /**
     * Tests password validation with valid password
     */
    @Test
    public void testIsValidPasswordValid()
    {
        // Arrange
        String password = "Password123!";
        
        // Act
        boolean result = callPrivateIsValidPassword(authManager, password);
        
        // Assert
        assertTrue("Valid password should pass validation", result);
    }
    
    /**
     * Tests password validation with password too short
     */
    @Test
    public void testIsValidPasswordTooShort()
    {
        // Arrange
        String password = "Pass1!";
        
        // Act
        boolean result = callPrivateIsValidPassword(authManager, password);
        
        // Assert
        assertFalse("Password with <8 chars should be invalid", result);
    }
    
    /**
     * Tests password validation with null password
     */
    @Test
    public void testIsValidPasswordNull()
    {
        // Act
        boolean result = callPrivateIsValidPassword(authManager, null);
        
        // Assert
        assertFalse("Null password should be invalid", result);
    }
    
    /**
     * Tests password validation without uppercase
     */
    @Test
    public void testIsValidPasswordNoUppercase()
    {
        // Arrange
        String password = "password123!";
        
        // Act
        boolean result = callPrivateIsValidPassword(authManager, password);
        
        // Assert
        assertFalse("Password without uppercase should be invalid", result);
    }
    
    /**
     * Tests password validation without number
     */
    @Test
    public void testIsValidPasswordNoNumber()
    {
        // Arrange
        String password = "Password!";
        
        // Act
        boolean result = callPrivateIsValidPassword(authManager, password);
        
        // Assert
        assertFalse("Password without number should be invalid", result);
    }
    
    /**
     * Tests password validation without special character
     */
    @Test
    public void testIsValidPasswordNoSpecialChar()
    {
        // Arrange
        String password = "Password123";
        
        // Act
        boolean result = callPrivateIsValidPassword(authManager, password);
        
        // Assert
        assertFalse("Password without special char should be invalid", result);
    }
    
    /**
     * Helper method to call private isValidUsername method using reflection
     */
    private boolean callPrivateIsValidUsername(AuthManager manager, String username)
    {
        try
        {
            Method method = AuthManager.class.getDeclaredMethod("isValidUsername", String.class);
            method.setAccessible(true);
            return (boolean) method.invoke(manager, username);
        }
        catch (Exception e)
        {
            fail("Failed to call private isValidUsername method: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Helper method to call private isValidPassword method using reflection
     */
    private boolean callPrivateIsValidPassword(AuthManager manager, String password)
    {
        try
        {
            Method method = AuthManager.class.getDeclaredMethod("isValidPassword", String.class);
            method.setAccessible(true);
            return (boolean) method.invoke(manager, password);
        }
        catch (Exception e)
        {
            fail("Failed to call private isValidPassword method: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Helper method to access stored first name using reflection
     */
    private String getStoredFirstName(AuthManager manager)
    {
        try
        {
            Method method = AuthManager.class.getDeclaredMethod("getStoredFirstName");
            return (String) method.invoke(manager);
        }
        catch (Exception e)
        {
            fail("Failed to get stored first name: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Helper method to access stored last name using reflection
     */
    private String getStoredLastName(AuthManager manager)
    {
        try
        {
            Method method = AuthManager.class.getDeclaredMethod("getStoredLastName");
            return (String) method.invoke(manager);
        }
        catch (Exception e)
        {
            fail("Failed to get stored last name: " + e.getMessage());
            return null;
        }
    }
}