package com.jashngoyl.blogging_platform_api.pojo;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BlogPostRequest {

	@NotBlank(message = "Title must not be blank")
	@Size(max = 100, message = "Title must not exceed 100 characters")
	private String title;

	@NotBlank(message = "Content must not be blank")
	private String content;

	@NotBlank(message = "Category must not be blank")
	private String category;

	@NotNull(message = "Tag list must not be null")
	@Size(min = 1, message = "Tag list must contain atleast one tag")
	private List<@NotBlank(message = "Any tag must not be blank") String> tags;
}
