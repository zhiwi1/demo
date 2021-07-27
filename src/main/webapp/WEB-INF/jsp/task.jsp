<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:if test="${not empty sessionScope.locale}">
    <fmt:setLocale value="${sessionScope.locale}"/>
</c:if>
<fmt:setBundle basename="locale" var="rb"/>

<html>
<head>
    <title>Profile</title>
</head>
<body>
<c:import url="/WEB-INF/jsp/templates/header.jsp" charEncoding="utf-8"/>


<body>
<c:out value="${task}"/>;
<p>Add answer</p>
<form action="controller?command=add_answer_command&title=${task.title}" method="post">
    <input type="text" name="answer">
    <input type="submit" name="button" value="answer">
</form>
<c:out value="${answers}"/>
<p>Add Comment</p>
<form action="controller?command=add_comment_command&title=${task.title}" method="post">
    <input type="text" name="comment">
    <input type="submit" name="button" value="comment">
</form>
<c:out value="${comments}"/>
</body>
</html>
