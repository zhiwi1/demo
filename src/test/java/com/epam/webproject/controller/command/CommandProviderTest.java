package com.epam.webproject.controller.command;

import com.epam.webproject.controller.command.impl.AddTaskCommand;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CommandProviderTest {
    private static final CommandProvider commandProvider = CommandProvider.getInstance();

    @DataProvider(name = "invalidCommandNames")
    public static Object[][] invalidCommandNames() {
        return new Object[][]{ {"dfasdfasdfasdf"}, {null}};
    }


    @Test(dataProvider = "invalidCommandNames")
    public void getCommandTest(String comment) {
        {
            Command command = commandProvider.getCommand(comment);
            Assert.assertEquals(command, commandProvider.getCommand(CommandType.DEFAULT_COMMAND.name()));
        }
    }


    @Test
    public void openHomePageTest() {
        Command command = commandProvider.getCommand(CommandType.ADD_TASK_COMMAND.name());
        Assert.assertEquals(command.getClass(), AddTaskCommand.class);
    }
}