package org.academiadecodigo.balboas.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import org.academiadecodigo.balboas.model.Client;
import org.academiadecodigo.balboas.model.MessageProtocol;

/**
 * Created by Daniel Baeta on 24/11/17.
 */
public class FightController implements Controller{

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
    void beginFight(ActionEvent event) {

    }

    @FXML
    void move(KeyEvent event) {

        System.out.println(event.getCode());
        switch (event.getCode()) {
            case LEFT:
                player1.setX(player1.getX() - 20.0d);
                if (player1.getX() <= 0) {
                    player1.setX(0.0d);
                }
                break;
            case RIGHT:
                player1.setX(player1.getX() + 20.0d);
                if (player1.getX() >= (player2.getX() - player1.getFitWidth())) {
                    player1.setX(player2.getX() - player2.getFitWidth());
                }
                if (player1.getX() >= 320) {
                    player1.setX(320.0d);
                }
                break;
            case SPACE:
                if (player1.getX() >= (player2.getX() - player1.getFitWidth())) {
                   // fight.attack();
                    //Vai decrementar a vida do player2
                }
                break;
        }
    }

    public void setHealth(String health){

        healthLabel.setText(health);
    }

    public void setStrength(String strength){
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

}
