package com.xyrality.slist.model;

import java.io.Serializable;

/**
 * Created by youyou on 15.10.2015.
 */
public class WorldStatus implements Serializable {

      /*  description = Description string of a status
        id = Identifier indicating the status*/

    private String description;
    private String id;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
