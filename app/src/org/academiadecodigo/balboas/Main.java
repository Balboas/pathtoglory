package org.academiadecodigo.balboas;

import javafx.application.Application;
import javafx.stage.Stage;
import org.academiadecodigo.balboas.controller.LoginController;
import org.academiadecodigo.balboas.controller.Navigation;

public class Main extends Application {



    @Override
    public void start(Stage primaryStage) throws Exception{


        Navigation navigation = Navigation.getInstance();
        navigation.setStage(primaryStage);


        // Load the login screen§
        navigation.loadScreen(LoginController.getNAME());

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
