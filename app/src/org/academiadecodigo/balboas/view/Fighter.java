package org.academiadecodigo.balboas.view;

import javafx.scene.image.ImageView;

/**
 * Created by Daniel Baeta on 24/11/17.
 */
public interface Fighter {

    void moveLeft(ImageView player1, ImageView player2, String clientName);

    void moveRight(ImageView player1, ImageView player2, String clientName);

    void attack(ImageView player1, ImageView player2, String text, String clientName);
}
