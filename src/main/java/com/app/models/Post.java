package com.app.models;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Entity
@Table(name="posts")
@Data
public class Post implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="post_id")
	private Long postId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id", referencedColumnName = "user_id", foreignKey = @ForeignKey(name="post_fk"))
	private User user;
	
	@Column(name="created_at")
	private LocalDateTime createdAt;
	
	@Column(name="post_title")
	private String postTitle;
	
	@Column(name="post_content")
	private String postContent;
	
	@Column(name="post_likes")
	private Integer postLikes;
	
	@Column(name="post_dislikes")
	private Integer postDislikes;
	
	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="article_id", referencedColumnName = "article_id", foreignKey = @ForeignKey(name="post_fk_1"))
	private Article article;
	
	
}
