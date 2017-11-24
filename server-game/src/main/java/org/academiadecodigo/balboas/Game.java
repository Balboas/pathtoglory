package org.academiadecodigo.balboas;

import org.academiadecodigo.balboas.game.Player;
import org.academiadecodigo.balboas.utils.MessageProtocol;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Daniel Baeta on 23/11/17.
 */
public class Game {

    private Map<String, Player> playersList;

    public Game() {
        this.playersList = new HashMap<>();
    }

    public void checkGameReadyToStart(){
        if (playersList.size() == 2){
            for (Player p : playersList.values()) {
                MessageProtocol.encode(MessageProtocol.BEGIN, p.getUsername(), "start the game");
            }
        }
    }

    public Map<String, Player> getPlayersList() {
        return playersList;
    }

    public void addPlayer(Player player){
        playersList.put(player.getUsername(), player);
    }

    public void checkGameOver(){

        for (Player player: playersList.values()) {
            if(player.getHealth() <= 0){
                String message = MessageProtocol.encode(MessageProtocol.BEGIN, player.getUsername(), "");
                MessageProtocol.broadcastMessage(message);
                break;
            }
        }
    }

    public void stop() {
        //???? mudar a vista?
    }

    //Make the rules!!
}
