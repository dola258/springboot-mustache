package com.cos.blogapp.web;

import javax.servlet.http.HttpSession;	

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.cos.blogapp.domain.user.User;
import com.cos.blogapp.domain.user.UserRepository;
import com.cos.blogapp.web.dto.JoinReqDto;
import com.cos.blogapp.web.dto.LoginReqDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class UserController {

	private final UserRepository userRepository;
	private final HttpSession session;
	
	@GetMapping({"/", "/home"})  //{}선택
	public String home() {
		return "home";
	}
	
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
	public String login(LoginReqDto dto) {
		
		// 1. username, password 받기
		System.out.println(dto.getUsername());
		System.out.println(dto.getPassword());
		
		if( dto.getUsername() == null ||
			    dto.getPassword() == null ||
			    dto.getUsername().equals("") ||
			    dto.getPassword().equals("") 
			) {
				return "error/error";
			}
		
		
		// 2. DB -> 조회
		User userEntity = userRepository.mLogin(dto.getUsername(), dto.getPassword());
		
		if(userEntity == null) {
			return "redirect:/loginForm";
		}else {
			session.setAttribute("principal", userEntity);
			return "redirect:/home";
		}
		// 3. 있으면
		// 4. session에 저장
		// 5. 메인페이지를 돌려주기
	}
	
	@PostMapping("/join")
	public String join(JoinReqDto dto) {		// username=love&password=1234&email=love@nate.com

		if( dto.getUsername() == null ||
		    dto.getPassword() == null ||
		    dto.getEmail() == null ||
		    dto.getUsername().equals("") ||
		    dto.getPassword().equals("") ||
		    dto.getEmail().equals("") 
		    
		) {
			return "error/error";
		}
		
		userRepository.save(dto.toEntity());	// user 오브젝트에다가 dto에 있는거 채워넣어 
		
		return "redirect:/loginForm";	// 리다이렉션 (300)
	}
	
}



