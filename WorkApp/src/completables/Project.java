package completables;

import java.util.ArrayList;
import java.util.List;

public class Project implements Completable{

    public static final String PROJECT_PREFIX = "*** ";
    public static final String LINK_PREFIX = " --";

    /**
     * Project name and link are assumed not to have any spaces
     */
    private String name;
    private String link;
    private List<Task> tasks = new ArrayList<Task>();

    @Override
    public void deserialize(ArrayList<String> lines) {
        String header = lines.get(0);
        int projectPrefixIndex = header.indexOf(PROJECT_PREFIX);
        int linkPrefixIndex = header.indexOf(LINK_PREFIX);
        name = header.substring(projectPrefixIndex + 4, linkPrefixIndex);
        if (header.length() > linkPrefixIndex + 3) {
            link = header.substring(linkPrefixIndex + 4);
        } else {
            link = "";
        }
        for (int i = 1; i < lines.size(); i++) {
            if (lines.get(i).equals("")) {
                continue;
            }
            Task task = new Task();
            ArrayList<String> serializedTask = new ArrayList<>();
            serializedTask.add(lines.get(i));
            task.deserialize(serializedTask);
            tasks.add(task);
        }
    }

    @Override
    public String serialize() {
        ArrayList<String> lines = new ArrayList<String>();
        lines.add(PROJECT_PREFIX + name + LINK_PREFIX);
        if (!link.equals("")) {
            lines.set(0, lines.get(0) + " " + link);
        }
        for (Task task : tasks) {
            lines.add(task.serialize());
        }
        StringBuffer sb = new StringBuffer();
        for (String line : lines) {
            sb.append(line);
            sb.append("\n");
        }
        return sb.toString();
    }

    @Override
    public String getName() {
        return name;
    }

    public void clearTasks() {
        tasks = new ArrayList<Task>();
    }

    public void setName(String newName) {
        name = newName;
    }

    public void setUrl(String newURL) {
        this.link = newURL;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }
	
}
