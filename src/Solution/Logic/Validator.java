package Solution.Logic;

/**
 * Provides validation methods for various inputs
 */
public class Validator
{
    /**
     * Validates username format
     * @param username
     * @return 
     */
    public boolean isValidUsername(String username)
    {
        return username != null && 
               username.length() <= 5 && 
               username.contains("_");
    }

    /**
     * Validates password complexity
     * @param password
     * @return 
     */
    public boolean isValidPassword(String password)
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
            
            if (hasUpper && hasDigit && hasSpecial)
            {
                return true;
            }
        }
        
        return false;
    }

    /**
     * Validates task description length
     * @param description
     * @return 
     */
    public boolean isValidTaskDescription(String description)
    {
        return description != null && description.length() <= 50;
    }
}