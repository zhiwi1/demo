<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:if test="${not empty sessionScope.locale}">
    <fmt:setLocale value="${sessionScope.locale}"/>
</c:if>
<fmt:setBundle basename="locale" var="rb"/>
<html>
<head>
    <title><fmt:message key="alltasks.alltasks" bundle="${ rb }"/></title>
</head>
<body>
<c:import url="/jsp/templates/header.jsp" charEncoding="utf-8"/>
<body>

<a href="controller?command=go_to_home_page_command">
    <fmt:message key="alltasks.return" bundle="${ rb }"/>
</a>

<c:forEach var="task" items="${tasks}">
<p>${task.title}</p>
    <a href="controller?command=open_task_page_command&title=${task.title}">
    <a href="controller?command=find_answers_of_task_command&title=${task.title}">
        <p> Посмотреть ответы на это задание от пользователей</p></a>
    <a href="controller?command=delete_task_by_user_command&title=${task.title}">
        Удалить задание
    </a>

    </c:forEach>
</body>
</html>