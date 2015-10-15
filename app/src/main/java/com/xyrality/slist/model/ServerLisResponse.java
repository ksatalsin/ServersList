package com.xyrality.slist.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ServerLisResponse implements Serializable{

   /* "serverVersion": "1.0.",
            "allAvailableWorlds"

            googleLoginSwitchOn": true,
"serverVersion": "4.14.4",
"facebookLoginSwitchOn": true,

featureHelpshift": true,
"time": "2015-10-15T18:19:20Z",
"info": "Unknown user"

*/

    public String getServerVersion() {
        return serverVersion;
    }

    public void setServerVersion(String serverVersion) {
        this.serverVersion = serverVersion;
    }

    public ArrayList<Server> getAllAvailableWorlds() {
        return allAvailableWorlds;
    }

    public void setAllAvailableWorlds(ArrayList<Server> allAvailableWorlds) {
        this.allAvailableWorlds = allAvailableWorlds;
    }

    public boolean isGoogleLoginSwitchOn() {
        return googleLoginSwitchOn;
    }

    public void setGoogleLoginSwitchOn(boolean googleLoginSwitchOn) {
        this.googleLoginSwitchOn = googleLoginSwitchOn;
    }

    public boolean isFacebookLoginSwitchOn() {
        return facebookLoginSwitchOn;
    }

    public void setFacebookLoginSwitchOn(boolean facebookLoginSwitchOn) {
        this.facebookLoginSwitchOn = facebookLoginSwitchOn;
    }

    private boolean googleLoginSwitchOn;
    private boolean facebookLoginSwitchOn;
    private boolean featureHelpshift;
    private String serverVersion;

    public boolean isFeatureHelpshift() {
        return featureHelpshift;
    }

    public void setFeatureHelpshift(boolean featureHelpshift) {
        this.featureHelpshift = featureHelpshift;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    private String time;
    private String info;
    private ArrayList<Server> allAvailableWorlds;
}
