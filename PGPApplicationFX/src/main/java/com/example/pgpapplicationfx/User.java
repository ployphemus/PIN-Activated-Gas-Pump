package com.example.pgpapplicationfx;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
public class User extends Application{
    @Override
    public void start(Stage stage) throws Exception {
        Button button=new Button("Logout");
        button.setOnAction(e-> {
            try {
                mainMenu(stage);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        Button pump=new Button("Pump Gas");
        pump.setOnAction(e -> {pumpGas(stage);});
        Text text=new Text();
        Button checkTotal=new Button("Check Total Used");
        checkTotal.setOnAction(e -> {text.setText(checkTotal());});
        Button checkTank=new Button("Check Tank Level");
        checkTank.setOnAction(e -> {text.setText(checkTank());});
        VBox box=new VBox();
        box.setSpacing(10);
        box.getChildren().addAll(pump, checkTotal, checkTank, text);
        box.setAlignment(Pos.CENTER);
        StackPane root=new StackPane();
        root.getChildren().addAll(box, button);
        root.setAlignment(box, Pos.CENTER);
        root.setAlignment(button, Pos.TOP_LEFT);
        Scene scene = new Scene(root, 400, 400);
        stage.setTitle("User");
        stage.setScene(scene);
        stage.show();
    }

    public void mainMenu(Stage stage) throws IOException {
        HelloApplication h=new HelloApplication();
        h.start(stage);
    }

    public void pumpGas(Stage stage){

    }

    public String checkTotal(){
        return"Total Used: 9999";
    }

    public String checkTank(){
        return "Tank Level: 9999";
    }
}
