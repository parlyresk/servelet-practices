<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="guestbook.dao.GuestbookDao"%>
<%@ page import="guestbook.vo.GuestbookVo"%>
<%@ page import="java.io.IOException"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>

<%
	request.setCharacterEncoding("UTF-8");
	String name = request.getParameter("name");
	String password = request.getParameter("password");
	String contents = request.getParameter("contents");
	String regDate = request.getParameter("reg_date");
	GuestbookVo vo = new GuestbookVo();
	vo.setName(name);
	vo.setPassword(password);
	vo.setContents(contents);
	vo.setRegDate(regDate);
	
	GuestbookDao dao = new GuestbookDao();
	boolean result = dao.insert(vo);

	response.sendRedirect("index.jsp");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>add.jsp</h1>
</body>
</html>