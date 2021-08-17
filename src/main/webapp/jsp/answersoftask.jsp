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
    <title><fmt:message key="answersoftask" bundle="${rb}"/></title>
</head>
<body>

<c:import url="/jsp/templates/header.jsp" charEncoding="utf-8"/>
<c:import url="/jsp/templates/timeweather.jsp" charEncoding="utf-8"/>
<c:forEach var="answer" items="${answers}">
    <c:if test="${answer.correctness =='INCORRECT'}">
        <p><c:out value="${answer.userLogin}:${answer.content}"/></p>
        <p><fmt:message key="answersoftask.incorrect" bundle="${ rb }"/></p>
        <form action="controller?command=mark_correct_answer_command&answer_id=${answer.answerId}&task_id=${answer.taskId}"
              method="post">
            <input class="button" type="submit" name="button" value="<fmt:message key="answersoftasks.markcorrect" bundle="${rb}"/>">
        </form>
    </c:if>
    <c:if test="${answer.correctness =='CORRECT'}">
        <p><c:out value="${answer.userLogin}:${answer.content}"/></p>
        <p><fmt:message key="answersoftask.correct" bundle="${ rb }"/></p>
        <form action="controller?command=mark_incorrect_answer_command&answer_id=${answer.answerId}&task_id=${answer.taskId}"
              method="post">
            <input class="button" type="submit" name="button" value="<fmt:message key="answersoftasks.markincorrect" bundle="${rb}"/>">
        </form>
    </c:if>
</c:forEach>
<mytag:pagination page="${requestScope.current_page}" maxPage="${requestScope.max_page}"/>
<a class="cntr" href="controller?command=go_to_home_page_command">
    <fmt:message key="return" bundle="${rb}"/>
</a>
</body>
<script src="js/main.js"></script>
</html>
