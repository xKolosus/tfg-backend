package com.app.repository;

import com.app.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.models.Article;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long>{
    List<Article> findByUser(final User user);
}
