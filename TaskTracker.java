import java.util.ArrayList;
import java.util.Scanner;

public class TaskTracker {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        ArrayList<Task> tasks;

        try {
            tasks = loadFromFile();
        } catch (Exception e) {
            tasks = new ArrayList<>();
        }

        while (true) {

            System.out.println("\nChoose option:");
            System.out.println("1 Add task");
            System.out.println("2 Update task");
            System.out.println("3 Delete task");
            System.out.println("4 Mark done");
            System.out.println("5 Mark in progress");
            System.out.println("6 List all");
            System.out.println("7 List done");
            System.out.println("8 List todo");
            System.out.println("9 List in-progress");
            System.out.println("10 Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    addTask(scanner, tasks);
                    break;

                case 2:
                    updateTask(scanner, tasks);
                    break;

                case 3:
                    deleteTask(scanner, tasks);
                    break;

                case 4:
                    markDone(scanner, tasks);
                    break;

                case 5:
                    markInProgress(scanner, tasks);
                    break;

                case 6:
                    listTasks(tasks, "all");
                    break;

                case 7:
                    listTasks(tasks, "done");
                    break;

                case 8:
                    listTasks(tasks, "todo");
                    break;

                case 9:
                    listTasks(tasks, "in-progress");
                    break;

                case 10:
                    System.out.println("Goodbye");
                    return;

                default:
                    System.out.println("Invalid choice");

            }
        }
    }

    private static void addTask(Scanner scanner, ArrayList<Task> tasks) {

        System.out.println("Enter description:");
        String description = scanner.nextLine();

        int id = 1;

        if (!tasks.isEmpty()) {
            id = tasks.get(tasks.size() - 1).id + 1;
        }

        Task newTask = new Task(id, description);
        tasks.add(newTask);

        saveToFile(tasks);

        System.out.println("Task added (ID: " + id + ")");
    }

    private static void updateTask(Scanner scanner, ArrayList<Task> tasks) {

        if (tasks.isEmpty()) {
            System.out.println("No tasks available");
            return;
        }

        listTasks(tasks, "all");

        System.out.println("Enter task number:");
        int index = scanner.nextInt() - 1;
        scanner.nextLine();

        if (index < 0 || index >= tasks.size()) {
            System.out.println("Invalid task");
            return;
        }

        System.out.println("Enter new description:");
        tasks.get(index).description = scanner.nextLine();
        tasks.get(index).updatedAt = java.time.LocalDateTime.now().toString();

        saveToFile(tasks);

        System.out.println("Task updated");
    }

    private static void deleteTask(Scanner scanner, ArrayList<Task> tasks) {

        if (tasks.isEmpty()) {
            System.out.println("No tasks available");
            return;
        }

        listTasks(tasks, "all");

        System.out.println("Enter task number:");
        int index = scanner.nextInt() - 1;
        scanner.nextLine();

        if (index < 0 || index >= tasks.size()) {
            System.out.println("Invalid task");
            return;
        }

        tasks.remove(index);

        saveToFile(tasks);

        System.out.println("Task deleted");
    }

    private static void markDone(Scanner scanner, ArrayList<Task> tasks) {

        if (tasks.isEmpty()) {
            System.out.println("No tasks available");
            return;
        }

        listTasks(tasks, "all");

        System.out.println("Enter task number:");
        int index = scanner.nextInt() - 1;
        scanner.nextLine();

        if (index < 0 || index >= tasks.size()) {
            System.out.println("Invalid task");
            return;
        }

        tasks.get(index).status = "done";
        tasks.get(index).updatedAt = java.time.LocalDateTime.now().toString();

        saveToFile(tasks);

        System.out.println("Marked as done");
    }

    private static void markInProgress(Scanner scanner, ArrayList<Task> tasks) {

        if (tasks.isEmpty()) {
            System.out.println("No tasks available");
            return;
        }

        listTasks(tasks, "all");

        System.out.println("Enter task number:");
        int index = scanner.nextInt() - 1;
        scanner.nextLine();

        if (index < 0 || index >= tasks.size()) {
            System.out.println("Invalid task");
            return;
        }

        tasks.get(index).status = "in-progress";
        tasks.get(index).updatedAt = java.time.LocalDateTime.now().toString();

        saveToFile(tasks);

        System.out.println("Marked in progress");
    }

    private static void listTasks(ArrayList<Task> tasks, String filter) {

        if (tasks.isEmpty()) {
            System.out.println("No tasks found");
            return;
        }

        for (int i = 0; i < tasks.size(); i++) {

            Task t = tasks.get(i);

            if (filter.equals("all") || t.status.equals(filter)) {

                System.out.println(
                        (i + 1) + ". " +
                        t.description +
                        " [" + t.status + "]"
                );
            }
        }
    }

    private static void saveToFile(ArrayList<Task> tasks) {

        try {

            StringBuilder json = new StringBuilder("[\n");

            for (int i = 0; i < tasks.size(); i++) {

                Task t = tasks.get(i);

                json.append("{\n");
                json.append("\"id\":").append(t.id).append(",\n");
                json.append("\"description\":\"").append(t.description).append("\",\n");
                json.append("\"status\":\"").append(t.status).append("\",\n");
                json.append("\"createdAt\":\"").append(t.createdAt).append("\",\n");
                json.append("\"updatedAt\":\"").append(t.updatedAt).append("\"\n");
                json.append("}");

                if (i < tasks.size() - 1) {
                    json.append(",");
                }

                json.append("\n");
            }

            json.append("]");

            java.nio.file.Files.writeString(
                    java.nio.file.Path.of("tasks.json"),
                    json.toString()
            );

        } catch (Exception e) {

            System.out.println("Error saving file");

        }
    }

    private static ArrayList<Task> loadFromFile() throws Exception {

        ArrayList<Task> tasks = new ArrayList<>();

        java.nio.file.Path path = java.nio.file.Path.of("tasks.json");

        if (!java.nio.file.Files.exists(path)) {

            java.nio.file.Files.writeString(path, "[]");
            return tasks;
        }

        String content = java.nio.file.Files.readString(path);

        content = content.replace("[", "")
                .replace("]", "")
                .trim();

        if (content.isEmpty()) {
            return tasks;
        }

        String[] objects = content.split("\\},\\s*\\{");

        for (String obj : objects) {

            obj = obj.replace("{", "")
                    .replace("}", "");

            String[] lines = obj.split(",");

            int id = Integer.parseInt(lines[0].split(":")[1]);

            String description = lines[1]
                    .split(":")[1]
                    .replace("\"", "");

            String status = lines[2]
                    .split(":")[1]
                    .replace("\"", "");

            String createdAt = lines[3]
                    .split(":")[1]
                    .replace("\"", "");

            String updatedAt = lines[4]
                    .split(":")[1]
                    .replace("\"", "");

            Task t = new Task(id, description);

            t.status = status;
            t.createdAt = createdAt;
            t.updatedAt = updatedAt;

            tasks.add(t);
        }

        return tasks;
    }
}