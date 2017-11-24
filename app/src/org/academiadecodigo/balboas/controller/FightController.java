package org.academiadecodigo.balboas.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;

/**
 * Created by Daniel Baeta on 24/11/17.
 */
public class FightController {



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

        player1.setImage(new Image("/punching1.png"));
        if (player1.getX() >= (player2.getX() - player1.getFitWidth())) {
            //fight.attack();
            //Vai decrementar a vida do player2
        }
    }

    @FXML
    void moveLeft(ActionEvent event) {

        player1.setImage(new Image("/fighter1Still.png"));
        player1.setX(player1.getX() - 20.0d);
        if (player1.getX() <= 0) {
            player1.setX(0.0d);
        }
    }

    @FXML
    void moveRight(ActionEvent event) {

        player1.setImage(new Image("/fighter1Still.png"));
        player1.setX(player1.getX() + 20.0d);
        if (player1.getX() >= (player2.getX() - player1.getFitWidth())) {
            player1.setX(player2.getX() - player2.getFitWidth());
        }
        if (player1.getX() >= 320) {
            player1.setX(320.0d);
        }
    }


    public void setHealth(int health){
        healthLabel.setText(String.valueOf(health));
    }

    public void setStrength(int strength){
        strengthLabel.setText(String.valueOf(strength));
    }

}
