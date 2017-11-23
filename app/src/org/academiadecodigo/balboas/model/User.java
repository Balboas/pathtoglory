package org.academiadecodigo.balboas.model;

import org.academiadecodigo.balboas.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by Daniel Baeta on 23/11/17.
 */
public class User implements Runnable {

    private Socket socket;
    private PrintWriter writer;
    private BufferedReader reader;
    private int port = 32323;
    private Controller controller;

    public User(Controller controller) {

        this.controller = controller;
    }

    //Must be invoked after instantiation
    public void connect(){

        try {
            socket = new Socket("localhost", port);
            writer = new PrintWriter(socket.getOutputStream());
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Messages must be encoded before.
    public void sendMessage(String message){
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
