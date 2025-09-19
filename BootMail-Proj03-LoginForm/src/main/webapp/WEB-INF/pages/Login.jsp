<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form"  prefix="frm"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1 style="text-align: center; color: red"> Login Form</h1>

<frm:form modelAttribute="user" method="post" action="login">

<h3>Enter Email</h3>
<frm:input path="email"/>
<h3>Enter Password</h3>
<frm:input path="password"/>

<input type="submit" value="Login" />
</frm:form>
</body>
</html>