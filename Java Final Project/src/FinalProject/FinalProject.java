/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FinalProject;

import java.io.*;
import javafx.scene.control.Button;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;

import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.swing.JOptionPane;

public class FinalProject extends Application {

    public static void main(String[] args) {

        JOptionPane.showMessageDialog(null, "Welcome!!!");

        // call start method
        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
        // Password authentication method
        Password.loginCheck();

        // Enter main system if password has been verified
        if (Password.authenticationPass() == true) {

            // create roomArray object
            Room[] roomArray = new Room[10];

            // initialise each content in roomArray to default constructor values
            for (int i = 0; i < roomArray.length; i++) {
                roomArray[i] = new Room();
            }

            // customerArray object
            Customer[] customerArray = new Customer[10];

            // initialise each content in customerArray to default constructor values
            for (int i = 0; i < 10; i++) {
                customerArray[i] = new Customer();

            }
            // Open Room file and load all values into roomArray
            Files.openFile("Room.txt");
            Files.readRoomFile(roomArray);
            Files.closeFile();

            // Open Customer file and load all values into customerArray
            Files.openFile("Customer.txt");
            Files.readCustomerFile(customerArray);
            Files.closeFile();

            // Create main window and prevent user from resizing
            primaryStage.setTitle("Main Menu");
            primaryStage.setResizable(false);

            // VBox layout for vertical position
            VBox layout = new VBox(20);
            layout.setPrefWidth(10);
            layout.setAlignment(Pos.CENTER);

            /*
             * Load an icon into displayRoom button
             * Set icon size as well as button size
             * Call desired method upon pressing of displayRoom button
             * 0 as parameter to indicate normal viewing of table ( searching will used another number)
             */
            ImageView viewIcon1 = new ImageView(new Image(new FileInputStream("images/view.png")));
            viewIcon1.setFitWidth(25);
            viewIcon1.setFitHeight(25);
            Button displayRoom = new Button("Display Rooms       ", viewIcon1);
            displayRoom.setPrefSize(250, 40);
            displayRoom.setOnAction(e -> Fxtable.Roomtable(roomArray, 0));

            /*
             * Load an icon into addRoom button
             * Set icon size as well as button size
             * Call desired method upon pressing of addRoom button
             * Passed entire roomArray as parameter
             */
            ImageView addIcon = new ImageView(new Image(new FileInputStream("images/add.png")));
            addIcon.setFitWidth(25);
            addIcon.setFitHeight(25);
            Button addRoom = new Button("Add Room               ", addIcon);
            addRoom.setPrefSize(250, 40);
            addRoom.setOnAction(e -> addRecord(roomArray));

            /*
             * Load an icon into deleteRoom button
             * Set icon size as well as button size
             * Call desired method upon pressing of deleteRoom button
             * Passed entire roomArray and customerArray as parameters
             */
            ImageView deleteIcon = new ImageView(new Image(new FileInputStream("images/delete.png")));
            deleteIcon.setFitWidth(25);
            deleteIcon.setFitHeight(25);
            Button deleteRoom = new Button("Delete Room            ", deleteIcon);
            deleteRoom.setPrefSize(250, 40);
            deleteRoom.setOnAction(e -> deleteRecord(roomArray, customerArray));

            /*
             * Load an icon into deleteRoom button
             * Set icon size as well as button size
             * Call desired method upon pressing of deleteRoom button
             * Passed entire roomArray and customerArray as parameters
             */
            ImageView bookIcon = new ImageView(new Image(new FileInputStream("images/book.png")));
            bookIcon.setFitWidth(25);
            bookIcon.setFitHeight(30);
            Button bookRoom = new Button("Book Room             ", bookIcon);
            bookRoom.setPrefSize(250, 40);
            bookRoom.setOnAction(e -> bookRoom(roomArray, customerArray));

            /*
             * Load an icon into searchRoom button
             * Set icon size as well as button size
             * Call desired method upon pressing of searchRoom button
             * Passed entire roomArray as parameter
             */
            ImageView searchIcon = new ImageView(new Image(new FileInputStream("images/search.png")));
            searchIcon.setFitWidth(25);
            searchIcon.setFitHeight(25);
            Button searchRoom = new Button("Search Room              ", searchIcon);
            searchRoom.setPrefSize(250, 40);
            searchRoom.setOnAction(e -> searchRoom(roomArray));

            /*
             * Load an icon into sort button
             * Set icon size as well as button size
             * Call desired method upon pressing of sort button
             * Passed entire roomArray as parameter
             */
            ImageView sortIcon = new ImageView(new Image(new FileInputStream("images/sort.png")));
            sortIcon.setFitWidth(25);
            sortIcon.setFitHeight(25);
            Button sortButton = new Button("Sorting                        ", sortIcon);
            sortButton.setPrefSize(250, 40);
            sortButton.setOnAction(e -> sorting(roomArray));

            /*
             * Load an icon into exit button
             * Set icon size as well as button size
             * Call desired method upon pressing of exit button
             */
            ImageView exitIcon = new ImageView(new Image(new FileInputStream("images/exit.png")));
            exitIcon.setFitWidth(25);
            exitIcon.setFitHeight(25);
            Button exitButton = new Button("Exit                               ", exitIcon);
            exitButton.setPrefSize(250, 40);
            exitButton.setOnAction(e -> promptOnClosing());

            /*
             * Load an icon into displayCustomers button
             * Set icon size as well as button size
             * Call desired method upon pressing of displayCustomers button
             * Passed entire customerArray as parameter
             */
            ImageView viewIcon2 = new ImageView(new Image(new FileInputStream("images/view.png")));
            viewIcon2.setFitWidth(25);
            viewIcon2.setFitHeight(25);
            Button displayCustomers = new Button("View Customers         ", viewIcon2);
            displayCustomers.setPrefSize(250, 40);
            displayCustomers.setOnAction(e -> Fxtable.customerTable(customerArray));

            /*
             * Load an icon into addCustomer button
             * Set icon size as well as button size
             * Call desired method upon pressing of addCusotmer button
             * Passed entire customerArray as parameter
             */
            ImageView addIcon2 = new ImageView(new Image(new FileInputStream("images/add.png")));
            addIcon2.setFitWidth(25);
            addIcon2.setFitHeight(25);
            Button addCustomer = new Button("Add Customers          ", addIcon2);
            addCustomer.setPrefSize(250, 40);
            addCustomer.setOnAction(e -> AddCustomer(customerArray));

            /*
             * Call promptOnClosing method if user pressed the close button
             * Consume answer before closing
             */
            primaryStage.setOnCloseRequest(e -> {
                e.consume();
                promptOnClosing();
            });

            // Add all buttons to VBox layouy
            layout.getChildren().addAll(displayRoom, addRoom, deleteRoom, bookRoom, displayCustomers, addCustomer, searchRoom, sortButton, exitButton);

            /*
             * Introduce scene to the window
             * Add VBox layout to scene & set scene size
             * Add icon to scene as well as a CSS file for styling
             */
            Scene mainScene = new Scene(layout, 700, 700);
            mainScene.getStylesheets().add("file:stylesheet.css");
            primaryStage.setScene(mainScene);
            primaryStage.getIcons().add(new Image("file:images/mdx.jpg"));
            primaryStage.show();
        }

    }

