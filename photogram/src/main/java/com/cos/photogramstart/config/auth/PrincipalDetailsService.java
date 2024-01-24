package com.cos.photogramstart.config.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service //IoC
public class PrincipalDetailsService implements UserDetailsService{

	private final UserRepository userRepository;
	
	// 1. 패스워드는 알아서 체킹하니까 신경쓸 필요 없음
	// 2. return이 잘 되면 자동으로 UserDetails 타입을 세션으로 만든다.
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User userEntity = userRepository.findByUsername(username);
		
		if(userEntity == null) {
			return null;
		} else {
			return new PrincipalDetails(userEntity); // 세션에 저장된 userObject 데이터를 활용할 수 있게 됨
		}
	}

}
