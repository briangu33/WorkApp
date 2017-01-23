package utils;

import completables.TaskList;

import java.io.*;
import java.util.ArrayList;

public class FileUtils {

    public static final String FILE_PATH = "";
    public static final String FILE_NAME = "tasks.txt";
    public static final String OLD_TASKS_FILE_NAME = "oldtasks.txt";

    /**
     * Check that tasks.txt exists; if it doesn't, create it.
     */
    public static void checkTaskFileExists() {
        File f = new File(FILE_PATH + FILE_NAME);
        if (!f.exists()) {
            System.out.println("File \"" + FILE_NAME + "\" does not exist. Attempting to create file...");
            try {
                PrintWriter pw = new PrintWriter(FILE_PATH + FILE_NAME);
                pw.close();
                System.out.println("File successfully created.");
            } catch (IOException e) {
                System.out.println("Unable to create file. Aborting.");
                System.exit(0);
            }
        } else {
            System.out.println("Found file \"" + FILE_NAME + "\"");
        }
    }

    /**
     * Create TaskList object by deserializing the tasks.txt file.
     */
    public static TaskList initializeTaskList() {
        TaskList taskList = new TaskList();
        try {
            File f = new File(FILE_PATH + FILE_NAME);
            ArrayList<String> lines = fileToLines(f);
            taskList.deserialize(lines);
        } catch (Exception e) {
            System.out.println("Using blank task list.");
        }
        return taskList;
    }

    /**
     * Write to tasks.txt by serializing the TaskList object.
     */
    public static void updateTaskList(TaskList taskList, String filePath, String fileName) {
        File f = new File(filePath + fileName);
        try {
            PrintWriter pw = new PrintWriter(filePath + fileName);
            pw.println(taskList.serialize());
            pw.close();
        } catch (IOException e) {
            System.out.println("An error occurred. Unable to update task list.");
        }
    }

    /**
     * Utility method for converting a file into a list of its lines (ArrayList of Strings).
     * Used in initializing the TaskList; our deserialization methods take in lists of strings and return objects.
     */
    public static ArrayList<String> fileToLines(File f) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(f));
        ArrayList<String> lines = new ArrayList<>();

        String current = br.readLine();
        while (current != null) {
            lines.add(current);
            current = br.readLine();
        }

        return lines;
    }

}
