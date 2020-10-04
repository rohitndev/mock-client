package net.upstox.analytics.ohlcclient;

import java.util.Scanner;

import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import javax.annotation.PostConstruct;

/**
 * Stand alone WebSocketStompClient.
 *
 */
@Component
public class StompClient {

    private static String URL = "ws://localhost:61000/events";

    @PostConstruct
    public void run(){
        WebSocketClient client = new StandardWebSocketClient();
        WebSocketStompClient stompClient = new WebSocketStompClient(client);

        stompClient.setMessageConverter(new MappingJackson2MessageConverter());

        String stock = System.getProperty("stock.subscribe");

        System.out.println("Stock to be subscribed :" + stock);
        StompSessionHandler sessionHandler = new OHLCStompSessionHandler(stock);
        stompClient.connect(URL, sessionHandler);

        new Scanner(System.in).nextLine(); // Don't close immediately.
    }


}
