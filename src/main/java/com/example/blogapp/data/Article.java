package com.example.blogapp.data;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Setter
@Getter
@Entity
@Table(name = "articles")
public class Article {
    @Id
    @GeneratedValue
    @Column(columnDefinition = "VARCHAR(36)", updatable = false)
    @Type(type = "uuid-char")
    private UUID id;

    @NotBlank
    private String title;

    @NotBlank
    private String category;

    @NotBlank
    private String date;

    @NotBlank
    @Column (columnDefinition = "text")
    private String content;
}
