package com.jashngoyl.blogging_platform_api.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BlogPostRequestDTO {

	private String title;

	private String content;

	private String category;

	private List<String> tags;
}
