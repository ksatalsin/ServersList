package com.xyrality.slist.model;

import java.io.Serializable;
import java.util.List;

public class ServerLisResponse implements Serializable{

   /* "serverVersion": "1.0.",
            "allAvailableWorlds"*/

    public String getServerVersion() {
        return serverVersion;
    }

    public void setServerVersion(String serverVersion) {
        this.serverVersion = serverVersion;
    }

    public List<Server> getAllAvailableWorlds() {
        return allAvailableWorlds;
    }

    public void setAllAvailableWorlds(List<Server> allAvailableWorlds) {
        this.allAvailableWorlds = allAvailableWorlds;
    }

    private String serverVersion;
    private List<Server> allAvailableWorlds;
}
