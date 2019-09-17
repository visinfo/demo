package com.upday.demo.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldIndex;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.springframework.data.elasticsearch.annotations.FieldType.Nested;

@Document(indexName = "news", type = "article")
@Data

@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
public class Article {

    @Id
    @Field(type = FieldType.String, store = true)
    private String id;

    @Field(type= FieldType.String)
    private String header;
    @Field(type= FieldType.String)
    private String description;
    @Field(type= FieldType.String)
    private String content ;

    @Field(type = FieldType.Date,includeInParent = true)
    private Date publishedAt;

    @Field(type = Nested, includeInParent = true)
    private List<Author> authors;

    @Field(type = FieldType.String, index = FieldIndex.not_analyzed)
    private List<String> tags;

}
