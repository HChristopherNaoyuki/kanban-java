package Solution.UI;

import Solution.Logic.AuthManager;
import Solution.Logic.TaskManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Main task panel with welcome message, task display, and action buttons
 * Includes new "View All Tasks" and "Logout" functionality
 */
public class TaskPanel extends JPanel
{
    private final AuthFrame   parentFrame;
    private final TaskManager taskManager;
    private final AuthManager authManager;
    private JTextArea   taskDisplay;

    public TaskPanel(AuthFrame frame, TaskManager taskMgr, AuthManager authMgr)
    {
        this.parentFrame = frame;
        this.taskManager = taskMgr;
        this.authManager = authMgr;

        setLayout(new BorderLayout(0, 40));
        setBorder(BorderFactory.createEmptyBorder(80, 80, 80, 80));
        setBackground(new Color(250, 250, 252));

        add(createWelcomeHeader(),   BorderLayout.NORTH);
        add(createScrollableDisplay(), BorderLayout.CENTER);
        add(createButtonBar(),       BorderLayout.SOUTH);
    }

    private JLabel createWelcomeHeader()
    {
        String name = authManager.getStoredFirstName() + " " + authManager.getStoredLastName();
        JLabel welcome = new JLabel("Welcome, " + name, SwingConstants.CENTER);
        welcome.setFont(new Font("SF Pro Display", Font.PLAIN, 30));
        welcome.setForeground(new Color(28, 28, 30));
        welcome.setBorder(BorderFactory.createEmptyBorder(0, 0, 60, 0));
        return welcome;
    }

    private JScrollPane createScrollableDisplay()
    {
        taskDisplay = new JTextArea();
        taskDisplay.setEditable(false);
        taskDisplay.setLineWrap(true);
        taskDisplay.setWrapStyleWord(true);
        taskDisplay.setFont(new Font("SF Mono", Font.PLAIN, 14));
        taskDisplay.setBackground(Color.WHITE);
        taskDisplay.setForeground(new Color(40, 40, 45));
        taskDisplay.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(225, 225, 230)));

        JScrollPane scroll = new JScrollPane(taskDisplay);
        scroll.setBorder(BorderFactory.createEmptyBorder());
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        return scroll;
    }

    private JPanel createButtonBar()
    {
        JPanel bar = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 20));
        bar.setOpaque(false);

        String[] labels = {
                "Add New Task",
                "View All Tasks",
                "Logout"
        };

        for (String label : labels)
        {
            JButton btn = createFlatButton(label);
            bar.add(btn);

            if (label.equals("Add New Task"))
                btn.addActionListener(e -> showAddTaskDialog());
            else if (label.equals("View All Tasks"))
                btn.addActionListener(e -> showAllTasks());
            else if (label.equals("Logout"))
                btn.addActionListener(e -> parentFrame.showLoginPanel());
        }

        return bar;
    }

    private JButton createFlatButton(String text)
    {
        JButton btn = new JButton(text);
        btn.setFont(new Font("SF Pro Text", Font.PLAIN, 15));
        btn.setForeground(new Color(28, 28, 30));
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(14, 44, 14, 44));
        btn.setBackground(new Color(240, 240, 245));
        btn.setOpaque(true);
        btn.setBorderPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return btn;
    }

    // ──────────────────────────────────────────────
    // Dialog / display logic (minimal changes)
    // ──────────────────────────────────────────────

    private void showAddTaskDialog()
    {
        // (keep your existing implementation – JOptionPane sequence)
        // omitted here for brevity – copy from previous version
    }

    private void showAllTasks()
    {
        String content = taskManager.getAllTasks();
        taskDisplay.setText(content.trim().isEmpty() ? "No tasks have been added yet." : content);
    }
}