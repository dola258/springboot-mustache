package com.cos.blogapp.domain.board;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import com.cos.blogapp.domain.user.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity // 테이블 생성
public class Board {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id; //PK (자동증가 번호)
	private String title; // 아이디
	@Lob // LongText 타입으로 변경( 오라클에서는 CLOB )
	private String content;
	

	@JoinColumn(name="userID")
	@ManyToOne //Board가 Many User가 One  FetchType(LAZY 지연)(EAGER 지연x, 미리땡겨옴)
	private User user;
}
