package org.academiadecodigo.balboas.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Created by Daniel Baeta on 24/11/17.
 */
public class Fighter2 implements Fighter {

    @Override
    public void moveLeft(ImageView player1, ImageView player2) {
        player2.setImage(new Image("/fighter2Still.png"));
        player2.setX(player2.getX() - 20.0d);
        if (player2.getX() <= (player1.getX() + 60)) {
            player2.setX(player1.getX() + 60);
        }
        if (player2.getX() <= 0) {
            player2.setX(0.0d);
        }
    }

    @Override
    public void moveRight(ImageView player1, ImageView player2) {

        player2.setImage(new Image("/fighter2Still.png"));
        player2.setX(player2.getX() + 20.0d);
        if (player2.getX() >= 320) {
            player2.setX(320.0d);
        }
    }

    @Override
    public void attack(ImageView player1, ImageView player2) {

        player2.setImage(new Image("/punching2.png"));
        if (player2.getX() <= (player1.getX() - player2.getFitWidth())) {
            //fight.attack();
            //Vai decrementar a vida do player2
        }
    }
}
