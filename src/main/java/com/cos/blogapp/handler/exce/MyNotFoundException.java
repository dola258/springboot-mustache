package com.cos.blogapp.handler.exce;

/*
 * 
 * @author 이효빈 2021.09.16
 * 1. id를 못찾았을 때 사용
 * 
 * 
 */

public class MyNotFoundException extends RuntimeException{

	public MyNotFoundException(String msg) {
		super(msg);
	}
}
