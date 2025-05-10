package Controller;

import Services.ComandServices;

public class ComandController {

    ComandServices comand = new ComandServices();
    public void comand(String[] args){
        String Command =args[0];
        switch (Command){
            case "add":
                if(args.length < 2){
                    System.out.println("Systanxis:Task_CLI <add> <description> ");
                    return;
                }
                comand.Add(args[1]);
                break;
            case "update":
                if(args.length < 3){
                    System.out.println("Systanxis:Task_CLI <update> <id> <new description> ");
                    return;
                }
                comand.Update(args[1],args[2]);
                break;
            case "delete":
                if(args.length < 2){
                    System.out.println("Systanxis:Task_CLI <delete> <id>");
                    return;
                }
                comand.Delete(args[1]);
                break;
            case "mark-in-progress":
                if(args.length < 2){
                    System.out.println("Systanxis:Task_CLI <mark-in-progress> <id>");
                    return;
                }
                comand.MarkProgress(args[1]);
                break;
            case "mark-done":
                if(args.length < 2){
                    System.out.println("Systanxis:Task_CLI <mark-done> <id>");
                    return;
                }
                comand.MarkDone(args[1]);
                break;
            case "list":
                System.out.println(3);
                if(args.length < 2){
                    comand.List();
                    return;
                }else {
                    String Status = args[1];
                    switch (Status){
                        case "done":
                            comand.ListStatus("done");
                            break;
                        case "todo":
                            comand.ListStatus("todo");
                            break;
                        case "in-progress":
                            comand.ListStatus("in-progress");
                            break;
                        default:
                            System.out.println("Systanxis:Task_CLI <list> <status>");
                            break;
                    }

                }
                break;
            case "?":
                comand.ComandList();
                break;
            default:
                System.out.println("Systanxis:Task_CLI <Command> <description>"+"\n"+"Command lsit:Task_CLI ?");
                break;
        }
        comand.savelist();
    }
}
