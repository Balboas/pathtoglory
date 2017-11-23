package org.academiadecodigo.balboas.controller;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public final class Navigation {


    public final static String VIEW_PATH = "../view";
    private static Navigation instance;
    private LinkedList<Scene> scenes = new LinkedList<>();
    private Map<String, Controller> controllers = new HashMap<>();
    private Stage stage;

    private Navigation() {
    }

    public static Navigation getInstance() {

        if (instance == null) {
            instance = new Navigation();
        }

        return instance;

    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Controller getController(String view) {
        return controllers.get(view);
    }

    public void loadScreen(String view) {

        // Instantiate the view and the controller
        Parent root = loadView(view);

        // Create a new scene and add it to the stack
        Scene scene = new Scene(root);
        scenes.push(scene);

        // Put the scene on the stage
        setScene(scene);

    }

    public Parent loadView(String view) {

        Parent root = null;

        try {

            // Instantiate the the controller
            FXMLLoader fxmlLoader;
            fxmlLoader = new FXMLLoader(getClass().getResource(VIEW_PATH + "/" + view + ".fxml"));
            root = fxmlLoader.load();

            // Store the view and the controller
            controllers.put(view, fxmlLoader.<Controller>getController());

        } catch (IOException e) {
            System.out.println("Failure to load view " + view + " : " + e.getMessage());
            e.printStackTrace();
        }

        return root;

    }

    public void back() {

        if (scenes.isEmpty()) {
            return;
        }

        // remove the current scene from the stack
        scenes.pop();

        // load a new scene from the top of the stack
        setScene(scenes.peek());

    }

    private void setScene(Scene scene) {

        // set the scene
        stage.setScene(scene);

        // show the stage
        stage.show();
    }

    public void close() {

        stage.close();

    }

}
