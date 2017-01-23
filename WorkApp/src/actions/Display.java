package actions;

import completables.TaskList;

/**
 * @author Brian Gu (brian.gu@addepar.com)
 */
public class Display implements Action {

    private String name = "display";
    private TaskList taskList;

    public Display(TaskList taskList) {
        this.taskList = taskList;
    }

    @Override
    public String getActionName() {
        return name;
    }

    @Override
    public void execute() {
        System.out.println(taskList.serialize());
    }

    @Override
    public void parseActionString(String actionString) {

    }

}
