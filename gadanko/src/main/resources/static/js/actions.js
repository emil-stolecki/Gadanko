function logout(){
    //terminate session
    location.href="/";
}
function return_page(page){
    location.href="/"+page
}
function connect(){
    //create socket
    //connect stompclient

}
function send_message(){
    let text = document.getElementById("message_input")
    //send
    text.value = ""
    //event.preventDefault()
}
function receive_message(){
    //create elem and append
}