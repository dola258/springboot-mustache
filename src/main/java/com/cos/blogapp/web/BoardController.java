package com.cos.blogapp.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cos.blogapp.domain.board.Board;
import com.cos.blogapp.domain.board.BoardRepository;
import com.cos.blogapp.domain.user.User;
import com.cos.blogapp.util.Script;
import com.cos.blogapp.web.dto.BoardSaveReqDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // final을 붙이면 생성자를 따로 안 만들수있다.
@Controller // 컴퍼넌트 스캔(스프링) IoC
public class BoardController {
	
	// final이 붙은 변수는 무조건 초기화를 한다
	private final BoardRepository boardRepository;
	private final HttpSession session;
	
	
	
	
	@PostMapping("/board")
	public @ResponseBody String save(@Valid BoardSaveReqDto dto, BindingResult bindingResult ) {
		
		User principal = (User) session.getAttribute("pricipal");
		
		// 인증체크
		if(principal == null) {
			return Script.href("/loginForm", "잘못된 접근입니다.");
		}
		
		
		if(bindingResult.hasErrors()) {
			Map<String, String> errorMap = new HashMap<>();
			for(FieldError error:bindingResult.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage());
				System.out.println("필드 : " + error.getField());
				System.out.println("메시지 : " + error.getDefaultMessage());
			}
			return Script.back("", errorMap.toString());
		}
		
		
		
		
		
		boardRepository.save(dto.toEntity(principal));
	/*	↑밑에랑 똑같이 움직인다.
		User user = new User();
		user.setId(3);
		boardRepository.save(dto.toEntity(user));
	*/	
		return Script.href("/", "글쓰기 성공");
	}
	
	
	
	@GetMapping("board/saveForm")
	public String saveForm() {
		return "/board/saveForm";
	}
	
	
	@GetMapping("/board")  //{}선택
	public String home(Model model, int page) {

	/* 
	    첫번째 방법( 파라미터를 Integer만 가능)
	 	if(page == null) {
			System.out.println("null입니다.");
			page = 0;
		}
	*/	
		
		
		
		
		PageRequest pageRequest = PageRequest.of(page, 3, Sort.by("id").descending()); // 페이징

		
	// Sort.by(Sort.Direction.DESC, "id") // id를 기준으로 내림차순 정렬
			   
				// 영속화된 오브젝트
		Page<Board> boardsEntity = boardRepository.findAll(pageRequest); 
		model.addAttribute("boardsEntity", boardsEntity);
//		System.out.println(boardsEntity.get(0).getUser().getUsername());
		return "board/list";
	}
	
	
}
