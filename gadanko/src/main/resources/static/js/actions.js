var stompClient = null;

function logout(){
    //terminate session
    location.href="/";
}
function return_page(page){
    location.href="/"+page
}
function connect(){
    var socket = new SockJS("/sent");
    stompClient = Stomp.over(socket);
    stompClient.connect({}, onConnected, onError);

}
function onConnected() {
  stompClient.subscribe("/chat/messages", receive_message);

}

function onError(error) {
    alert("connection error")

}
function send_message(){

    let text = document.getElementById("message_input")
    if (text.value && stompClient){
        let message = {
            username: "user",
            text: text.value
        }
        stompClient.send("/app/sent", {}, JSON.stringify(message));
        text.value = ""
        event.preventDefault()
    }

}
function receive_message(payload){
   alert("MASZ WIADOMOŚĆ")
}