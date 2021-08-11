<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:if test="${not empty sessionScope.locale}">
    <fmt:setLocale value="${sessionScope.locale}"/>
</c:if>
<fmt:setBundle basename="locale" var="rb" />
<html>
<head>
    <title>   <fmt:message key="home.home" bundle="${rb}"/></title>
</head>
<body>
<c:import url="/jsp/templates/header.jsp" charEncoding="utf-8"/>
<c:import url="/jsp/templates/timeweather.jsp" charEncoding="utf-8"/>
<a class="cntr" href="controller?command=go_to_add_task_page_command"  role="button">
    <fmt:message key="home.add" bundle="${rb}"/>
</a>
<br>
<a class="cntr" href="controller?command=show_all_tasks_command"  role="button">
    <fmt:message key="home.show" bundle="${rb}"/>
</a>
<br>
<a class="cntr" href="controller?command=show_my_tasks_command"  role="button">
    <fmt:message key="home.showmy" bundle="${rb}"/>
</a>
<br>

<c:if test = "${role == 'ADMIN'}">
    <div class="cntr"><fmt:message key="admin" bundle="${rb}"/></div>
<a class="cntr" href="controller?command=show_all_users_command">
    <fmt:message key="home.a_show" bundle="${rb}"/>
</a>
</c:if>
<br>
<a class="cntr" href="controller?command=sign_out_command" role="button">
    <fmt:message key="home.exit" bundle="${rb}"/>
</a>

</body>
<script src="js/main.js"></script>
</html>
