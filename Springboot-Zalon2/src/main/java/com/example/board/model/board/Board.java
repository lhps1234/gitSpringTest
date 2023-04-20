package com.example.board.model.board;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Board {
	
	private Long board_id; 	//게시물 아이디
	private String title;	//제목
	private String contents;	//내용
	private String member_id;	//작성자
	private Long hit;			//조회수
	private LocalDateTime created_time;	//작성일
	
	
	public Board(String title, String contents, String member_id ) {
		super();
		this.title = title;
		this.contents = contents;
		this.member_id = member_id;
		this.hit = 0L;
		this.created_time = LocalDateTime.now();
	}
	
	public void addHit() {
		this.hit++;
	}
	
}
