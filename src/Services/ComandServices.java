package Services;

import Domain.Task;

import java.nio.file.Files;

import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class ComandServices {
    private final Path filepath = Path.of("Resources/Task.JSON");
    private final List<Task> tasks;
    public ComandServices() {
        this.tasks = loadlist();
    }
    public List<Task> loadlist(){
        List<Task> listask = new ArrayList<>();
        try{
            String file = Files.readString(filepath);

                String []data = file.replace("[", "")
                        .replace("]", "")
                        .split("},");

                for(String item : data){
                    if(!item.endsWith("}")){
                        item = item + "}";
                        listask.add(Task.fromJson(item));

                    }else{
                        listask.add(Task.fromJson(item));
                    }
                }

        }catch(Exception e){
            System.out.println("valio generando respaldo");
            String jdeft = "default";
            listask.add(Task.Ghost(jdeft));
        }
        return  listask;
    }
    public void Add(String description){
        Task task = new Task(description);
        tasks.add(task);
        System.out.println("Task added success");
    }
    public void Update(String id,String description){
        for(Task item : tasks) {
            if(item.getId()==Integer.parseInt(id)){
                item.setDescription(description);
                item.setUpdatedAt(LocalDateTime.now());
                System.out.println("task update success");
                return;
            }
        }
        System.out.println("Id not found");
    }
    public void Delete(String id){
        for(Task item : tasks) {
            if(item.getId()==Integer.parseInt(id)){
                tasks.remove(item);
                System.out.println("task delete success");
                return;
            }
        }
        System.out.println("Id not found");
    }
    public void MarkDone(String id){
        for(Task item : tasks) {
            if(item.getId()==Integer.parseInt(id)){
                item.setStatus("done");
                System.out.println("task marked done success");
                return;
            }
        }
        System.out.println("Id not found");
    }
    public void MarkProgress(String id){
        for(Task item : tasks) {
            if(item.getId()==Integer.parseInt(id)){
                item.setStatus("in-progress");
                System.out.println("task marked done success");
                return;
            }
        }
        System.out.println("Id not found");
    }
    public void List(){
        if(tasks.isEmpty()) {
            System.out.println("no data");
        }else{
            for(Task item : tasks) {
                System.out.println(item.toString());
            }
        }
    }
    public void ListStatus(String ListStatus){

        if(tasks.isEmpty()) {
            System.out.println("no data");
        }
        else{
            for(Task item : tasks) {
                if(item.getStatus().equals(ListStatus)){
                    System.out.println(item);
                }
            }
        }
    }
    public void savelist(){
        try {
            StringBuilder cont;
            cont = new StringBuilder("[\n");
            int count=1;
            for(Task item : tasks) {
                if(count <tasks.size()){
                    cont.append(item.toJson()).append(",\n");
                }else{
                    cont.append(item.toJson()).append("\n");
                }
                count++;
            }
            cont.append("]");
            Files.writeString(filepath, cont.toString());
            System.out.println("successful.");
        }catch (Exception e){
            throw new RuntimeException("fail");
        }
    }
    public void ComandList(){
        System.out.println("java Task_CLI list");
        System.out.println("java Task_CLI list <done>");
        System.out.println("java Task_CLI list <todo>");
        System.out.println("java Task_CLI list <in-progress>");
        System.out.println("java Task_CLI add <description>");
        System.out.println("java Task_CLI update <id> <description>");
        System.out.println("java Task_CLI delete <id> <description>");
        System.out.println("java Task_CLI mark-done <id>");
        System.out.println("java Task_CLI mark-in-progress <id>");
    }

}
