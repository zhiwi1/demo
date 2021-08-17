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
    <title><fmt:message key="task.task" bundle="${ rb }"/></title>
</head>
<body>
<c:import url="/jsp/templates/header.jsp" charEncoding="utf-8"/>
<c:import url="/jsp/templates/timeweather.jsp" charEncoding="utf-8"/>

<body>
<h2 class="cntr"><c:out value="${task.title}"/></h2>
<hr>
<blockquote class="cntr"><c:out value="${task.text}"/></blockquote>
<hr>
<h4 class="cntr"><fmt:message key="complexity" bundle="${ rb }"/><c:out value="${task.complexity}"/></h4>
<hr>
<h3 ><fmt:message key="task.answers" bundle="${ rb }"/></h3>
<br>

<form  action="controller?command=add_answer_command&title=${task.title}" method="post">
    <input class="textfield" type="text" name="answer" value="" required pattern="${requestScope.regexp_answer}">
    <input class="pb button" type="submit" name="button" value="<fmt:message key="task.add_answer" bundle="${ rb }"/>">
</form>
<c:forEach var="answer" items="${answers}">
    <p ><c:out value="${answer.userLogin}"/>: <c:out value="${answer.content}"/> <c:if test="${answer.correctness=='CORRECT'}">
        <fmt:message key="task.mark" bundle="${ rb }"/></c:if></p>
    <br>
</c:forEach>
<mytag:pagination page="${requestScope.current_page}" maxPage="${requestScope.max_page}"/>
<br>
<hr>
<h3 ><fmt:message key="task.comments" bundle="${ rb }"/></h3>
<br>

<form  action="controller?command=add_comment_command&title=${task.title}" method="post">
    <input class=" textfield" type="text" name="comment" value="" required pattern="${requestScope.regexp_comment}">
    <input class=" pb button" type="submit" name="button" value="<fmt:message key="task.add_comment" bundle="${ rb }"/>">

</form>
<c:forEach var="comment" items="${comments}">
    <p ><c:out value="${comment.loginOfUser}"/>:<c:out value="${comment.text}"/></p>
    <br>
</c:forEach>
<mytag:pagination_comment commentPage="${requestScope.comment_current_page}" maxPage="${requestScope.comment_max_page}"/>
<hr>
<a class="cntr" href="controller?command=go_to_home_page_command">
    <fmt:message key="return" bundle="${rb}"/>
</a>
</script>
</body>
<script src="js/main.js"></script>
<script src="js/validation_alert.js"></script>
</html>
