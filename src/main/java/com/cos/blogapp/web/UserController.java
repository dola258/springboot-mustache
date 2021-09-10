package com.cos.blogapp.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cos.blogapp.domain.user.User;
import com.cos.blogapp.domain.user.UserRepository;
import com.cos.blogapp.util.MyAlgorithm;
import com.cos.blogapp.util.SHA;
import com.cos.blogapp.util.Script;
import com.cos.blogapp.web.dto.JoinReqDto;
import com.cos.blogapp.web.dto.LoginReqDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // final을 붙이면 생성자를 따로 안 만들수있다.
@Controller
public class UserController {

	private final UserRepository userRepository;
	private final HttpSession session;
	
	// /WEB-INF/views/user/login.jsp
	// /WEB-INF/views/login.jsp
	
	//  /WEB-INF/views/user/login.jsp
	@GetMapping("/loginForm")
	public String loginForm() {
		return "user/loginForm";
	}
	
	@GetMapping("/joinForm")
	public String joinForm() {
		return "user/joinForm";
	}
	
	@PostMapping("/login")                                                   
	public @ResponseBody String login(@Valid LoginReqDto dto, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			Map<String, String> errorMap = new HashMap<>();
			for(FieldError error:bindingResult.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage());
				System.out.println("필드 : " + error.getField());
				System.out.println("메시지 : " + error.getDefaultMessage());
			}
			return Script.back("", errorMap.toString());
		}
		
		// 1. username, password 받기
		System.out.println(dto.getUsername());
		System.out.println(dto.getPassword());
		
		// 입력한 비밀번호를 암호화한다.
		String encPassword = SHA.encrypt(dto.getPassword(), MyAlgorithm.SHA256);
		dto.setPassword(encPassword);
		
		// 2. DB -> 조회
		User userEntity = userRepository.mLogin(dto.getUsername(), dto.getPassword());
		
		if(userEntity == null) {
			return Script.back("", "아이디 혹은 비밀번호를 잘못 입력하였습니다.");
		}else { 	// 3. 있으면
			// 4. session에 저장
			session.setAttribute("principal", userEntity);
			// 세션이 날라가는 조건: 1. session.invalidate() - 로그아웃
			//                       2. 브라우저를 닫으면 날라간다.
			
			// 5. 메인페이지를 돌려주기
			return Script.href("/", "로그인성공");
		}
	}
	
	@PostMapping("/join")
	public @ResponseBody String join(@Valid JoinReqDto dto, BindingResult bindingResult) {		// username=love&password=1234&email=love@nate.com
								   // Valid : JoinReqDto내부에 있는 @을 기반으로 검증한다.
					 			   // 검증에 실패한면 BindingResult에 담아둔다.
	//	System.out.println("에러 사이즈 : " + bindingResult.getFieldErrors().size()); // 여기에 다 담긴다.(사이즈가 0이면 에러없음)
	/*
	  	1. 유효성 검사 실패 - 자바스크립트 응답(경고창, 뒤로가기) 
	  	2. 유효성 검사 정상 - 로그인 페이지
	*/
		if(bindingResult.hasErrors()) {
			Map<String, String> errorMap = new HashMap<>();
			for(FieldError error:bindingResult.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage());
				System.out.println("필드 : " + error.getField());
				System.out.println("메시지 : " + error.getDefaultMessage());
			}
			return Script.back("", errorMap.toString());
		}
		
		// 입력한 비밀번호를 암호화한다.
		String encPassword = SHA.encrypt(dto.getPassword(), MyAlgorithm.SHA256);
		dto.setPassword(encPassword);
		
		userRepository.save(dto.toEntity());	// user 오브젝트에다가 dto에 있는거 채워넣어 
		
		return Script.href("/loginForm");	// 리다이렉션 (300)
	}
	
}



