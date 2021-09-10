package com.cos.blogapp.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;

import org.junit.jupiter.api.Test;

public class SHA256Test {

	@Test
	public void encrypt() { // JUnit은 매개변수를 받을수없다.
		String salt = "코스";
		String rawPassword = "1234!"+salt;
		
		// SHA256 함수를 가진 클래스 객체 가져오기
		// 내가 new를 못하는구나 new가 되어있는걸 재사용하는 구나
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-256"); 
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// 비밀번호 1234를 SHA256에 던지기
		// 바이트로 변경
		md.update(rawPassword.getBytes());

/*		for(Byte b:rawPassword.getBytes()) {
			System.out.print(b);  // 1234의 ASCII코드
		}
*/		
		StringBuilder sb = new StringBuilder();
		
		for(Byte c:md.digest()) {
			// 16진수로 변경
			sb.append(String.format("%02x", c));
		}
		
		System.out.println(sb.toString().length());
	
	}
}
