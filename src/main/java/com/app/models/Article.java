package com.app.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@Entity
@Table(name="articles")
@Data
public class Article implements Serializable{
	private static final long serialVersionUID = 1L;
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	
	
	@Id
	@Column(name="article_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long articleId;
	
	@Column(name="articleTitle")
	private String articleTitle;
	
	@Column(name="article_content")
	private String articleContent;
	
	@Column(name="created_at")
	private LocalDateTime createdAt;
	
	@Column(name="article_image_url")
	private String articleImageUrl;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="category_id", referencedColumnName = "category_id", foreignKey = @ForeignKey(name="article_fk"))
	private Category category;
	
	@ManyToOne
	@JoinColumn(name="user_id", referencedColumnName = "user_id", foreignKey = @ForeignKey(name="fk_user_id"))
	private User user;
	
	@JsonManagedReference
	@OneToMany(cascade = CascadeType.ALL, mappedBy="article")
	@LazyCollection(LazyCollectionOption.EXTRA)
	private List<Post> posts;
}
