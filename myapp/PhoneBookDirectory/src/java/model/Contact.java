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
public class Contact {
    public int contact_id;
    public String first_name, last_name, line1, line2, city, state, country, zip, mobile, home, work;
    
    Contact(int contact_id, String first_name, String last_name, String line1, String line2, String city, String state, String country, String zip, String mobile, String home, String work){
        this.contact_id = contact_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.line1 = line1;
        this.line2 = line2;
        this.city = city;
        this.state = state;
        this.country = country;
        this.zip = zip;
        this.mobile = mobile;
        this.home = home;
        this.work = work;
    }
    
    @Override
    public String toString(){
        
//        String div = "<div id = "+contact_id+"></div>";
//        String fname = 
        String str_c_id = "<td><input type=\"text\" id = \"c_id\" value=\'"+contact_id+"\' readonly/></td>";
        String str_first_name = "<td><input type=\"text\" id = \"first_name\" value=\'"+first_name+"\'/></td>";
        String str_last_name = "<td><input type=\"text\" id = \"last_name\" value=\'"+last_name+"\'/></td>";
        String str_line1 = "<td><input type=\"text\" id = \"line1\" value=\'"+line1+"\'/></td>";
        String str_line2 = "<td><input type=\"text\" id = \"line2\" value=\'"+line2+"\'/></td>";
        String str_city = "<td><input type=\"text\" id = \"city\" value=\'"+city+"\'/></td>";
        String str_state = "<td><input type=\"text\" id = \"state\" value=\'"+state+"\'/></td>";
        String str_country = "<td><input type=\"text\" id = \"country\" value=\'"+country+"\'/></td>";
        String str_zip = "<td><input type=\"text\" id = \"zip\" value=\'"+zip+"\'/></td>";
        String str_mobile = "<td><input type=\"text\" id = \"mobile\" value=\'"+mobile+"\'/></td>";
        String str_home = "<td><input type=\"text\" id = \"home\" value=\'"+home+"\'/></td>";
        String str_work = "<td><input type=\"text\" id = \"work\" value=\'"+work+"\'/></td>";
        String str_button = "<td><button id=\"editID\">Save changes</button></td>";
        String str = "<tr>"+str_c_id+str_first_name+str_last_name+str_line1+str_line2+str_city+str_state+str_country+str_zip+str_mobile+str_home+str_work+str_button+"</tr>";
        return(str);
//        return("<tr><td>"+contact_id+"</td>"+str_first_name+"<td>"+last_name+"</td><td>"+line1+"</td><td>"+line2+"</td><td>"+city+"</td><td>"+state+"</td><td>"+country+"</td><td>"+zip+"</td>"+str_mobile+"<td>"+home+"</td><td>"+work+"</td>"+str_button+"</tr>");
    }
}
