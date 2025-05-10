package Domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {

    private static int count=0;
    private int id;
    private String description;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private static final DateTimeFormatter form = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");


    public Task(String description) {
        this.id = ++count;
        this.description = description;
        this.status = "todo";
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
    public static Task fromJson(String item){

        item = item.replace("{","").replace("}","").replace("\"","");
        String[] items = item.split(",");

        String id = items[0].split(":",2)[1].strip();
        String description = items[1].split(":",2)[1].strip();
        String status = items[2].split(":",2)[1].strip();
        String createdAt = items[3].split("[a-z]:",2)[1].strip();
        String updatedAt = items[4].split("[a-z]:",2)[1].strip();

        Task task = new Task(description);
        task.id=Integer.parseInt(id);
        task.description=description;
        task.status=status;
        task.createdAt = LocalDateTime.parse(createdAt, form);
        task.updatedAt = LocalDateTime.parse(updatedAt, form);

        if (Integer.parseInt(id) > count) {
            count= Integer.parseInt(id);
        }

        return  task;
    }
    public static Task Ghost(String description) {
        Task ghost = new Task(description);
        count=count-1;
        ghost.id=0;
        ghost.description=description;
        ghost.status="default";
        ghost.createdAt = LocalDateTime.parse("01/01/2000 00:00",form);
        ghost.updatedAt = LocalDateTime.parse("01/01/2000 00:00",form);
        return  ghost;
    }

    public Integer getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
    public String toJson(){
        String task;
        task="{ \"id\": " +getId() + ", " +
                "\"description\": \"" +getDescription() + "\", " +
                "\"status\": \"" +getStatus() + "\", " +
                "\"createdAt\": \"" +getCreatedAt().format(form) + "\", " +
                "\"updatedAt\": \"" +getUpdatedAt().format(form) + "\" }";


        return task;
    }
    @Override
    public  String toString(){
        String task;
        String id = "id: "+getId();
        String description = "description: "+getDescription();
        String status = "status: "+getStatus();
        String createdAt = "createdAt: "+getCreatedAt();
        String updatedAt = "updatedAt: "+getUpdatedAt();
        task=id+" , "+description+" , "+status+" , "+createdAt+" , "+updatedAt;
        return  task;
    }
}
