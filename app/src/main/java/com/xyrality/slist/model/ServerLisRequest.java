package com.xyrality.slist.model;

import java.io.Serializable;

public class ServerLisRequest implements Serializable{

  /*  login The device identifier or email address used to sign in.
    password The corresponding password for the login.
    deviceType A string describing the hardware and operating system of a device.
    deviceId A unique identifier that identifies the device.*/

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    private String login;
    private String password;
    private String deviceType;
    private String deviceId;
}
