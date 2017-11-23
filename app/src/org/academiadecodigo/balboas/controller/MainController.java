package org.academiadecodigo.balboas.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class MainController implements Controller{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField gymField;

    @FXML
    private TextField dietField;

    @FXML
    private TextField beerField;

    @FXML
    private TextField smokeField;

    @FXML
    private Button backButton;

    @FXML
    private Button sendButton;

    @FXML
    void onBackButton(ActionEvent event) {

        Navigation.getInstance().back();
    }

    @FXML
    void onSendButton(ActionEvent event) {




    }

    @FXML
    void initialize() {
        assert gymField != null : "fx:id=\"gymField\" was not injected: check your FXML file 'mainView.fxml'.";
        assert dietField != null : "fx:id=\"dietField\" was not injected: check your FXML file 'mainView.fxml'.";
        assert beerField != null : "fx:id=\"beerField\" was not injected: check your FXML file 'mainView.fxml'.";
        assert smokeField != null : "fx:id=\"smokeField\" was not injected: check your FXML file 'mainView.fxml'.";
        assert backButton != null : "fx:id=\"backButton\" was not injected: check your FXML file 'mainView.fxml'.";
        assert sendButton != null : "fx:id=\"sendButton\" was not injected: check your FXML file 'mainView.fxml'.";

    }
}

