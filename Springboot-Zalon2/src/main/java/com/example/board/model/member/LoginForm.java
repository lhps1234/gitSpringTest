package com.example.board.model.member;


import javax.validation.constraints.Size;


import lombok.Data;

@Data 
public class LoginForm {
	// 아이디는 4 ~ 20자리 사이
	@Size(min = 4, max = 20 , message ="아이디 4~20자리 사이로 해줭")
	private String member_id;
	
	// 패스워드는 4~20자리 사이
	@Size(min = 4, max = 20 , message ="비밀번호 4~20자리 사이로 해줭")
	private String password;
	

}
