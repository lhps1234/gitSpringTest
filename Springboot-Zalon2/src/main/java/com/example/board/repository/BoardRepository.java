package com.example.board.repository;

import java.util.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.example.board.model.board.Board;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class BoardRepository {
//	
//	private BoardMapper boardMapper;
//	private Map<Long,Board> boards = new HashMap<>();
//
//	private Long sequence = 0L;
//	
//	public BoardRepository() {
//		log.info("BoardRepository 생성");
//	}
//	
//	public void saveBoard(Board board) {
//		board.setId(++sequence);
//		boards.put(board.getId(), board);
//		boardMapper.saveBoard(board);
//	}
//	
//	public Board findBoard(Long id) {
//		Board board = boards.get(id);
//		return board;
//		
//	}
//	
//	public void removeBoard(Long id) {
//		
//		boards.remove(id);
//	}
//	
//	public void updateBoard(Long id, Board updateBoard) {
//		Board board = boards.get(id);
//		if(board!=null) {
//			board.setContents(updateBoard.getContents());
//			board.setUsername(updateBoard.getUsername());
//			board.setTitle(updateBoard.getTitle());
//		}
//		
//	}
//	
//	public List<Board> findAllBoards(){
//		List<Board> list = new ArrayList<>();
//		list.addAll(boards.values());
//		return list;
//		
//	}
}
