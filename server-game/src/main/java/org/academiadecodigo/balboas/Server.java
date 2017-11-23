package org.academiadecodigo.balboas;

import org.academiadecodigo.balboas.utils.MessageProtocol;

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

    private static int port = 32323;
    private ServerSocket serverSocket;
    private static ExecutorService executorService;

    public Server() {
        init();
    }

    public static void main(String[] args) {
        Server server = new Server();

        Game game = new Game();
        MessageProtocol.setGame(game);

        while (true) {
            server.acceptConnections();
        }
    }

    private void init(){
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        executorService = Executors.newFixedThreadPool(2);
    }

    private void acceptConnections() {
        try {
            Socket socket = serverSocket.accept();
            System.out.println("A new connection!");
            ServerWorker worker = new ServerWorker(socket);
            executorService.submit(worker);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public class ServerWorker implements Runnable {

        private Socket socket;
        private BufferedReader reader;
        private PrintWriter writer;

        public ServerWorker(Socket socket) {
            this.socket = socket;
            init();
        }

        private void init() {
            try {
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                writer = new PrintWriter(socket.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void sendMessage(String message) {

            writer.println(message);
        }

        @Override
        public void run() {

            String message = "";

            try {
                while ((message = reader.readLine()) != null) {
                    MessageProtocol.decode(message, this);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    socket.close();
                    System.out.println("Closing connection");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
