package org.academiadecodigo.balboas.game;

import org.academiadecodigo.balboas.Server;
import org.academiadecodigo.balboas.utils.MessageProtocol;

/**
 * Created by Daniel Baeta on 23/11/17.
 */
public class Player {

    private Server.ServerWorker serverWorker;
    private int health;
    private int strength;

    private String username;


    public Player(Server.ServerWorker serverWorker) {
        this.serverWorker = serverWorker;
    }

    public void sufferAttack(int damage){
        if((health - damage) < 0){
            health = 0;
            serverWorker.sendMessage(MessageProtocol.encode(MessageProtocol.GAMEOVER, username, "over"));
            return;
        }
        health -= damage;
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

    public void setHealth(int health) {
        System.out.println(health);
        this.health = health;
    }

    public void setStrength(int strength) {
        System.out.println(strength);
        this.strength = strength;
    }

    public int getHealth() {
        return health;
    }

    public int getStrength() {
        return strength;
    }
}
