package com.app.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.app.models.User;
import com.app.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dto.ArticleDTO;
import com.app.dto.PostDTO;
import com.app.exceptions.NotFoundException;
import com.app.models.Article;
import com.app.models.Post;
import com.app.repository.ArticleRepository;
import com.app.repository.PostRepository;

@Service
public class ArticleService {
	
	@Autowired
	private ArticleRepository articleRepository;
	
	@Autowired
	private PostRepository postRepository;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public List<ArticleDTO> getAllArticles(){
		List<ArticleDTO> articles = new ArrayList<>();
		articleRepository.findAll().stream().forEach((article) -> articles.add(modelMapper.map(article, ArticleDTO.class)));
		return articles;
	}
	
	public ArticleDTO getArticleById(final Long id) {
		Optional<Article> art = articleRepository.findById(id);
		if(!art.isPresent()) {
			throw new NotFoundException();
		}
		ArticleDTO article = modelMapper.map(art.get(),ArticleDTO.class);				
		return article;
	}
	
	public ArticleDTO createArticle(final ArticleDTO articleDTO) {
		if(articleDTO.getArticleImageUrl() == null) {
			articleDTO.setArticleImageUrl("https://i.ibb.co/VqvJz9M/default.jpg");
		}
		Article article = modelMapper.map(articleDTO, Article.class);
		articleRepository.save(article);
		
		return modelMapper.map(article, ArticleDTO.class);
	}
	
	public ArticleDTO editArticle(final ArticleDTO articleDTO, final Long id) {
		Optional<Article> article = articleRepository.findById(id);
		if(!article.isPresent()) {
			throw new NotFoundException();
		}
		
		Article articleGet = article.get();
		if(articleDTO.getArticleContent() != null) {
			articleGet.setArticleContent(articleDTO.getArticleContent());
		}
		if(articleDTO.getArticleTitle() != null) {
			articleGet.setArticleTitle(articleDTO.getArticleTitle());
		}
		if(articleDTO.getArticleImageUrl() != null) {
			articleGet.setArticleImageUrl(articleDTO.getArticleImageUrl());
		}
		
		articleRepository.save(articleGet);
		return modelMapper.map(articleGet, ArticleDTO.class);
	}
	
	public void deleteArticle(final Long id) {
		articleRepository.deleteById(id);
	}
	
	public List<PostDTO> getPostsById(final Long id){
		ArticleDTO articleDTO = getArticleById(id);
		if(articleDTO == null) {
			throw new NotFoundException();
		}
		List<PostDTO> posts = new ArrayList<>();
		articleDTO.getPosts().stream().forEach((post)->posts.add(modelMapper.map(post, PostDTO.class)));
		
		return posts;
		
	}
	
	public PostDTO addPostToAnArticle(final PostDTO post, final Long id) {
		Optional<Article> article = articleRepository.findById(id);
		if(!article.isPresent()) {
			throw new NotFoundException("Article doesn't exist!");
		}
		post.setCreatedAt(LocalDateTime.now());
		post.setArticleId(id);
		Post postEnt = postRepository.save(modelMapper.map(post, Post.class));
		return modelMapper.map(postEnt, PostDTO.class);
	}

	public void deleteByUserId(final Long userId){
		Optional<User> user = userRepository.findById(userId);
		if(!user.isPresent()){
			throw new NotFoundException("User not found!");
		}
		List<Article> articlesToDelete = articleRepository.findByUser(user.get());
		for(Article art : articlesToDelete){
			articleRepository.delete(art);
		}
	}
	
	public List<ArticleDTO> getAllArticlesByCategoryId(final Long categoryId){
		List<ArticleDTO> articles = new ArrayList<>();
		articleRepository.findAll().stream().forEach((article) -> {
			if(article.getCategory().getCategoryId() == categoryId) {
				articles.add(modelMapper.map(article, ArticleDTO.class));
			}	
		});
		return articles;
	}

}
