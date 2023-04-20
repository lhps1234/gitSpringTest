package com.example.board.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.board.model.board.AttachedFile;
import com.example.board.model.board.Board;
import com.example.board.repository.BoardMapper;
import com.example.board.util.FileService;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly=true)
@RequiredArgsConstructor
public class BoardService {
	
	private final BoardMapper boardMapper;
	private final FileService fileService;
	
	@Value("${file.upload.path}")
	private String uploadPath;
	
	
	@Transactional
	public void saveBoard(Board board, MultipartFile file){
		
		
		boardMapper.saveBoard(board);
		
		if(file !=null || file.getSize()>0 ) {
			AttachedFile saveFile = fileService.saveFile(file);
			saveFile.setBoard_id(board.getBoard_id());
			boardMapper.saveFile(saveFile);
			
		}
		
	}
	
	public AttachedFile findFile(Long board_id) {
		
		return boardMapper.findFile(board_id);
	}
	
	  public AttachedFile findFileByAttachedFileId(Long attachedFile_id) {
	        return boardMapper.findFileByAttachedFileId(attachedFile_id);
	    }
	
	
	
	
	
	
	
}
