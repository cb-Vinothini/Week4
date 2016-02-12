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
    public String firstName, lastName, email, line1, line2, city, state, country, zip;
    private String password;

    public User(String firstName, String lastName, String email, String password){
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.password = password;
        line1 = null;
        line2 = null;
        city = null;
        state = null;
        country = null;
        zip = null;
    }

    public User(String firstName, String lastName, String email, String password, String line1, String line2, String city, String state, String country, String zip){
            this.email = email;
            this.password = password;
            this.lastName = lastName;
            this.firstName = firstName;
            this.line1 = line1;
            this.line2 = line2;
            this.city = city;
            this.state = state;
            this.country = country;
            this.zip = zip;
    }
    
    public String getPassword(){
        return password;
    } 
    
    public void setPassword(String password){
        this.password = password;
    }

    @Override
    public String toString(){
        return(firstName+", "+lastName+", "+email+", "+line1+", "+line2+", "+city+", "+state+", "+country+", "+zip);
    }
    
}
