package com.cos.blogapp.web;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.cos.blogapp.domain.board.Board;
import com.cos.blogapp.domain.board.BoardRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // final을 붙이면 생성자를 따로 안 만들수있다.
@Controller // 컴퍼넌트 스캔(스프링) IoC
public class BoardController {
	// final이 붙은 변수는 무조건 초기화를 한다
	private final BoardRepository boardRepository;
	
	@GetMapping("/board")  //{}선택
	public String home(Model model, int page) {

	/* 첫번째 방법( 파라미터를 Integer만 가능)
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
