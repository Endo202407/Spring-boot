package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.AnimeInfo;
import com.example.demo.form.AnimeForm;
import com.example.demo.service.AnimeService;

import lombok.RequiredArgsConstructor;

/**
 * アニメコントローラー
 * @author mamet
 *
 */
@Controller
@RequiredArgsConstructor
public class AnimeController {
	
	private final AnimeService animeService;
	
	/**
	 * 一覧表示画面
	 * @return 一覧表示画面
	 */
	@GetMapping("/list")
	public String animeList(Model model) {
	    List<AnimeInfo> animeList = animeService.searchAll();
	    model.addAttribute("animeList", animeList);
	    model.addAttribute("animeForm", new AnimeForm()); // 常に新しいフォームを提供
	    return "list";
	}
	/**
	 * 新規入力フォームの表示
	 * @param animeForm 入力フォーム
	 * @return フォーム
	 */
    @GetMapping("/form")
    public String form(@ModelAttribute AnimeForm animeForm) {
        return "form";
    }
    
    //新規入力データの保存
    /**
     * 
     * @param animeForm　入力フォーム
     * @return 一覧表示画面
     */
    @PostMapping("/form")
    public String create(@ModelAttribute @Validated AnimeForm animeForm, BindingResult bdResult) {
    	if(bdResult.hasErrors()) {
    		return "form";
    	}
    	
	    animeService.registAnimeInfo(animeForm);
	    return "redirect:/list"; 
    	
    }
    @GetMapping("/list/edit/{id}")
    public String edit(@ModelAttribute AnimeForm animeForm, @PathVariable Long id) {
    	animeForm = animeService.edit(animeForm,id);
    	return "edit";
    }
    
    @PostMapping("/edit/{id}")
    public String update(@ModelAttribute AnimeForm animeForm , @PathVariable Long id) {
    	animeService.updateAnimeInfo(animeForm,id);
    	return "redirect:/list";
    	
    }

}
