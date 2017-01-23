package actions;

public class Help implements Action {

	private String name = "help";

    @Override
    public String getActionName() {
        return name;
    }

    @Override
    public void execute() {
        System.out.println("This is the help menu.");
        System.out.println("Type #ExampleProject [Task] to add the task [Task] to ExampleProject.");
        System.out.println("If ExampleProject does not already exist, you will be prompted to create it as a new project.");
        System.out.println("Type setUrl #ExampleProject [URL] to set ExampleProject's URL to [URL].");
        System.out.println("Use the project name \"urgent\" to send an email directly with the task name.");
        System.out.println("Type \"display\" to display the tasks file in console.");
        System.out.println("Type \"exit\" to exit the program.");
    }

    @Override
    public void parseActionString(String actionString) {

    }

}