    public static void addRecord(Room[] roomArray) {
        // Assign indexAvailable to the returning value of returnIndex method 
        // returnIndex method checks if there are any index available to add a room
        int indexAvailable = returnIndex(roomArray);

        // switch statement for index returned
        switch (indexAvailable) {

            case 10:
                // display an error message if no free space left
                JOptionPane.showMessageDialog(null, "Cannot add, limit reached");
                break;

            default:
                // call AddRoom main GUI window
                AddRoom.GUI();

                // checks if all data has been entered in AddRoom GUUI
                if (AddRoom.proceedToAdd() == true) {

                    // Assign roomtype
                    String roomType = AddRoom.returnRoomType();

                    // Switch statement for description depending on roomType
                    String description;
                    switch (roomType) {

                        case "SB":
                            description = "Single-bed";
                            break;

                        case "DB":
                            description = "Double-bed";
                            break;
                        default:
                            description = "Separate-bed";
                            break;

                    }
                    // automatically assign a roomId
                    int roomId = returnRoomId(roomArray);

                    // Add the new room to roomArray
                    roomArray[indexAvailable].setRoomId(roomId);
                    roomArray[indexAvailable].setType(AddRoom.returnRoomType());
                    roomArray[indexAvailable].setPrice(AddRoom.returnPrice());
                    roomArray[indexAvailable].setAvailability(true);
                    roomArray[indexAvailable].setCusId(0);
                    roomArray[indexAvailable].setDaysBooked(0);
                    roomArray[indexAvailable].setDescription(description);

                    //write to files
                    try {
                        Files.writeRoomFile(roomArray);
                    } catch (Exception e) {
                    }
                }

        }

    }

