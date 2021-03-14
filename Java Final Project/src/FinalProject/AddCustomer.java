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
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javax.swing.JOptionPane;

public class AddCustomer {
  
    // GUI features
    private static Stage window;
    private static TextField CustomerID;
    private static TextField Customer_FName;
    private static TextField Customer_LName;
    private static TextField Customer_Number;
    private static TextField Customer_Address;
    private static TextField Customer_Age;

    // Private variables
    private static int finalcusID;
    private static int finalcusAge;
    private static int finalphoneNo;
    private static String finalFName;
    private static String finalLName;
    private static String finalAddress;
    private static boolean condition1, condition2, condition3, condition4;
    private static boolean allCorrectDataEntered = false;
    
    // Default constructor
    public AddCustomer(){}
    
    
    // Main GUI for AddCustomer Class
    public static void GUI(Customer[] customerArray) {
        // Declares window as stage, set title and modality
        window = new Stage();
        window.setTitle("ADD CUSTOMER RECORD");
        window.setResizable(false);
        window.initModality(Modality.APPLICATION_MODAL);

        // Defines GridPane 
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(10, 10, 10, 10));
        pane.setVgap(8);
        pane.setHgap(10);

        // Label and set it's location on the GridPane
        Label custID = new Label("Enter ID: ");
        GridPane.setConstraints(custID, 1, 2);

        // Textfield and set it's location on the gridPane
        CustomerID = new TextField();
        GridPane.setConstraints(CustomerID, 2, 2);

        // Label and set it's location on the GridPane
        Label custFName = new Label("Enter First Name: ");
        GridPane.setConstraints(custFName, 1, 3);

        // Textfield and set it's location on the gridPane
        Customer_FName = new TextField();
        GridPane.setConstraints(Customer_FName, 2, 3);

        // Label and set it's location on the GridPane
        Label custLName = new Label("Enter Last Name: ");
        GridPane.setConstraints(custLName, 1, 4);

        // Textfield and set it's location on the gridPane
        Customer_LName = new TextField();
        GridPane.setConstraints(Customer_LName, 2, 4);

        // Label and set it's location on the GridPane
        Label phoneNum = new Label("Enter Phone Number: ");
        GridPane.setConstraints(phoneNum, 1, 5);

        // Textfield and set it's location on the gridPane
        Customer_Number = new TextField();
        GridPane.setConstraints(Customer_Number, 2, 5);

        // Label and set it's location on the GridPane
        Label cusAdd = new Label("Enter Address: ");
        GridPane.setConstraints(cusAdd, 1, 6);

        // Textfield and set it's location on the gridPane
        Customer_Address = new TextField();
        GridPane.setConstraints(Customer_Address, 2, 6);

        // Label and set it's location on the GridPane
        Label cusAge = new Label("Enter Age: ");
        GridPane.setConstraints(cusAge, 1, 7);

        // Textfield and set it's location on the gridPane
        Customer_Age = new TextField();
        GridPane.setConstraints(Customer_Age, 2, 7);

        // Create back button and set it's purpose when pressed
        Button back = new Button(" Back ");
        back.setOnAction(e -> {
            window.close();
            allCorrectDataEntered = false;
        });

        // Create submit button and call below validations when pressed
        Button submit = new Button(" Submit ");
        submit.setOnAction(e -> {
            validateName();
            validateCusID(customerArray);
            validatePhone();
            validateAge();

            // Condition to make sure all correct data has been entered, then close this window
            if (condition1 == true && condition2 == true && condition3 == true && condition4 == true) {

                window.close();

            }
        });

        // HBox for horizontal alignment for the buttons
        HBox buttons = new HBox(5, submit, back);
        GridPane.setConstraints(buttons, 1, 10);

        // Add all features to the GridPane
        pane.getChildren().addAll(custID, CustomerID, custFName, Customer_FName, custLName, Customer_LName, phoneNum, Customer_Number, cusAdd, Customer_Address, cusAge, Customer_Age, buttons);

