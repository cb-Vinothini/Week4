/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function requestAjax(){
    var ajaxRequest;
    try{
       ajaxRequest = new XMLHttpRequest();
    }catch (e){
        try{
            ajaxRequest = new ActiveXObject("Msxml2.XMLHTTP");
        }catch (e) {
            try{
                ajaxRequest = new ActiveXObject("Microsoft.XMLHTTP");
            }catch (e){
                alert("Your browser broke!");
                return false;
            }
        }
    }
    return ajaxRequest;
}

function checkEmail(){
    var ajaxRequest = requestAjax();
    
    ajaxRequest.onreadystatechange = function(){

        if(ajaxRequest.readyState == 4){
            var txt = ajaxRequest.responseText;            
            if(txt)
                alert(txt);
        }
    }
    var userID = document.getElementById('userID').value;    
    var queryString = "?user_id=" + userID ;
    ajaxRequest.open("POST", "emailValidate" + queryString, true);
    ajaxRequest.send(null);
}

function loginCheck(){
    var ajaxRequest = requestAjax();
    ajaxRequest.onreadystatechange = function(){

        if(ajaxRequest.readyState == 4){
            var txt = ajaxRequest.responseText;
            if(txt[0]!= "1"){
                alert("user name is not register, first register");
            }
        }
    }
    var userID = document.getElementById('userID').value;    
    var queryString = "?user_id=" + userID ;
    ajaxRequest.open("POST", "emailValidate" + queryString, true);
    ajaxRequest.send(null);
}
