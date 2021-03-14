/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FinalProject;

import java.util.Scanner;
import javax.swing.JOptionPane;
import java.io.*;

public class Files {

    // private scanner variable thisfile
    private static Scanner thisfile;

    public static void openFile(String text) {

        // try statement to open file
        try {
            thisfile = new Scanner(new java.io.File(text));

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "File does not exist!!!");

        }

    }

    public static void readRoomFile(Room[] roomArray) {
        int roomId;
        String type;
        String description;
        boolean availability;
        float price;
        int cusId;
        int days_Booked;
        int count = 0;
        
        // loop until current file has data to be read
        while (thisfile.hasNext()) {

            // retrieve data
            // convert them into appropriate data
            roomId = Integer.parseInt(thisfile.next());
            type = thisfile.next();
            description = thisfile.next();
            availability = Boolean.parseBoolean(thisfile.next());
            price = Float.parseFloat(thisfile.next());
            cusId = Integer.parseInt(thisfile.next());
            days_Booked = Integer.parseInt(thisfile.next());

            // Load the data into the array
            roomArray[count] = new Room(roomId, type, description, availability, price, cusId, days_Booked);

            // Increment counter
            count++;
        }

    }
    
    
    public static void readCustomerFile(Customer[] customerArray) {

        int cusID;
        String cusFName;
        String cusLName;
        int cusAge;
        int cusRoom;
        int cusPhone;
        String cusAddress;
        int count = 0;

        // loop until current file has data to be read
        while (thisfile.hasNext()) {

            cusID = Integer.parseInt(thisfile.next());
            cusFName = thisfile.next();
            cusLName = thisfile.next();
            cusAge = Integer.parseInt(thisfile.next());
            cusRoom = Integer.parseInt(thisfile.next());
            cusPhone = Integer.parseInt(thisfile.next());
            cusAddress = thisfile.next();

            // retrieve data
            // convert them into appropriate data
            customerArray[count] = new Customer(cusID, cusFName, cusLName, cusAge, cusRoom, cusPhone, cusAddress);
            
            // Increment counter
            count++;
        }
    
    
    
    
    
    
    }
    
    
    
    
    
    
    
    
    

    public static void writeRoomFile(Room[] roomArray) throws IOException {

        // Define file to be overwritten
        File myFile = new File("Room.txt");
        PrintStream theWriter = new PrintStream(myFile);

       // Load data from array into files
        for (int i = 0; i < roomArray.length; i++) {

            if (!(roomArray[i].getRoomId() == 0)) {

                theWriter.print(roomArray[i].getRoomId());
                theWriter.print(" ");
                theWriter.print(roomArray[i].getType());
                theWriter.print(" ");
                theWriter.print(roomArray[i].getDescription());
                theWriter.print(" ");
                theWriter.print(roomArray[i].getAvailability());
                theWriter.print(" ");
                theWriter.print(roomArray[i].getPrice());
                theWriter.print(" ");
                theWriter.print(roomArray[i].getCusId());
                theWriter.print(" ");
                theWriter.println(roomArray[i].getDays_Booked());

            }

        }
        // Close the current file
        theWriter.close();
    }
    
     public static void writeCustomerFile(Customer[] customerArray) throws IOException {

         // Define file to be overwritten
        File myFile = new File("Customer.txt");
        PrintStream theWriter = new PrintStream(myFile);

       
        // Load data from array into files
        for (int i = 0; i < customerArray.length; i++) {

            if (!(customerArray[i].getCusID() == 0)) {

                theWriter.print(customerArray[i].getCusID());
                theWriter.print(" ");
                theWriter.print(customerArray[i].getCusFName());
                theWriter.print(" ");
                theWriter.print(customerArray[i].getCusLName());
                theWriter.print(" ");
                theWriter.print(customerArray[i].getCusAge());
                theWriter.print(" ");
                theWriter.print(customerArray[i].getCusRoom());
                theWriter.print(" ");
                theWriter.print(customerArray[i].getCusPhone());
                theWriter.print(" ");
                theWriter.println(customerArray[i].getCusAddress());

            }

        }
        //Close current file
        theWriter.close();
    }

     // Close the file
    public static void closeFile() {

        thisfile.close();

    }
}
