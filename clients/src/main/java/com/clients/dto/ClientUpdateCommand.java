package com.clients.dto;

public class ClientUpdateCommand {

    private Long id;
    private String name;
    private boolean isOlder;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
}
