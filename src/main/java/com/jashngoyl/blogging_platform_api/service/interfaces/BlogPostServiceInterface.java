package com.jashngoyl.blogging_platform_api.service.interfaces;

import com.jashngoyl.blogging_platform_api.dto.BlogPostRequestDTO;
import com.jashngoyl.blogging_platform_api.dto.BlogPostResponseDTO;

public interface BlogPostServiceInterface {
	
	public BlogPostResponseDTO createBlogPostService(BlogPostRequestDTO blogPostRequestDTO);
	

}
