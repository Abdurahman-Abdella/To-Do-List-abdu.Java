import java.util.*;
import java.time.*;

// Task class to represent individual tasks
class Task {
    private String title;
    private String description;
    private LocalDateTime deadline;
    private String priority;
    private boolean isCompleted;

    public Task(String title, String description, LocalDateTime deadline, String priority) {
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.priority = priority;
        this.isCompleted = false;
    }

    public void markAsCompleted() {
        this.isCompleted = true;
    }

    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public LocalDateTime getDeadline() { return deadline; }
    public String getPriority() { return priority; }
    public boolean isCompleted() { return isCompleted; }

    @Override
    public String toString() {
        return "Task: " + title + " | Priority: " + priority + " | Deadline: " + deadline + " | Completed: " + isCompleted;
    }
}

// TaskManager class to manage tasks
class TaskManager {
    private List<Task> tasks;

    public TaskManager() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
        System.out.println("Task added successfully!");
    }

    public void removeTask(String title) {
        tasks.removeIf(task -> task.getTitle().equalsIgnoreCase(title));
        System.out.println("Task removed successfully!");
    }

    public void markTaskCompleted(String title) {
        for (Task task : tasks) {
            if (task.getTitle().equalsIgnoreCase(title)) {
                task.markAsCompleted();
                System.out.println("Task marked as completed!");
                return;
            }
        }
        System.out.println("Task not found.");
    }

    public void displayTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
            return;
        }
        for (Task task : tasks) {
            System.out.println(task);
        }
    }

    public void displayPendingTasks() {
        for (Task task : tasks) {
            if (!task.isCompleted()) {
                System.out.println(task);
            }
        }
    }

    public void displayCompletedTasks() {
        for (Task task : tasks) {
            if (task.isCompleted()) {
                System.out.println(task);
            }
        }
    }

    public void sortTasksByDeadline() {
        tasks.sort(Comparator.comparing(Task::getDeadline));
        System.out.println("Tasks sorted by deadline.");
    }

    public void sortTasksByPriority() {
        tasks.sort(Comparator.comparing(Task::getPriority));
        System.out.println("Tasks sorted by priority.");
    }
}

// Main class to run the program
public class SmartTaskManager {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskManager manager = new TaskManager();

        while (true) {
            System.out.println("\nSmart Task Manager");
            System.out.println("1. Add Task");
            System.out.println("2. Remove Task");
            System.out.println("3. Mark Task as Completed");
            System.out.println("4. View All Tasks");
            System.out.println("5. View Pending Tasks");
            System.out.println("6. View Completed Tasks");
            System.out.println("7. Sort Tasks by Deadline");
            System.out.println("8. Sort Tasks by Priority");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter task title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter description: ");
                    String description = scanner.nextLine();
                    System.out.print("Enter deadline (YYYY-MM-DDTHH:MM): ");
                    LocalDateTime deadline = LocalDateTime.parse(scanner.nextLine());
                    System.out.print("Enter priority (High/Medium/Low): ");
                    String priority = scanner.nextLine();
                    manager.addTask(new Task(title, description, deadline, priority));
                    break;
                case 2:
                    System.out.print("Enter task title to remove: ");
                    String removeTitle = scanner.nextLine();
                    manager.removeTask(removeTitle);
                    break;
                case 3:
                    System.out.print("Enter task title to mark as completed: ");
                    String completedTitle = scanner.nextLine();
                    manager.markTaskCompleted(completedTitle);
                    break;
                case 4:
                    manager.displayTasks();
                    break;
                case 5:
                    manager.displayPendingTasks();
                    break;
                case 6:
                    manager.displayCompletedTasks();
                    break;
                case 7:
                    manager.sortTasksByDeadline();
                    break;
                case 8:
                    manager.sortTasksByPriority();
                    break;
                case 9:
                    System.out.println("Exiting program.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
