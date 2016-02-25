/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletContext;

/**
 *
 * @author cb-vinothini
 */
public class UserDB {
    private static String JDBC_DRIVER;
    private static String DB_URL;
    private static String USER;
    private static String PASS;

    String userExists = "SELECT COUNT(1) as count FROM users WHERE user_id = ?";
    String insertAccount = "INSERT INTO users (first_name, last_name, password, user_id) VALUES (?, ?, ?, ?)";                    
    String TrueLogin = "SELECT 'true' FROM users WHERE user_id = ? AND password = ?";            
    String userDetails = "SELECT user_id, first_name, last_name, password FROM users WHERE user_id = ?"; 
    String getLocationid = "SELECT location_id FROM location WHERE state_id = ? AND country_id =?";
    String addAddress = "INSERT INTO address(line1, line2, city, location_id, zip) VALUES(?, ?, ?, ?, ?)";    
    String addConct = "INSERT INTO contacts(user_id, c_first_name, c_last_name, c_address_id, mobile, home, work) VALUES (?, ?, ?, last_insert_id(), ?, ?, ?)";
    String dispAllContact = "SELECT c.contact_id, c.user_id, c.c_first_name, c.c_last_name, c.mobile, c.home, c.work, a.line1, a.line2, a.city, s.state, co.country, a.zip FROM contacts c INNER JOIN address a on a.address_id = c.c_address_id inner join location l on l.location_id = a.location_id inner join states s on s.state_id = l.state_id inner join countries co on co.country_id = l.country_id where c.user_id = ?";
    String selectByPartialName = "SELECT c.contact_id, c.user_id, c.c_first_name, c.c_last_name, c.mobile, c.home, c.work, a.line1, a.line2, a.city, s.state, co.country, a.zip FROM contacts c INNER JOIN address a on a.address_id = c.c_address_id inner join location l on l.location_id = a.location_id inner join states s on s.state_id = l.state_id inner join countries co on co.country_id = l.country_id where c.user_id = ? and (c.c_first_name like concat('%',?,'%') || c.c_last_name like concat('%',?,'%'))";
    String selectState = "SELECT state FROM states";
    String selectCountry = "Select country FROM country";
    String selectByNumber = "SELECT c.contact_id, c.user_id, c.c_first_name, c.c_last_name, c.mobile, c.home, c.work, a.line1, a.line2, a.city, s.state, co.country, a.zip FROM contacts c INNER JOIN address a on a.address_id = c.c_address_id inner join location l on l.location_id = a.location_id inner join states s on s.state_id = l.state_id inner join countries co on co.country_id = l.country_id where c.user_id = ? and (c.mobile = ? or c.home = ? or c.work = ?)";
    String updateContact = "UPDATE contacts set c_first_name = ?, c_last_name = ?, mobile = ?, home = ?, work = ? WHERE contact_id =?";
    String updateAddress = "update contacts c, address a set a.line1 = ?, a.line2 =?, a.city = ?, a.location_id = ?, a.zip = ? where c.contact_id = ? and c.c_address_id = a.address_id";
    String getLocation = "SELECT l.location_id FROM location l inner join states s on s.state_id=l.state_id inner join countries c on c.country_id=l.country_id WHERE s.state = ? AND c.country =?";
    
    public void editDetails(String c_id,String fname, String lname, String line1, String line2, String city, String state, String country, String zip, String mobile, String home, String work){
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try{
            conn = getConnectionToDatabase();
            preparedStatement = conn.prepareStatement(updateContact);
            preparedStatement.setString(1, fname);
            preparedStatement.setString(2, lname);
            preparedStatement.setString(3, mobile);
            preparedStatement.setString(4, home);
            preparedStatement.setString(5, work);
            preparedStatement.setInt(6, Integer.parseInt(c_id));
            preparedStatement.executeUpdate();

            preparedStatement = conn.prepareStatement(getLocation);
            preparedStatement.setString(1, state);
            preparedStatement.setString(2, country);
            ResultSet rs = preparedStatement.executeQuery();
            preparedStatement = conn.prepareStatement(updateAddress);
            preparedStatement.setString(1, line1);
            preparedStatement.setString(2, line2);
            preparedStatement.setString(3, city);
            preparedStatement.setString(5, zip);
            preparedStatement.setInt(6, Integer.parseInt(c_id));
            if(rs.next()){
                preparedStatement.setInt(4, rs.getInt("location_id"));                
            }else{
                preparedStatement.setInt(4, 1);                
            }
            preparedStatement.executeUpdate();
            
        }catch(Exception e){
            System.out.println(e);
        }finally{
            closePreparedStatement(preparedStatement);
            closeConnection(conn);            
        }
        
    }

