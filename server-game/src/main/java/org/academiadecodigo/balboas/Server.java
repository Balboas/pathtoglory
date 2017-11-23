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
    ServerSocket serverSocket;

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Server server = new Server();

        while (true) {
            server.acceptConnections(executorService);
        }
    }

    private void acceptConnections(ExecutorService service) {
        try {
            Socket socket = serverSocket.accept();
            ServerWorker worker = new ServerWorker(socket);
            service.submit(worker);
            MessageProtocol.addPlayer(worker, worker.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public class ServerWorker implements Runnable {

        private Socket socket;
        private BufferedReader reader;
        private PrintWriter writer;
        private String name;

        public ServerWorker(Socket socket) {
            this.socket = socket;
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

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
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
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
