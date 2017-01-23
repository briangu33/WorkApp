import actions.Action;
import completables.TaskList;
import utils.EmailUtils;
import utils.FileUtils;
import utils.ActionUtils;

import java.util.Scanner;

public class Work {

    public static void main(String[] args) {
        TaskList taskList = new TaskList();
        FileUtils.checkTaskFileExists();
        taskList = FileUtils.initializeTaskList();
        EmailUtils.checkWantToEmail(args, taskList);

        System.out.println("Welcome! Type help for more information.");

        Scanner scan = new Scanner(System.in);

        while (true) {
            Action currentAction = ActionUtils.getActionFromUserInput(scan, taskList);
            currentAction.execute();
            //we completely rewrite the file each time
            //since the file should not be large at all, this shouldn't be a problem.
            //if performance becomes an issue, it isn't too hard to insert/modify lines rather than rewriting.
            FileUtils.updateTaskList(taskList, FileUtils.FILE_PATH, FileUtils.FILE_NAME);
        }

    }
}
