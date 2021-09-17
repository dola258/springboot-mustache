package com.cos.blogapp.test;

// 1.8버전부터 람다식 -> 메서드 내부 스택만 넘길 수 있다

interface MySupplier {
	void get();
}


public class LamdaTest {
	
	static void start(MySupplier s) {
		s.get();
	}
	
	public static void main(String[] args) {
		
		start(

			() -> {
				System.out.println("get함수 호출됨");
			}

		);
	}
	
	
}
