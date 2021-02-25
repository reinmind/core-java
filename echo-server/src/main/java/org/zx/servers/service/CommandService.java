package org.zx.servers.service;

import org.zx.entity.Command;

public class CommandService {
    private static CommandService commandService;
    private MessageService messageService;
    private String command;
    private String name;
    private String message;

    public String query(String line){
        String result = "please input the right command.";
        String[] args = line.split(" ");
        if(args.length < 1) {
            return message;
        }
        command = args[0];
        if(command.equalsIgnoreCase(Command.EXIT.name())){
            return "exit";
        }
        if(command.equalsIgnoreCase(Command.STATUS.name())){
            name = args[1];
            return messageService.getStatus(name);
        }
        if(command.equalsIgnoreCase(Command.SEND.name())){
            name = args[1];
            message = args[2];
            messageService.sendMessage(name,message);
            return "message sent";
        }
        return result;
    }

    public CommandService() {
        messageService = MessageService.getService();
    }

    public static CommandService getService(){
        if(commandService == null){
            synchronized (CommandService.class){
                if(commandService == null){
                    commandService = new CommandService();
                }
            }
        }
        return commandService;
    }
}
