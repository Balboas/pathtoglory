package org.academiadecodigo.balboas.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import org.academiadecodigo.balboas.sound.SoundManager;
import org.academiadecodigo.balboas.view.Fighter;
import org.academiadecodigo.balboas.model.Client;

/**
 * Created by Daniel Baeta on 24/11/17.
 */
public class FightController implements Controller {

    private SoundManager soundManager;

    private Fighter fighter;
    private int playerNumber;

    private static final String NAME = "FightView";

    @FXML
    private Button fightButton;

    @FXML
    private ImageView player1;

    @FXML
    private ImageView player2;

    @FXML
    private Label healthLabel;

    @FXML
    private Label strengthLabel;

    private Client client;

    private String clientName;

    @FXML
    private Button moveLeftButton;

    @FXML
    private Button moveRightButton;

    @FXML
    void fight(ActionEvent event) {

        fighter.attack(player1, player2, strengthLabel.getText(), clientName);

    }

    @FXML
    void moveLeft(ActionEvent event) {

        fighter.moveLeft(player1, player2, clientName);
    }

    @FXML
    void moveRight(ActionEvent event) {

        fighter.moveRight(player1, player2, clientName);
    }

    public void setHealth(String health) {

        healthLabel.setText(health);
    }

    public String getClientName() {
        return clientName;
    }

    public Fighter getFighter() {
        return fighter;
    }

    public void setStrength(String strength) {
        strengthLabel.setText(strength);
    }

    public static String getName() {
        return NAME;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public void setFighter(Fighter fighter) {
        System.out.println(fighter.getClass().getSimpleName());
        if (fighter.getClass().getSimpleName().equals("Fighter1")) {
            playerNumber = 1;
        } else {
            playerNumber = 2;
        }
        this.fighter = fighter;
    }

    public void setOpponentPlayerPosition(String playerName, String position) {

        if (!playerName.equals(clientName)) {
            if (playerNumber == 1) {
                player2.setX(Double.parseDouble(position));
            } else {
                player1.setX(Double.parseDouble(position));
            }
        }
    }
}
