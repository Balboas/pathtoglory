package org.academiadecodigo.balboas;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Daniel Baeta on 23/11/17.
 */
public class Server {

    ServerSocket serverSocket;
    ExecutorService service;
    private int portNumber = 32323;

    public Server() {
        init();
    }

    public static void main(String[] args) {

        Server server = new Server();

    }

    private void init() {

        try {
            serverSocket = new ServerSocket();
            service = Executors.newCachedThreadPool();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void acceptConnections(){

        try {
            Socket socket = serverSocket.accept();
            service.submit(new ServerWorker(socket));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private class ServerWorker implements Runnable{

        Socket socket;

        public ServerWorker(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {

        }
    }
}