    // checks if there is any free index to add a room
    public static int returnIndex(Room[] room) {
        
        // By default id will be last index
        int id = 10;
        for (int i = 0; i < 10; i++) {

            // Loop till end of array, to find a free index
            if (room[i].getRoomId() == 0) {
                id = i;
                break;
            }

        }
        // return id
        return id;

    }

    // Automatically increment roomId for a new room
    public static int returnRoomId(Room[] roomArray) {
        
        // Delcare max and assign 0
        int max = 0;

        // Loop till end of array, to find last Room Id
        for (int i = 0; i < roomArray.length; i++) {

            if (!(roomArray[i].getRoomId() == 0)) {
                if (roomArray[i].getRoomId() > max) {

                    max = roomArray[i].getRoomId();

                }
            }

        }
        
        // Return the next roomID to be assigned
        return (max + 1);
    }

    public static void deleteRecord(Room[] roomArray, Customer[] customerArray) {
        // call Delete GUI
        DeleteRoom.GUI(roomArray);

        // checks if can proceed to delete, if user aborted, below code will not execute
        if (DeleteRoom.proceedToDelete() == true) {

            // Return roomId to be deleted
            int roomToBeDelete = DeleteRoom.returnRoomId();

            int indexToBeDelete = 0;

            // Find index of roomId to be deleted
            for (int i = 0; i < roomArray.length; i++) {

                if (roomArray[i].getRoomId() == roomToBeDelete) {
                    indexToBeDelete = i;
                    break;

                }

            }

            // Delete the room, i.e rest current room to default values
            roomArray[indexToBeDelete].setRoomId(0);
            roomArray[indexToBeDelete].setType("");
            roomArray[indexToBeDelete].setDescription("");
            roomArray[indexToBeDelete].setAvailability(false);
            roomArray[indexToBeDelete].setPrice(0);
            roomArray[indexToBeDelete].setCusId(0);
            roomArray[indexToBeDelete].setDaysBooked(0);

            // Unbook room from customer
            for (int i = 0; i < customerArray.length; i++) {

                if (customerArray[i].getCusRoom() == roomToBeDelete) {
                    customerArray[i].setCusRoom(0);
                    break;
                }

            }

            // shifting element of array 1 by 1 after deletion  
            for (int i = 0; i < roomArray.length; i++) {

                int tempRoomId = 0;
                String tempType = "";
                String tempDescription = "";
                boolean tempAvailability = false;
                float tempPrice = 0;
                int tempCusId = 0;
                int tempDays_Booked = 0;

                if (roomArray[i].getRoomId() == 0 && (i < 9)) {

                    tempRoomId = roomArray[i].getRoomId();
                    tempType = roomArray[i].getType();
                    tempDescription = roomArray[i].getDescription();
                    tempAvailability = roomArray[i].getAvailability();
                    tempPrice = roomArray[i].getPrice();
                    tempCusId = roomArray[i].getCusId();
                    tempDays_Booked = roomArray[i].getDays_Booked();

                    roomArray[i].setRoomId(roomArray[i + 1].getRoomId());
                    roomArray[i].setType(roomArray[i + 1].getType());
                    roomArray[i].setDescription(roomArray[i + 1].getDescription());
                    roomArray[i].setAvailability(roomArray[i + 1].getAvailability());
                    roomArray[i].setPrice(roomArray[i + 1].getPrice());
                    roomArray[i].setCusId(roomArray[i + 1].getCusId());
                    roomArray[i].setDaysBooked(roomArray[i + 1].getDays_Booked());

                    roomArray[i + 1].setRoomId(tempRoomId);
                    roomArray[i + 1].setType(tempType);
                    roomArray[i + 1].setDescription(tempDescription);
                    roomArray[i + 1].setAvailability(tempAvailability);
                    roomArray[i + 1].setPrice(tempPrice);
                    roomArray[i + 1].setCusId(tempCusId);
                    roomArray[i + 1].setDaysBooked(tempDays_Booked);
                }

                // Write to files
                try {
                    Files.writeRoomFile(roomArray);
                } catch (Exception e) {
                }
            }

        }

    }

