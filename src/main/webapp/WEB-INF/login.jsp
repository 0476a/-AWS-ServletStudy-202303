<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri=""%>

<%!
	//선언문(클래스 정의, 함수 정의)
	//클래스 안이라고 생각하면됨.
%>
<%
	//스크립틀릿(무려 JSP에만 있음!) : 자바 소스코드를 쓸수 있음!
	//메소드 안이라고 생각하면됨.
	//단, 밖에서는 못씀
	String wc = (String) request.getAttribute("welcome");
	out.println(wc);
	int[] numbers = (int[]) request.getAttribute("numbers");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1><%=wc %></h1>
	<%
		for(int i = 0; i < numbers.length; i++) {
	%>
		<div><%=numbers[i] %></div>
	<%
		}
	%>
	<form action="/auth/signin" method="post">
		<input type="text" name="username" placeholder="username">
		<input type="password" name="password" placeholder="password">
		<button type="submit">로그인</button>
	</form>
</body>
</html>