package actions;

import completables.Project;
import completables.TaskList;
import utils.ActionUtils;

import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public class SetUrl implements Action {

    private String actionName = "Set URL";
    private String newURL = "";
    private String projectName;
    private TaskList taskList;

    public SetUrl(String actionString, TaskList taskList) {
        this.taskList = taskList;
        parseActionString(actionString);
    }

    @Override
    public String getActionName() {
        return actionName;
    }

    @Override
    public void execute() {
        if (newURL.equals("")) {
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
        //update project URL
        thisProject.setUrl(newURL);
        System.out.println("Set URL successfully.");
    }

    /**
     * Project name, new URL assumed to not have any spaces
     */
    @Override
    public void parseActionString(String actionString) {
        StringTokenizer st = new StringTokenizer(actionString);
        st.nextToken(); //skip over the "setUrl" token
        try {
            projectName = st.nextToken().substring(1); //omit the "#" at the beginning of the string
        } catch (NoSuchElementException e) {
            System.out.println("Could not perform action: no project supported, or missing delimiter.");
        }
        try {
            newURL = st.nextToken();
        } catch (NoSuchElementException e) {
            System.out.println("Could not perform action: no URL specified.");
        }
    }

}
