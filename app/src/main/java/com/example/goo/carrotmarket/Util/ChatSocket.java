package com.example.goo.carrotmarket.Util;


import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;

/**
 * Created by Goo on 2019-07-01.
 */

public class ChatSocket {
    private static Socket socket;

    private ChatSocket() {
    }

    public static Socket getSocket() {
        if (socket == null) {
            try {
                socket = IO.socket("http://54.180.32.57:3000/chat");
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }

        return socket;
    }
}
