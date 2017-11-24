package org.academiadecodigo.balboas.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import org.academiadecodigo.balboas.model.Client;
import org.academiadecodigo.balboas.model.MessageProtocol;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by codecadet on 23/11/17.
 */
public class LoginController implements Controller {

    private static final String NAME = "Login";
    private Client client;

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

    public void initialize() {
        client = new Client(this);

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

    public void showLogin() {

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


        String username = userFieldId.getText();
        String password = passFieldId.getText();
        String message = MessageProtocol.encode(MessageProtocol.LOGIN, username + MessageProtocol.DELIMITER + password, username);
        client.sendMessage(message);

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

        String username = userFieldId.getText();
        String password = passFieldId.getText();
        String email = emailFieldId.getText();
        String message = MessageProtocol.encode(MessageProtocol.REGISTER,
                username + MessageProtocol.DELIMITER + password + MessageProtocol.DELIMITER + email, username);

        client.sendMessage(message);
        showConsoleText("registration successful");

    }

    public void showConsoleText(String text) {

        messagesId.setText("console.log(\"" + text + "\");");
        messagesId.setVisible(true);

    }


    public static String getNAME() {
        return NAME;
    }
}

