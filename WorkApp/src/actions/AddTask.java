package actions;

import completables.Project;
import completables.Task;
import completables.TaskList;
import utils.ActionUtils;

import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public class AddTask implements Action {

    protected String actionName = "Add Task";
    protected String taskName = "";
    protected String projectName;
    protected TaskList taskList;

    public AddTask(String actionString, TaskList taskList) {
        this.taskList = taskList;
        parseActionString(actionString);
    }

    @Override
    public String getActionName() {
        return actionName;
    }

    @Override
    public void execute() {
        if (taskName.equals("")) {
            return;
        }
        //check if this is an urgent task
        if (projectName.equals("urgent")) {
            AddUrgent urgentAction = new AddUrgent("#" + projectName + " " + taskName, taskList);
            urgentAction.execute();
            return;
        }
        //search for project in existing projects
        Project thisProject = ActionUtils.getProjectWithName(projectName, taskList);
        //prompt user to create project if it does not already exist
        if (thisProject == null) {
            thisProject = ActionUtils.promptUserCreateProject(projectName, taskList);
            if (thisProject == null) {
                return;
            }
        }
        //update project
        Task newTask = new Task();
        newTask.setName(taskName);
        thisProject.addTask(newTask);
        System.out.println("Added task");
    }

    /**
     * Project name assumed to not have any spaces
     */
    @Override
    public void parseActionString(String actionString) {
        StringTokenizer st = new StringTokenizer(actionString);
        try {
            projectName = st.nextToken().substring(1); //omit the "#" at the beginning of the string
        } catch (NoSuchElementException e) {
            System.out.println("Could not perform action: could not parse project name.");
        }
        if (actionString.length() >= projectName.length() + 2) {
            taskName = actionString.substring(projectName.length() + 2); //omit the "#", the project name, and the space following
        } else {
            System.out.println("Could not perform action: no task name specified.");
        }
    }

}
