/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FinalProject;

import java.awt.HeadlessException;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class BookRoom {
 
    // GUI features
    private static Stage window;
    private static ComboBox<Integer> comboBox;
    private static TextField cus, days;
    
    // Private variables
    private static boolean dataEntered = false;
    private static int finalCusId, finalNumOfDays, finalRoomId = 0;
    
    // Default constructors
    public BookRoom(){}

    // Main GUI for BookRoom class
    public static void GUI(Room[] roomArray, Customer[] customerArray) {

        // Declares window as stage, set title and modality
        window = new Stage();
        window.setTitle("BOOKING A ROOM");
        window.setResizable(false);
        window.initModality(Modality.APPLICATION_MODAL);

        // Defines GridPane 
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        // Label and set it's location on the GridPane
        Label chooseRoom = new Label("Kindly choose roomID:");
        GridPane.setConstraints(chooseRoom, 1, 1);

        // Create a comboBox
        comboBox = new ComboBox<>();

        // Loop through roomArray to retrieve roomID and adds them to the comboBox
        for (int i = 0; i < roomArray.length; i++) {

            if (!(roomArray[i].getRoomId() == 0) && roomArray[i].getAvailability() == true) {

                comboBox.getItems().add(roomArray[i].getRoomId());

            }

        }
        // Set comboBox default value to 0
        comboBox.setValue(0);
        GridPane.setConstraints(comboBox, 3, 1);

        // Label and set it's location on the GridPane
        Label CusID = new Label("CusID:");
        GridPane.setConstraints(CusID, 1, 2);

        // Textfield and set it's location on the gridPane
        cus = new TextField();
        cus.setPromptText("Enter CUSID");
        GridPane.setConstraints(cus, 3, 2);

        // Label and set it's location on the GridPane
        Label Days_Booked = new Label("Day(s):");
        GridPane.setConstraints(Days_Booked, 1, 3);

        // Textfield and set it's location on the gridPane
        days = new TextField();
        days.setPromptText("Enter days to be booked");
        GridPane.setConstraints(days, 3, 3);

        // Submit button
        Button submit = new Button("Submit");
        submit.setOnAction(e -> validation(comboBox.getValue(), cus.getText(), days.getText(),roomArray,customerArray));

        // Abort method
        Button abort = new Button("Abort");
        abort.setOnAction(e -> {
            window.close();
            dataEntered = false;
        });

        // HBox to arrange the buttons horizontally
        HBox buttonBox = new HBox(10, submit, abort);
        GridPane.setConstraints(buttonBox, 1, 20);

        // Add the window features to the gridPane
        grid.getChildren().addAll(chooseRoom, comboBox, CusID, cus, days, Days_Booked, buttonBox);

         // Create scene to be display on the window
        // Add scene to window, link scene to css file
        Scene bookRoomScene = new Scene(grid, 400, 325);
        bookRoomScene.getStylesheets().add("file:stylesheet.css");
        window.getIcons().add(new Image("file:images/mdx.jpg"));
        window.setScene(bookRoomScene);
        window.showAndWait();

    }

    // Validation method
    private static void validation(int value, String t1, String t2,Room[]  roomArray, Customer[] customerArray) {
        boolean condition1 = true;
        boolean condition2 = true;
        boolean condition3 = true;
        boolean duplicateCusId;
        int cusId = 0;
        int day = 0;
        
        // Checks if user selected a roomID from the comboBox
        if (value == 0) {
            JOptionPane.showMessageDialog(null, "Please select a Room ID");
            condition1 = false;
        }else{
        finalRoomId = value;
        
        }
       boolean found = false;
        try {

            // Convert Textfield data into Integer
            cusId = Integer.parseInt(t1);
            
            // Checks if customer ID exists
            for(int x =0;x<customerArray.length;x++){
             
                if(customerArray[x].getCusID()==cusId){
                found=true;
                break;
                }
            
            
            }
            
            if(found==true){
            
                // Prevens duplicate CusID
                 for(int i=0;i<customerArray.length;i++){
           
              if((customerArray[i].getCusID()==cusId) && (customerArray[i].getCusRoom() !=0)){
              JOptionPane.showMessageDialog(null, "CusId "+cusId+" is already in use!!!!");
                cus.clear();
                condition2 = false;
              }
           }

                 // Condition for valid cusID
            if(cusId > 0 && found==true){
            
                 finalCusId = cusId;
            
            }

               
            

            
            
            }else{
            
                // error message if cusID does not exists
             JOptionPane.showMessageDialog(null, "Please enter a valid CusID ");
            cus.clear();
            condition2 = false;
            
            }
          
            // Catch statement to prevent program from crashing
        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(null, "Please enter a valid CusID ");
            cus.clear();
            condition2 = false;
        }

        // validation for number of days
        try {

            day = Integer.parseInt(t2);

            if (day <= 0) {

                JOptionPane.showMessageDialog(null, "Please enter a valid number of days ");
                days.clear();
                condition3 = false;
            } else {

                finalNumOfDays = day;

            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(null, "Please enter a valid number of days ");
            days.clear();
            condition3 = false;
        }

        // Condition to check if all correct data has been entered
        if (condition1 == true && condition2 == true && condition3 == true) {

            dataEntered = true;
            window.close();

        }

    }

    // getters
    public static boolean proceedToAdd() {
        return dataEntered;

    }

    public static int returnCusID() {
        return finalCusId;
    }

    public static int returnDays() {
        return finalNumOfDays;

    }
    
    public static int returnRoomId(){
    return finalRoomId;
    
    }

}
