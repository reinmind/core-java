package org.zx.servers.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.zx.entity.Status;
import org.zx.entity.User;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Service
public class MessageService {
    private static MessageService messageService;
    private static Logger logger = LoggerFactory.getLogger(MessageService.class);

    private Map<String, User> users;

    public MessageService() {
        this.users = new HashMap<>();
    }

    public void addUser(String name,Socket socket){
        User user = new User(name,socket);
        users.put(name,user);
    }

    /**
     * check status before sending
     * @param name
     * @param message
     * @return
     */
    public boolean sendMessage(String name,String message){
        boolean result = false;
        User receiver = users.get(name);
        Socket socket = receiver.getSocket();
        try {
            writeLine(socket.getOutputStream(),message);
        } catch (IOException e) {
            logger.error("send to {} failed!",name);
        }
        return result;
    }

    public String getStatus(String name){
        return !users.containsKey(name) ? Status.OFFLINE.name() : users.get(name).getStatus().name();
    }

    private void writeLine(OutputStream outputStream,String line){
        PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(outputStream,StandardCharsets.UTF_8),true);
        printWriter.println(line);
    }

    public static MessageService getService(){
        if(messageService == null){
            synchronized (MessageService.class){
                if(messageService == null){
                    messageService = new MessageService();
                    return messageService;
                }
            }
        }
        return messageService;
    }

    public void setStatus(String name,Status status){
        users.get(name).setStatus(status);
    }
}
