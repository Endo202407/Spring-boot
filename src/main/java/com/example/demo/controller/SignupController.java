package com.example.demo.controller;

import java.util.Optional;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.constant.SignupMessage;
import com.example.demo.entity.UserInfo;
import com.example.demo.form.SignupForm;
import com.example.demo.service.SignupService;
import com.example.demo.util.AppUtil;

import lombok.RequiredArgsConstructor;
@Controller
@RequiredArgsConstructor
public class SignupController {
	/** 新規登録サービス*/
	private final SignupService service;
	
	/** メッセージソース*/
	private final MessageSource messageSource;
	
	/**
	 * 初期表示
	 * @param model　モデル
	 * @param form　入力情報
	 * @return　表示画面
	 */
	@GetMapping("/signup")
	public String view(Model model,SignupForm form) {
		
		return "signup";	
	}

	/**
	 * 新規登録
	 * @param model　モデル
	 * @param form　入力情報
	 * @param bdResult　入力チェック結果
	 * @return　表示画面
	 */
	@PostMapping("/signup")
	public void login(Model model,@Validated SignupForm form, BindingResult bdResult) {
		if(bdResult.hasErrors()) {
			return;
		}
		Optional<UserInfo> userInfoOpt = service.registUserInfo(form);
		SignupMessage signupMessage = judgeMessageKey(userInfoOpt);
		String messageId = AppUtil.getMessage(messageSource,signupMessage.getMessageId());
		model.addAttribute("message",messageId);
		model.addAttribute("isError",signupMessage.isError());
		 

	}
	
	private SignupMessage judgeMessageKey(Optional<UserInfo> userInfoOpt) {
		if(userInfoOpt.isEmpty()) {
			return SignupMessage.EXISTED_LOGIN_ID;
		}else {
			return SignupMessage.SUCCEED;		
		}
	}
	
}
