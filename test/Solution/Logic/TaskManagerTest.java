package Solution.Logic;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

/**
 * Unit tests for TaskManager class
 * Tests task creation, retrieval, and operations
 */
public class TaskManagerTest
{
    private TaskManager taskManager;
    
    /**
     * Sets up test fixture before each test
     */
    @Before
    public void setUp()
    {
        taskManager = new TaskManager();
    }
    
    /**
     * Cleans up test fixture after each test
     */
    @After
    public void tearDown()
    {
        taskManager = null;
    }
    
    /**
     * Tests successful addition of a task
     */
    @Test
    public void testAddTaskSuccess()
    {
        // Arrange
        String name = "Login Feature";
        String description = "Implement user login functionality";
        List<String> developers = Arrays.asList("John Doe", "Jane Smith");
        float duration = 8.5f;
        String status = "Doing";
        
        // Act
        taskManager.addTask(name, description, developers, duration, status);
        
        // Assert
        String allTasks = taskManager.getAllTasks();
        assertTrue("Task should be added successfully", allTasks.contains("Login Feature"));
        assertTrue("Task should contain description", allTasks.contains("Implement user login"));
        assertTrue("Task should contain developers", allTasks.contains("John Doe"));
    }
    
    /**
     * Tests addition of task with description exceeding 50 characters
     */
    @Test(expected = IllegalArgumentException.class)
    public void testAddTaskDescriptionTooLong()
    {
        // Arrange
        String name = "Login Feature";
        String description = "This description is definitely way too long because it has more than fifty characters in total length";
        List<String> developers = Arrays.asList("John Doe");
        float duration = 8.5f;
        String status = "Doing";
        
        // Act & Assert
        taskManager.addTask(name, description, developers, duration, status);
    }
    
    /**
     * Tests addition of task with no developers
     */
    @Test(expected = IllegalArgumentException.class)
    public void testAddTaskNoDevelopers()
    {
        // Arrange
        String name = "Login Feature";
        String description = "Implement login";
        List<String> developers = new ArrayList<>(); // Empty list
        float duration = 8.5f;
        String status = "Doing";
        
        // Act & Assert
        taskManager.addTask(name, description, developers, duration, status);
    }
    
    /**
     * Tests addition of task with more than 5 developers
     */
    @Test(expected = IllegalArgumentException.class)
    public void testAddTaskTooManyDevelopers()
    {
        // Arrange
        String name = "Login Feature";
        String description = "Implement login";
        List<String> developers = Arrays.asList("Dev1", "Dev2", "Dev3", "Dev4", "Dev5", "Dev6");
        float duration = 8.5f;
        String status = "Doing";
        
        // Act & Assert
        taskManager.addTask(name, description, developers, duration, status);
    }
    
    /**
     * Tests retrieval of all tasks when no tasks exist
     */
    @Test
    public void testGetAllTasksEmpty()
    {
        // Act
        String result = taskManager.getAllTasks();
        
        // Assert
        assertEquals("Should return message for no tasks", "No tasks available", result);
    }
    
    /**
     * Tests retrieval of all tasks with multiple tasks
     */
    @Test
    public void testGetAllTasksMultiple()
    {
        // Arrange
        addSampleTasks();
        
        // Act
        String result = taskManager.getAllTasks();
        
        // Assert
        assertTrue("Should contain Login Feature", result.contains("Login Feature"));
        assertTrue("Should contain Dashboard", result.contains("Dashboard"));
        assertTrue("Should contain API Integration", result.contains("API Integration"));
    }
    
    /**
     * Tests getting tasks by developer when developer has tasks
     */
    @Test
    public void testGetTasksByDeveloperSuccess()
    {
        // Arrange
        addSampleTasks();
        String developer = "John Doe";
        
        // Act
        String result = taskManager.getTasksByDeveloper(developer);
        
        // Assert
        assertTrue("Should contain tasks for John Doe", result.contains("John Doe"));
        assertFalse("Should not contain tasks for other developers", result.contains("Jane Smith"));
    }
    
