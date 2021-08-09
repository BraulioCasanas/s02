package com.clients.dto;

public class ClientSaveCommand {

    private String name;
    private String city;
    private boolean isOlder;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getIsOlder() {
        return isOlder;
    }

    public void setIsOlder(boolean isOlder) {
        this.isOlder = isOlder;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
