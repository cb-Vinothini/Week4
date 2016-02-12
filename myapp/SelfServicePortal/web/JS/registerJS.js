/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function validateForm(){
    var displayString ="";
    var email = document.forms["form"]["email"].value;
    var cemail = document.forms["form"]["confirm_email"].value;
    if(email.localeCompare(cemail) != 0 ){
        displayString +="Email mismatch ";
    }
    var password = document.forms["form"]["password"].value;
    var cpassword = document.forms["form"]["confirm_password"].value;
    if(password.localeCompare(cpassword) != 0){
        displayString +="Password mismatch ";
    }

    var atpos = email.indexOf("@");
    var dotpos = email.lastIndexOf(".");
    if (atpos<1 || dotpos<atpos+2 || dotpos+2>=email.length) {
        displayString +="Not a valid e-mail address";
    }
    
    if(displayString != ""){
        alert(displayString);
        return false;
    }
    else{
        return true;
    }
}


