package actions;

public class Exit implements Action {

    private String name = "exit";

    @Override
    public String getActionName() {
        return name;
    }

    @Override
    public void execute() {
        System.out.println("Exiting.");
        System.exit(0);
    }

    @Override
    public void parseActionString(String actionString) {

    }

}
