package com.example.demo.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * ユーザー情報テーブル　エンティティ
 * @author mamet
 *
 */
@Entity
@Table(name="anime_info")
@Data
public class AnimeInfo {
	
	  @Id
	  @Column(name = "id")
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private Long id;

	  @Column(name = "name")
	  private String name;
	  
	  @Column(name = "genre")
	  private Long genre;
	  
	  @Column(name = "start_date")
	  private LocalDate startDate;
}
