<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<form name="reg-form" method="POST" action="controller?command=sign_up_command">
    Email: <input type="text" name="email">
    <input type="hidden" name="command" value="register"/>
    <br/> Login:
    <input type="text" name="login" value=""/>
    <br/>Password:
    <input type="text" name="password" value="">
    <br/>Confirm password:
    <input type="text" name="confirm-password" value="">
    <br/>


    <button type="submit"> Зарегистрироваться</button>
</form>

</body>
</html>