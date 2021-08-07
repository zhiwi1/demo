<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="customtag" prefix="mytag" %>
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

<form class="form" name="add-post-form" method="POST" action="controller?command=tasks_full_text_search_command">
    <p class="cntr"><fmt:message key="find" bundle="${ rb }"/></p>
    <input type="text" name="text">

    <button class="bcenter button" type="submit"><fmt:message key="go" bundle="${ rb }"/></button>
</form>

<c:forEach var="title" items="${fts_titles}">
    <p class="cntr">${title}</p>
    <a class="cntr" href="controller?command=open_task_page_command&title=${title}">
        <p class="cntr"><fmt:message key="alltasks.more" bundle="${ rb }"/></p></a>
</c:forEach>

<c:forEach var="task" items="${tasks}">
    <p class="cntr">${task}</p>
    <a class="cntr" href="controller?command=open_task_page_command&title=${task.title}">
        <p><fmt:message key="alltasks.more" bundle="${ rb }"/></p></a>
</c:forEach>

<mytag:pagination page="${requestScope.current_page}" maxPage="${requestScope.max_page}" />
<a class="cntr" href="controller?command=go_to_home_page_command">
    <fmt:message key="alltasks.return" bundle="${ rb }"/>
</a>



</body>
<script src="js/main.js"></script>
</html>
