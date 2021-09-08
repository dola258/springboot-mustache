package com.cos.blogapp.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserControllerTest {
	
	@GetMapping("/test/join")
	public @ResponseBody String testJoin() {
		return "test/join";
	}

	@GetMapping("/test/login")
	public @ResponseBody String testLogin() {
		return "<script>alert('Hello');</script>"; // 브라우저가 script해석
	}
	
	//           /test/data/1
	@GetMapping("/test/data/{num}")      //주소뒤에{num}을 파싱해서 넣어준다. 
	public @ResponseBody String testData(@PathVariable int num) {
		if(num == 1) { // 정상
			StringBuilder sb = new StringBuilder();
			sb.append("<script>");
			sb.append("location.href='/';");
			sb.append("</script>");
			
			return sb.toString(); // 오브젝트가 들고있는 toString()을 호출
		} else { // 오류
			return "오류가 났습니다.";
		}
	}
}
