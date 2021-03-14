/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FinalProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;


public class ConfirmationBox {
    
    private static Stage alertWindow;
    
    
   private static boolean answer;
   
   // Default constructor
   public ConfirmationBox(){}
   
   
    public static boolean display() throws FileNotFoundException {

        // Main GUI
        alertWindow = new Stage();
        alertWindow.initModality(Modality.APPLICATION_MODAL);
        alertWindow.setTitle("EXIT");
        alertWindow.setMinWidth(400);
        alertWindow.setResizable(false);

        // Label Text
        Label message = new Label();
        message.setText("Do you really want to exit the application?");

        // Set an icon on yesButton
        ImageView tickIcon = new ImageView(new Image(new FileInputStream("images/tick.png")));
        tickIcon.setFitWidth(25);
        tickIcon.setFitHeight(25);
        Button yesButton = new Button("Yes", tickIcon);

        // Set an icon on noButton
        ImageView crossIcon = new ImageView(new Image(new FileInputStream("images/cross.png")));
        crossIcon.setFitWidth(25);
        crossIcon.setFitHeight(25);
        Button noButton = new Button("No", crossIcon);


        // yesButton action
        yesButton.setOnAction(e -> {

            
            answer = true;
            alertWindow.close();
        });

        // noButton action
        // consume asnwer before exiting
        noButton.setOnAction(e -> {
            e.consume();
            alertWindow.close();
            answer = false;

        });

        // VBox layout for vertical arrangment
        VBox layout = new VBox(10);
        layout.getChildren().addAll(message, yesButton, noButton);
        layout.setAlignment(Pos.CENTER);

        // Scene for alertWindow
        Scene alertScene = new Scene(layout);
        alertScene.getStylesheets().add("file:stylesheet.css");
        alertWindow.getIcons().add(new Image("file:images/mdx.jpg"));
        alertWindow.setScene(alertScene);
        alertWindow.showAndWait();

        // return answer
        return answer;


    
    
    
    
    
    
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
} 
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

