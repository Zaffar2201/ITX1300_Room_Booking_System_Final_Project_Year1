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
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.swing.JOptionPane;


public class DeleteRoom {

    private static Stage window;
    private static ComboBox<Integer> comboBox;
    private static boolean dataEntered = false;

    // Default constructor
    public DeleteRoom(){}
    
    public static void GUI(Room[] roomArray) {

        // GUI window for deleteRoom
        window = new Stage();
        window.setTitle("DELETING A ROOM");
        window.setResizable(false);
        window.initModality(Modality.APPLICATION_MODAL);

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        Label chooseRoom = new Label("Kindly choose roomID:");
        GridPane.setConstraints(chooseRoom, 1, 1);

        // comboBox
        comboBox = new ComboBox<>();

        for (int i = 0; i < roomArray.length; i++) {

            if (!(roomArray[i].getRoomId() == 0)) {
                // add roomId to combobox
                comboBox.getItems().add(roomArray[i].getRoomId());

            }

        }
        // set initial value of combobox
        comboBox.setValue(0);

        //comboBox.setPromptText("RoomID");
        GridPane.setConstraints(comboBox, 3, 1);

        Button submit = new Button("Submit");
        submit.setOnAction(e -> {
            // checks if user chose a roomID
            if ((comboBox.getValue() > 0)) {
                dataEntered = true;
                JOptionPane.showMessageDialog(null, "Room has been sucessfully deleted");
                window.close();
            } else {
                JOptionPane.showMessageDialog(null, "ERROR! Please choose a RoomId");
                window.close();
            }
        });

        // abort button action
        Button abort = new Button("Abort");
        abort.setOnAction(e -> {
            window.close();
            dataEntered = false;
        });

        HBox buttonBox = new HBox(10, submit, abort);
        GridPane.setConstraints(buttonBox, 1, 33);

        grid.getChildren().addAll(chooseRoom, comboBox, buttonBox);

        Scene deleteRoomScene = new Scene(grid, 400, 350);
        deleteRoomScene.getStylesheets().add("file:stylesheet.css");
        window.getIcons().add(new Image("file:images/mdx.jpg"));
        window.setScene(deleteRoomScene);
        window.showAndWait();

    }

    // return roomId to be deleted
    public static int returnRoomId() {
        int num = comboBox.getValue();
        return num;
    }

    public static boolean proceedToDelete() {
        return dataEntered;

    }

}
