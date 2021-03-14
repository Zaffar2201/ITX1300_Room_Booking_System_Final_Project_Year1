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
public class Customer {

    // Private attributes
    private int cusID;
    private String cusFName;
    private String cusLName;
    private int cusAge;
    private int cusRoom;
    private int cusPhone;
    private String cusAddress;   // name,number,address, id

    // Default constructor
    public Customer() {
        cusID = 0;
        cusFName = "";
        cusLName = "";
        cusAge = 0;
        cusRoom = 0;
        cusPhone = 0;
        cusAddress = "";
    }

    // 2nd constructor
    public Customer(int id, String fname, String lname, int age, int room, int phone, String address) {
        cusID = id;
        cusFName = fname;
        cusLName = lname;
        cusAge = age;
        cusRoom = room;
        cusPhone = phone;
        cusAddress = address;

    }

    // Setters and getters
    public int getCusID() {
        return cusID;
    }

    public void setCusID(int cusID) {
        this.cusID = cusID;
    }

    public String getCusFName() {
        return cusFName;
    }

    public void setCusFName(String cusFName) {
        this.cusFName = cusFName;
    }

    public int getCusAge() {
        return cusAge;
    }

    public void setCusAge(int cusAge) {
        this.cusAge = cusAge;
    }

    public int getCusRoom() {
        return cusRoom;
    }

    public void setCusRoom(int cusRoom) {
        this.cusRoom = cusRoom;
    }

    public int getCusPhone() {
        return cusPhone;
    }

    public void setCusPhone(int cusPhone) {
        this.cusPhone = cusPhone;
    }

    public String getCusAddress() {
        return cusAddress;
    }

    public void setCusAddress(String cusAddress) {
        this.cusAddress = cusAddress;
    }

    public String getCusLName() {
        return cusLName;
    }

    public void setCusLName(String cusLName) {
        this.cusLName = cusLName;
    }

}
