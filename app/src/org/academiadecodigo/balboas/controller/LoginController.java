package org.academiadecodigo.balboas.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * Created by codecadet on 23/11/17.
 */
public class LoginController implements Controller{

    private static final String NAME = "Login";

    @FXML
    private Pane paneId;

    @FXML
    private Label userId;

    @FXML
    private Label passwordId;

    @FXML
    private Label emailId;

    @FXML
    private TextField userFieldId;

    @FXML
    private PasswordField passFieldId;

    @FXML
    private TextField emailFieldId;

    @FXML
    private Button mainButtonId;

    @FXML
    private Hyperlink registerLinkId;

    @FXML
    private Label TittleId;

    @FXML
    private Label messagesId;

    private boolean login = true;

    public void initialize () {
        emailId.setVisible(false);
        emailFieldId.setVisible(false);
    }

    @FXML
    void onClick(ActionEvent event) {

    }

    @FXML
    void onPress(ActionEvent event) {

    }
    private void showLogin() {

        login = true;

        messagesId.setVisible(false);

        emailId.setVisible(false);
        emailFieldId.setVisible(false);



        mainButtonId.setText("Login");
        registerLinkId.setText("Register");

    }

    private void showRegister() {

        login = false;

        messagesId.setVisible(false);

        emailId.setVisible(true);
        emailFieldId.setVisible(true);

        mainButtonId.setText("Submit");
        registerLinkId.setText("Cancel");

    }

    private void doLogin() {

        if (userFieldId.getText().isEmpty()) {
            showConsoleText("username missing");
            return;
        }

        if (passFieldId.getText().isEmpty()) {
            showConsoleText("password missing");
            return;
        }

        showConsoleText("login accepted");
    }

    private void doRegister() {

        if (userFieldId.getText().isEmpty()) {
            showConsoleText("username missing");
            return;
        }

        if (passFieldId.getText().isEmpty()) {
            showConsoleText("password missing");
            return;
        }

        if (emailFieldId.getText().isEmpty()) {
            showConsoleText("email missing");
            return;
        }

        showLogin();
        showConsoleText("registration successful");

    }

    private void showConsoleText(String text) {

        messagesId.setText("console.log(\"" + text + "\");");
        messagesId.setVisible(true);

    }

    public void onButton(ActionEvent event) {

        if (login) {
            doLogin();
        } else {
            doRegister();
        }

    }

    public void onLink(MouseEvent event) {

        if (login) {
            showRegister();
        } else {
            showLogin();
        }

    }

    public static String getNAME() {
        return NAME;
    }
}

