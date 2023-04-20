package com.example.board.model.board;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class BoardWriteForm {
	
	private Long board_id; 	//게시물 아이디
	
	@NotBlank
	private String title;	//제목
	@NotBlank
	private String contents;	//내용
	
	private String member_id;	//작성자
	private Long hit;			//조회수
	private LocalDateTime created_time;	//작성일
	
	
	public BoardWriteForm(String title, String contents, String member_id ) {

		this.title = title;
		this.contents = contents;

		this.hit = 0L;
		this.created_time = LocalDateTime.now();
	}
	
	public static Board toBoard(BoardWriteForm boardWriteForm) {
		Board board = new Board();
		board.setContents(boardWriteForm.getContents());
		board.setTitle(boardWriteForm.getTitle());
		
		return board;
	}
	
	public void addHit() {
		this.hit++;
	}
	
}
