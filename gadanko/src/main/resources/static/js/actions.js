var stompClient = null;
var chatbox = null;
var user_name = null;
var group_name = null;

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
    chatbox = document.getElementById("chat-box");
    user_name = document.getElementById("user").textContent;
    group_name = document.getElementById("group-name").textContent;

}

function onError(error) {
    alert("connection error")

}
function send_message(){

    let text = document.getElementById("message_input")
    if (text.value && stompClient){
        let message = {
            username: user_name,
            text: text.value,
            date: new Date()
        }
        stompClient.send("/app/sent", {}, JSON.stringify(message));
        text.value = ""
        event.preventDefault()
    }

}
function receive_message(payload){

    var messageBlock = document.createElement("div");
    var pfpBlock = document.createElement("div");
    var metadata = document.createElement("div");
    var content = document.createElement("div");
    var message = JSON.parse(payload.body);

    if(message.user = user_name){
        messageBlock.classList.add("message-own");
        pfpBlock.classList.add("pfp-own")
        metadata.classList.add("name-date-own");
        content.classList.add("message-text-own");
    }
    else{
        messageBlock.classList.add("message");
        pfpBlock.classList.add("pfp")
        metadata.classList.add("name-date");
        content.classList.add("message-text");
    }
    metadata.textContent=message.username + " | " + message.date
    content.textContent=message.text

    messageBlock.appendChild(pfpBlock)
    messageBlock.appendChild(metadata)
    messageBlock.appendChild(content)
    chatbox.appendChild(messageBlock)
    chatbox.scrollTop = chatbox.scrollHeight;
}