package com.jashngoyl.blogging_platform_api.service.impl;

import java.time.LocalDateTime;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.jashngoyl.blogging_platform_api.dto.BlogPostRequestDTO;
import com.jashngoyl.blogging_platform_api.dto.BlogPostResponseDTO;
import com.jashngoyl.blogging_platform_api.entity.BlogPost;
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

}
