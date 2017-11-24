package org.academiadecodigo.balboas.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.academiadecodigo.balboas.model.Client;
import org.academiadecodigo.balboas.model.MessageProtocol;
import org.academiadecodigo.balboas.model.User;

public class MainController implements Controller{

    private Client client;
    private User user;
    private static final String NAME = "mainView";

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
    private String clientName;

    @FXML
    void onBackButton(ActionEvent event) {

        Navigation.getInstance().back();
        Navigation.getInstance().back();
        Navigation.getInstance().loadScreen(LoginController.getNAME());
    }

    @FXML
    void onSendButton(ActionEvent event) {

        String stringBuilder = new StringBuilder(gymField.getText()).append(MessageProtocol.DELIMITER).append(dietField.getText())
                .append(MessageProtocol.DELIMITER).append(beerField.getText()).append(MessageProtocol.DELIMITER).append(smokeField.getText()).toString();

        String message = MessageProtocol.encode(MessageProtocol.SENDDATA, stringBuilder, clientName);
        client.sendMessage(message);
    }

    @FXML
    void initialize() {

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

