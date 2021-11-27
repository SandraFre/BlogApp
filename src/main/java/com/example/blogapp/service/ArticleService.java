package com.example.blogapp.service;


import com.example.blogapp.data.Article;
import com.example.blogapp.exception.ArticleNotExistExeption;
import com.example.blogapp.repository.ArticleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ArticleService {
    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public Page<Article> getArticles(Pageable pageable){
        return articleRepository.findAll(pageable);
    }

    public Article getArticle(UUID id){
        return articleRepository.findById(id).orElseThrow(()->new ArticleNotExistExeption(id));
    }

}
