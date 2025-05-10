import Controller.ComandController;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Task_CLI {
    public static void main(String[] args) {
        Path source = Paths.get("Resources/Task.JSON");
        try {
            Files.createDirectories(source.getParent());

            if(Files.notExists(source)){
                Files.createFile(source);

            }

        }catch(Exception e){
            throw new RuntimeException("No se encontro el archivo primerpaso");
        }

        ComandController comand = new ComandController();


        if (args.length == 0) {
            System.out.println("Syntaxis:Task_CLI <commant> <description> ");
            return;
        }

        comand.comand(args);


    }
}