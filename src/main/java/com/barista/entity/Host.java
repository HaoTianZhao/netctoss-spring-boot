package com.barista.entity;

import java.io.Serializable;

public class Host implements Serializable {
    private String hostId;

    private String hostName;

    private String hostLocation;

    private static final long serialVersionUID = 1L;

    public Host(String hostId, String hostName, String hostLocation) {
        this.hostId = hostId;
        this.hostName = hostName;
        this.hostLocation = hostLocation;
    }

    public Host() {
        super();
    }

    public String getHostId() {
        return hostId;
    }

    public void setHostId(String hostId) {
        this.hostId = hostId;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getHostLocation() {
        return hostLocation;
    }

    public void setHostLocation(String hostLocation) {
        this.hostLocation = hostLocation;
    }
}