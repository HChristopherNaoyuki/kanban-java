package Solution.Logic;

/**
 * Handles user authentication and registration
 */
public class AuthManager
{
    private String storedUsername;
    private String storedPassword;
    private String storedFirstName;
    private String storedLastName;

    /**
     * Registers a new user
     * @param username
     * @param password
     * @param firstName
     * @param lastName
     * @throws IllegalArgumentException if validation fails
     */
    public void registerUser(String username, String password, 
                           String firstName, String lastName)
        throws IllegalArgumentException
    {
        if (!isValidUsername(username))
        {
            throw new IllegalArgumentException(
                "Username must contain _ and be ≤5 characters");
        }

        if (!isValidPassword(password))
        {
            throw new IllegalArgumentException(
                "Password needs 8+ chars with uppercase, number, and special char");
        }

        storedUsername = username;
        storedPassword = password;
        storedFirstName = firstName;
        storedLastName = lastName;
    }

    /**
     * Authenticates a user
     * @param username
     * @param password
     * @return true if successful
     * @throws IllegalArgumentException if credentials are invalid
     */
    public boolean loginUser(String username, String password)
        throws IllegalArgumentException
    {
        if (storedUsername == null || !storedUsername.equals(username) || 
            !storedPassword.equals(password))
        {
            throw new IllegalArgumentException("Invalid credentials");
        }
        return true;
    }

    /**
     * Gets stored first name
     * @return 
     */
    public String getStoredFirstName()
    {
        return storedFirstName;
    }

    /**
     * Gets stored last name
     * @return 
     */
    public String getStoredLastName()
    {
        return storedLastName;
    }

    /**
     * Validates username format
     */
    boolean isValidUsername(String username)
    {
        return username != null && 
               username.length() <= 5 && 
               username.contains("_");
    }

    /**
     * Validates password complexity
     */
    boolean isValidPassword(String password)
    {
        if (password == null || password.length() < 8)
        {
            return false;
        }

        boolean hasUpper = false;
        boolean hasDigit = false;
        boolean hasSpecial = false;
        
        for (char c : password.toCharArray())
        {
            if (Character.isUpperCase(c))
            {
                hasUpper = true;
            }
            else if (Character.isDigit(c))
            {
                hasDigit = true;
            }
            else if (!Character.isLetterOrDigit(c))
            {
                hasSpecial = true;
            }
        }
        return hasUpper && hasDigit && hasSpecial;
    }
}