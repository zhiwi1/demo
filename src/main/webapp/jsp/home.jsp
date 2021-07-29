<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:if test="${not empty sessionScope.locale}">
    <fmt:setLocale value="${sessionScope.locale}"/>
</c:if>
<fmt:setBundle basename="locale" var="rb" />
<html>
<head>
    <title>Home</title>
</head>
<body>
<c:import url="/jsp/templates/header.jsp" charEncoding="utf-8"/>
<a href="controller?command=go_to_add_task_page_command"  role="button">
add new task
</a>
<br>
<a href="controller?command=show_all_tasks_command"  role="button">
    show all tasks
</a>
<br>
<c:out value="${role}"/>
<c:if test = "${role == 'ADMIN'}">

<a href="controller?command=show_all_users_command">
    show all users
</a>
</c:if>
<a  href="controller?command=sign_out_command" role="button">
    выйти
</a>

</body>
</html>
