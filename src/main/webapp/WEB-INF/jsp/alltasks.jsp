<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:if test="${not empty sessionScope.locale}">
    <fmt:setLocale value="${sessionScope.locale}"/>
</c:if>
<fmt:setBundle basename="locale" var="rb" />
<html>
<head>
    <title>All tasks</title>
</head>
<body>
<c:import url="/WEB-INF/jsp/templates/header.jsp" charEncoding="utf-8"/>
<body>
<form name="add-post-form" method="POST" action="controller?command=tasks_full_text_search_command">
    FIND
 <input type="text" name="text">

    <button type="submit">Find</button>
</form>
<a href="controller?command=go_to_home_page_command">
    Вернуться на домашнюю страницу
</a>
<c:forEach var="title" items="${fts_titles}">
    <p>${title}</p>
    <a href="controller?command=open_task_page_command&title=${title}" >
        <p>Подробнее</p></a>
</c:forEach>

<c:forEach var="task" items="${tasks}">
    <p>${task.title}</p>
    <a href="controller?command=open_task_page_command&title=${task.title}" >
    <p>Подробнее</p></a>
</c:forEach>
</body>
</html>
