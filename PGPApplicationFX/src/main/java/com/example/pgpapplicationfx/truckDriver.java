package com.example.pgpapplicationfx;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class truckDriver extends Application {

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
        Button add=new Button("Add Fuel");
        add.setOnAction(e -> {addFuel(stage);});
        Button checkTotal=new Button("Check Total Added");
        checkTotal.setOnAction(e -> {text.setText(checkTotal());});
        Button changeStatus=new Button("Change Pump Status");
        changeStatus.setOnAction(e -> {changeStatus(stage);});
        StackPane root=new StackPane();
        VBox box=new VBox();
        box.setSpacing(10);
        box.setAlignment(Pos.CENTER);
        box.getChildren().addAll(add, checkTotal, changeStatus, text);
        root.getChildren().addAll(box, button);
        root.setAlignment(box, Pos.CENTER);
        root.setAlignment(button, Pos.TOP_LEFT);
        Scene scene = new Scene(root, 400, 400);
        stage.setTitle("Truck Driver");
        stage.setScene(scene);
        stage.show();
    }

    public void mainMenu(Stage stage) throws IOException {
        HelloApplication h=new HelloApplication();
        h.start(stage);
    }

    public void addFuel(Stage stage){

    }
    public String checkTotal(){
        return "Total Added: 9999";
    }

    public void changeStatus(Stage stage){

    }
}
