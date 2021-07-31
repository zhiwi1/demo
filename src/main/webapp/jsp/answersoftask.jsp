<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:if test="${not empty sessionScope.locale}">
    <fmt:setLocale value="${sessionScope.locale}"/>
</c:if>
<fmt:setBundle basename="locale" var="rb" />
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:import url="/jsp/templates/header.jsp" charEncoding="utf-8"/>
<c:forEach var="answer" items="${answers}">
    <p>${answer}</p>
    <form action="controller?command=mark_correct_answer_command&answer_id=${answer.answerId}" method="post">
        <input type="submit" name="button" value="mark correct">
    </form>
</c:forEach>
</body>
</html>
