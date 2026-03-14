/*import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

private static void SaveTasks(ArrayList<Task> tasks) {
    try (
            BufferedWriter writer = new BufferedWriter(new FileWriter("tasks.txt"))) {
        for (Task task : tasks) {
            writer.write(task.getTitle()
                    + "|" +
                    task.getDescription() +
                    "|" +
                    task.getStatus());
            writer.newLine();
        }

    } catch (IOException e) {
        System.out.println("Error saving file");

    }

} 
    */
