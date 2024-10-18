package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.AnimeInfo;

/**
 * ユーザー情報テーブルDAO
 * @author mamet
 *
 */
public interface AnimeInfoRepository extends JpaRepository<AnimeInfo, Long>{

}