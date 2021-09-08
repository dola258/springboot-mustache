package com.cos.blogapp.util;

public class Script {
	public static String back(String path, String msg) {
		StringBuilder sb = new StringBuilder();
		sb.append("<script>");
		sb.append("alert('"+msg+"');"); // msg로 받아야 동적으로(메세지 내용이 바뀌게)할 수 있다.
		sb.append("history.back();"); // 뒤로가기
		sb.append("</script>");
		
		return sb.toString();
	}

	public static String href(String path) {
		StringBuilder sb = new StringBuilder();
		sb.append("<script>");
		sb.append("location.href='"+path+"';"); // 뒤로가기
		sb.append("</script>");
		
		return sb.toString();
	}
	
	// 오버로딩 중복정의(매개변수를 다르게한다)
	public static String href(String path, String msg) {
		StringBuilder sb = new StringBuilder();
		sb.append("<script>");
		sb.append("alert('"+msg+"');");
		sb.append("location.href='"+path+"';"); // 뒤로가기
		sb.append("</script>");
		
		return sb.toString();
	}
}
