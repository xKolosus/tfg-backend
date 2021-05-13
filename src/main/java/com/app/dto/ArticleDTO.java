package com.app.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class ArticleDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long articleId;
	private String articleTitle;
	private String articleContent;
	private LocalDateTime createdAt;
	private String articleImageUrl;
	private CategoryDTO category;
	private UserNoPasswdDTO user;
	private List<PostDTO> posts;
}
