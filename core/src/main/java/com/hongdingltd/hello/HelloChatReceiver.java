package com.hongdingltd.hello;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

/**
 * Created by jcchen on 15-12-12.
 */
public class HelloChatReceiver {

    public void receiveMessage(String message) {
        System.out.println(" >>>>>>> RECEIVE MESSAGE <<<<<<< ");
        System.out.println(message);
    }

}
