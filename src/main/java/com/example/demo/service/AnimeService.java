package com.example.demo.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.entity.AnimeInfo;
import com.example.demo.form.AnimeForm;
import com.example.demo.repository.AnimeInfoRepository;

import lombok.RequiredArgsConstructor;

/**
 * ログイン画面　サービス
 * @author mamet
 *
 */
@Service
@RequiredArgsConstructor
public class AnimeService {

	/** ユーザー情報テーブルDAO*/
	private final AnimeInfoRepository animeRepository;
	
	/**
	 * アニメ情報全権取得
	 * @return アニメ情報
	 */
	public List<AnimeInfo> searchAll(){
		return animeRepository.findAll();
	}
	
	/**
	 * アニメ情報登録
	 * @param animeForm 登録内容（フォーム）
	 * @return 登録情報
	 */
	public Optional<AnimeInfo> registAnimeInfo(AnimeForm animeForm){
		
		AnimeInfo animeInfo = new AnimeInfo();
		
		animeInfo.setName(animeForm.getName());
		animeInfo.setGenre(animeForm.getGenre());
		LocalDate startDate = LocalDate.parse(animeForm.getStartDate());
		animeInfo.setStartDate(startDate);
			
		return Optional.of(animeRepository.save(animeInfo));
	}
	
	/**
	 * 更新するアニメ情報の取得
	 * @param animeForm 入力フォーム
	 * @param id ID
	 * @return アニメ情報（フォーム）
	 */
	public AnimeForm edit(AnimeForm animeForm, Long id) {
		
		Optional<AnimeInfo> animeInfOpt = animeRepository.findById(id);
		animeForm.setName(animeInfOpt.get().getName());
		animeForm.setGenre(animeInfOpt.get().getGenre());
		DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
        animeForm.setStartDate(animeInfOpt.get().getStartDate().format(formatter));
        
        return animeForm;
	}
	
	/**
	 * アニメ情報更新
	 * @param animeForm 更新するアニメ情報(フォーム）
	 * @param id ID
	 * @return 更新情報
	 */
	public Optional<AnimeInfo> updateAnimeInfo(AnimeForm animeForm, Long id){
		
		Optional<AnimeInfo> animeInfoOpt = animeRepository.findById(id);
		
		AnimeInfo animeInfo = animeInfoOpt.get();
		
		animeInfo.setName(animeForm.getName());
		animeInfo.setGenre(animeForm.getGenre());
		LocalDate startDate = LocalDate.parse(animeForm.getStartDate());
		animeInfo.setStartDate(startDate);
		
		return Optional.of(animeRepository.save(animeInfo));
	}
	
}