    public boolean emailExists(String email){
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try{
            conn = getConnectionToDatabase();
            preparedStatement = conn.prepareStatement(userExists);
            preparedStatement.setString(1, email);
            ResultSet rs = preparedStatement.executeQuery();
            int count = -1;
            if(rs.next()){
                count = rs.getInt("count");                
            }
            if(count==0){
                return false;
            }
            else{
                return true;
            }
        }catch(Exception e){
            System.out.println(e);
        }finally{
            closePreparedStatement(preparedStatement);
            closeConnection(conn);            
        }
        return false;
    }
    
    public boolean AddContacts(String userID, String firstName, String lastName, String mobile, String home, String work, String line1, String line2, String city, String state_id, String country_id, String zip){
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try{
            conn = getConnectionToDatabase();
            preparedStatement = conn.prepareStatement(getLocationid);
            preparedStatement.setInt(1, Integer.parseInt(state_id));
            preparedStatement.setInt(2, Integer.parseInt(country_id));
            ResultSet rs = preparedStatement.executeQuery();
            int locationID = 1;
            if(rs.next()){
                locationID = rs.getInt("location_id");
            }
            preparedStatement = conn.prepareStatement(addAddress);
            preparedStatement.setString(1, line1);
            preparedStatement.setString(2, line2);
            preparedStatement.setString(3, city);
            preparedStatement.setInt(4, locationID);
            preparedStatement.setString(5, zip);
            preparedStatement.executeUpdate();

            preparedStatement = conn.prepareStatement(addConct);
            preparedStatement.setString(1, userID);
            preparedStatement.setString(2, firstName);
            preparedStatement.setString(3, lastName);
            preparedStatement.setString(4, mobile);
            preparedStatement.setString(5, home);
            preparedStatement.setString(6, work);
            int val = preparedStatement.executeUpdate();
            
            if(val==1){
                return true;
            }
        }catch(Exception e){
            System.out.println(e);
        }finally{
            closePreparedStatement(preparedStatement);
            closeConnection(conn);            
        }
        return false;        
    }
    
