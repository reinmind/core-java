package org.zx.servers.threaded;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zx.entity.Status;
import org.zx.servers.service.CommandService;
import org.zx.servers.service.MessageService;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class ThreadEchoHandler implements Runnable{
    private final CommandService commandService;
    private final MessageService messageService;

    private static final Logger logger = LoggerFactory.getLogger(ThreadEchoHandler.class);
    private final Socket incoming;

    public ThreadEchoHandler(Socket incoming) {
        this.incoming = incoming;
        // 单例
        this.messageService = MessageService.getService();
        this.commandService = CommandService.getService();
    }

    @Override
    public void run() {
        try(
                InputStream inputStream = incoming.getInputStream();
                OutputStream outputStream = incoming.getOutputStream();)
        {
            Scanner scanner = new Scanner(inputStream, StandardCharsets.UTF_8);
            PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(outputStream,StandardCharsets.UTF_8),true);
            printWriter.println("type 'exit' to exit.");

            String name = scanner.nextLine();
            messageService.addUser(name,incoming);
            boolean done = false;
            while(!done && scanner.hasNextLine()){
                String line = scanner.nextLine();
                printWriter.println(commandService.query(line));
                if(line.trim().equals("exit")){
                    done = true;
                }
            }
            messageService.setStatus(name, Status.OFFLINE);
            printWriter.println("连接关闭");
            incoming.close();
        } catch (IOException e) {
            logger.error("获取Socket错误");
        }
    }
}
