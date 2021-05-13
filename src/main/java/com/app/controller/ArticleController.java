package com.app.controller;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.ArticleDTO;
import com.app.dto.PostDTO;
import com.app.exceptions.NotFoundException;
import com.app.service.ArticleService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/articles")
public class ArticleController {

	@Autowired
	private ArticleService articleService;

	@GetMapping
	@Produces("application/json")
	public @ResponseBody List<ArticleDTO> getAllArticles(@QueryParam("categoryId") final Long categoryId){
		List<ArticleDTO> list = new ArrayList<>();
		if(categoryId != null) {
			list = articleService.getAllArticlesByCategoryId(categoryId);
		}
		return articleService.getAllArticles();
	}
	
	@PostMapping("/add")
	@Produces("application/json")
	public @ResponseBody ArticleDTO createArticle(@RequestBody final ArticleDTO articleDTO) {
		ArticleDTO article = articleService.createArticle(articleDTO);
		return article;
	}

	@GetMapping("{id}")
	@Produces("application/json")
	public @ResponseBody ArticleDTO getArticleById(@PathVariable final Long id) {
		ArticleDTO article = articleService.getArticleById(id);
		if(article != null) {
			return article;
		} else {
			throw new NotFoundException();
		}
	}
	
	@PutMapping("{id}")
	@Produces("application/json")
	public @ResponseBody ArticleDTO editArticle(@PathVariable final Long id, @RequestBody final ArticleDTO articleDTO) {
		ArticleDTO article = articleService.editArticle(articleDTO, id);
		return article;
	}
	
	@GetMapping("{id}/posts")
	@Produces("application/json")
	public @ResponseBody List<PostDTO> getArticlePosts(@PathVariable final Long id){
		return articleService.getPostsById(id);
	}
	
	@PostMapping("{id}/posts")
	@Produces("application/json")
	public @ResponseBody PostDTO addPostToAnArtcile(@RequestBody final PostDTO post, @PathVariable final Long id) {
		return articleService.addPostToAnArticle(post, id);
	}
}

