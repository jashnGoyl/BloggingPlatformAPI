package com.jashngoyl.blogging_platform_api.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.jashngoyl.blogging_platform_api.dto.BlogPostRequestDTO;
import com.jashngoyl.blogging_platform_api.dto.BlogPostResponseDTO;
import com.jashngoyl.blogging_platform_api.entity.BlogPost;
import com.jashngoyl.blogging_platform_api.exception.ResourceNotFoundException;
import com.jashngoyl.blogging_platform_api.repository.BlogPostRepository;
import com.jashngoyl.blogging_platform_api.service.interfaces.BlogPostServiceInterface;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BlogPostServiceImpl implements BlogPostServiceInterface {

	private BlogPostRepository blogPostRepository;

	private ModelMapper modelMapper;

	public BlogPostServiceImpl(BlogPostRepository blogPostRepository, ModelMapper modelMapper) {
		this.blogPostRepository = blogPostRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public BlogPostResponseDTO createBlogPostService(BlogPostRequestDTO blogPostRequestDTO) {

		log.info("Recieved RequestDTO in service: " + blogPostRequestDTO);

		BlogPost blogPost = BlogPost.builder().title(blogPostRequestDTO.getTitle())
				.content(blogPostRequestDTO.getContent()).category(blogPostRequestDTO.getCategory())
				.tags(blogPostRequestDTO.getTags()).createdAt(LocalDateTime.now()).updatedAt(LocalDateTime.now())
				.build();

		BlogPostResponseDTO blogPostResponseDTO = modelMapper.map(blogPostRepository.save(blogPost),
				BlogPostResponseDTO.class);

		log.info("Blog Post Response DTO: " + blogPostResponseDTO);

		return blogPostResponseDTO;

	}

	@Override
	public BlogPostResponseDTO updateBlogPostService(Long id, BlogPostRequestDTO blogPostRequestDTO) {

		log.info("Recieved RequestDTO in service: " + blogPostRequestDTO);

		BlogPost existingPost = blogPostRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Blog Post not found with ID: " + id));

		existingPost.setTitle(blogPostRequestDTO.getTitle());
		existingPost.setCategory(blogPostRequestDTO.getCategory());
		existingPost.setContent(blogPostRequestDTO.getContent());
		existingPost.setTags(blogPostRequestDTO.getTags());
		existingPost.setUpdatedAt(LocalDateTime.now());

		BlogPostResponseDTO blogPostResponseDTO = modelMapper.map(blogPostRepository.save(existingPost),
				BlogPostResponseDTO.class);

		log.info("Blog Post Response DTO: " + blogPostResponseDTO);

		return blogPostResponseDTO;
	}

	@Override
	public void deleteBlogPostService(Long id) {

		BlogPost existingPost = blogPostRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Blog Post not found with ID: " + id));

		blogPostRepository.delete(existingPost);
		log.info("Blog post deleted!!!");
	}

	@Override
	public BlogPostResponseDTO getBlogPostService(Long id) {
		BlogPost existingPost = blogPostRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Blog Post not found with ID: " + id));

		BlogPostResponseDTO blogPostResponseDTO = modelMapper.map(existingPost, BlogPostResponseDTO.class);

		log.info("Blog Post Response DTO: " + blogPostResponseDTO);

		return blogPostResponseDTO;

	}

	@Override
	public List<BlogPostResponseDTO> getAllBlogPostService(String term) {
		List<BlogPost> allBlogPosts = null;

		if (term == null || term.trim().isEmpty()) {
			allBlogPosts = blogPostRepository.findAll();
		} else {
			allBlogPosts = blogPostRepository.searchPostsByTerm(term);
		}

		if (allBlogPosts.isEmpty()) {
			log.info("No post found");
		}
		log.info("List of all entities: " + allBlogPosts.toString());

		List<BlogPostResponseDTO> responseList = new ArrayList<>();
		for (BlogPost bp : allBlogPosts) {
			responseList.add(modelMapper.map(bp, BlogPostResponseDTO.class));
		}
		log.info("response list: " + responseList.toString());
		return responseList;
	}

}
