function initializeData() {
	document.getElementById("initialize").disabled = true;
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.open( "POST", "/initialize", false );
    xmlHttp.send( null );
    location.reload();
}
function getUserDetails(customerID){
    location.replace("/getUser?customerID=" + customerID);
}
function validateInitialCredit(){
    var initialCredit = document.getElementById("initialCredit");
    if(initialCredit.value > 100000){
        initialCredit.value = 100000;
    }else if(initialCredit.value < -100000){
        initialCredit.value = -100000;
    }
}
function openAccount(customerID){
    var initialCredit = document.getElementById("initialCredit");
    if(initialCredit.value == "" || initialCredit.value == undefined ||initialCredit.value == null){
        initialCredit.value = 0;
    }
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.open( "POST", "/openAccount?customerID=" + customerID + "&initialCredit=" + initialCredit.value, false );
    xmlHttp.send( null );
    initialCredit.value = "";
    location.reload();
}