package com.epam.webproject.controller.command.impl;

import com.epam.webproject.controller.command.Command;
import com.epam.webproject.controller.command.Router;
import com.epam.webproject.controller.command.RouterType;
import com.epam.webproject.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class ChangeLocaleCommand implements Command {

        @Override
        public Router execute(HttpServletRequest request) throws CommandException {
            String requiredLocale = request.getParameter(RequestFieldKey.KEY_LOCALE.getValue());
            HttpSession session = request.getSession();
            Localization localization = Localization.valueOf(requiredLocale.toUpperCase());
            session.setAttribute(SessionKey.CURRENT_LOCALE.name(), requiredLocale);
            session.setAttribute(SessionKey.LOCALE.name(), localization.getResourceBundle());
            String prevPath = request.getParameter("redirect_url");
            return new Router(RouterType.REDIRECT, prevPath);
        }
    }