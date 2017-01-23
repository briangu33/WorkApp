package completables;

import java.util.ArrayList;

public class TaskList implements Completable {
	private ArrayList<Project> projects = new ArrayList<Project>();
	private String name = "TASK LIST";

	@Override
	public void deserialize(ArrayList<String> lines) {
		ArrayList<String> currentProjectSerialized = new ArrayList<String>();
		for (String line : lines) {
			if (line.length() > 3 && line.substring(0, 4).equals(Project.PROJECT_PREFIX)) {
				if (currentProjectSerialized.size() > 0) {
					addProject(currentProjectSerialized);
				}
				currentProjectSerialized = new ArrayList<String>();
				currentProjectSerialized.add(line);
			} else {
				currentProjectSerialized.add(line);
			}
		}
		addProject(currentProjectSerialized);
	}

	@Override
	public String serialize() {
		StringBuffer sb = new StringBuffer();
		for (Project project : projects) {
			sb.append(project.serialize());
			sb.append("\n");
		}
		return sb.toString();
	}

	@Override
	public String getName() {
		return name;
	}

	public ArrayList<Project> getProjects() {
		return projects;
	}

	public void addNewProject(Project project) {
		projects.add(project);
	}

	public void clearAllTasks() {
		for (Project project : projects) {
			project.clearTasks();
		}
	}

	/**
	 * Helper method for deserialization of the tasks.txt file
	 */
	private void addProject(ArrayList<String> serializedProject) {
		Project project = new Project();
		project.deserialize(serializedProject);
		projects.add(project);
	}

}