    // bookRoom method
    public static void bookRoom(Room[] roomArray, Customer[] customerArray) {

        // call bookRoom GUI
        BookRoom.GUI(roomArray, customerArray);
        int index = 0;

        if (BookRoom.proceedToAdd() == true) {

            // return roomId to be booked
            int RoomID = BookRoom.returnRoomId();

            for (int i = 0; i < roomArray.length; i++) {

                // book the room
                if (roomArray[i].getRoomId() == RoomID) {

                    roomArray[i].setAvailability(false);
                    roomArray[i].setCusId(BookRoom.returnCusID());
                    roomArray[i].setDaysBooked(BookRoom.returnDays());
                    break;
                }

            }

            // update customer for the booking
            for (int x = 0; x < customerArray.length; x++) {

                if (customerArray[x].getCusID() == BookRoom.returnCusID()) {
                    customerArray[x].setCusRoom(RoomID);
                    break;
                }

            }

        }

    }

    // Searching method
    public static void searchRoom(Room[] roomArray) {

        // Call SearchGUI where the user decides types of searching
        Search.SearchGui();

        switch (Search.returnSearchCriteria()) {

            case 1:

                // User wants to search by Room ID
                Search.displayRoomIdComboBox(roomArray);
                if (Search.proceedToSearch() == true) {

                    // Call specified method
                    // 1 as parameter, to indicate singleRoomIdSearch
                    Fxtable.Roomtable(roomArray, 1);

                }
                break;

            case 2:

                // User wants to search rooms where availability == true
                Fxtable.Roomtable(roomArray, 2);

                break;

            case 3:

                // User wants to search by room type
                Search.displayRoomTypeComboBox(roomArray);

                if (Search.proceedToSearch() == true) {

                    Fxtable.Roomtable(roomArray, 3);

                }
                break;

        }

        // need to call new window
        // then 2nd window
        // then re write seatch method
        // proceed to return
        // SearchTable.display(roomArray);
        // int index = 0;
        // if (SearchTable.proceedToSearch() == true) {

        /* for (int i = 0; i < roomArray.length; i++) {
         if (roomArray[i].getRoomId() == SearchTable.returnRoomId()) {
         index = i;
         break;

         }

         }*/
        // Fxtable.Roomtable(roomArray, SearchTable.returnRoomId());
        //SearchTable sort = new SearchTable(roomArray, index);
        // }
    }

