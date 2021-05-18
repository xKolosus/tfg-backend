package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.models.Article;
import com.app.models.Category;
import com.app.models.User;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long>{
    List<Article> findByUser(final User user);
     
    List<Article> findByCategory(final Category category);
}
