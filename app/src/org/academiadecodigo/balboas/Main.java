package org.academiadecodigo.balboas;

import javafx.application.Application;
import javafx.stage.Stage;
import org.academiadecodigo.balboas.controller.LoginController;
import org.academiadecodigo.balboas.controller.Navigation;
import org.academiadecodigo.balboas.model.Client;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main extends Application {

    ExecutorService executorService = Executors.newSingleThreadExecutor();

    @Override
    public void start(Stage primaryStage) throws Exception{


        Navigation navigation = Navigation.getInstance();
        navigation.setStage(primaryStage);


        // Load the login screen§
        navigation.loadScreen(LoginController.getNAME());
        executorService.submit(new Client(navigation.getController(LoginController.getNAME())));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
