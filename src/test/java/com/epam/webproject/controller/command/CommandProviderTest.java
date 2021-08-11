package com.epam.webproject.controller.command;

import com.epam.webproject.controller.command.impl.AddAnswerCommand;
import com.epam.webproject.controller.command.impl.AddTaskCommand;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CommandProviderTest {
    CommandProvider commandProvider = CommandProvider.getInstance();

    @Test
    public void badInputTest() {
        Command command = commandProvider.getCommand("dfasdfasdfasdf");
        System.out.println(command);
        Assert.assertEquals(command, commandProvider.getCommand(CommandType.DEFAULT_COMMAND.name()));
    }

    @Test
    public void nullInputTest() {
        Command command = commandProvider.getCommand(null);
        Assert.assertEquals(command, commandProvider.getCommand(CommandType.DEFAULT_COMMAND.name()));
    }

    @Test
    public void openHomePageTest() {
        Command command = commandProvider.getCommand(CommandType.ADD_TASK_COMMAND.name());
        Assert.assertEquals(command.getClass(), AddTaskCommand.class);
    }
}