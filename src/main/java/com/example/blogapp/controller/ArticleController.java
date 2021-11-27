package com.example.blogapp.controller;

import com.example.blogapp.data.Article;
import com.example.blogapp.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.management.monitor.StringMonitor;
import javax.validation.Valid;
import java.util.UUID;

@Slf4j
@Controller
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/public/blog")
    public String openBlogPage(Model model, @PageableDefault(size = 5) @SortDefault(sort = {"title"}, caseSensitive = false) Pageable pageable){
        model.addAttribute("pageOfArticles", articleService.getArticles(pageable));
        return "articles";
    }

    @GetMapping("/public/blog/article")
    public String openArticle(@RequestParam UUID id, Model model){
        model.addAttribute("article", articleService.getArticle(id));
        return "article";
    }

    @GetMapping("/public/blog/article/create")
    public String loadArticleForm(Model model){
        model.addAttribute("article", new Article());
        return "article-form";
    }

    @PostMapping("/public/blog/article/create")
    public String createArticle(@Valid Article article, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()){
            log.warn("Cannot create article {}, errors {}", article, bindingResult);
            return "article-form";
        }
        articleService.saveArticle(article);
        redirectAttributes.addFlashAttribute("success", "com.blogapp.article.created.successfully");

        return "redirect:/public/blog"        ;
    }

}
