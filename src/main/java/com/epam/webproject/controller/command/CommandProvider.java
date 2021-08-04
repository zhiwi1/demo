package com.epam.webproject.controller.command;

import com.epam.webproject.controller.command.impl.*;

import java.util.EnumMap;

public class CommandProvider {
    private static CommandProvider instance;
    private final EnumMap<CommandType, Command> commands = new EnumMap(CommandType.class);

    public CommandProvider() {
        //todo import static
        commands.put(CommandType.SIGN_UP_COMMAND, new RegisterUserCommand());
        commands.put(CommandType.SIGN_IN_COMMAND, new SignInCommand());
        commands.put(CommandType.GO_TO_LOGIN_PAGE_COMMAND, new GoToLoginPageCommand());
        commands.put(CommandType.GO_TO_HOME_PAGE_COMMAND, new GoToHomePageCommand());
        commands.put(CommandType.GO_TO_REGISTRATION_PAGE_COMMAND, new GoToRegistrationPageCommand());
        commands.put(CommandType.CHANGE_LOCALE_COMMAND, new ChangeLocaleCommand());
        commands.put(CommandType.GO_TO_ADD_TASK_PAGE_COMMAND, new GoToAddTaskPageCommand());
        commands.put(CommandType.ADD_TASK_COMMAND, new AddTaskCommand());
        commands.put(CommandType.SIGN_OUT_COMMAND, new SignOutCommand());
        commands.put(CommandType.SHOW_ALL_TASKS_COMMAND, new ShowAllTasksCommand());
        commands.put(CommandType.FIND_PROFILE_INFO_COMMAND, new FindProfileInfoCommand());
        commands.put(CommandType.SHOW_ALL_USERS_COMMAND, new ShowAllUsersCommand());
        commands.put(CommandType.FIND_EDITING_INFO_COMMAND, new FindEditingInfoCommand());
        commands.put(CommandType.EDIT_PAGE_COMMAND, new EditPageCommand());
        commands.put(CommandType.OPEN_TASK_PAGE_COMMAND, new OpenTaskPageCommand());
        commands.put(CommandType.ADD_COMMENT_COMMAND, new AddCommentCommand());
        commands.put(CommandType.ADD_ANSWER_COMMAND, new AddAnswerCommand());
        commands.put(CommandType.TASKS_FULL_TEXT_SEARCH_COMMAND, new TasksFullTextSearchCommand());
        commands.put(CommandType.USERS_FULL_TEXT_SEARCH_COMMAND, new UsersFullTextSearchCommand());
        commands.put(CommandType.LIKE_ANSWER_COMMAND, new LikeAnswerCommand());
        commands.put(CommandType.BLOCK_USER_COMMAND, new BlockUserCommand());
        commands.put(CommandType.UNBLOCK_USER_COMMAND, new UnblockUserCommand());
        commands.put(CommandType.SEND_PASSWORD_COMMAND, new SendPasswordCommand());
        commands.put(CommandType.GO_TO_SEND_PASSWORD_COMMAND, new GoToSendPasswordCommand());
        commands.put(CommandType.SHOW_MY_TASKS_COMMAND, new ShowMyTasksCommand());
        commands.put(CommandType.DELETE_TASK_BY_USER_COMMAND, new DeleteTaskByUserCommand());
        commands.put(CommandType.FIND_ANSWERS_OF_TASK_COMMAND,new FindAnswersOfTaskCommand());
        commands.put(CommandType.MARK_CORRECT_ANSWER_COMMAND,new MarkCorrectAnswerCommand());
        commands.put(CommandType.MARK_INCORRECT_ANSWER_COMMAND,new MarkIncorrectAnswerCommand());
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