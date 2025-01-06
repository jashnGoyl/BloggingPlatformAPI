package com.jashngoyl.blogging_platform_api.service.interfaces;

import java.util.List;

import com.jashngoyl.blogging_platform_api.dto.BlogPostRequestDTO;
import com.jashngoyl.blogging_platform_api.dto.BlogPostResponseDTO;

public interface BlogPostServiceInterface {
	
	public BlogPostResponseDTO createBlogPostService(BlogPostRequestDTO blogPostRequestDTO);
	
	public BlogPostResponseDTO updateBlogPostService(Long id, BlogPostRequestDTO blogPostRequestDTO);
	
	public void deleteBlogPostService(Long id);
	
	public BlogPostResponseDTO getBlogPostService(Long id);
	
	public List<BlogPostResponseDTO>  getAllBlogPostService(String term);
	
}
