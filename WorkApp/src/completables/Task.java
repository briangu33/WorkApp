package completables;

import java.util.ArrayList;

public class Task implements Completable {

    /**
     * name may contain spaces
     */
    private String name;

    /**
     * nameList ought to be an ArrayList of size 1, containing only the name of the task
     */
    @Override
    public void deserialize(ArrayList<String> nameList) {
        this.name = nameList.get(0);
    }

    @Override
    public String serialize() {
        return getName();
    }

    @Override
    public String getName() {
        return name;
    }

    /**
     * Similar to deserialize, but for the use case of actions
     */
    public void setName(String newName) {
        this.name = newName;
    }

}
