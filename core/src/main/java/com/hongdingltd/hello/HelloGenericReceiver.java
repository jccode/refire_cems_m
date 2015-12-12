package com.hongdingltd.hello;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jcchen on 15-12-12.
 */
public class HelloGenericReceiver {

    public void receiveMessage(HashMap<String, String> object) {
        System.out.println(" >>>>>>> RECEIVE GENERIC MESSAGE <<<<<<< ");
        System.out.println(object);
        System.out.println();
    }
}
