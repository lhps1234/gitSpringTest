package com.example.board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.binding.BindingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.example.board.model.member.LoginForm;
import com.example.board.model.member.Member;
import com.example.board.repository.MemberMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("member")
@Controller
public class MemberController {
	
	
	private MemberMapper memberMapper;
	
	@Autowired
	public void setMemberMapper(MemberMapper memberMapper) {
		this.memberMapper=memberMapper;
	}
		
	@GetMapping("join")
	public String joinForm(Model model) {
		model.addAttribute("member",new Member());
		return "member/joinForm"; 
	}
	
	@PostMapping("join")
	public String join(@Validated
			@ModelAttribute("member") Member member, BindingResult result) {
		log.info("member : {}",member);
		
		if(result.hasErrors()) {
			return "member/joinForm";
		}
		
		memberMapper.saveMember(member);
		
		return "redirect:";
	}
	
	@GetMapping("login")
	public String loginForm(Model model) {
		model.addAttribute("loginForm", new LoginForm());
		return "member/loginForm";
		
	}
	
	@PostMapping("login")
	public String login(@Validated @ModelAttribute("loginForm") LoginForm loginForm
			,HttpServletRequest request,BindingResult result
			,@RequestParam(defaultValue="/") String redirectURL) {
		
		if(result.hasErrors()) {
			return "member/loginForm";
		}
		
		Member findMember = memberMapper.findMember(loginForm.getMember_id());
		log.info("findMember : {}",findMember);
		
		if(findMember==null|| !findMember.getPassword().equals(loginForm.getPassword())) {
			result.reject("loginError","아이디 혹은 패스워드 불일치!");
			return "member/loginForm";
		}
		
		
		HttpSession session = request.getSession();
		session.setAttribute("loginMember", findMember);
		return "redirect:"+redirectURL ;
	}
	
	@GetMapping("session")
	public String session(HttpServletRequest request) {
		
		HttpSession session = request.getSession(false);
		log.info("sessionId : {}", session);
		return "redirect:/";
		
	}
	
	@GetMapping("logout")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();

		session.invalidate();
		
		return "redirect:/";
	}
}
