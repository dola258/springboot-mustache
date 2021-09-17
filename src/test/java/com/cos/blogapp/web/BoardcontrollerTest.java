package com.cos.blogapp.web;

import org.junit.jupiter.api.Test;

import com.cos.blogapp.domain.board.Board;

public class BoardcontrollerTest {
	
	@Test
	public void 익셉션테스트() {
		try {
			Board b = null;
			System.out.println(b.getContent());
		} catch(Exception e) {
	
		 	System.out.println("오류가 났음"); 
		 	System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void 익셉션테스트2() throws Exception {
		throw new Exception();
		
		
	}
}
