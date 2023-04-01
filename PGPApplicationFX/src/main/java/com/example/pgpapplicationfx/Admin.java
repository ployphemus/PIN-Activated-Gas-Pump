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
public class Admin extends Application{
    @Override
    public void start(Stage stage) throws Exception {
        Button button=new Button("Logout");
        button.setOnAction(e -> {
            try {
                mainMenu(stage);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        Text text=new Text();
        Button add=new Button("Add User");
        add.setOnAction(e -> {addUser(stage);});
        Button remove=new Button("Remove User");
        remove.setOnAction(e -> {removeUser(stage);});
        Button checkTank=new Button("Check Tank Level");
        checkTank.setOnAction(e -> {text.setText(checkTank());});
        Button changeStatus=new Button("Change Pump Status");
        changeStatus.setOnAction(e -> {changeStatus(stage);});
        StackPane root=new StackPane();
        VBox box=new VBox();
        box.setSpacing(10);
        box.setAlignment(Pos.CENTER);
        box.getChildren().addAll(add, remove, checkTank, changeStatus, text);
        root.getChildren().addAll(box, button);
        root.setAlignment(box, Pos.CENTER);
        root.setAlignment(button, Pos.TOP_LEFT);
        Scene scene = new Scene(root, 400, 400);
        stage.setTitle("Admin");
        stage.setScene(scene);
        stage.show();
    }

    public void mainMenu(Stage stage) throws IOException {
        HelloApplication h=new HelloApplication();
        h.start(stage);
    }

    public void addUser(Stage stage){

    }

    public void removeUser(Stage stage){

    }

    public String checkTank(){
        return "Tank level: 9999";
    }

    public void changeStatus(Stage stage){

    }
}
