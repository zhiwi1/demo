package com.epam.webproject.tag;


import jakarta.servlet.jsp.tagext.TagSupport;
import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.JspWriter;

import java.io.IOException;

public class CustomCommentPaginationTag extends TagSupport {

    private int commentPage;
    private int maxPage;

    public void setCommentPage(int commentPage) {
        this.commentPage = commentPage;
    }

    public void setMaxPage(int maxPage) {
        this.maxPage = maxPage;
    }

    @Override
    public int doStartTag() throws JspException {
        try {
            if (commentPage > 0 && maxPage > 0) {
                JspWriter writer = pageContext.getOut();

                StringBuilder stringBuilder = new StringBuilder("<div class='pagination' style='text-align: center'>");
                for (int i = 1; i <= maxPage; i++) {
                    if (i == commentPage) {
                        stringBuilder.append("<b class='pagination_item_comment pagination__item_current_comment'>")
                                .append(i)
                                .append("</b>");
                    } else {
                        if (i == 1 || i == maxPage) {
                            stringBuilder.append("<a class='pagination_item_comment'>")
                                    .append(i)
                                    .append("</a>");
                        } else {
                            if ((commentPage - 3) < i && (commentPage + 3) > i) {
                                stringBuilder.append("<a class='pagination_item_comment'>")
                                        .append(i)
                                        .append("</a>");
                            } else {
                                stringBuilder.append(".");
                            }
                        }
                    }
                }
                stringBuilder.append("</div>");

                writer.write(stringBuilder.toString());
            }
            return SKIP_BODY;
        } catch (IOException e) {
            throw new JspException("PaginationTag error: " + e.getMessage(), e);
        }
    }

}
