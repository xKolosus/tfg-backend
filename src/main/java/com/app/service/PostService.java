package com.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.app.models.User;
import com.app.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dto.PostDTO;
import com.app.exceptions.NotFoundException;
import com.app.models.Post;
import com.app.repository.PostRepository;

@Service
public class PostService {
	@Autowired
	private PostRepository postRepository;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public List<PostDTO> getAllPosts(){
		List<PostDTO> articles = new ArrayList<>();
		postRepository.findAll().stream().forEach((post) -> articles.add(modelMapper.map(post, PostDTO.class)));
		return articles;
	}
	
	public PostDTO getPostById(final Long id) {
		Optional<Post> postOpt = postRepository.findById(id);
		if(!postOpt.isPresent()) {
			throw new NotFoundException("Post not found");
		}
		PostDTO post = modelMapper.map(postOpt.get(), PostDTO.class);
		return post;
	}
	
	public PostDTO editPost(final PostDTO postDTO, final Long id) {
		Optional<Post> post = postRepository.findById(id);
		if(!post.isPresent()) {
			return null;
		}
		postDTO.setPostId(id);
		
		postRepository.save(modelMapper.map(postDTO,Post.class));
		
		return postDTO;
	}
	
	public void deletePost(final Long id) {
		postRepository.deleteById(id);
	}

	public void deleteByUserId(final Long userId){
		Optional<User> user = userRepository.findById(userId);
		if(!user.isPresent()){
			throw new NotFoundException("User not found!");
		}
		List<Post> posts = postRepository.findPostByUser(user.get());
		for(Post post : posts){
			postRepository.delete(post);
		}
	}
}
