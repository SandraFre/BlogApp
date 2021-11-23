package com.example.blogapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.management.monitor.StringMonitor;

@Controller
@RequestMapping("/blog")
public class ArticleController {

//    private final ArticleService articleService;
//
//    public ArticleController(ArticleService articleService) {
//        this.articleService = articleService;
//    }

//    @GetMapping
//    public String openBlogPage(Model model, @PageableDefault(size = 5) @SortDefault(sort = {"title"}, caseSensitive = false) Pageable pageable){
//        model.addAttribute("pageOfArticles", articleService.getArticles(pageable));
//        return "articles";
//    }

    @GetMapping
    public String openBlogPage(){
        return "articles";
    }
}
