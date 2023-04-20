package com.example.board.repository;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.board.model.board.AttachedFile;
import com.example.board.model.board.Board;

@Mapper
public interface BoardMapper {
	
	void saveBoard(Board board);
	
	List<Board> findAllBoards(String member_id);
	
	Board findBoard(Long board_id);
	
	void removeBoard(Long board_id);
	
	void updateBoard(Board Board);
	
	void updateHit(Long board_id);
	
	void saveFile(AttachedFile attachedFile);

	AttachedFile findFile(Long board_id);
	
	AttachedFile findFileByAttachedFileId(Long attachedFile_id);
	
	
}
