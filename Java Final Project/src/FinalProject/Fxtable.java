/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FinalProject;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author HP
 */
public class Fxtable {

   private static Stage  RoomtableWindow, CustomerTableWindow;
   private static TableView tableItems;

   // Method to load data from roomArray into javafx table
    public static Room[] RoomTableData(Room[] roomArray) {

        for (int i = 0; i < roomArray.length; i++) {
            if (roomArray[i].getRoomId() != 0) {
                // add data to each row in the table
                tableItems.getItems().add(new Room(roomArray[i].getRoomId(), roomArray[i].getType(),
                        roomArray[i].getDescription(), roomArray[i].getAvailability(), roomArray[i].getPrice(),
                        roomArray[i].getCusId(), roomArray[i].getDays_Booked()
                ));
            }
        }

        // return the array
        return roomArray;
    }

    // Method to load a roomArray data into javafx table
    public static Room[] roomSearch(Room[] roomArray) {

        for (int i = 0; i < roomArray.length; i++) {

            // Checks for the roomID
            if (roomArray[i].getRoomId() == Search.returnRoomId()) {
                
                // add the room data to the javafx table
                tableItems.getItems().add(new Room(roomArray[i].getRoomId(), roomArray[i].getType(),
                        roomArray[i].getDescription(), roomArray[i].getAvailability(), roomArray[i].getPrice(),
                        roomArray[i].getCusId(), roomArray[i].getDays_Booked()
                ));

                break;
            }

        }

        // return the array
        return roomArray;

    }
    // Method loading available rooms into javafx table
    public static Room[] availabilitySearch(Room[] roomArray){
    
        // Loop through array, and add rooms where availability == true to javafx table
        for (int i = 0; i < roomArray.length; i++) {

            if (roomArray[i].getAvailability() == true) {
                tableItems.getItems().add(new Room(roomArray[i].getRoomId(), roomArray[i].getType(),
                        roomArray[i].getDescription(), roomArray[i].getAvailability(), roomArray[i].getPrice(),
                        roomArray[i].getCusId(), roomArray[i].getDays_Booked()
                ));

                
            }

        }
        
        
    return roomArray;
    }
    
    
    // Method to retrieve rooms based on roomTypes
    public static Room[] roomTypeSearch(Room[] roomArray){
    
         for (int i = 0; i < roomArray.length; i++) {

             // Loop till end of array & retrieve desired room type 
            if (roomArray[i].getType().equalsIgnoreCase(Search.returnRoomType())) {
                tableItems.getItems().add(new Room(roomArray[i].getRoomId(), roomArray[i].getType(),
                        roomArray[i].getDescription(), roomArray[i].getAvailability(), roomArray[i].getPrice(),
                        roomArray[i].getCusId(), roomArray[i].getDays_Booked()
                ));

                
            }

        }
        
        
    return roomArray;
    
    
    
    
    }

