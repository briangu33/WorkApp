package completables;

import java.util.ArrayList;

/**
 * The way completables are structured allows for further nesting (beyond task list - project - task) with little modification.
 */
public interface Completable {

    void deserialize(ArrayList<String> lines);

    String serialize();

    String getName();

}
