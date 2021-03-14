/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FinalProject;


import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author HP
 */
public class Search {
    
    private static Stage window;
    private static int criteria = 0;
    private static Stage idCombowindow;
    private static Stage typewindow;
    private static ComboBox<Integer> comboBox;
    private static ComboBox<String> roomTypeComboBox;
    private static boolean dataEntered;
    private static int RoomID;
    private static String finalRoomType;
    
    
 public static void SearchGui(){
 
 window = new Stage();
        window.setTitle("SEARCHING CRITERIA");
        window.setResizable(false);
        window.initModality(Modality.APPLICATION_MODAL);
        window.setOnCloseRequest(e-> criteria=0);
        
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);
 
         Label  chooseSearch = new Label("Kindly choose type of searching:");
        GridPane.setConstraints(chooseSearch, 1, 2);
 
        ToggleGroup group = new ToggleGroup();
         

        RadioButton rb1 = new RadioButton("Room Id");
        rb1.setToggleGroup(group);
        rb1.setSelected(true);

        RadioButton rb2 = new RadioButton("Availability");
        rb2.setToggleGroup(group);
        
        RadioButton rb3 = new RadioButton("Room Type");
        rb3.setToggleGroup(group);
 
        VBox radioBox = new VBox(10, rb1,rb2,rb3);
        GridPane.setConstraints(radioBox,1,3);
 
        Button submit = new Button("Submit");
        submit.setOnAction(e -> 
        {
        if(rb1.isSelected()){
        criteria=1;
        window.close();
        }else if (rb2.isSelected()){
        criteria=2;
        window.close();
        }else if (rb3.isSelected()){
        criteria=3;
        window.close();
        }else{
        criteria=0;
        }
        
        
        });
        
        Button abort = new Button("Abort");
        abort.setOnAction(e -> {
         criteria=0;
        window.close();
                });
        
        HBox buttonBox = new HBox(10,submit,abort);
        GridPane.setConstraints(buttonBox,1,10);
        
        grid.getChildren().addAll(chooseSearch,radioBox,buttonBox);
        
        
        
        
        Scene s1 = new Scene(grid, 350, 250);
        s1.getStylesheets().add("file:stylesheet.css");
        window.getIcons().add(new Image("file:images/mdx.jpg"));
        window.setScene(s1);
        window.showAndWait();
 
 } 
 
  public static void displayRoomIdComboBox(Room[] roomArray) {

        idCombowindow = new Stage();
        idCombowindow.setTitle("SEARCH BY ROOM ID");
        idCombowindow.setResizable(false);
        idCombowindow.initModality(Modality.APPLICATION_MODAL);

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        Label chooseRoom = new Label("Kindly choose roomID:");
        GridPane.setConstraints(chooseRoom, 1, 1);

        comboBox = new ComboBox<>();

        for (Room roomArray1 : roomArray) {
            if (!(roomArray1.getRoomId() == 0)) {
                comboBox.getItems().add(roomArray1.getRoomId());
            }
        }
        comboBox.setValue(0);

        //comboBox.setPromptText("RoomID");
        GridPane.setConstraints(comboBox, 3, 1);

        Button submit = new Button("Submit");
        submit.setOnAction(e -> {
            if ((comboBox.getValue() > 0)) {
                dataEntered = true;
                RoomID = comboBox.getValue();
                idCombowindow.close();
            } else {
                JOptionPane.showMessageDialog(null, "ERROR! Please choose a RoomId");
                idCombowindow.close();
            }
        });

        Button abort = new Button("Abort");
        abort.setOnAction(e -> {
            idCombowindow.close();
            dataEntered = false;
        });

        HBox buttonBox = new HBox(10, submit, abort);
        GridPane.setConstraints(buttonBox, 1, 35);

        grid.getChildren().addAll(chooseRoom, comboBox, buttonBox);

        Scene s1 = new Scene(grid, 300, 350);
        s1.getStylesheets().add("file:stylesheet.css");
        idCombowindow.setScene(s1);
        idCombowindow.getIcons().add(new Image("file:images/mdx.jpg"));
        idCombowindow.showAndWait();


    
    
    }
  
  
  public static void displayRoomTypeComboBox(Room[] roomArray){
  
      typewindow = new Stage();
      typewindow.setTitle("SEARCH BY ROOM TYPE");
      typewindow.setResizable(false);
      typewindow.initModality(Modality.APPLICATION_MODAL);

       GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        Label chooseType = new Label("Room Type:");
        GridPane.setConstraints(chooseType, 1, 1);
        
         roomTypeComboBox = new ComboBox<>();
  
        roomTypeComboBox.getItems().addAll("Single-bed","Double-bed","Separate-bed");
        roomTypeComboBox.setPromptText("Please select Room Type");
        GridPane.setConstraints(roomTypeComboBox,2,1);
        
         Button submit = new Button("Submit");
         submit.setOnAction(e -> validation(roomTypeComboBox.getValue()));

        Button abort = new Button("Abort");
         abort.setOnAction(e -> {
            typewindow.close();
            
        });

        HBox buttonBox = new HBox(10, submit, abort);
        GridPane.setConstraints(buttonBox, 1, 35);

  
        grid.getChildren().addAll(chooseType, roomTypeComboBox, buttonBox);
        
         Scene s1 = new Scene(grid, 425, 370);
         s1.getStylesheets().add("file:stylesheet.css");
         typewindow.getIcons().add(new Image("file:images/mdx.jpg"));
        typewindow.setScene(s1);
        typewindow.showAndWait();
  
       
  
  
  
  }
    
    
    public static int returnSearchCriteria(){
    return criteria;
    } 
    
     public static boolean proceedToSearch(){
    
    return dataEntered;
    
    }
    
    public static int returnRoomId(){
    
       return RoomID;
    
    }
    
    public static String returnRoomType(){
    
        return finalRoomType;
    
    }
    
    
    public static void validation(String text){
    
   if(text == null){
   
   JOptionPane.showMessageDialog(null,"Invalid Room Type");
   dataEntered=false;
   typewindow.close();
   
   }else{
   
       switch (text) {

           case "Single-bed":
               finalRoomType = "SB";
               dataEntered = true;
               break;
           case "Double-bed":
               finalRoomType = "DB";
               dataEntered = true;
               break;
           case "Separate-bed":
               finalRoomType = "SP";
               dataEntered = true;
               break;

       }
      
     typewindow.close();
   
   }
    
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
}
