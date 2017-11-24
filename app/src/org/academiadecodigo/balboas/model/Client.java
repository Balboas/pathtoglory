package org.academiadecodigo.balboas.model;

import org.academiadecodigo.balboas.controller.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by Daniel Baeta on 23/11/17.
 */
public class Client implements Runnable {

    private Socket socket;
    private PrintWriter writer;
    private BufferedReader reader;
    private int port = 32323;
    private Controller controller;

    public Client(Controller controller) {

        this.controller = controller;
        connect();
    }

    //Must be invoked after instantiation
    public void connect(){

        try {
            socket = new Socket("localhost", port);
            writer = new PrintWriter(socket.getOutputStream(), true);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            new Thread(this).start();

            System.out.println("Connect done client");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Messages must be encoded before.
    public void sendMessage(String message){
        System.out.println("Send message from client " + message);
        writer.println(message);
    }


    //Messages are received and decoded
    public void getMessage(String message){
        MessageProtocol.decode(message);
    }

    @Override
    public void run() {

        String message = null;

        try {
            //Keeps waiting for messages
            while (true) {
                message = reader.readLine();
                System.out.println("Message readen inside client ");
                getMessage(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                System.out.println("Socket closed");
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}