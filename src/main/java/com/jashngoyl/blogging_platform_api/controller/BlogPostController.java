package com.jashngoyl.blogging_platform_api.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jashngoyl.blogging_platform_api.dto.BlogPostRequestDTO;
import com.jashngoyl.blogging_platform_api.dto.BlogPostResponseDTO;
import com.jashngoyl.blogging_platform_api.pojo.BlogPostRequest;
import com.jashngoyl.blogging_platform_api.pojo.BlogPostResponse;
import com.jashngoyl.blogging_platform_api.service.impl.BlogPostServiceImpl;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/posts")
@Slf4j
public class BlogPostController {

	private ModelMapper modelMapper;

	private BlogPostServiceImpl blogPostServiceImpl;

	public BlogPostController(ModelMapper modelMapper, BlogPostServiceImpl blogPostServiceInterface) {
		this.modelMapper = modelMapper;
		this.blogPostServiceImpl = blogPostServiceInterface;
	}

	@PostMapping
	public ResponseEntity<BlogPostResponse> createBlogPost(@Valid @RequestBody BlogPostRequest blogPostRequest) {
		log.info("Received request to create a blog post: " + blogPostRequest);

		BlogPostRequestDTO blogPostRequestDTO = modelMapper.map(blogPostRequest, BlogPostRequestDTO.class);
		log.info("Mapped the request from POJO layer to DTO layer successfully: " + blogPostRequestDTO);

		BlogPostResponseDTO blogPostResponseDTO = blogPostServiceImpl.createBlogPostService(blogPostRequestDTO);
		log.info("Recived the response in Controller from Service: " + blogPostResponseDTO);

		BlogPostResponse blogPostResponse = modelMapper.map(blogPostResponseDTO, BlogPostResponse.class);
		log.info("Mapped the response from DTO layer to POJO layer successfully: " + blogPostResponse);

		ResponseEntity<BlogPostResponse> responseEntity = new ResponseEntity<>(blogPostResponse, HttpStatus.CREATED);
		log.info("Response Entity: " + responseEntity.getBody());

		return responseEntity;

	}

	@PutMapping("/{id}")
	public ResponseEntity<BlogPostResponse> updateBlogPost(@PathVariable Long id,
			@Valid @RequestBody BlogPostRequest blogPostRequest) {

		log.info("Received request to update a blog post: " + blogPostRequest);

		BlogPostRequestDTO blogPostRequestDTO = modelMapper.map(blogPostRequest, BlogPostRequestDTO.class);
		log.info("Mapped the request from POJO layer to DTO layer successfully: " + blogPostRequestDTO);

		BlogPostResponseDTO blogPostResponseDTO = blogPostServiceImpl.updateBlogPostService(id, blogPostRequestDTO);
		log.info("Recived the response in Controller from Service: " + blogPostResponseDTO);

		BlogPostResponse blogPostResponse = modelMapper.map(blogPostResponseDTO, BlogPostResponse.class);
		log.info("Mapped the response from DTO layer to POJO layer successfully: " + blogPostResponse);

		ResponseEntity<BlogPostResponse> responseEntity = new ResponseEntity<>(blogPostResponse, HttpStatus.OK);
		log.info("Response Entity: " + responseEntity.getBody());

		return responseEntity;

	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteBlogPost(@PathVariable Long id) {
		
		blogPostServiceImpl.deleteBlogPostService(id);

		ResponseEntity<Object> responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
		log.info("Response Entity: " + responseEntity.getBody());

		return responseEntity;

	} 
	
	@GetMapping("/{id}")
	public ResponseEntity<BlogPostResponse> getBlogPost(@PathVariable Long id) {

		BlogPostResponseDTO blogPostResponseDTO = blogPostServiceImpl.getBlogPostService(id);
		log.info("Recived the response in Controller from Service: " + blogPostResponseDTO);

		BlogPostResponse blogPostResponse = modelMapper.map(blogPostResponseDTO, BlogPostResponse.class);
		log.info("Mapped the response from DTO layer to POJO layer successfully: " + blogPostResponse);

		ResponseEntity<BlogPostResponse> responseEntity = new ResponseEntity<>(blogPostResponse, HttpStatus.OK);
		log.info("Response Entity: " + responseEntity.getBody());

		return responseEntity;

	}
	
	@GetMapping()
	public ResponseEntity<List<BlogPostResponse>> getAllBlogPost(
			@RequestParam(required = false) String term) {

		List<BlogPostResponseDTO> blogPostResponseDTOList = blogPostServiceImpl.getAllBlogPostService(term);
		log.info("Recived the response in Controller from Service: " + blogPostResponseDTOList);
		
		List<BlogPostResponse> responseList = new ArrayList<>();
		for(BlogPostResponseDTO bp: blogPostResponseDTOList) {
			responseList.add(modelMapper.map(bp, BlogPostResponse.class));
		}
		ResponseEntity<List<BlogPostResponse>> responseEntity = new ResponseEntity<>(responseList, HttpStatus.OK);
		log.info("Response Entity: " + responseEntity.getBody());

		return responseEntity;

	}

}