    // Sorting method
    public static void sorting(Room[] roomArray) {

        // Sorting GUI to allow user to choose Sorting type
        SortingTable.GUI(roomArray);

        // Temporaries variables
        int tempRoomId, tempCusId, tempDaysBooked;
        String tempType, tempDescription;
        boolean tempAvailability;
        float tempPrice;

        // Sort bu sortingID
        switch (SortingTable.returnSortingID()) {

            // Single sort by roomID (Ascending)
            case 1:

                for (int i = 0; i < roomArray.length; i++) {

                    for (int j = i + 1; j < roomArray.length; j++) {

                        if (roomArray[i].getRoomId() > roomArray[j].getRoomId()) {

                            // Swapping process
                            tempRoomId = roomArray[i].getRoomId();
                            tempType = roomArray[i].getType();
                            tempDescription = roomArray[i].getDescription();
                            tempAvailability = roomArray[i].getAvailability();
                            tempPrice = roomArray[i].getPrice();
                            tempCusId = roomArray[i].getCusId();
                            tempDaysBooked = roomArray[i].getDays_Booked();

                            roomArray[i].setRoomId(roomArray[j].getRoomId());
                            roomArray[i].setType(roomArray[j].getType());
                            roomArray[i].setDescription(roomArray[j].getDescription());
                            roomArray[i].setAvailability(roomArray[j].getAvailability());
                            roomArray[i].setPrice(roomArray[j].getPrice());
                            roomArray[i].setCusId(roomArray[j].getCusId());
                            roomArray[i].setDaysBooked(roomArray[j].getDays_Booked());

                            roomArray[j].setRoomId(tempRoomId);
                            roomArray[j].setType(tempType);
                            roomArray[j].setDescription(tempDescription);
                            roomArray[j].setAvailability(tempAvailability);
                            roomArray[j].setPrice(tempPrice);
                            roomArray[j].setCusId(tempCusId);
                            roomArray[j].setDaysBooked(tempDaysBooked);

                        }

                    }

                }
                // Display the new sorted table
                Fxtable.Roomtable(roomArray, 0);

                break;

              
            // Double Sorting
            case 2:

                for (int i = 0; i < roomArray.length; i++) {

                    for (int j = i + 1; j < roomArray.length; j++) {

                        // Checks pricing
                        if (roomArray[i].getPrice() > roomArray[j].getPrice()) {

                            tempRoomId = roomArray[i].getRoomId();
                            tempType = roomArray[i].getType();
                            tempDescription = roomArray[i].getDescription();
                            tempAvailability = roomArray[i].getAvailability();
                            tempPrice = roomArray[i].getPrice();
                            tempCusId = roomArray[i].getCusId();
                            tempDaysBooked = roomArray[i].getDays_Booked();

                            roomArray[i].setRoomId(roomArray[j].getRoomId());
                            roomArray[i].setType(roomArray[j].getType());
                            roomArray[i].setDescription(roomArray[j].getDescription());
                            roomArray[i].setAvailability(roomArray[j].getAvailability());
                            roomArray[i].setPrice(roomArray[j].getPrice());
                            roomArray[i].setCusId(roomArray[j].getCusId());
                            roomArray[i].setDaysBooked(roomArray[j].getDays_Booked());

                            roomArray[j].setRoomId(tempRoomId);
                            roomArray[j].setType(tempType);
                            roomArray[j].setDescription(tempDescription);
                            roomArray[j].setAvailability(tempAvailability);
                            roomArray[j].setPrice(tempPrice);
                            roomArray[j].setCusId(tempCusId);
                            roomArray[j].setDaysBooked(tempDaysBooked);

                            // If prices are same
                            // then sort by room type
                            // double sorting process
                        } else if (roomArray[i].getPrice() == roomArray[j].getPrice()) {

                            if (roomArray[i].getType().compareToIgnoreCase(roomArray[j].getType()) > 0) {

                                tempRoomId = roomArray[i].getRoomId();
                                tempType = roomArray[i].getType();
                                tempDescription = roomArray[i].getDescription();
                                tempAvailability = roomArray[i].getAvailability();
                                tempPrice = roomArray[i].getPrice();
                                tempCusId = roomArray[i].getCusId();
                                tempDaysBooked = roomArray[i].getDays_Booked();

                                roomArray[i].setRoomId(roomArray[j].getRoomId());
                                roomArray[i].setType(roomArray[j].getType());
                                roomArray[i].setDescription(roomArray[j].getDescription());
                                roomArray[i].setAvailability(roomArray[j].getAvailability());
                                roomArray[i].setPrice(roomArray[j].getPrice());
                                roomArray[i].setCusId(roomArray[j].getCusId());
                                roomArray[i].setDaysBooked(roomArray[j].getDays_Booked());

                                roomArray[j].setRoomId(tempRoomId);
                                roomArray[j].setType(tempType);
                                roomArray[j].setDescription(tempDescription);
                                roomArray[j].setAvailability(tempAvailability);
                                roomArray[j].setPrice(tempPrice);
                                roomArray[j].setCusId(tempCusId);
                                roomArray[j].setDaysBooked(tempDaysBooked);

                            }

                        }

                    }

                }
                // Display the double sorted table
                Fxtable.Roomtable(roomArray, 0);
                
                break;

        }

    }

    // Exiting the application
    public static void promptOnClosing() {

        // Prompts the user  if he/she really wants to exit the application
        try {
            boolean answer = ConfirmationBox.display();
            if (answer == true) {

                Platform.exit();

            }
        } catch (Exception e) {
        }

    }

    // Add Customer method
    public static void AddCustomer(Customer[] customerArray) {

        // Call AddCustomer GUI
        AddCustomer.GUI(customerArray);

        // Checks if all correct data has been entered
        if (AddCustomer.proceedToAdd() == true) {

            // Find a free index in the customerArray
            int indexAvailable = indexAvailableInCustomerArray(customerArray);

            // Create a new record in customerArray
            customerArray[indexAvailable].setCusID(AddCustomer.getFinalcusID());
            customerArray[indexAvailable].setCusFName(AddCustomer.getFinalFName());
            customerArray[indexAvailable].setCusLName(AddCustomer.getFinalLName());
            customerArray[indexAvailable].setCusAge(AddCustomer.getFinalcusAge());
            customerArray[indexAvailable].setCusRoom(0);
            customerArray[indexAvailable].setCusPhone(AddCustomer.getFinalphoneNo());
            customerArray[indexAvailable].setCusAddress(AddCustomer.getFinalAddress());

            // Write to customer files
            try {
                Files.writeCustomerFile(customerArray);
            } catch (Exception e) {
            }

        }

    }

    // Find a free index in customerArray
    public static int indexAvailableInCustomerArray(Customer[] customerArray) {
        int index = 0;
        for (int i = 0; i < customerArray.length; i++) {

            if (customerArray[i].getCusID() == 0) {
                index = i;
                break;
            }

        }

        return index;

    }
}
