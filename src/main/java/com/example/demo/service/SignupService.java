package com.example.demo.service;


import java.util.Optional;

import org.dozer.Mapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.UserInfo;
import com.example.demo.form.SignupForm;
import com.example.demo.repository.UserInfoRepository;

import lombok.RequiredArgsConstructor;
/**
 * ログイン画面　サービス
 * @author mamet
 *
 */
@Service
@RequiredArgsConstructor
public class SignupService {

	/** ユーザー情報テーブルDAO*/
	private final UserInfoRepository repository;
	
	/** Dozer Mapper*/
	private final Mapper mapper;
	
	/** PasswordEncorder*/
	private final PasswordEncoder passwordEncoder;
	
	/**
	 * ユーザー情報テーブル　新規登録
	 * @param form
	 * @return　登録情報（ユーザー情報Entity）、すでに同じユーザーIDで登録されている場合はempty
	 */
	public Optional<UserInfo> registUserInfo(SignupForm form){
		Optional<UserInfo> userInfoExistedOpt = repository.findById(form.getLoginId());
		if(userInfoExistedOpt.isPresent()) {
			return Optional.empty();
		}
		
		UserInfo userInfo = mapper.map(form, UserInfo.class);
		String encordedPassword = passwordEncoder.encode(form.getPassword());
		userInfo.setPassword(encordedPassword);

		return Optional.of(repository.save(userInfo));
	}
	
}
