<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:if test="${not empty sessionScope.locale}">
    <fmt:setLocale value="${sessionScope.locale}"/>
</c:if>
<fmt:setBundle basename="locale" var="rb" />
<html>
<head>
    <title><fmt:message key="alltasks.alltasks" bundle="${ rb }" /></title>
</head>
<body>
<c:import url="/jsp/templates/header.jsp" charEncoding="utf-8"/>
<body>
<form name="add-post-form" method="POST" action="controller?command=tasks_full_text_search_command">
    <fmt:message key="find" bundle="${ rb }" />
 <input type="text" name="text">

    <button type="submit"><fmt:message key="go" bundle="${ rb }" /></button>
</form>
<a href="controller?command=go_to_home_page_command">
    <fmt:message key="alltasks.return" bundle="${ rb }" />
</a>
<c:forEach var="title" items="${fts_titles}">
    <p>${title}</p>
    <a href="controller?command=open_task_page_command&title=${title}" >
        <p><fmt:message key="alltasks.more" bundle="${ rb }" /></p></a>
</c:forEach>

<c:forEach var="task" items="${tasks}">
    <p>${task.title}</p>
    <a href="controller?command=open_task_page_command&title=${task.title}" >
    <p><fmt:message key="alltasks.more" bundle="${ rb }" /></p></a>
</c:forEach>
</body>
</html>
