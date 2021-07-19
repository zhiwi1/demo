package com.epam.webproject.controller;
import com.epam.webproject.controller.command.*;
import com.epam.webproject.exception.ProjectException;
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {

        String commandName = request.getParameter(RequestParameter.COMMAND);
        Command command = COMMAND_PROVIDER.getCommand(commandName);
        Router router = null;
        try {
            router = command.execute(request);
        } catch (ProjectException exception) {
            throw new ServletException("Project Exception: " + exception.getMessage(), exception);
        }
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
