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

function dispByPatrilaName(user_id){
    var ajaxRequest = requestAjax();
    
    ajaxRequest.onreadystatechange = function(){

        if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
            var parsedJson = JSON.parse(ajaxRequest.responseText);
            if(parsedJson){
                var len = parsedJson.length;
                var txt = "";
                if(len > 0){
                    for(var i=0;i<len;i++){
//                        if(data[i].bookId && data[i].bookName){
                        var str_c_id = "<td><input type=\"text\" id = \"c_id\" value=\'"+parsedJson[i].contact_id+"\' readonly/></td>";
                        var str_first_name = "<td><input type=\"text\" id = \"first_name\" value=\'"+parsedJson[i].first_name+"\'/></td>";
                        var str_last_name = "<td><input type=\"text\" id = \"last_name\" value=\'"+parsedJson[i].last_name+"\'/></td>";
                        var str_line1 = "<td><input type=\"text\" id = \"line1\" value=\'"+parsedJson[i].line1+"\'/></td>";
                        var str_line2 = "<td><input type=\"text\" id = \"line2\" value=\'"+parsedJson[i].line2+"\'/></td>";
                        var str_city = "<td><input type=\"text\" id = \"city\" value=\'"+parsedJson[i].city+"\'/></td>";
                        var str_state = "<td><input type=\"text\" id = \"state\" value=\'"+parsedJson[i].state+"\'/></td>";
                        var str_country = "<td><input type=\"text\" id = \"country\" value=\'"+parsedJson[i].country+"\'/></td>";
                        var str_zip = "<td><input type=\"text\" id = \"zip\" value=\'"+parsedJson[i].zip+"\'/></td>";
                        var str_mobile = "<td><input type=\"text\" id = \"mobile\" value=\'"+parsedJson[i].mobile+"\'/></td>";
                        var str_home = "<td><input type=\"text\" id = \"home\" value=\'"+parsedJson[i].home+"\'/></td>";
                        var str_work = "<td><input type=\"text\" id = \"work\" value=\'"+parsedJson[i].work+"\'/></td>";
                        var str_button = "<td><button id=\"editID\">Save changes</button></td>";

                        txt = txt + "<tr>"+str_c_id+str_first_name+str_last_name+str_line1+str_line2+str_city+str_state+str_country+str_zip+str_mobile+str_home+str_work+str_button+"</tr>";
//                    }
                    }
                    if(txt != ""){
                        document.getElementById("disp").innerHTML = txt;
                    }
                }else{
                    document.getElementById("disp").innerHTML = txt;
                }
            }
        }
    }

    var name = document.getElementById('partialUserName').value;    
    var queryString = "?pattern=" + name+"&user_id="+user_id;
    ajaxRequest.open("POST", "partialName" + queryString, true);
    ajaxRequest.send();
}

function dispByNumber(user_id){
    var ajaxRequest = requestAjax();
    
    ajaxRequest.onreadystatechange = function(){

        if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
            var parsedJson = JSON.parse(ajaxRequest.responseText);
            if(parsedJson){
                var len = parsedJson.length;
                var txt = "";
                if(len > 0){
                    for(var i=0;i<len;i++){
//                        if(data[i].bookId && data[i].bookName){
                        var str_c_id = "<td><input type=\"text\" id = \"c_id\" value=\'"+parsedJson[i].contact_id+"\' readonly/></td>";
                        var str_first_name = "<td><input type=\"text\" id = \"first_name\" value=\'"+parsedJson[i].first_name+"\'/></td>";
                        var str_last_name = "<td><input type=\"text\" id = \"last_name\" value=\'"+parsedJson[i].last_name+"\'/></td>";
                        var str_line1 = "<td><input type=\"text\" id = \"line1\" value=\'"+parsedJson[i].line1+"\'/></td>";
                        var str_line2 = "<td><input type=\"text\" id = \"line2\" value=\'"+parsedJson[i].line2+"\'/></td>";
                        var str_city = "<td><input type=\"text\" id = \"city\" value=\'"+parsedJson[i].city+"\'/></td>";
                        var str_state = "<td><input type=\"text\" id = \"state\" value=\'"+parsedJson[i].state+"\'/></td>";
                        var str_country = "<td><input type=\"text\" id = \"country\" value=\'"+parsedJson[i].country+"\'/></td>";
                        var str_zip = "<td><input type=\"text\" id = \"zip\" value=\'"+parsedJson[i].zip+"\'/></td>";
                        var str_mobile = "<td><input type=\"text\" id = \"mobile\" value=\'"+parsedJson[i].mobile+"\'/></td>";
                        var str_home = "<td><input type=\"text\" id = \"home\" value=\'"+parsedJson[i].home+"\'/></td>";
                        var str_work = "<td><input type=\"text\" id = \"work\" value=\'"+parsedJson[i].work+"\'/></td>";
                        var str_button = "<td><button id=\"editID\">Save changes</button></td>";

                        txt = txt + "<tr>"+str_c_id+str_first_name+str_last_name+str_line1+str_line2+str_city+str_state+str_country+str_zip+str_mobile+str_home+str_work+str_button+"</tr>";
//                    }
                    }
                    if(txt != ""){
                        document.getElementById("disp").innerHTML = txt;
                    }
                }else{
                    document.getElementById("disp").innerHTML = txt;
                }
            }
        }
    }
            
    var number = document.getElementById('number').value;    
    var queryString = "?number=" + number+"&user_id="+user_id;
    ajaxRequest.open("POST", "displayByNum" + queryString, true);
    ajaxRequest.send();
}

