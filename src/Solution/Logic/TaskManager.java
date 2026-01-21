package Solution.Logic;

import java.util.*;
import java.text.DecimalFormat;

/**
 * Manages task creation and operations with multiple developers
 */
public class TaskManager
{
    private final List<Task> tasks = new ArrayList<>();
    private final DecimalFormat hoursFormat = new DecimalFormat("#.##");

    /**
     * Adds a new task with multiple developers
     * @param name
     * @param description
     * @param developers
     * @param duration
     * @param status
     * @throws IllegalArgumentException if validation fails
     */
    public void addTask(String name, String description, 
                       List<String> developers, float duration, String status)
        throws IllegalArgumentException
    {
        if (description.length() > 50)
        {
            throw new IllegalArgumentException("Description must be ≤50 characters");
        }

        if (developers.size() < 1 || developers.size() > 5)
        {
            throw new IllegalArgumentException("Must have 1-5 developers");
        }

        Task newTask = new Task(name, description, developers, duration, status);
        tasks.add(newTask);
    }

    /**
     * Gets all tasks as formatted string
     * @return 
     */
    public String getAllTasks()
    {
        if (tasks.isEmpty())
        {
            return "No tasks available";
        }

        StringBuilder sb = new StringBuilder();
        for (Task task : tasks)
        {
            sb.append(task).append("\n\n");
        }
        return sb.toString();
    }

    /**
     * Gets tasks by developer
     * @param developer
     * @return 
     * @throws IllegalArgumentException if no tasks found
     */
    public String getTasksByDeveloper(String developer)
        throws IllegalArgumentException
    {
        StringBuilder sb = new StringBuilder();
        for (Task task : tasks)
        {
            if (task.getDevelopers().contains(developer))
            {
                sb.append(task).append("\n\n");
            }
        }

        if (sb.length() == 0)
        {
            throw new IllegalArgumentException("No tasks for " + developer);
        }
        return sb.toString();
    }

    /**
     * Gets task with longest duration
     * @return 
     * @throws IllegalStateException if no tasks exist
     */
    public String getTaskWithLongestDuration()
        throws IllegalStateException
    {
        if (tasks.isEmpty())
        {
            throw new IllegalStateException("No tasks available");
        }

        Task longest = tasks.get(0);
        for (Task task : tasks)
        {
            if (task.getDuration() > longest.getDuration())
            {
                longest = task;
            }
        }
        return "Longest task:\n" + longest;
    }

    /**
     * Represents a task with multiple developers
     */
    private static class Task
    {
        private final String name;
        private final String description;
        private final List<String> developers;
        private final float duration;
        private final String status;
        private final String id;

        /**
         * Constructs a new task
         */
        public Task(String name, String description, 
                   List<String> developers, float duration, String status)
        {
            this.name = name;
            this.description = description;
            this.developers = developers;
            this.duration = duration;
            this.status = status;
            this.id = generateId();
        }

        /**
         * Generates a task ID
         */
        private String generateId()
        {
            String devPart = developers.stream()
                .map(dev -> dev.length() >= 3 ? 
                    dev.substring(dev.length() - 3) : "???")
                .findFirst()
                .orElse("???")
                .toUpperCase();
            
            return (name.length() >= 2 ? name.substring(0, 2) : "??").toUpperCase() + 
                   ":" + devPart;
        }

        /**
         * Gets developer names
         */
        public List<String> getDevelopers()
        {
            return developers;
        }

        /**
         * Gets task duration
         */
        public float getDuration()
        {
            return duration;
        }

        /**
         * String representation of task
         */
        @Override
        public String toString()
        {
            return String.format(
                "Task: %s\nDesc: %s\nDevs: %s\nDuration: %.2f hrs\nStatus: %s\nID: %s",
                name, description, String.join(", ", developers), duration, status, id);
        }
    }
}