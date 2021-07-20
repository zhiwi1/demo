package com.epam.webproject.controller.command;

import com.epam.webproject.controller.command.impl.*;

import java.util.EnumMap;

public class CommandProvider {
    private static CommandProvider instance;
    private final EnumMap<CommandType, Command> commands = new EnumMap(CommandType.class);

    public CommandProvider() {
        commands.put(CommandType.SIGN_UP_COMMAND, new RegisterUserCommand());
        commands.put(CommandType.SIGN_IN_COMMAND, new SignInCommand());
        commands.put(CommandType.GO_TO_LOGIN_PAGE_COMMAND,new GoToLoginPageCommand());
        commands.put(CommandType.GO_TO_HOME_PAGE_COMMAND,new GoToHomePageCommand());
        commands.put(CommandType.GO_TO_REGISTRATION_PAGE_COMMAND,new GoToRegistrationPageCommand());
    }

    public static CommandProvider getInstance() {
        if (instance == null) {
            instance = new CommandProvider();
        }
        return instance;
    }

    public Command getCommand(String commandName) {
        if (commandName == null) {
            return commands.get(CommandType.DEFAULT);
        }
        CommandType commandType;
        try {
            commandType = CommandType.valueOf(commandName.toUpperCase());
        } catch (IllegalArgumentException e) {
            commandType = CommandType.DEFAULT;
        }
        return commands.get(commandType);
    }
}