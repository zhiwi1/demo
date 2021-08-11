package com.epam.webproject.tag;



import jakarta.servlet.jsp.JspWriter;
import jakarta.servlet.jsp.tagext.TagSupport;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class CustomImageTag extends TagSupport {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public int doStartTag() {
        try {
            JspWriter out = pageContext.getOut();
            out.write("<img class=\"img-responsive\" src=\"assets/images/logo.png\" style=\"margin-top: 9px;height: 12px;width: 50px; float: left;\">");
        } catch (IOException e) {
            logger.error("Error while writing to out stream for tag", e);
        }
        return SKIP_BODY;
    }
}