package utils;

import actions.*;
import completables.Project;
import completables.TaskList;

import java.util.Scanner;

public class ActionUtils {

    public static Action getActionFromUserInput(String actionString, TaskList taskList) {
        if (actionString.length() > 0 && actionString.charAt(0) == '#') {
            return new AddTask(actionString, taskList);
        } else if (actionString.length() > 3 && actionString.substring(0, 4).equals("help")) {
            return new Help();
        } else if (actionString.length() > 3 && actionString.substring(0, 4).equals("exit")) {
            return new Exit();
        } else if (actionString.length() > 5 && actionString.substring(0, 6).equals("setUrl")) {
            return new SetUrl(actionString, taskList);
        } else if (actionString.length() > 6 && actionString.substring(0, 7).equals("display")) {
            return new Display(taskList);
        } else {
            return new UnknownAction();
        }
    }

    /**
     * Return a new project with the given name if user responds "y"; otherwise, return null.
     */
    public static Project promptUserCreateProject(String projectName, TaskList taskList) {
        Project ret = new Project();
        System.out.println("Project " + projectName + " does not exist. Create new project? (y or n)");
        Scanner scan = new Scanner(System.in);
        char response = scan.next().charAt(0);
        if (response == 'n') {
            System.out.println("No action taken.");
            return null;
        } else if (response == 'y') {
            ret.setName(projectName);
            ret.setUrl("");
            taskList.addNewProject(ret);
            System.out.println("New project created.");
            return ret;
        } else {
            System.out.println("Unable to read response. No action taken.");
            return null;
        }
    }

    public static Action getActionFromUserInput(Scanner scan, TaskList taskList) {
        String userInput = scan.nextLine();
        Action ret = getActionFromUserInput(userInput, taskList);
        return ret;
    }

    public static Project getProjectWithName(String projectName, TaskList taskList) {
        for (Project project : taskList.getProjects()) {
            if (project.getName().equals(projectName)) {
                return project;
            }
        }
        return null;
    }
}
