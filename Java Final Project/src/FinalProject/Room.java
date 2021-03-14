/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FinalProject;

/**
 *
 * @author HP
 */
public class Room {
	 // declaring attributes
    private int roomId;
    private String type;
    private String description;
    private boolean availability;
    private float price;
    private int cusId;
    private int days_Booked;

    // default constructor
    public Room() {

        roomId = 0;
        type = "";
        description = "";
        availability = false;
        price = 0;
        cusId = 0;
        days_Booked = 0;

    }

    // 2nd constructor
    public Room(int i, String t, String des, boolean a, float p, int c, int d) {
        roomId = i;
        type = t;
        description = des;
        availability = a;
        price = p;
        cusId = c;
        days_Booked = d;

    }

    // accessors
    public void setRoomId(int id) {
        this.roomId = id;

    }

    public void setType(String type) {
        this.type = type;

    }

    public void setDescription(String description) {
        this.description = description;

    }

    public void setAvailability(boolean available) {
        this.availability = available;

    }

    public void setPrice(float price) {
        this.price = price;

    }

    public void setCusId(int cusId) {
        this.cusId = cusId;

    }

    public void setDaysBooked(int days) {
        this.days_Booked = days;
    }

    // mutators
    public int getRoomId() {
        return roomId;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public boolean getAvailability() {
        return availability;
    }

    public float getPrice() {
        return price;
    }

    public int getCusId() {
        return cusId;
    }

    public int getDays_Booked() {
        return days_Booked;
    }

    // print methods
    public void printAll(Room[] currentRoom) {

        System.out.printf("|%10s |%10s |%15s |%13s |%10s |%10s |%13s", roomId, type, description, availability, price, cusId, days_Booked + "|\n");

    }

    public void printAvailable(Room[] currentRoom) {

        System.out.printf("|%7s |%7s |%15s |%10s", roomId, type, description, price + "|\n");

    }

    public void printRented(Room[] currentRoom) {

        System.out.printf("|%7s |%7s |%15s |%10s", roomId, type, description, cusId + "|\n");

    }

}
