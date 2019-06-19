package ma.valueit.testingplatform.core.model.auditing;

import lombok.Getter;

/**
 * Created by yelansari on 5/2/18.
 */
public enum Action {
    INSERTED("INSERTED"),
    UPDATED("UPDATED"),
    DELETED("DELETED");
    @Getter
    private String name;

    private Action(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
