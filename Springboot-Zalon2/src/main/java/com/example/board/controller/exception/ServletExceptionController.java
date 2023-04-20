package com.example.board.controller.exception;

import javax.management.RuntimeErrorException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ServletExceptionController {

	
	@GetMapping("error-ex")
	public void errorEx() {
		throw new RuntimeException("런타임 예외 발생");
	}
	
	
}
