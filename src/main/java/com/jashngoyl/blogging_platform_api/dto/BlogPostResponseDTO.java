package com.jashngoyl.blogging_platform_api.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BlogPostResponseDTO {
	
	private Long id;
	private String title;
	private String content;
	private String category;
	private List<String> tags;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	
}
