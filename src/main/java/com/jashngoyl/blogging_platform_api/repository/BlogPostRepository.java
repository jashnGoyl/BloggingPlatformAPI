package com.jashngoyl.blogging_platform_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jashngoyl.blogging_platform_api.entity.BlogPost;

@Repository
public interface BlogPostRepository extends JpaRepository<BlogPost, Long> {
	
	@Query("SELECT DISTINCT p FROM BlogPost p LEFT JOIN p.tags t " +
		       "WHERE LOWER(p.title) LIKE LOWER(CONCAT('%', :term, '%')) " +
		       "OR LOWER(p.content) LIKE LOWER(CONCAT('%', :term, '%')) " +
		       "OR LOWER(p.category) LIKE LOWER(CONCAT('%', :term, '%')) " +
		       "OR LOWER(t) LIKE LOWER(CONCAT('%', :term, '%'))")
		List<BlogPost> searchPostsByTerm(@Param("term") String term);

	
}
