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
import javax.servlet.ServletContext;

/**
 *
 * @author cb-vinothini
 */
public class UserDB {
    private final String JDBC_DRIVER;
    private final String DB_URL;
    private final String USER;
    private final String PASS;

    String userExists = "SELECT COUNT(1) as count FROM users WHERE email = ?";
    String TrueLogin = "SELECT 'true' FROM users WHERE email = ? AND password = ?";
    String insertAccount = "INSERT INTO users (first_name, last_name, email, password) VALUES (?, ?, ?, ?)";
    String selectAddressID = "SELECT address_id FROM users WHERE email = ?";
    String insertAddress = "INSERT INTO address(line1, line2, city, state_id, country_id, zip) VALUES(?, ?, ?, ?, ?, ?)";
    String updateAddressIDinUser = "UPDATE users SET first_name = ?, last_name = ?, address_id = last_insert_id() WHERE email = ?";
    String updateAddress = "UPDATE address SET line1 = ?, line2 = ?, city = ?, state_id = ?, country_id = ?, zip = ? WHERE address_id = ?";
    String updateUser = "UPDATE users SET first_name = ?, last_name = ? WHERE email = ?";
    String userDetailsAddressNULL = "SELECT first_name, last_name, email, password FROM users WHERE email = ?";
    String userDetailsAddressNotNULL = "SELECT u.first_name, u.last_name, u.email, u.password, a.line1, a.line2, a.city, s.state, c.country, a.zip FROM users u INNER JOIN address a ON a.address_id = u.address_id INNER JOIN states s ON s.state_id = a.state_id INNER JOIN countries c ON c.country_id = a.country_id WHERE email = ?";
    
    String deleteAccount = "DELETE FROM users WHERE email = ?";
    
    public UserDB(ServletContext sc){
        JDBC_DRIVER = sc.getInitParameter("JDBC_DRIVER");
        DB_URL = sc.getInitParameter("DB_URL");
        USER = sc.getInitParameter("USER");
        PASS = sc.getInitParameter("PASS");
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

    public void editDetails(String email, String fname, String lname, String line1, String line2, String city, String state_id, String country_id, String zip){
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try{
            conn = getConnectionToDatabase();
            preparedStatement = conn.prepareStatement(selectAddressID);
            preparedStatement.setString(1, email);
            rs = preparedStatement.executeQuery();
            if(rs.next()){
                int addressID = rs.getInt("address_id");
                if(addressID == 0){
                    preparedStatement = conn.prepareStatement(insertAddress);
                    preparedStatement.setString(1, line1);
                    preparedStatement.setString(2, line2);
                    preparedStatement.setString(3, city);
                    preparedStatement.setInt(4, Integer.parseInt(state_id));
                    preparedStatement.setInt(5, Integer.parseInt(country_id));
                    preparedStatement.setString(6, zip);
                    preparedStatement.executeUpdate();
                    preparedStatement = conn.prepareStatement(updateAddressIDinUser);
                }
                else{
                    preparedStatement = conn.prepareStatement(updateAddress);
                    preparedStatement.setString(1, line1);
                    preparedStatement.setString(2, line2);
                    preparedStatement.setString(3, city);
                    preparedStatement.setInt(4, Integer.parseInt(state_id));
                    preparedStatement.setInt(5, Integer.parseInt(country_id));
                    preparedStatement.setString(6, zip);
                    preparedStatement.setInt(7, addressID);
                    preparedStatement.executeUpdate();
                    preparedStatement = conn.prepareStatement(updateUser);
                }
                preparedStatement.setString(1, fname);
                preparedStatement.setString(2, lname);
                preparedStatement.setString(3, email);
                preparedStatement.executeUpdate();                    
            }            
        }catch(Exception e){
            System.out.println(e);
        }finally{
            closePreparedStatement(preparedStatement);
            closeConnection(conn);            
        }
    }

    public User displayEmailRow(String email){
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        User user = null;
        try{
            conn = getConnectionToDatabase();
            preparedStatement = conn.prepareStatement(selectAddressID);
            preparedStatement.setString(1, email);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                if(rs.getInt("address_id") != 0){
                    preparedStatement = conn.prepareStatement(userDetailsAddressNotNULL);
                    preparedStatement.setString(1, email);
                    rs = preparedStatement.executeQuery();
                    if(rs.next()){
                        String fname = rs.getString("first_name");
                        String lname = rs.getString("last_name");
                        String password = rs.getString("password");
                        String line1 = rs.getString("line1");
                        String line2 = rs.getString("line2");
                        String city = rs.getString("city");
                        String state = rs.getString("state");
                        String country = rs.getString("country");
                        String zip = rs.getString("zip");   
                        user = new User(fname, lname, email, password, line1, line2, city, state, country,zip);
                    }                    
                }else{
                    preparedStatement = conn.prepareStatement(userDetailsAddressNULL);
                    preparedStatement.setString(1, email);
                    rs = preparedStatement.executeQuery();
                    if(rs.next()){
                        String fname = rs.getString("first_name");
                        String lname = rs.getString("last_name");
                        String password = rs.getString("password");
                        user = new User(fname, lname, email, password);
                    }                    
                }
            }
        }catch(Exception e){
            System.out.println(e);
        }finally{
            closePreparedStatement(preparedStatement);
            closeConnection(conn);            
        }
        return user;
    }
    
    public boolean authenticate(String email, String password) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try{
            conn = getConnectionToDatabase();
            preparedStatement = conn.prepareStatement(TrueLogin);
            preparedStatement.setString(1, email);
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
            String fname = user.firstName;
            String lname = user.lastName;
            String email = user.email;
            String password = user.getPassword();
            if(fname != null && lname != null && email != null && password != null){
                conn = getConnectionToDatabase();
                preparedStatement = conn.prepareStatement(insertAccount);
                preparedStatement.setString(1, fname);
                preparedStatement.setString(2, lname);
                preparedStatement.setString(3, email);
                preparedStatement.setString(4, password);
                preparedStatement.executeUpdate();
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

    public boolean deleteFromTable(String email){
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try{
            conn = getConnectionToDatabase();
            preparedStatement = conn.prepareStatement(deleteAccount);
            preparedStatement.setString(1, email);
            if(preparedStatement.executeUpdate() == 1 ){  
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

}
