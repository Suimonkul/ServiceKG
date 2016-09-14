package appkg.kg.servicekg.model;

import java.io.Serializable;

/**
 * Created by Suimonkul on 07.09.2016.
 */
public class Categories implements Serializable {
    String[] groups;
    String[][] children;

    public Categories(String[] groups, String[][] children) {
        this.groups = groups;
        this.children = children;
    }

    public String[] getGroups() {
        return groups;
    }



    public String[][] getChildren() {
        return children;
    }

}
