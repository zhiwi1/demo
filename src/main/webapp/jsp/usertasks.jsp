<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="mytag" uri="customtag" %>
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
<c:import url="/jsp/templates/timeweather.jsp" charEncoding="utf-8"/>


<c:forEach var="task" items="${tasks}">
<h3 class="cntr">${task.title}</h3>
<div class="cntr">${task.timeCreatedAt}</div>
<a class="cntr" href="controller?command=open_task_page_command&title=${task.title}">
    <div><fmt:message key="alltasks.more" bundle="${ rb }"/></div>
</a>
<a class="cntr" href="controller?command=open_task_page_command&title=${task.title}">
    <a class="cntr" href="controller?command=find_answers_of_task_command&title=${task.title}">
        <p class="cntr"> Посмотреть ответы на это задание от пользователей</p></a>
    <a class="cntr" href="controller?command=delete_task_by_user_command&title=${task.title}">
        Удалить задание
    </a>
    <br>
    </c:forEach>
    <hr>
    <mytag:pagination page="${requestScope.current_page}" maxPage="${requestScope.max_page}"/>
    <a class="cntr" href="controller?command=go_to_home_page_command">
        <fmt:message key="alltasks.return" bundle="${ rb }"/>
    </a>
</body>
<script src="js/main.js"></script>
</html>
