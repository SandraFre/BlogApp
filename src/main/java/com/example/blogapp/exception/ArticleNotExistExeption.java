package com.example.blogapp.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class ArticleNotExistExeption extends RuntimeException{
    private final UUID articleId;
}
