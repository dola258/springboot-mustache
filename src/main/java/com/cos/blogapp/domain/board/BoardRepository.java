package com.cos.blogapp.domain.board;

import org.springframework.data.jpa.repository.JpaRepository;
// 원래는 @Repository를 붙여야하는데       ↓붙이면 생략 가능
public interface BoardRepository extends JpaRepository<Board, Integer> {
													// ↑Board타입으로 매핑(Vo할때 했던걸 대신 해준다.)		
	
}
