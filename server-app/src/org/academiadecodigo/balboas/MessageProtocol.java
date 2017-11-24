package org.academiadecodigo.balboas;

import org.academiadecodigo.balboas.services.JdbcUserService;
import org.academiadecodigo.balboas.services.UserService;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by Daniel Baeta on 23/11/17.
 */
public enum MessageProtocol {

    REGISTER("REG"),
    LOGIN("LOGIN"),
    SENDDATA("SENDDATA"),
    FIGHT("FIGHT"),
    ATTACK("ATTACK"),
    MOVE("MOVE");

    private String protocol;
    private static JdbcUserService jdbcUserService;
    public static final String DELIMITER = "##";
    private static Queue<String> fighters;
    private static LinkedList<Server.ServerWorker> list;


    MessageProtocol(String protocol) {
        this.protocol = protocol;
        init();
    }

    public static void init() {
        fighters = new PriorityQueue<>();
        list = new LinkedList<>();
        fighters.offer("1");
        fighters.offer("2");
    }

    public static void addServerWorkers(Server.ServerWorker serverWorker) {

        list.add(serverWorker);
        serverWorker.setID(list.size());
    }

    public static String decode(String message, Server.ServerWorker serverWorker) {

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

                    String name = splittedMessage[2];
                    serverWorker.setName(name);

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
                return encode(FIGHT, "done" + DELIMITER + splittedMessage[2] + DELIMITER + life + DELIMITER + strength + DELIMITER + fighters.poll());
            case ATTACK:
                String strengthAtacker = jdbcUserService.getStrength(serverWorker.getName());

                Server.ServerWorker enemy = getEnemyServerWorker(serverWorker);

                String enemyLife = jdbcUserService.getLife(enemy.getName());

                Integer strenght = Integer.parseInt(strengthAtacker);
                Integer enemyLife1 = Integer.parseInt(enemyLife);
                int result = strenght - enemyLife1;

                String answer = "" + result;

                System.out.println(answer);

                return encode(ATTACK, "done" + DELIMITER + answer);

            case MOVE:

                Server.ServerWorker enemyServerWorker = getEnemyServerWorker(serverWorker);

                String msg = encode(MOVE, splittedMessage[1] + DELIMITER + splittedMessage[2]);
                enemyServerWorker.sendMessage(msg);
                break;
        }
        return null;
    }

    private static Server.ServerWorker getEnemyServerWorker(Server.ServerWorker serverWorker) {

        Server.ServerWorker enemy = null;
        for (Server.ServerWorker worker : list) {

            if (worker.getID() != serverWorker.getID()) {
                System.out.println("entreiuiiii");
                enemy = worker;
                return enemy;
            }
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