    // Main table for room
    public static void Roomtable(Room[] roomArray, int id) {

        RoomtableWindow = new Stage();
        RoomtableWindow.setTitle("ROOM TABLE");
        RoomtableWindow.initModality(Modality.APPLICATION_MODAL);
        RoomtableWindow.setResizable(false);
        try {

            // Create column and indicate which data type will be held
            // Prevent resizing of column
            TableColumn<Room, String> IdColumn = new TableColumn<>("Room ID");
            IdColumn.setCellValueFactory(new PropertyValueFactory<>("roomId"));
            IdColumn.setResizable(false);
            IdColumn.setMinWidth(100);
            IdColumn.setSortable(false);

            TableColumn<Room, String> TypeColumn = new TableColumn<>("Room Type");
            TypeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
            TypeColumn.setResizable(false);
            TypeColumn.setMinWidth(100);
            TypeColumn.setSortable(false);

            TableColumn<Room, String> DesColumn = new TableColumn<>("Description");
            DesColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
            DesColumn.setResizable(false);
            DesColumn.setMinWidth(150);
            DesColumn.setSortable(false);

            TableColumn<Room, Boolean> AvailabilityColumn = new TableColumn<>("Availability");
            AvailabilityColumn.setCellValueFactory(new PropertyValueFactory<>("availability"));
            AvailabilityColumn.setResizable(false);
            AvailabilityColumn.setMinWidth(120);
            AvailabilityColumn.setSortable(false);

            TableColumn<Room, Float> PriceColumn = new TableColumn<>("Price");
            PriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
            PriceColumn.setResizable(false);
            PriceColumn.setMinWidth(125);
            PriceColumn.setSortable(false);

            TableColumn<Room, Integer> CusIdColumn = new TableColumn<>("Cus Id");
            CusIdColumn.setCellValueFactory(new PropertyValueFactory<>("cusId"));
            CusIdColumn.setResizable(false);
            CusIdColumn.setMinWidth(100);
            CusIdColumn.setSortable(false);

            TableColumn<Room, Integer> DaysColumn = new TableColumn<>("Days Booked");
            DaysColumn.setCellValueFactory(new PropertyValueFactory<>("days_Booked"));
            DaysColumn.setResizable(false);
            DaysColumn.setMinWidth(100);
            DaysColumn.setSortable(false);

            tableItems = new TableView<>();
            tableItems.prefHeightProperty().bind(RoomtableWindow.heightProperty());
            tableItems.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

            // Call desried method for the javafx display
            switch (id) {
                case 0:
                    RoomTableData(roomArray);
                    break;
                case 1:
                    roomSearch(roomArray);
                    break;
                case 2:
                   availabilitySearch(roomArray); 
                    break;
                default:
                    roomTypeSearch(roomArray);
                    break;
                    
            }
            // Link data from array to the javafx table
            tableItems.getColumns().addAll(IdColumn, TypeColumn, DesColumn, AvailabilityColumn, PriceColumn, CusIdColumn, DaysColumn);

        } catch (Exception e) {
        }

        Button btnBack = new Button("Back");
        btnBack.setPadding(new Insets(10, 10, 10, 10));
        btnBack.setMinWidth(200);
        btnBack.setOnAction(e -> RoomtableWindow.close());

        VBox vbox = new VBox();
        vbox.getChildren().addAll(tableItems);
        vbox.setAlignment(Pos.BOTTOM_CENTER);

        BorderPane bp = new BorderPane();
        bp.setCenter(vbox);

        Scene scene = new Scene(bp, 800, 800);
        scene.getStylesheets().add("file:stylesheet.css");
        RoomtableWindow.getIcons().add(new Image("file:images/mdx.jpg"));
        RoomtableWindow.setScene(scene);
        RoomtableWindow.show();

    }
    
    
    public static void customerTable(Customer[] customerArray){
    
        // Customer table similar to Room table
         CustomerTableWindow = new Stage();
        CustomerTableWindow.setTitle("CUSTOMER TABLE");
        CustomerTableWindow.initModality(Modality.APPLICATION_MODAL);
        CustomerTableWindow.setResizable(false);
    
        TableColumn<Customer, Integer> IdColumn = new TableColumn<>("ID");
            IdColumn.setCellValueFactory(new PropertyValueFactory<>("cusID"));
            IdColumn.setResizable(false);
            IdColumn.setMinWidth(100);
            IdColumn.setSortable(false);

            TableColumn<Customer, String> FNameColumn = new TableColumn<>("First Name");
            FNameColumn.setCellValueFactory(new PropertyValueFactory<>("cusFName"));
            FNameColumn.setResizable(false);
            FNameColumn.setMinWidth(100);
            FNameColumn.setSortable(false);

            TableColumn<Customer, String> LNameColumn = new TableColumn<>("Last Name");
            LNameColumn.setCellValueFactory(new PropertyValueFactory<>("cusLName"));
            LNameColumn.setResizable(false);
            LNameColumn.setMinWidth(150);
            LNameColumn.setSortable(false);

            TableColumn<Customer, Integer> AgeColumn = new TableColumn<>("Age");
            AgeColumn.setCellValueFactory(new PropertyValueFactory<>("cusAge"));
            AgeColumn.setResizable(false);
            AgeColumn.setMinWidth(120);
            AgeColumn.setSortable(false);

            TableColumn<Customer, Integer> RoomColumn = new TableColumn<>("Room ID");
            RoomColumn.setCellValueFactory(new PropertyValueFactory<>("cusRoom"));
            RoomColumn.setResizable(false);
            RoomColumn.setMinWidth(125);
            RoomColumn.setSortable(false);

            TableColumn<Room, Integer> PhoneColumn = new TableColumn<>("Phone");
            PhoneColumn.setCellValueFactory(new PropertyValueFactory<>("cusPhone"));
            PhoneColumn.setResizable(false);
            PhoneColumn.setMinWidth(100);
            PhoneColumn.setSortable(false);

            TableColumn<Room, String> AddressColumn = new TableColumn<>("Address");
            AddressColumn.setCellValueFactory(new PropertyValueFactory<>("cusAddress"));
            AddressColumn.setResizable(false);
            AddressColumn.setMinWidth(100);
            AddressColumn.setSortable(false);

            tableItems = new TableView<>();
            tableItems.prefHeightProperty().bind(CustomerTableWindow.heightProperty());
            tableItems.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    
            CustomerTableData(customerArray);
           tableItems.getColumns().addAll(IdColumn, FNameColumn, LNameColumn, AgeColumn, RoomColumn, PhoneColumn, AddressColumn);
           
        
        VBox vbox = new VBox();
        vbox.getChildren().addAll(tableItems);
        vbox.setAlignment(Pos.BOTTOM_CENTER);

        Scene scene = new Scene(vbox, 800, 800);
        scene.getStylesheets().add("file:stylesheet.css");
        CustomerTableWindow.getIcons().add(new Image("file:images/mdx.jpg"));
        CustomerTableWindow.setScene(scene);
        CustomerTableWindow.show();

            
    }
    
    
    // Method to retrieve Customer data
    public static Customer[] CustomerTableData(Customer[] customerArray) {

        // loop till end of array
        // Display customer data
        for (int i = 0; i < customerArray.length; i++) {
            if (customerArray[i].getCusID() != 0) {
                tableItems.getItems().add(new Customer(customerArray[i].getCusID(), customerArray[i].getCusFName(),
                        customerArray[i].getCusLName(), customerArray[i].getCusAge(), customerArray[i].getCusRoom(),
                        customerArray[i].getCusPhone(), customerArray[i].getCusAddress()
                ));
            }
        }

        return customerArray;
    }
    
    
    
    

}
