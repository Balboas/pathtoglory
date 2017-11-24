package org.academiadecodigo.balboas.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Created by Daniel Baeta on 24/11/17.
 */
public class Fighter1 implements Fighter {


    @Override
    public void moveLeft(ImageView player1, ImageView player2) {

        player1.setImage(new Image("/fighter1Still.png"));
        player1.setX(player1.getX() - 20.0d);
        if (player1.getX() <= 0) {
            player1.setX(0.0d);
        }
    }

    @Override
    public void moveRight(ImageView player1, ImageView player2) {

        player1.setImage(new Image("/fighter1Still.png"));
        player1.setX(player1.getX() + 20.0d);
        if (player1.getX() >= (player2.getX() - player1.getFitWidth())) {
            player1.setX(player2.getX() - player2.getFitWidth());
        }
        if (player1.getX() >= 320) {
            player1.setX(320.0d);
        }
    }

    @Override
    public void attack(ImageView player1, ImageView player2) {

        player1.setImage(new Image("/punching1.png"));
        if (player1.getX() >= (player2.getX() - player1.getFitWidth())) {
            //fight.attack();
            //Vai decrementar a vida do player2
        }
    }
}
