package com.example.board.controller;

import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriUtils;

import com.example.board.model.board.AttachedFile;
import com.example.board.model.board.Board;
import com.example.board.model.board.BoardUpdateForm;
import com.example.board.model.board.BoardWriteForm;
import com.example.board.model.member.LoginForm;
import com.example.board.model.member.Member;
import com.example.board.repository.BoardMapper;
import com.example.board.repository.BoardRepository;
import com.example.board.service.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("board")
@Controller
@RequiredArgsConstructor
public class BoardController {
	
	
	private final BoardMapper boardMapper;
	private final BoardService boardService;
	
	
	@Value("${file.upload.path}")
	private String uploadPath;
	
//	@Autowired
//	private BoardRepository boardRepository ;
//	
//	@PostConstruct
//	public void initData() {	
//		boardRepository.saveBoard(new Board("제목1","내용1","홍길동","1234"));
//		boardRepository.saveBoard(new Board("제목2","내용2","문동은","1234"));
//		boardRepository.saveBoard(new Board("제목2","내용2","하도영","1234"));
//	}
//	
	
	@GetMapping("/")
	public String home() {
		log.info("home() 실행");
		return "redirect:/board/list";
	}
	
	@GetMapping("write")
	public String writeForm(Model model) {
		model.addAttribute("writeForm", new BoardWriteForm());
		
		return"board/write";
	}
	
	@PostMapping("write")
	public String write(@SessionAttribute(name = "loginMember",required=false) Member loginMember
			,@Validated @ModelAttribute("writeForm") BoardWriteForm boardWriteForm,BindingResult result
						,@RequestParam(required=false) MultipartFile file) {
		log.info("boardWriteForm :{}",boardWriteForm);
	 	log.info("file : {}",file);
		
		if(result.hasErrors()) {
			log.info("글자 수 부족");
			return "board/write";
		}
		
		
		Board board = BoardWriteForm.toBoard(boardWriteForm);
		board.setMember_id(loginMember.getMember_id());
		log.info("board :{}",board);
		
		boardService.saveBoard(board,file);
		
		return"redirect:/board/list";
	}
	
	@GetMapping("list")
	public String list(Model model,@ModelAttribute String member_id
						) {
		List<Board> findAllBoards = boardMapper.findAllBoards(member_id);
		model.addAttribute("findAllBoards", findAllBoards);
			
		return "board/list";
	}
	
	
	@GetMapping("read")
	public String read(@SessionAttribute(name="loginMember",required=false) Member loginMember
			,Model model, @RequestParam Long id,@RequestParam(required=false) MultipartFile file) {
		
		Board board = boardMapper.findBoard(id);
		
		log.info("board : {}",board);
		board.addHit();
		boardMapper.updateHit(id);
		AttachedFile attachedFile =  boardService.findFile(id);
		
		model.addAttribute("file", attachedFile);
		model.addAttribute("board",board);
		
		return "board/read";
	}
	
	@PostMapping("delete")
	public String delete(@RequestParam Long id
						) {
		Board board = boardMapper.findBoard(id);
		boardMapper.removeBoard(id);
		
		return "redirect:/board/list";
	}
	@GetMapping("update")
	public String updateForm(@RequestParam Long id, Model model) {
		model.addAttribute("board",boardMapper.findBoard(id));
		return "board/update";
	}
	
	
	@PostMapping("update")
	public String update(@SessionAttribute(name="loginMember",required=false) Member loginMember
						,@RequestParam Long id, 
						@ModelAttribute("board") BoardUpdateForm updateBoard) {
		
		Board board = BoardUpdateForm.toBoard(updateBoard);
		board.setBoard_id(id);
		
		boardMapper.updateBoard(board);
		
		return "redirect:/";
	}
	
	

	
	
	
	
	 @GetMapping("download/{id}")
	    public ResponseEntity<Resource> download(@PathVariable Long id) throws MalformedURLException {
	        AttachedFile attachedFile = boardService.findFileByAttachedFileId(id);
	        String fullPath = uploadPath + "/" + attachedFile.getSaved_filename();
	        UrlResource resource = new UrlResource("file:" + fullPath);
	        String encodingFileName = UriUtils.encode(attachedFile.getOriginal_filename(), StandardCharsets.UTF_8);
	        String contentDisposition = "attachment; filename=\"" + encodingFileName + "\"";
	        return ResponseEntity.ok()
	                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
	                .body(resource);
	    }
	
	
	 
	
	 
	 
	 
}
