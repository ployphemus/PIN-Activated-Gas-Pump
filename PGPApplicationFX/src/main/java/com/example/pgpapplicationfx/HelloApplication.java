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

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        mainMenu(stage);



    }

    public Scene mainMenu(Stage stage){
        User user=new User();
        Admin admin=new Admin();
        TextField input=new TextField("Admin, User, or Truck Driver");
        input.setMinSize(300, 40);
        input.setMaxSize(300, 40);
        input.setFont(Font.font(20));
        input.setOnAction(e -> {
            try {
                checkInput(input.getText(), stage);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        Button button=new Button("Enter");
        button.setMinSize(100, 80);
        button.setMaxSize(100, 80);
        button.setFont(Font.font(20));
        button.setOnAction(e -> {
            try {
                checkInput(input.getText(), stage);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        Text title=new Text("PIN Activated Gas Pump");
        title.setFont(Font.font(30));
        StackPane root=new StackPane();
        root.setPrefSize(400, 400);
        VBox box=new VBox();
        box.setSpacing(10);
        box.setAlignment(Pos.CENTER);
        box.getChildren().addAll(input, button);
        root.getChildren().addAll(title, box);
        root.setAlignment(title, Pos.TOP_CENTER);
        root.setAlignment(box, Pos.CENTER);
        root.setPadding(new Insets(40));
        Scene scene = new Scene(root);
        stage.setTitle("Main Menu");
        stage.setScene(scene);
        stage.show();
        return scene;
    }

    public void checkInput(String s, Stage stage) throws Exception {
        if(s.equals("User") || s.equals("user")){
            User user=new User();
            user.start(stage);
        }
        else if(s.equals("Admin") || s.equals("admin")){
            Admin admin=new Admin();
            admin.start(stage);
        }
        else if(s.equals("Truck Driver") || s.equals("td")){
            truckDriver truckDriver=new truckDriver();
            truckDriver.start(stage);
        }
        else{
            error(stage);
        }
    }

    public void error(Stage stage){
        TextField input=new TextField("Admin or User");
        input.setOnAction(e -> {
            try {
                checkInput(input.getText(), stage);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        input.setMinSize(300, 40);
        input.setMaxSize(300, 40);
        input.setFont(Font.font(20));;
        Button button=new Button("Enter");
        button.setMinSize(100, 80);
        button.setMaxSize(100, 80);
        button.setFont(Font.font(20));
        button.setOnAction(e -> {
            try {
                checkInput(input.getText(), stage);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        Text title=new Text("PIN Activated Gas Pump");
        title.setFont(Font.font(30));
        Text error=new Text("Error, enter a valid option. ");
        StackPane root=new StackPane();
        root.setPrefSize(400, 400);
        VBox box=new VBox();
        box.setSpacing(10);
        box.setAlignment(Pos.CENTER);
        box.getChildren().addAll(input, button, error);
        root.getChildren().addAll(title, box);
        root.setAlignment(title, Pos.TOP_CENTER);
        root.setAlignment(box, Pos.CENTER);
        root.setPadding(new Insets(40));
        Scene scene = new Scene(root);
        stage.setTitle("Main Menu");
        stage.setScene(scene);
        stage.show();
    }












        public static void main(String[] args) {
        launch();
    }
}
