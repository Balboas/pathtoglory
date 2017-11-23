package org.academiadecodigo.balboas.model;

/**
 * Created by Daniel Baeta on 23/11/17.
 */
public enum MessageProtocol {

    REGISTER("REG"),
    LOGIN("LOGIN"),
    SENDDATA("SENDDATA");


    private String protocol;
    public static final String DELIMITER = "##";


    MessageProtocol(String protocol) {
        this.protocol = protocol;
    }

    public static String decode(String message) {

        String[] splittedMessage = message.split(DELIMITER);
        MessageProtocol protocol = MessageProtocol.valueOf(splittedMessage[0]);

        if (protocol == null) {
            return null;
        }

        switch (protocol) {
            case LOGIN:
                break;
            case REGISTER:
                break;
            case SENDDATA:
                break;

        }
        return null;
    }

    public static String encode(MessageProtocol protocol, String message, String username) {
        return new StringBuilder(protocol.name()).append(DELIMITER).append(username).append(message).toString();
    }
}