    public User displayUserRow(String userID){
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        User user = null;
        try{
            conn = getConnectionToDatabase();
            preparedStatement = conn.prepareStatement(userDetails);
            preparedStatement.setString(1, userID);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                String fName = rs.getString("first_name");
                String lName = rs.getString("last_name");
                String password = rs.getString("password");
                user = new User(userID, fName, lName, password);
            }
        }catch(Exception e){
            System.out.println(e);
        }finally{
            closePreparedStatement(preparedStatement);
            closeConnection(conn);            
        }
        return user;
    }
    
    public List<Contact> displayAllContactDetails(String userID){
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        List<Contact> contacts = new ArrayList<Contact>();
        try{
            conn = getConnectionToDatabase();
            preparedStatement = conn.prepareStatement(dispAllContact);
            preparedStatement.setString(1, userID);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                int id=rs.getInt("contact_id");
                String fname = rs.getString("c_first_name");
                String lname = rs.getString("c_last_name");
                String line1 = rs.getString("line1");
                String line2 = rs.getString("line2");
                String city = rs.getString("city");
                String state= rs.getString("state");
                String country = rs.getString("country");
                String zip = rs.getString("zip");
                String mobile = rs.getString("mobile");
                String home = rs.getString("home");
                String work = rs.getString("work");
                Contact cont = new Contact(id, fname, lname, line1, line2, city, state, country, zip, mobile, home, work);
                contacts.add(cont);
            }
        }catch(Exception e){
            System.out.println(e);
        }finally{
            closePreparedStatement(preparedStatement);
            closeConnection(conn);            
        }
        return contacts;        
    }

    public List<String> displayStates(){
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        List<String> states = new ArrayList<String>();
        try{
            conn = getConnectionToDatabase();
            preparedStatement = conn.prepareStatement(selectState);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                String state= rs.getString("state");
                states.add(state);
            }
        }catch(Exception e){
            System.out.println(e);
        }finally{
            closePreparedStatement(preparedStatement);
            closeConnection(conn);            
        }
        return states;        
    }
    
    public List<String> displayCountry(){
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        List<String> countries = new ArrayList<String>();
        try{
            conn = getConnectionToDatabase();
            preparedStatement = conn.prepareStatement(selectCountry);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                String country= rs.getString("country");
                countries.add(country);
            }
        }catch(Exception e){
            System.out.println(e);
        }finally{
            closePreparedStatement(preparedStatement);
            closeConnection(conn);            
        }
        return countries;        
    }

    public List<Contact> displayAllContactByPartialName(String pattern, String user_id){
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        List<Contact> contacts = new ArrayList<Contact>();
        try{
            conn = getConnectionToDatabase();
            preparedStatement = conn.prepareStatement(selectByPartialName);
            preparedStatement.setString(1, user_id);
            preparedStatement.setString(2, pattern);
            preparedStatement.setString(3, pattern);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                int id=rs.getInt("contact_id");
                String fname = rs.getString("c_first_name");
                String lname = rs.getString("c_last_name");
                String line1 = rs.getString("line1");
                String line2 = rs.getString("line2");
                String city = rs.getString("city");
                String state= rs.getString("state");
                String country = rs.getString("country");
                String zip = rs.getString("zip");
                String mobile = rs.getString("mobile");
                String home = rs.getString("home");
                String work = rs.getString("work");
                Contact cont = new Contact(id, fname, lname, line1, line2, city, state, country, zip, mobile, home, work);
                contacts.add(cont);
            }
        }catch(Exception e){
            System.out.println(e);
        }finally{
            closePreparedStatement(preparedStatement);
            closeConnection(conn);            
        }
        return contacts;        
    }

    public List<Contact> displayAllContactByNumber(String number, String user_id){
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        List<Contact> contacts = new ArrayList<Contact>();
        try{
            conn = getConnectionToDatabase();
            preparedStatement = conn.prepareStatement(selectByNumber);
            preparedStatement.setString(1, user_id);
            preparedStatement.setString(2, number);
            preparedStatement.setString(3, number);
            preparedStatement.setString(4, number);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                int id=rs.getInt("contact_id");
                String fname = rs.getString("c_first_name");
                String lname = rs.getString("c_last_name");
                String line1 = rs.getString("line1");
                String line2 = rs.getString("line2");
                String city = rs.getString("city");
                String state= rs.getString("state");
                String country = rs.getString("country");
                String zip = rs.getString("zip");
                String mobile = rs.getString("mobile");
                String home = rs.getString("home");
                String work = rs.getString("work");
                Contact cont = new Contact(id, fname, lname, line1, line2, city, state, country, zip, mobile, home, work);
                contacts.add(cont);
            }
        }catch(Exception e){
            System.out.println(e);
        }finally{
            closePreparedStatement(preparedStatement);
            closeConnection(conn);            
        }
        return contacts;        
    }
    
    public boolean authenticate(String userID, String password) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try{
            conn = getConnectionToDatabase();
            preparedStatement = conn.prepareStatement(TrueLogin);
            preparedStatement.setString(1, userID);
            preparedStatement.setString(2, password);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                return true;                    
            }
        }catch(Exception e){
            System.out.println(e);
        }finally{
            closePreparedStatement(preparedStatement);
            closeConnection(conn);            
        }
        return false;
    }
    
    public boolean register(User user){
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try{
            String userID = user.userID;
            String fname = user.firstName;
            String lname = user.lastName;
            String password = user.getPassword();
            conn = getConnectionToDatabase();
            preparedStatement = conn.prepareStatement(insertAccount);
            preparedStatement.setString(1, fname);
            preparedStatement.setString(2, lname);
            preparedStatement.setString(3, password);
            preparedStatement.setString(4, userID);
            if(preparedStatement.executeUpdate()==1)
                return true;
        }catch(Exception e){
            System.out.println(e);
        }finally{
            closePreparedStatement(preparedStatement);
            closeConnection(conn);            
        }
        return false;
    }

                    //    public boolean createTable(String userName){
                    //        Connection conn = null;
                    //        PreparedStatement preparedStatement = null;
                    //        Statement statement = null;
                    //        try{
                    //            conn = getConnectionToDatabase();
                    //            String creation = "CREATE TABLE IF NOT EXISTS "+userName+ " (user_name VARCHAR(20) NOT NULL PRIMARY KEY, first_name VARCHAR(20) NOT NULL, last_name VARCHAR(20) NOT NULL, work VARCHAR(10) DEFAULT NULL, mobile VARCHAR(10) DEFAULT NULL, home VARCHAR(10) DEFAULT NULL, password VARCHAR(100) NOT NULL, address_id INT(10) DEFAULT NULL, CONSTRAINT fk_users_address FOREIGN KEY(address_id) REFERENCES address (address_id))";
                    //            preparedStatement = conn.prepareStatement(creation);
                    //            int var = preparedStatement.executeUpdate();
                    //            if(var == 0){
                    //                String trigger = "CREATE TRIGGER update_"+userName+" BEFORE UPDATE ON "+userName+" FOR EACH ROW BEGIN IF NEW.first_name = \"\" THEN SET NEW.first_name = OLD.first_name; IF NEW.last_name = \"\" THEN SET NEW.last_name = OLD.last_name; IF NEW.work = \"\" THEN SET NEW.work = OLD.work; END IF; IF NEW.mobile = \"\" THEN SET NEW.mobile = OLD.mobile; END IF; IF NEW.home = \"\" THEN SET NEW.home = OLD.home; END IF; END ;";
                    //                statement = conn.createStatement();
                    //                statement.execute(trigger);
                    //                return true;                
                    //            }
                    //        }catch(Exception e){
                    //            System.out.println(e);
                    //        }finally{
                    //            try {
                    //                if (statement != null) {
                    //                    statement.close();
                    //                }
                    //            }catch (SQLException sqle) {
                    //                    sqle.printStackTrace();
                    //                }
                    //            closePreparedStatement(preparedStatement);
                    //            closeConnection(conn);            
                    //        }
                    //        return false;        
                    //    }
    
