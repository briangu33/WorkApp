package actions;

import completables.TaskList;
import utils.EmailUtils;

public class AddUrgent extends AddTask {

    public AddUrgent(String actionString, TaskList taskList) {
        super(actionString, taskList);
        actionName = "Add Urgent Task";
    }

    @Override
    public void execute() {
        try {
            System.out.println("Sending email...");
            EmailUtils.sendEmail("URGENT TASK", taskName);
            System.out.println("Urgent task email sent successfully.");
        } catch (Exception e) {
            System.out.println("An exception occurred while sending mail.");
        }
    }

}
