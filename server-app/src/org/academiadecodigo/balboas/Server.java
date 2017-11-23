package org.academiadecodigo.balboas;

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

            MessageProtocol.decode(message);
        }

        public void sendMessage(String message){

            writer.println(message);
        }

        @Override
        public void run() {

            String message = "";

            try {
                while ((message = reader.readLine()) != null) {
                    getMessage(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
