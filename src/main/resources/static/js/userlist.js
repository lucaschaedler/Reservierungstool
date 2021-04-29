userlistbtn.addEventListener("click", () => {
    userlistclicked();
    userPanel.hidden = false;
    accountRequestPanel.hidden = true;
    accountrequestbtn.disabled = false;
    userlistbtn.disabled = true;
    console.log("Tabelle Userliste wird angezeigt");
  });
accountrequestbtn.addEventListener("click", () => {
    accountRequestlistclicked();
    userPanel.hidden = true;
    accountRequestPanel.hidden = false;
    userlistbtn.disabled = false;
    accountrequestbtn.disabled = true;
    console.log("Tabelle Userliste wird angezeigt");
  });

function userlistclicked(){
    //$.getJSON("data2.json").done(handleUserlistReply);
    $.getJSON("api/users").done(handleUserlistReply);
    }

function accountRequestlistclicked(){
     //$.getJSON("data3.json").done(handleAccountRequestlistReply);
     $.getJSON("/api/accountRequests").done(handleAccountRequestlistReply);
    }

    function applyFilter(){
        var filter = $("#inpUserList").val();
        $.getJSON("api/users",{"filter": filter}).done(handleUsersReply);
        }

    function applyFilter(){
        var filter = $("#inpAcc").val();
        $.getJSON("/api/accountRequests",{"filter": filter}).done(handleAccountRequestlistReply);
        }

   function handleUserlistReply(users){
       $("#tblUserList tbody").empty();

      for ( let user of users ) {
        addUserToList(user);
        }}

        function addUserToList(user){
           let id = user['userId'];
            console.log(id);
       
           var newRow = "<tr>";
           newRow += "<td>" + user['userId'] + "<td>";
           newRow += "<td>" + user['userName'] + "<td>";
           newRow += "<td>" + user['userEmail'] + "<td>";
           newRow += "<td>" + user['userMobile'] + "<td>";
           newRow += "<td>" + user['authorization'] + "<td>";
           newRow += "<td> <button id = 'u"+ user['userId'] + "' onClick='deleteUser("+ user['userId'] + ")'> Delete </button> </td>";
           newRow +="</tr>";

           $("#tblUserList tbody").append(newRow);  
        }

        function handleAccountRequestlistReply(reqs){
            $("#tblAccList tbody").empty();
            
           for ( let req of reqs ) {
             addReqToList(req);
             } }
     
             function addReqToList(req){
                let id = req['accountRequestId'];
                 console.log(id);
            
                var newRow = "<tr>";
                newRow += "<td>" + req['accountRequestId'] + "<td>";
                newRow += "<td>" + req['accountRequestName'] + "<td>";
                newRow += "<td>" + req['accountRequestEmail'] + "<td>";
                newRow += "<td>" + req['accountRequestMobile'] + "<td>";
                newRow += "<td> <button id = 'u"+ req['accountRequestId'] + "' onClick='deleteAccRequest("+ req['accountRequestId'] + ")'> Delete </button> </td>";
                newRow += "<td> <button id = 'u"+ req['accountRequestId'] + "' onClick='createUser("+ req['accountRequestId'] + ")'> Account erstellen </button> </td>";
                newRow +="</tr>";

                $("#tblAccList tbody").append(newRow); 
             }

            function deleteUser(id){
                var urlstring = "api/user/delete/"+id;
                console.log("delete  " + id);
              $.ajax({
                type: "DELETE",
                url: urlstring,
                success: deleteUserRespons,
                dataType: "json",
               contentType: "application/json",

              })
            }

                   

                        function deleteAccRequest(id){
                        console.log("delete request" + id);
                        var urlstring = "/api/deleteAccountRequest/"+id;
                $.ajax({
                type: "DELETE",
                url: urlstring,
                success: deleteReqRespons,
                dataType: "json",
                contentType: "application/json",

              })
                        }

                        function createUser(id){
                        console.log("create user"+id);
                        var urlstring = "api/createUser/"+id;
                        $.ajax({
                            type: "Post",
                            url: urlstring,
                            success: createUserResponse,
                            dataType: "json",
                            contentType: "application/jsin",
                        })}

                

                 function deleteUserRespons(response){
                     if(response == true){
                        userlistclicked()
                     }else{console.log("fehler beim löschen des users")}
                 }
                 function deleteReqRespons(response){
                     if(response ==true){
                         accountRequestlistclicked();
                        }else{console.log("fehler beim löschen des request")}
                 }
                 function createUserResponse(response){
                    if(response ==true){
                        accountRequestlistclicked();
                       }else{console.log("fehler beim erstellen des users")} 
                 }