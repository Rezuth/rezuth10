$(document).ready(function(){ //wait until all the document loads
$("#uname").change(function(){ //when element with uname id changes
$("#uname_error").html("Loading"); //change innerHTML of the span to loading
$("#uname_error").show();
var tickets=$("#tickets").val();
$.ajax({
type:"post", //http method
url: "Final", //servlet url
data:"tickets="+tickets, //parameter for servlet
success:function(data){
if(data==0){
$("#uname_error").html("No more tickets available");
}
else{
$("#uname_error").html(data + " tickets left");
}
}
})})});