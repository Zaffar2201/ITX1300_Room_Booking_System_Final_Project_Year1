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
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author HP
 */
public class SortingTable {
    
    static Stage window;
    static boolean dataEntered;
    static int sortingId;


   public static void GUI(Room[] roomArray){
   
          window = new Stage();
       window.setTitle("Room Booking System");
       window.setResizable(false);
       window.initModality(Modality.APPLICATION_MODAL);

       GridPane grid = new GridPane();
       grid.setPadding(new Insets(10, 10, 10, 10));
       grid.setVgap(8);
       grid.setHgap(10);

       Label chooseSorting = new Label("Kindly choose type of sorting:");
       GridPane.setConstraints(chooseSorting, 1, 2);


        ToggleGroup group = new ToggleGroup();
         

        RadioButton rb1 = new RadioButton("By Room ID(Single Sort)");
        rb1.setToggleGroup(group);
        rb1.setSelected(true);

        RadioButton rb2 = new RadioButton("By Price(Double Sort)");
        rb2.setToggleGroup(group);
   
       
   
   
        VBox radioBox = new VBox(10,rb1,rb2);
        GridPane.setConstraints(radioBox,1,4);
   
        Button submit = new Button("Submit");
        submit.setOnAction(e -> {
         
            if(rb1.isSelected()){
            sortingId=1;
            }else if(rb2.isSelected()){
            sortingId=2;
            }else{
            sortingId=3;
            
            }
        window.close();
        
        
        });
        
        
        
        Button abort = new Button("Abort");
        abort.setOnAction(e -> window.close());
        
        
        HBox buttonBox = new HBox(10,submit,abort);
        GridPane.setConstraints(buttonBox,1,13);
        
       grid.getChildren().addAll(chooseSorting,radioBox,buttonBox);
   
        Scene s1 = new Scene(grid, 325, 250);
        s1.getStylesheets().add("file:stylesheet.css");
        window.getIcons().add(new Image("file:images/mdx.jpg"));
        window.setScene(s1);
        window.showAndWait();
   
   
   }
   
   public static int returnSortingID(){
   return sortingId;
   
   }
    
    
}