        // Create scene to be display on the window
        // Add scene to window, link scene to css file
        Scene addCustomerScene = new Scene(pane, 600, 350);
        addCustomerScene.getStylesheets().add("file:stylesheet.css");
        window.getIcons().add(new Image("file:images/mdx.jpg"));
        window.setScene(addCustomerScene);
        window.showAndWait();

    }

    // Validate customer ID method
    public static void validateCusID(Customer[] customerArray) {
        condition1 = true;

        try {

            // Convert the String cusID into Integer cusID 
            finalcusID = (Integer.parseInt(CustomerID.getText()));

            // Loop through customerArray to prevent duplicate cusID
            for (int i = 0; i < customerArray.length; i++) {

                if (customerArray[i].getCusID() == getFinalcusID()) {

                    // Error message & automatically clears CustomerID textfield
                    condition1 = false;
                    JOptionPane.showMessageDialog(null, "Choose a unique customer ID!!!");
                    CustomerID.clear();
                    break;
                }

            }

            // Prevent invalid cusID like -45
            if (finalcusID <= 0) {
                condition1 = false;
                JOptionPane.showMessageDialog(null, "Invalid Customer ID!!!");

            }
            // Catch statement to prevent program from crashing
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid Customer ID!!!");
            CustomerID.clear();
            condition1 = false;
        }

    }
    
    // Validate phone number method
    public static void validatePhone(){
    condition2=true;
    try{
    
        // Convert data from textfield into Integer
    finalphoneNo = Integer.parseInt(Customer_Number.getText());
    
        // Prevent invalid phoneNumber
        if (finalphoneNo <= 0) {
            condition2=false;
            JOptionPane.showMessageDialog(null, "Invalid Phone Number!!!");

        }
    }catch(Exception e){
    JOptionPane.showMessageDialog(null, "Invalid Phone Number!!!");
    Customer_Number.clear();
    condition2=false;
    
    }
    }
    
    // Validate Age method
     public static void validateAge(){
    condition3=true;
    try{
    
        // Convert data from textfield into Integer
    finalcusAge = Integer.parseInt(Customer_Age.getText());
    
        if (finalcusAge <= 0) {
            condition3=false;
            JOptionPane.showMessageDialog(null, "Invalid Age!!!");

        }
    }catch(Exception e){
    JOptionPane.showMessageDialog(null, "Invalid Age!!!");
    Customer_Age.clear();
    condition3=false;
    
    }
    
    
    
    }
    
    
     // Validate Names method
   public static void validateName(){
    condition4=true;
    
    // Prevent user to enter invalid characters
    if(!Customer_FName.getText().matches("^[a-zA-z]+$")){
     JOptionPane.showMessageDialog(null, "Invalid FNAME!!!\nTry using '_' for more than 1 word");
     Customer_FName.clear();
     condition4=false;
    
    }else{
    
    finalFName = Customer_FName.getText();
    }
    
    if(!Customer_LName.getText().matches("^[a-zA-z]+$")){
     JOptionPane.showMessageDialog(null, "Invalid LNAME!!!Try using '_' for more than 1 word");
     Customer_LName.clear();
     condition4=false;
    
    }else{
    
    finalLName = Customer_LName.getText();
    }
    
    if(!Customer_Address.getText().matches("^[a-zA-Z0-9]+$")){
        JOptionPane.showMessageDialog(null, "Invalid Address!!!Try using '_' for more than 1 word");
        Customer_Address.clear();
        condition4=false;
    }else{
    
        finalAddress = Customer_Address.getText();
    
    }
    }
     
    public static boolean proceedToAdd() {

        if (condition1 == true && condition2 == true && condition3 == true && condition4 == true) {

            allCorrectDataEntered = true;

        }

        return allCorrectDataEntered;
    }


    // getters
    public static int getFinalcusID() {
        return finalcusID;
    }

    public static int getFinalcusAge() {
        return finalcusAge;
    }

    
    public static int getFinalphoneNo() {
        return finalphoneNo;
    }

    
    public static String getFinalFName() {
        return finalFName;
    }

   
    public static String getFinalLName() {
        return finalLName;
    }

    
    public static String getFinalAddress() {
        return finalAddress;
    }
    
    
    
    
    
    
}
