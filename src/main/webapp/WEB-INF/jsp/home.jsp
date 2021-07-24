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
<jsp:include page="/WEB-INF/jsp/templates/header.jsp"/>
<a href="controller?command=go_to_add_task_page_command"  role="button">
add new post
</a>
<br>
<a href="#slider"  role="button">
    show all posts
</a>
<br>
<a  href="controller?command=sign_out_command" role="button">
    выйти
</a>
</body>
</html>
