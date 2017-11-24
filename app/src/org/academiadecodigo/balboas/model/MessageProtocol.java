package org.academiadecodigo.balboas.model;

import javafx.application.Platform;
import org.academiadecodigo.balboas.controller.*;
import org.academiadecodigo.balboas.view.Fighter1;
import org.academiadecodigo.balboas.view.Fighter2;

import java.lang.management.PlatformLoggingMXBean;

/**
 * Created by Daniel Baeta on 23/11/17.
 */
public enum MessageProtocol {

    REGISTER("REG"),
    LOGIN("LOGIN"),
    SENDDATA("SENDDATA"),
    FIGHT("FIGHT"),
    ATTACK("ATTACK");

    private String protocol;
    public static final String DELIMITER = "##";

    MessageProtocol(String protocol) {
        this.protocol = protocol;
    }

    public static String decode(String message) {

        String[] splittedMessage = message.split(DELIMITER);
        MessageProtocol protocol = MessageProtocol.valueOf(splittedMessage[0]);

        System.out.println("Message received: " + message);
        if (protocol == null) {
            return null;
        }

        switch (protocol) {
            case LOGIN:
                if (splittedMessage[1].equals("done")) {
                    System.out.println("Entering login");
                    LoginController controller = (LoginController) Navigation.getInstance().getController(LoginController.getNAME());
                    Platform.runLater(() -> {
                        controller.showConsoleText("login accepted");
                        Navigation.getInstance().loadScreen(MainController.getName());
                        MainController mainController = (MainController) Navigation.getInstance().getController(MainController.getName());
                        mainController.setClientName(splittedMessage[2]);
                        mainController.setClient(controller.getClient());
                    });
                    break;
                }
                break;
            case REGISTER:
                if (splittedMessage[1].equals("done")) {
                    LoginController controller = (LoginController) Navigation.getInstance().getController(LoginController.getNAME());
                    Platform.runLater(() -> controller.showLogin());
                }
                break;
            case SENDDATA:
                if (splittedMessage[1].equals("done")) {

                }
                break;
            case FIGHT:
                if (splittedMessage[1].equals("done")) {
                    MainController controller = (MainController) Navigation.getInstance().getController(MainController.getName());
                    Platform.runLater(() -> {
                        Navigation.getInstance().loadScreen(FightController.getName());
                        FightController fightController = (FightController) Navigation.getInstance().getController(FightController.getName());
                        fightController.setClient(controller.getClient());
                        fightController.setClientName(splittedMessage[2]);
                        fightController.setHealth(splittedMessage[3]);
                        fightController.setStrength(splittedMessage[4]);
                        fightController.setClient(controller.getClient());
                        if (splittedMessage[5].equals("1")) {
                            fightController.setFighter(new Fighter1());
                            return;
                        }
                        fightController.setFighter(new Fighter2());

                    });
                    break;
                }
                break;
            case ATTACK:
                FightController controller =
                (FightController) Navigation.getInstance().getController(FightController.getName());
                if (!splittedMessage[2].equals(controller.getClientName())) {
                    controller.setHealth(splittedMessage[2]);
                }
        }
        return null;
    }

    public static String encode(MessageProtocol protocol, String message, String username) {
        return new StringBuilder(protocol.name()).append(DELIMITER).append(username).append(DELIMITER).append(message).toString();
    }
}
