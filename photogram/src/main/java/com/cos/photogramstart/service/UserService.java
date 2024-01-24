package com.cos.photogramstart.service;

import java.util.function.Supplier;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;
import com.cos.photogramstart.handler.ex.CustomException;
import com.cos.photogramstart.handler.ex.CustomValidationApiException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public User 회원프로필(int userId) {
		User userEntity = userRepository.findById(userId).orElseThrow(() -> {
			throw new CustomException("해당 프로필 페이지는 없는 페이지입니다.");
		});
		return userEntity;
	}
	
	@Transactional
	public User 회원수정(int id, User user) {
		// 1. 영속화
		User userEntity = userRepository.findById(id).orElseThrow(() -> { return new CustomValidationApiException("찾을 수 없는 id입니다.");}); // 1. 무조건 찾았다. 걱정마 get() 2. 못찾았어 익셉션 발동 orElseThrow()
	
		String rawPassword = user.getPassword();
		String encPassword = bCryptPasswordEncoder.encode(rawPassword);
		
		// 2. 영속화된 오브젝트 수정 - 더티체킹 (업데이트 완료)
		userEntity.setName(user.getName());
		userEntity.setPassword(encPassword);
		userEntity.setBio(user.getBio());
		userEntity.setWebsite(user.getWebsite());
		userEntity.setPhone(user.getPhone());
		userEntity.setGender(user.getGender());
		
		return userEntity;
	} //더티체킹 일어나서 업데이트가 완료됨. 
}