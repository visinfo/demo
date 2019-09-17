package com.upday.demo.controller.resources;

import com.upday.demo.domain.model.Author;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ArticleResource {

    private String header;

    private String description;

    private String content ;

    private List<Author> authors;

    private List<String> tags;

}
