/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FinalProject;


import java.awt.HeadlessException;
import javafx.scene.control.TextField;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javax.swing.JOptionPane;


public class AddRoom {

    private static Stage window;
    private static float finalPrice;
    private static String finalRoomType;
    private static TextField price;
    private static boolean dataEntered;
    
    //Default constructor
    public AddRoom(){}

    public static void GUI() {
        // window for adding a room
        window = new Stage();
        window.setTitle("ADDING A ROOM");
        window.setResizable(false);
        window.initModality(Modality.APPLICATION_MODAL);
     
        // Gridpane used on the scene
        // Set gridpane padding and gaps
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        // Display a label and set it's X & Y location on the gridpane
        Label chooseRoom = new Label("Kindly choose type of room:");
        GridPane.setConstraints(chooseRoom, 1, 2);

        // Togglegroup for radio buttons
        ToggleGroup group = new ToggleGroup();

        // Radio button, by default this one will be selected
        RadioButton sb = new RadioButton("Single-bed");
        sb.setToggleGroup(group);
        sb.setSelected(true);

        RadioButton db = new RadioButton("Double-bed");
        db.setToggleGroup(group);

        RadioButton sp = new RadioButton("Separate-bed");
        sp.setToggleGroup(group);
 
        // Add radio buttons to a HBox for horizontal alignment
        HBox radioBox = new HBox(10, sb, db, sp);
        GridPane.setConstraints(radioBox, 3, 2);

        Label enterPrice = new Label("Price:");
        GridPane.setConstraints(enterPrice, 1, 5);
        
        // Textfield for price
        price = new TextField();
        GridPane.setConstraints(price, 3, 5);
        
        // Submit button, call below validations
        Button submit = new Button("Submit");
        submit.setOnAction(e -> {

            if (sb.isSelected()) {

                validation(price.getText(), "SB");

            } else if (db.isSelected()) {

                validation(price.getText(), "DB");
            } else {

                validation(price.getText(), "SP");
            }

        });
 
        // Close current window
        Button abort = new Button("Abort");
        abort.setOnAction(e -> {
            window.close();
            dataEntered = false;
        });

        HBox buttonBox = new HBox(10, submit, abort);
        GridPane.setConstraints(buttonBox, 3, 7);

        grid.getChildren().addAll(chooseRoom, enterPrice, price, radioBox, buttonBox);

        Scene addRoomScene = new Scene(grid, 700, 200);
        addRoomScene.getStylesheets().add("file:stylesheet.css");
        window.getIcons().add(new Image("file:images/mdx.jpg"));
        window.setScene(addRoomScene);
        window.showAndWait();

    }

    public static void validation(String p, String t) {

        float thePrice = 0;

        // try statement for the conversion of price from string to float
        try {

            thePrice = Float.parseFloat(p);
 
            // Prevent invalid price
            if (thePrice <= 0) {
                JOptionPane.showMessageDialog(null, "Invalid Price");
                price.clear();
            }
        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(null, "Invalid Price");
            price.clear();
        }

        // In case for valid prices
        if (thePrice > 0) {
            finalPrice = thePrice;
            finalRoomType = t;
            dataEntered = true;
            window.close();

            JOptionPane.showMessageDialog(null, "Room has been sucessfully added to the database!!!");
        }

    }
    // getters
    public static float returnPrice() {

        return finalPrice;

    }

    public static String returnRoomType() {

        return finalRoomType;
    }

    public static boolean proceedToAdd() {
        return dataEntered;

    }

}
