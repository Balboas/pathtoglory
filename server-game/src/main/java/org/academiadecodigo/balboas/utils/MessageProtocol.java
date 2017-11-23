package org.academiadecodigo.balboas.utils;

import org.academiadecodigo.balboas.Server;
import org.academiadecodigo.balboas.game.Player;

import java.util.*;

/**
 * Created by Daniel Baeta on 23/11/17.
 */
public enum MessageProtocol {

    LOGIN("LOGIN"),
    MOVE("MOVE"),
    ATTACK("ATTACK"),
    GAMEOVER("OVER"),
    BEGIN("BEGIN"),
    STATS("STATS");

    private String protocolTag;
    public static final String DELIMITER = "##";
    private static Map<String, Player> listOfPlayers;

    static {
        listOfPlayers = new HashMap<>();
    }

    MessageProtocol(String protocolTag) {
        this.protocolTag = protocolTag;
    }


    public static String encode(MessageProtocol protocol, String username, String message) {
        return new StringBuilder(protocol.name()).append(DELIMITER).append(message).toString();
    }

    public static void decode(String message, Server.ServerWorker serverWorker) {

        String[] splitMessage = message.split(DELIMITER);
        MessageProtocol protocol = MessageProtocol.valueOf(splitMessage[0]);

        switch (protocol) {
            case LOGIN:


                addPlayer(new Player(serverWorker), serverWorker.getName());

                //JDBC service
                break;
            case MOVE:
                sendMessageToPlayer(splitMessage[2], serverWorker);
                break;
            case ATTACK:
                //Send message in format <protocol>##<username>##<damage>
                sendMessageToPlayer(splitMessage[2], serverWorker);
                break;
            case GAMEOVER:
                //broadcast Scores to players (both players receive the message)
                //broadcastMessage();
                break;
            case STATS:
                //Get these from the database
                String playerStats = null;
                sendMessageToPlayer(playerStats, serverWorker);
                break;
        }
    }

    private static void sendMessageToPlayer(String message, Server.ServerWorker serverWorker) {

        Collection<Player> setPlayers = listOfPlayers.values();

        for (Player player : setPlayers) {
            if (player.getUsername().equals(serverWorker.getName())){
                player.sendMessage(message);
            }
        }
    }

    private void broadcastMessage(String message){
        for (Player player : listOfPlayers.values()) {
            player.sendMessage(message);
        }
    }

    public static void addPlayer(Player player, String name) {
        player.setUsername(name);
        listOfPlayers.put(name, player);
    }
}

