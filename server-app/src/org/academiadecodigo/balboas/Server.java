package org.academiadecodigo.balboas;

import org.academiadecodigo.balboas.services.JdbcUserService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Daniel Baeta on 23/11/17.
 */
public class Server {

    private ServerSocket serverSocket;
    private ExecutorService service;
    private int portNumber = 32323;


    Server() {
        JdbcUserService jdbcUserService = new JdbcUserService();
        MessageProtocol.setService(jdbcUserService);
        init();
    }

    public static void main(String[] args) {

        Server server = new Server();

        while (true) {
            server.acceptConnections();
        }
    }


    private void init() {

        try {
            serverSocket = new ServerSocket(portNumber);
            service = Executors.newCachedThreadPool();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void acceptConnections() {

        try {
            Socket socket = serverSocket.accept();
            System.out.println("Connection established");
            service.submit(new ServerWorker(socket));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private class ServerWorker implements Runnable {

        private Socket socket;
        private BufferedReader reader;
        private PrintWriter writer;


        ServerWorker(Socket socket) {
            this.socket = socket;
            init();
        }

        private void init() {

            try {
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                writer = new PrintWriter(socket.getOutputStream(), true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void getMessage(String message) {

            String answer = MessageProtocol.decode(message);
            sendMessage(answer);
        }

        public void sendMessage(String message){
            System.out.println("Send message from server");
            writer.println(message);
        }

        @Override
        public void run() {

            String message = "";

            try {
                while (true) {
                    message = reader.readLine();
                    System.out.println(message);
                    System.out.println("Message read in Server Side " + message);
                    getMessage(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    System.out.println("socket closed");
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