//    public boolean deleteFromTable(String userName){
//        Connection conn = null;
//        PreparedStatement preparedStatement = null;
//        try{
//            conn = getConnectionToDatabase();
//            String deleteAccount = "DELETE FROM "+userName+" WHERE user_name = ?";
//            preparedStatement = conn.prepareStatement(deleteAccount);
//            preparedStatement.setString(1, userName);
//            if(preparedStatement.executeUpdate() == 1 ){  
//                return true;
//            }
//        }catch(Exception e){
//            System.out.println(e);
//        }finally{
//            closePreparedStatement(preparedStatement);
//            closeConnection(conn);            
//        }
//        return false;        
//    }    
    
    public Connection getConnectionToDatabase() throws SQLException, Exception{
        Connection conn = null;
        Class.forName(JDBC_DRIVER);
        conn = DriverManager.getConnection(DB_URL,USER,PASS);
        return conn;
    }
    
    public void closeConnection(Connection conn) {
          try {
             if (conn != null) {
                 conn.close();
             }
         } catch (SQLException sqle) {
             sqle.printStackTrace();
         }
     }
    
    public void closePreparedStatement(PreparedStatement preStmt) {
        try {
            if (preStmt != null) {
                preStmt.close();
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }
    
    public UserDB(ServletContext sc){
        JDBC_DRIVER = sc.getInitParameter("JDBC_DRIVER");
        DB_URL = sc.getInitParameter("DB_URL");
        USER = sc.getInitParameter("USER");
        PASS = sc.getInitParameter("PASS");
    }
}
