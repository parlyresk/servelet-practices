<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>안녕하세요</h1>
	<h2>안녕하세요</h2>
	<h3>안녕하세요</h3>
	<h4>안녕하세요</h4>
	<h5>안녕하세요</h5>
	<h6>안녕하세요</h6>
	
	<table border="1" cellspacing="0" cellpadding="10">
		<tr>
			<th>글 번호<th>
			<th>글 제목<th>
			<th>글 작성자<th>
		</tr>
		
		<tr>
			<td>1<td>
			<td>제목1<td>
			<td>작성자1<td>
		</tr>
		
		<tr>
			<td>2<td>
			<td>제목12<td>
			<td>작성자2<td>
		</tr>
	</table>
	
	<img src="http://localhost:8080/helloweb/images/cat.jpg">
	<img src="/helloweb/images/cat.jpg"><br>
	<img src="./images/cat.jpg">
	
	<p>
		문장입니다.<br>
		안녕
		감사
		사수
		
	</p>
	
	<a href="/helloweb/hello?id=hihihi"> hello로 가기</a>
	<br>
	<a href="/helloweb/form.jsp"> form로 가기</a>
</body>
</html>