<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	response.sendRedirect("/board?page=0");
//	request.getRequestDispatcher("/board?page=0").forward(request, response); // 페이지가 주소에 안남는다
%>