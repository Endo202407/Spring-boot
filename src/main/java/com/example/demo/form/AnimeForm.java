package com.example.demo.form;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * アニメ フォーム
 * @author mamet
 *
 */
@Data
public class AnimeForm {
	/**id*/
	private Long id;
	
	/**タイトル*/
	@NotEmpty(message = "タイトルを入力してください。")
	private String name;
	
	/**ジャンル*/
	@NotNull(message = "ジャンルを選択してください。")
	private Long genre;
	
	/**放送開始日*/
	private String startDate;
}
