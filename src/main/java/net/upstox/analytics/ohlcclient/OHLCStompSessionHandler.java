package net.upstox.analytics.ohlcclient;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;

import java.lang.reflect.Type;

@Slf4j
public class OHLCStompSessionHandler extends StompSessionHandlerAdapter {

    private final ObjectMapper objectMapper;
    private final String stock;

    public OHLCStompSessionHandler(String stock){
        this.objectMapper = new ObjectMapper();
        this.stock = stock;
    }


    @Override
    public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
        log.info("New session established : {}" , session.getSessionId());
        String subscriptionTopic = "/ohlc/".concat(stock);
        session.subscribe(subscriptionTopic, this);
        log.info("Subscribed to {}",subscriptionTopic);
     }

    @Override
    public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload, Throwable exception) {
        log.error("Got an exception", exception);
    }


    @Override
    public Type getPayloadType(StompHeaders headers) {
        return Ohlc.class;
    }

    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
        Ohlc ohlc = (Ohlc) payload;
        log.info("Received : {}", ohlc);
    }


}

