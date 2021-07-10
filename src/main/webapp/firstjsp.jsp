<%--
  Created by IntelliJ IDEA.
  User: Иван
  Date: 7/9/2021
  Time: 3:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--без вывода на экран--%>
<%--<% %>--%>

<%--с выводом на экран--%>
<p><%= "JAVA HELLO"%>
</p>
<% java.util.Date data = new java.util.Date();
    for (int i = 0; i < 4; i++) {
        out.println("out var");
    }

    String some = "Текущее время" + data;%>
<p><%= some%>
</p>
<h1>lol</h1>
</body>
</html>
