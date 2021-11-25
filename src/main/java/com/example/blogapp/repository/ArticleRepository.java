package com.example.blogapp.repository;

import com.example.blogapp.data.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

public interface ArticleRepository extends JpaRepository<Article, UUID> {

}
