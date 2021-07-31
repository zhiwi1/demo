<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:if test="${not empty sessionScope.locale}">
    <fmt:setLocale value="${sessionScope.locale}"/>
</c:if>
<fmt:setBundle basename="locale" var="rb"/>

<html>
<head>
    <title><fmt:message key="task.profile" bundle="${ rb }" /></title>
</head>
<body>
<c:import url="/jsp/templates/header.jsp" charEncoding="utf-8"/>


<body>
<c:out value="${task}"/>;
<p><fmt:message key="task.add_answer" bundle="${ rb }" /></p>
<form action="controller?command=add_answer_command&title=${task.title}" method="post">
    <input type="text" name="answer">
    <input type="submit" name="button" value="answer">
</form>

<c:forEach var="answer" items="${answers}">
    <p>${answer}</p>

    <form action="controller?command=like_answer_command&answer_id=${answer.answerId}" method="post">
        <input type="submit" name="button" value="like">
    </form>
</c:forEach>

<p><fmt:message key="task.add_comment" bundle="${ rb }" /></p>
<form action="controller?command=add_comment_command&title=${task.title}" method="post">
    <input type="text" name="comment">
    <input type="submit" name="button" value="comment">
</form>
<c:out value="${comments}"/>

</script>
</body>
</html>
