package com.cos.blogapp.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA {
				// encrypt: 암호화                  
	public static String encrypt(String rawPassword, MyAlgorithm algorithm)  {
		
		// SHA256 함수를 가진 클래스 객체 가져오기
		// 내가 new를 못하는구나 new가 되어있는걸 재사용하는 구나
		MessageDigest md = null;
		try {                                        // enum에 -가 안되서 get을 사용
			md = MessageDigest.getInstance(algorithm.getType());
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		// 비밀번호 1234를 SHA256에 던지기
		// 바이트로 변경
		md.update(rawPassword.getBytes());

		StringBuilder sb = new StringBuilder();
		
		
		// 암호화된 글자를 16진수로 변환(헥사코드)
		for(Byte c:md.digest()) {
			sb.append(String.format("%02x", c));
		}
		
		return sb.toString();
	
	}
}