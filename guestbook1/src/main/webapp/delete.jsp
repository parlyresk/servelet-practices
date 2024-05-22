<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="guestbook.dao.GuestbookDao"%>
<%@ page import="guestbook.vo.GuestbookVo"%>

<%
	
	long no = Long.parseLong(request.getParameter("no"));
	String password = request.getParameter("password");
	GuestbookDao dao = new GuestbookDao();
	dao.deleteByNoAndPassword(no,password);
	
	response.sendRedirect("index.jsp");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<a>delete.jsp</a>
</body>
</html>