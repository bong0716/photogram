package com.cos.photogramstart.web;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.handler.ex.CustomValidationException;
import com.cos.photogramstart.service.AuthService;
import com.cos.photogramstart.web.dto.auth.SignupDto;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor //DI 할 때 사용
@Controller // 1. IoC 2. 파일 리턴
public class AuthController {

	private final  AuthService authService;
	
	@GetMapping("/auth/signin")
	public String signinForm() {
		return "/auth/signin";
	}
	
	@GetMapping("/auth/signup")
	public String signupForm() {
		return "/auth/signup";
	}
	
	@PostMapping("/auth/signup")
	public String signup(@Valid SignupDto signupDto, BindingResult bindingResult) { //key=value 형식으로 넘어옴 : x-www-form-urlencoded
		
			User user = signupDto.toEntity();
			authService.회원가입(user);
			return "/auth/signin";
	}
}
