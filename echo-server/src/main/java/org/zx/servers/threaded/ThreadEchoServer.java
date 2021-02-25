package org.zx.servers.threaded;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zx.info.ServerInfo;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ThreadEchoServer {

    private static final Logger logger = LoggerFactory.getLogger(ThreadEchoServer.class);
    public static void main(String[] args) {
        try {
            ServerSocket socket = new ServerSocket(ServerInfo.PORT);
            int i = 1;
            while(true){
                Socket incoming = socket.accept();
                logger.info("Spawning: {}",i);
                Runnable r = new ThreadEchoHandler(incoming);
                Thread t = new Thread(r);
                t.start();
                ++i;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
