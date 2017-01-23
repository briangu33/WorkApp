package actions;

public interface Action {

	String getActionName();

	void execute();

    void parseActionString(String actionString);

}
