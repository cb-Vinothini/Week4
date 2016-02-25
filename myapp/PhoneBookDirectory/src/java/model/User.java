/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author cb-vinothini
 */

public class User {
    public String userID, firstName, lastName;
    private String password;

    public User(String userID, String firstName, String lastName, String password){
        this.userID = userID;
        this.lastName = lastName;
        this.firstName = firstName;
        this.password = password;
    }

//    public User(String userName, String firstName, String lastName, String home, String mobile, String work, String password, String line1, String line2, String city, String state, String country, String zip){
//        this.userName = userName;
//        this.mobile = mobile;
//        this.home = home;
//        this.work = work;
//        this.password = password;
//        this.lastName = lastName;
//        this.firstName = firstName;
//        this.line1 = line1;
//        this.line2 = line2;
//        this.city = city;
//        this.state = state;
//        this.country = country;
//        this.zip = zip;
//    }
    
    public String getPassword(){
        return password;
    } 
    
    public void setPassword(String password){
        this.password = password;
    }

    @Override
    public String toString(){
        return(userID+", "+firstName+", "+lastName);
    }
    
}
