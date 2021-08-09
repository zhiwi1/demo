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
    <title><fmt:message key="task.profile" bundle="${ rb }"/></title>
</head>
<body>
<c:import url="/jsp/templates/header.jsp" charEncoding="utf-8"/>
<c:import url="/jsp/templates/timeweather.jsp" charEncoding="utf-8"/>

<body>
<h2 class="cntr">${task.title}</h2>
<hr>
<blockquote class="cntr"><c:out value="${task.text}"/></blockquote>
<hr>
<h4 class="cntr">Complexity: <c:out value="${task.complexity}"/></h4>
<hr>
<h3 >ANSWERS</h3>
<br>

<form  action="controller?command=add_answer_command&title=${task.title}" method="post">
    <input class="textfield" type="text" name="answer" value="Your answer">
    <input class="pb button" type="submit" name="button" value="answer">
</form>
<c:forEach var="answer" items="${answers}">
    <p > ${answer.userLogin}: ${answer.content} <c:if test="${answer.correctness=='CORRECT'}">Помечено автором задания как правильный
    ответ</c:if></p>
    <br>
</c:forEach>
<mytag:pagination page="${requestScope.current_page}" maxPage="${requestScope.max_page}"/>
<br>
<hr>
<h3 >COMMENTS</h3>
<br>

<form  action="controller?command=add_comment_command&title=${task.title}" method="post">
    <input class=" textfield" type="text" name="comment" value="Your comment">
    <input class=" pb button" type="submit" name="button" value="comment">

</form>
<c:forEach var="comment" items="${comments}">
    <p > ${comment.loginOfUser}: ${comment.text}</p>
    <br>
</c:forEach>
<mytag:pagination_comment commentPage="${requestScope.comment_current_page}" maxPage="${requestScope.comment_max_page}"/>
</script>
</body>
<script src="js/main.js"></script>
</html>
