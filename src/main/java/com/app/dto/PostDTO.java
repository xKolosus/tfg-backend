package com.app.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class PostDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long postId;
	private UserNoPasswdDTO user;
	private LocalDateTime createdAt;
	private String postTitle;
	private String postContent;
	private Integer postLikes;
	private Integer postDislikes;
	private Long articleId;

}
