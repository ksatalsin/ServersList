package com.xyrality.slist.model;

import java.io.Serializable;
import java.util.ArrayList;

public class ServerLisResponse implements Serializable {

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
    private boolean googleLoginSwitchOn;
    private boolean facebookLoginSwitchOn;
    private boolean featureHelpshift;
    private String serverVersion;
}
