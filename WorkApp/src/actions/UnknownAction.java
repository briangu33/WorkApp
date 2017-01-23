package actions;

public class UnknownAction implements Action {

    private String actionName = "Unknown";

    @Override
    public String getActionName() {
        return actionName;
    }

    @Override
    public void execute() {
        System.out.println("Unknown action specified, please enter a new command.");
    }

    @Override
    public void parseActionString(String actionString) {

    }
}