function dispCont(user_id){
    var ajaxRequest = requestAjax();
    
    ajaxRequest.onreadystatechange = function(){

        if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
            var parsedJson = JSON.parse(ajaxRequest.responseText);
            if(parsedJson){
                var len = parsedJson.length;
                var txt = "";
                if(len > 0){
                    for(var i=0;i<len;i++){
//                        if(data[i].bookId && data[i].bookName){
                        var str_c_id = "<td><input type=\"text\" id = \"c_id\" value=\'"+parsedJson[i].contact_id+"\' readonly/></td>";
                        var str_first_name = "<td><input type=\"text\" id = \"first_name\" value=\'"+parsedJson[i].first_name+"\'/></td>";
                        var str_last_name = "<td><input type=\"text\" id = \"last_name\" value=\'"+parsedJson[i].last_name+"\'/></td>";
                        var str_line1 = "<td><input type=\"text\" id = \"line1\" value=\'"+parsedJson[i].line1+"\'/></td>";
                        var str_line2 = "<td><input type=\"text\" id = \"line2\" value=\'"+parsedJson[i].line2+"\'/></td>";
                        var str_city = "<td><input type=\"text\" id = \"city\" value=\'"+parsedJson[i].city+"\'/></td>";
                        var str_state = "<td><input type=\"text\" id = \"state\" value=\'"+parsedJson[i].state+"\'/></td>";
                        var str_country = "<td><input type=\"text\" id = \"country\" value=\'"+parsedJson[i].country+"\'/></td>";
                        var str_zip = "<td><input type=\"text\" id = \"zip\" value=\'"+parsedJson[i].zip+"\'/></td>";
                        var str_mobile = "<td><input type=\"text\" id = \"mobile\" value=\'"+parsedJson[i].mobile+"\'/></td>";
                        var str_home = "<td><input type=\"text\" id = \"home\" value=\'"+parsedJson[i].home+"\'/></td>";
                        var str_work = "<td><input type=\"text\" id = \"work\" value=\'"+parsedJson[i].work+"\'/></td>";
                        var str_button = "<td><button id=\"editID\">Save changes</button></td>";

                        txt = txt + "<tr>"+str_c_id+str_first_name+str_last_name+str_line1+str_line2+str_city+str_state+str_country+str_zip+str_mobile+str_home+str_work+str_button+"</tr>";
//                    }
                    }
                    if(txt != ""){
                        document.getElementById("disp").innerHTML = txt;
                    }
                }else{
                    document.getElementById("disp").innerHTML = txt;
                }
            }
        }
    }
    var queryString = "?user_id=" + user_id ;
    ajaxRequest.open("POST", "displayContacts" + queryString, true);
    ajaxRequest.send();
}

//
//function myFunction(obj) {
//obj.style.height="20px";
//obj.style.outline="0.5px solid #00c2b6";
//}
//
//
//
//function myFun(event,obj,c_id,col) {
//    var x = event.charCode;
//if(x==13){
//    
//var t = obj.value;
//alert(t);
//document.location.href="/PhoneBookDirectory/editDetails?c_id="+c_id+"&value="+t+"&col="+col;
//obj.style.outline="none";
//}
//}
