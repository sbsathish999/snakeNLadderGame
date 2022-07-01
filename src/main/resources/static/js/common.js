$(document).ready(function () {
    $("#playerDetails").click(function() {
      const playerNames = [];
       playerNames[0]= $("#player1Name").val().trim();
       playerNames[1]= $("#player2Name").val().trim();
       playerNames[2]= $("#player3Name").val().trim();
       playerNames[3]= $("#player4Name").val().trim();

        if(playerNames.at(0) == '' ||  playerNames.at(1) == '' || playerNames.at(2) == '' || playerNames.at(3) == '' ) {
            alert("Please Enter all 4 Player Names to proceed further");
            return false;
        }

      $.ajax({
       type : "POST",
       contentType : "application/json",
       url : "/initiate",
       data : JSON.stringify(playerNames),
       dataType : 'json',
       cache : false,
       timeout : 600000,
       success : function(data) {
            window.location.replace("http://localhost:8080/play");
       },
       error : function(e) {
        alert(e);
       }
      });
    });
   });

window.history.forward();
function noBack() {
   window.history.forward();
}