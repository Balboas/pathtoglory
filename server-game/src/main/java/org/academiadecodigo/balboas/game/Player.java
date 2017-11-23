package org.academiadecodigo.balboas.game;

import org.academiadecodigo.balboas.Server;

/**
 * Created by Daniel Baeta on 23/11/17.
 */
public class Player {

    private Server.ServerWorker serverWorker;

    private String username;

    public Player(Server.ServerWorker serverWorker) {
        this.serverWorker = serverWorker;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void sendMessage(String message) {
        serverWorker.sendMessage(message);
    }
}
