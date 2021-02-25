package org.zx.entity;

import lombok.Data;

import java.net.Socket;

@Data
public class User {
    private Socket socket;
    private String name;
    private Status status;

    public User(String name,Socket socket) {
        this.socket = socket;
        this.name = name;
        status = Status.ONLINE;
    }
}
