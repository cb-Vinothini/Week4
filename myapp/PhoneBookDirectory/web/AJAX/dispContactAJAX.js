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

function dispCont(user_id){
    var ajaxRequest = requestAjax();
    
    ajaxRequest.onreadystatechange = function(){

        if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
            document.getElementById("disp").innerHTML = ajaxRequest.responseText;
        }
    }
//    var user_id = 'vino';
    alert(user_id);
    var queryString = "?user_id=" + user_id ;
    ajaxRequest.open("POST", "displayContact" + queryString, true);
    ajaxRequest.send();
}

