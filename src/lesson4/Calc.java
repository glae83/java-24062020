package lesson4;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Calc extends Application {

    public double calculate (double valueOne, double valueTwo, String valueTop){
        switch (valueTop){
            case "-":
                return valueOne - valueTwo;
            case "+":
                return valueOne + valueTwo;
            case "*":
                return valueOne * valueTwo;
            case "/":
                if (valueTwo == 0)
                    return 0;
                return valueOne / valueTwo;
        }
        return 0;
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
    public static void main (String[] args){
        launch(args);
    }

}