    /**
     * Tests getting tasks by developer when no tasks exist for that developer
     */
    @Test(expected = IllegalArgumentException.class)
    public void testGetTasksByDeveloperNotFound()
    {
        // Arrange
        addSampleTasks();
        String developer = "Nonexistent Developer";
        
        // Act & Assert
        taskManager.getTasksByDeveloper(developer);
    }
    
    /**
     * Tests getting task with longest duration
     */
    @Test
    public void testGetTaskWithLongestDuration()
    {
        // Arrange
        addSampleTasks();
        
        // Act
        String result = taskManager.getTaskWithLongestDuration();
        
        // Assert
        assertTrue("Should identify API Integration as longest", result.contains("API Integration"));
        assertTrue("Should contain duration 20.0", result.contains("20.00"));
    }
    
    /**
     * Tests getting longest task when no tasks exist
     */
    @Test(expected = IllegalStateException.class)
    public void testGetTaskWithLongestDurationNoTasks()
    {
        // Act & Assert
        taskManager.getTaskWithLongestDuration();
    }
    
    /**
     * Tests task ID generation with valid developer names
     */
    @Test
    public void testTaskIdGeneration()
    {
        // Arrange
        String name = "Login Feature";
        String description = "Implement login";
        List<String> developers = Arrays.asList("John Doe");
        float duration = 8.5f;
        String status = "Doing";
        
        // Act
        taskManager.addTask(name, description, developers, duration, status);
        
        // Assert
        String allTasks = taskManager.getAllTasks();
        assertTrue("Task ID should be generated", allTasks.contains("LO:Doe"));
    }
    
    /**
     * Tests task ID generation with short developer name
     */
    @Test
    public void testTaskIdGenerationShortDeveloperName()
    {
        // Arrange
        String name = "API";
        String description = "Implement API";
        List<String> developers = Arrays.asList("Jo");
        float duration = 5.0f;
        String status = "To Do";
        
        // Act
        taskManager.addTask(name, description, developers, duration, status);
        
        // Assert
        String allTasks = taskManager.getAllTasks();
        // With short name "Jo", should take "??" for last 3 chars
        assertTrue("Task ID should handle short names", allTasks.contains("API:???"));
    }
    
    /**
     * Tests task ID generation with short task name
     */
    @Test
    public void testTaskIdGenerationShortTaskName()
    {
        // Arrange
        String name = "A";
        String description = "Short task";
        List<String> developers = Arrays.asList("Johnathan");
        float duration = 3.0f;
        String status = "Done";
        
        // Act
        taskManager.addTask(name, description, developers, duration, status);
        
        // Assert
        String allTasks = taskManager.getAllTasks();
        // With short name "A", should take "??" for first 2 chars
        assertTrue("Task ID should handle short task names", allTasks.contains("??:HAN"));
    }
    
    /**
     * Tests task ordering in getAllTasks
     */
    @Test
    public void testTaskOrdering()
    {
        // Arrange
        taskManager.addTask("First Task", "First", Arrays.asList("Dev1"), 5.0f, "To Do");
        taskManager.addTask("Second Task", "Second", Arrays.asList("Dev2"), 10.0f, "Doing");
        taskManager.addTask("Third Task", "Third", Arrays.asList("Dev3"), 15.0f, "Done");
        
        // Act
        String result = taskManager.getAllTasks();
        
        // Assert
        int firstIndex = result.indexOf("First Task");
        int secondIndex = result.indexOf("Second Task");
        int thirdIndex = result.indexOf("Third Task");
        
        assertTrue("Tasks should appear in order of addition", 
                  firstIndex < secondIndex && secondIndex < thirdIndex);
    }
    
    /**
     * Helper method to add sample tasks for testing
     */
    private void addSampleTasks()
    {
        taskManager.addTask(
            "Login Feature",
            "Implement user login functionality",
            Arrays.asList("John Doe", "Jane Smith"),
            8.5f,
            "Doing"
        );
        
        taskManager.addTask(
            "Dashboard",
            "Create user dashboard with widgets",
            Arrays.asList("Jane Smith", "Bob Johnson"),
            12.0f,
            "To Do"
        );
        
        taskManager.addTask(
            "API Integration",
            "Integrate with external API services",
            Arrays.asList("John Doe", "Alice Brown", "Bob Johnson"),
            20.0f,
            "Doing"
        );
    }
}