package org.zx.client;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zx.info.ServerInfo;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * @author xiang.zhang
 */
public class TelnetClient {
    private static final Logger logger = LoggerFactory.getLogger(TelnetClient.class);

    public static void main(String[] args) {
        // bind socket to localhost:8189
        Socket pipeline = new Socket();
        String name = args[0];
        if(name == null || "".equalsIgnoreCase(name)){
            logger.error("please put name arg.");
            return;
        }
        InetSocketAddress address = new InetSocketAddress(ServerInfo.ADDR,ServerInfo.PORT);
        try{
            pipeline.connect(address,ServerInfo.TIMEOUT);
            InputStream inStream = pipeline.getInputStream();
            OutputStream outStream = pipeline.getOutputStream();

            // scanner and printWriter
            Scanner in = new Scanner(inStream, StandardCharsets.UTF_8);
            PrintWriter out = new PrintWriter(
                    new OutputStreamWriter(outStream, StandardCharsets.UTF_8),
                    true
            );
            Scanner consoleInput = new Scanner(System.in,StandardCharsets.UTF_8);

            // send userinfo
            out.println(name);
            boolean end = false;
            while(!end){
                if(in.hasNextLine()){
                    logger.info(in.nextLine());
                }
                if(consoleInput.hasNextLine()){
                    String message = consoleInput.nextLine();
                    out.println(message);
                    if("exit".equals(message.trim())) {
                        end = true;
                    }
                }
            }
            pipeline.close();
        } catch (IOException e) {
            logger.error("连接到 {}:{} 失败",ServerInfo.ADDR,ServerInfo.PORT);
        }
    }
}
