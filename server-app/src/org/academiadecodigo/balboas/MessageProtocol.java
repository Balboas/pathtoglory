package org.academiadecodigo.balboas;

import org.academiadecodigo.balboas.services.JdbcUserService;
import org.academiadecodigo.balboas.services.UserService;

/**
 * Created by Daniel Baeta on 23/11/17.
 */
public enum MessageProtocol {

    REGISTER("REG"),
    LOGIN("LOGIN"),
    SENDDATA("SENDDATA"),
    FIGHT("FIGHT");

    private String protocol;
    private static JdbcUserService jdbcUserService;
    public static final String DELIMITER = "##";


    MessageProtocol(String protocol) {
        this.protocol = protocol;
    }

    public static String decode(String message) {

        System.out.println("Message decode from server " + message);
        String[] splittedMessage = message.split(DELIMITER);
        MessageProtocol protocol = MessageProtocol.valueOf(splittedMessage[0]);

        System.out.println("Protocol: " + protocol);

        if (protocol == null) {
            return null;
        }

        switch (protocol) {
            case LOGIN:
                if (jdbcUserService.authenticate(splittedMessage[2], splittedMessage[3])) {
                    System.out.println("User ok!");
                    return encode(LOGIN, "done" + DELIMITER + splittedMessage[2]);
                }
                break;
            case REGISTER:
                if (jdbcUserService.addUser(splittedMessage[2], splittedMessage[3], splittedMessage[4])) {
                    return encode(REGISTER, "done");
                }
                break;
            case SENDDATA:
                System.out.println("Data received from second view");
                jdbcUserService.registerData(splittedMessage[1], splittedMessage[2], splittedMessage[3], splittedMessage[4], splittedMessage[5]);
                return encode(SENDDATA, "done");
            case FIGHT:
                String life = jdbcUserService.getLife(splittedMessage[1]);
                String strength = jdbcUserService.getStrength(splittedMessage[1]);
                return encode(FIGHT, "done" + DELIMITER + splittedMessage[2] + DELIMITER + life + DELIMITER + strength);
        }
        return null;
    }

    public static String encode(MessageProtocol protocol, String message) {

        return new StringBuilder(protocol.name()).append(DELIMITER).append(message).toString();

    }

    public static void setService(UserService userService) {
        if (userService instanceof JdbcUserService) {
            jdbcUserService = (JdbcUserService) userService;

        }
    }

}
