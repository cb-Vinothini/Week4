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
    var email = document.getElementById('email').value;    
    var queryString = "?email=" + email ;
    ajaxRequest.open("POST", "emailValidate" + queryString, true);
    ajaxRequest.send(null);
}

function loginCheck(){
    var ajaxRequest = requestAjax();
    ajaxRequest.onreadystatechange = function(){

        if(ajaxRequest.readyState == 4){
            var txt = ajaxRequest.responseText;
            if(txt[0]!= "1"){
                alert("email is not register");
            }
        }
    }
    var email = document.getElementById('email').value;    
    var queryString = "?email=" + email ;
    ajaxRequest.open("POST", "emailValidate" + queryString, true);
    ajaxRequest.send(null);
}
