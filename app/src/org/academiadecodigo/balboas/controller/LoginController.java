package org.academiadecodigo.balboas.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

        if (login) {
            showRegister();
        } else {
            showLogin();
        }

    }

    @FXML
    void onPress(ActionEvent event) {

        if (login) {
            doLogin();
        } else {
            doRegister();
        }

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
        Navigation.getInstance().loadScreen(MainController.getName());
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

        String regex = "^([_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{1,6}))?$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(emailFieldId.getText());
        if (!matcher.matches()) {


            showConsoleText("not valid e-mail");

            return;
        }

        showLogin();
        showConsoleText("registration successful");

    }

    private void showConsoleText(String text) {

        messagesId.setText("console.log(\"" + text + "\");");
        messagesId.setVisible(true);

    }


    public static String getNAME() {
        return NAME;
    }
}

