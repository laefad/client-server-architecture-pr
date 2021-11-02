"use strict";

(() => {

    let stompClient = null;

    const showMessage = message => 
        $("#messages").append(`<hr><p class="msg"><span>${new Date().toLocaleTimeString()}</span>:\t${message}</p>`);

    const setConnected = connected => {
        $("#connect").prop("disabled", connected);
        $("#disconnect").prop("disabled", !connected);
        if (connected)
            $("#conversation").show();
        else
            $("#conversation").hide();
        $("#messages").append(`<hr><p class="msg"><span>${new Date().toLocaleTimeString()}</span>: 
            Вы ${connected ? "подключились к сокету" : "отключились от сокета"}.
        `);
    }

    const connect = () => {
        let socket = new SockJS("/websocket");
        stompClient = Stomp.over(socket);
        stompClient.connect({}, frame => {
            setConnected(true);
            console.log("Connected: " + frame);
            stompClient.subscribe("/topic/messages", 
                message => showMessage(JSON.parse(message.body).content)
            );
        });
    }

    const disconnect = () => {
        if (stompClient !== null) {
            stompClient.disconnect();
        }
        setConnected(false);
        console.log("Disconnected");
    }

    const sendValue = () => {
        stompClient.send(
            "/app/hello", 
            {}, 
            JSON.stringify({"value": $("#value").val()})
        );
    }

    $(() => {
        $("form").on("submit", e => e.preventDefault());
        $("#connect").click(connect);
        $("#disconnect").click(disconnect);
        $("#send").click(sendValue);
    });

})();
