package com.example.blogapp.service;


import com.example.blogapp.data.Article;
import com.example.blogapp.repository.ArticleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {
    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public Page<Article> getArticles(Pageable pageable){
        return articleRepository.findAll(pageable);
    }

}
