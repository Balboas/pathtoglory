package org.academiadecodigo.balboas;

import org.academiadecodigo.balboas.game.Player;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Daniel Baeta on 23/11/17.
 */
public class Game {

    List<Player> playersList;

    public Game() {
        this.playersList = new LinkedList<>();
    }

    public boolean isGameReadyToStart(){
        return playersList.size() == 2;
    }

    //Make the rules!!
}
