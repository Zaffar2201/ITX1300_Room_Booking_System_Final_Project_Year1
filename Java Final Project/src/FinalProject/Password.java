/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FinalProject;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author HP
 */
public class Password {

    private static TextField username;
    private static Label message, pass, usernameLabel;
    private static PasswordField password;
    private static Stage window;
    private static boolean authentication;

    // Default constructor
    public Password() {
    }

    public static void loginCheck() {

        // Setting up the window
        window = new Stage();
        window.setTitle("PASSWORD AUTHENTICATION");
        window.setResizable(false);
        window.setOnCloseRequest(e -> {
            authentication = false;

        });

        // Create GridPane and set it's gaps
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        // Label
        usernameLabel = new Label("Username:");
        GridPane.setConstraints(usernameLabel, 1, 2);

        // Textfield
        username = new TextField();
        GridPane.setConstraints(username, 2, 2);

        pass = new Label("Password:");
        GridPane.setConstraints(pass, 1, 3);

        message = new Label();
        GridPane.setConstraints(message, 2, 5);

        // PasswordField
        password = new PasswordField();
        password.setPromptText("Enter password here");
        GridPane.setConstraints(password, 2, 3);

        // Submit button
        // Validations upon pressed
        Button submit = new Button("Log in");
        submit.setOnAction(e -> validPassword(username.getText(), password.getText()));
        GridPane.setConstraints(submit, 2, 7);

        grid.getChildren().addAll(usernameLabel, username, pass, password, submit, message);
        Scene s1 = new Scene(grid, 400, 200);
        s1.getStylesheets().add("file:stylesheet.css");
        window.getIcons().add(new Image("file:images/mdx.jpg"));
        window.setScene(s1);
        window.showAndWait();

    }

    private static void validPassword(String userName, String passWord) {

        // Compares the username and Password
        if (userName.equals("Zaffar") && passWord.equals("Zaffar")) {
            window.close();
            authentication = true;

            // Display error message if wrong password entered
        } else {
            message.setText("Invalid Username/Password!");
            message.setTextFill(Color.rgb(210, 39, 30));
            username.clear();
            password.clear();
        }

    }

    // Getters
    public static boolean authenticationPass() {
        return authentication;

    }

}
    
    
    
    
    
    
    
    
    
    
    

