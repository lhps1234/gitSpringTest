package com.example.board.model.board;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class BoardUpdateForm {
	
	private Long board_id; 	//게시물 아이디
	private String title;	//제목
	private String contents;	//내용
	private String member_id;	//작성자
	private Long hit;			//조회수
	private LocalDateTime created_time;	//작성일
	
	
	public BoardUpdateForm(String title, String contents, String member_id ) {

		this.title = title;
		this.contents = contents;

		this.hit = 0L;
		this.created_time = LocalDateTime.now();
	}
	
	public static Board toBoard(BoardUpdateForm boardUpdateForm) {
		Board board = new Board();
		board.setContents(boardUpdateForm.getContents());
		board.setTitle(boardUpdateForm.getTitle());
		boardUpdateForm.setCreated_time(board.getCreated_time());
		boardUpdateForm.setHit(board.getHit());
		boardUpdateForm.setMember_id(board.getMember_id());
		
		return board;
	}
	
	public void addHit() {
		this.hit++;
	}
	
}
