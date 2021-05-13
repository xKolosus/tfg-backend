package com.app.controller;

import java.util.List;

import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.PostDTO;
import com.app.service.PostService;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/posts")
public class PostController {
	
	@Autowired
	private PostService postService;
	
	@GetMapping
	@Produces("application/json")
	public @ResponseBody List<PostDTO> getAllPosts(){
		return postService.getAllPosts();
	}
	
	@GetMapping("{id}")
	@Produces("application/json")
	public @ResponseBody PostDTO getPostById(@PathVariable final Long id) {
		return postService.getPostById(id);
	}
}
