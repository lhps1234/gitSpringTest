package com.example.board.model.member;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Null;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data 
public class Member {
	
	@Size(min=4,max=20,message="글자수맞춰!" )
	private String member_id;
	
	@Size(min=4,max=20,message="글자수")
	private String password;
	
	@Null(message="빈칸 안됨")
	private String name;
	
	@NotEmpty(message="빈칸 안됨")
	private String gender;
	
	@Past
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate birth;
	
	private String email;
	
}
