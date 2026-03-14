public class Task {

    int id;
    String description;
    String status;
    String createdAt;
    String updatedAt;

    public Task(int id, String description) {
        this.id = id;
        this.description = description;
        this.status = "todo";
        this.createdAt = java.time.LocalDateTime.now().toString();
        this.updatedAt = this.createdAt;
    }

}