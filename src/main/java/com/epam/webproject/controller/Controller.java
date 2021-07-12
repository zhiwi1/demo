package com.epam.webproject.controller;
import com.epam.webproject.controller.command.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebServlet(name = "controller", urlPatterns = "/controller")
public class Controller extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(Controller.class);
    private final CommandProvider COMMAND_PROVIDER = CommandProvider.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String commandName = request.getParameter(RequestParameter.COMMAND);
        Command command = COMMAND_PROVIDER.getCommand(commandName);
        Router router = command.execute(request,response);
        switch (router.getRouterType()) {
            case REDIRECT:
                response.sendRedirect(router.getRouterPath());
                break;
            case FORWARD:
                RequestDispatcher dispatcher = request.getRequestDispatcher(router.getRouterPath());
                dispatcher.forward(request, response);
                break;
            default:
                logger.error("incorrect route type " + router.getRouterType());
                response.sendRedirect(PagePath.ERROR_PAGE);
        }
    }
}
