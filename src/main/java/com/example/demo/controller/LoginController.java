package com.example.demo.controller;

import java.util.Optional;

import org.springframework.context.MessageSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.constant.MessageConst;
import com.example.demo.entity.UserInfo;
import com.example.demo.form.LoginForm;
import com.example.demo.service.LoginService;
import com.example.demo.util.AppUtil;

import lombok.RequiredArgsConstructor;


/**
 * ログイン画面コントローラー
 * 
 * @author mamet
 *
 */
@Controller
@RequiredArgsConstructor
public class LoginController {
	
	/** ログインサービス*/
	private final LoginService service;
	
	/** PasswordEncorder*/
	private final PasswordEncoder passwordEncoder;
	
	/** メッセージソース*/
	private final MessageSource messageSource;
	
	/**
	 * 初期表示
	 * @param model　モデル
	 * @param form　入力情報
	 * @return　表示画面
	 */
	@GetMapping("/login")
	public String view(Model model,LoginForm form) {
		
		return "login";	
	}

	/**
	 * ログイン
	 * @param model　モデル
	 * @param form　入力情報
	 * @return　表示画面
	 */
	@PostMapping("/login")
	public String login(Model model,LoginForm form) {
		Optional<UserInfo> userInfo = service.searchUserById(form.getLoginId());
		Boolean isCorrectUserAuth = userInfo.isPresent()
				&& passwordEncoder.matches(form.getPassword(), userInfo.get().getPassword());
		if(isCorrectUserAuth) {
			return "redirect:/list";
		}else{
			String errorMsg = AppUtil.getMessage(messageSource,MessageConst.LOGIN_WRONG_INPUT);
			model.addAttribute("errorMsg",errorMsg);
			return "login";
		}
	}
	
}
	