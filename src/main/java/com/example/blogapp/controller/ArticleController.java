package com.example.blogapp.controller;

import com.example.blogapp.data.Article;
import com.example.blogapp.data.Comment;
import com.example.blogapp.service.ArticleService;
import com.example.blogapp.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.security.Principal;
import java.util.UUID;

@Slf4j
@Controller
public class ArticleController {

    private final ArticleService articleService;
    private final CommentService commentService;

    public ArticleController(ArticleService articleService, CommentService commentService) {
        this.articleService = articleService;
        this.commentService = commentService;
    }

    @GetMapping("/public/blog")
    public String openBlogPage(Model model, @PageableDefault(size = 5) @SortDefault(sort = {"date"}, direction = Sort.Direction.DESC, caseSensitive = false) Pageable pageable,
                               Authentication authentication, Principal principal) {
        model.addAttribute("pageOfArticles", articleService.getArticles(pageable));
        return "articles";
    }

    @GetMapping("/public/blog/article")
    public String openArticle(@RequestParam UUID id, Model model, Authentication authentication, Principal principal) {
        model.addAttribute("article", articleService.getArticle(id));
        model.addAttribute("allComments", commentService.getAllComments());
        model.addAttribute("newComment", new Comment());
        return "article";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/private/blog/article/create")
    public String loadArticleForm(Model model) {
        model.addAttribute("article", new Article());
        return "article-form";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/private/blog/article/create")
    public String createArticle(@Valid Article article, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.warn("Cannot create article {}, errors {}", article, bindingResult);
            return "article-form";
        }
        articleService.saveArticle(article);

        return "redirect:/public/blog";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/private/blog/article/update")
    public String loadUpdateForm(@RequestParam UUID id, Model model) {
        model.addAttribute("article", articleService.getArticle(id));
        return "article-form";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/private/blog/article/update")
    public String updateArticle(@Valid Article article, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            articleService.saveArticle(article);
            return "article-form";
        }
        return "redirect:/public/blog";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/private/blog/article/{id}/delete")
    public String deleteArticle(@PathVariable UUID id) {
            articleService.deleteArticle(id);

        return "redirect:/public/blog";
    }


    @PreAuthorize("hasRole('USER')")
    @PostMapping("/private/blog/article")
    public String createComment(@Valid Comment comment, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.warn("Cannot create comment {}, errors {}", comment, bindingResult);
            return "article";
        }
        commentService.saveComment(comment);
        return "redirect:/public/blog/article";
    }
}
