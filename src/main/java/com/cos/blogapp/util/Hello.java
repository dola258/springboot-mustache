package com.cos.blogapp.util;

public class Hello {
   
   private static Hello instance = new Hello();
   
   public static Hello getInstance() {
      return instance;
   }
   
   private Hello() {}
}