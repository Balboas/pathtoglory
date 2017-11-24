package org.academiadecodigo.balboas.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import org.academiadecodigo.balboas.view.Fighter;

/**
 * Created by Daniel Baeta on 24/11/17.
 */
public class FightController {


    private Fighter fighter;
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

    @FXML
    private Button moveLeftButton;

    @FXML
    private Button moveRightButton;

    @FXML
    void fight(ActionEvent event) {

        fighter.attack(player1, player2);

    }

    @FXML
    void moveLeft(ActionEvent event) {

       fighter.moveLeft(player1, player2);
    }

    @FXML
    void moveRight(ActionEvent event) {

        fighter.moveRight(player1, player2);
    }


    public void setHealth(int health){
        healthLabel.setText(String.valueOf(health));
    }

    public void setStrength(int strength){
        strengthLabel.setText(String.valueOf(strength));
    }

}
