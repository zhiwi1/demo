<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<form name="reg-form" method="POST" action="reg-servlet">
    <input type="hidden" name="command" value="register"/>
    Login:
    <input type="text" name="login" value=""/>
    <br/>Password:
    <input type="text" name="password" value="">
    <br/>
    <button type="submit"> Зарегистрироваться</button>
</form>

</body>
</html>