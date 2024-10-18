package com.example.demo.form;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

/**
 * ログイン画面　フォーム
 * @author mamet
 *
 */
@Data
public class SignupForm {
	
	/** ログインID */
	@Length(min=8,max=20)
	private String loginId;
	
	/** パスワード */
	@Length(min=8,max=20)
	private String password;

}
