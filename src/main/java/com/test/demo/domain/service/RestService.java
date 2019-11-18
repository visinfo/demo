package com.test.demo.domain.service;

import com.test.demo.domain.model.Comment;
import com.test.demo.domain.model.Post;
import com.test.demo.domain.model.User;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class RestService {

  private final RestTemplate restTemplate;

  public RestService(RestTemplateBuilder restTemplateBuilder) {
    this.restTemplate = restTemplateBuilder.build();
  }

  public User getUser(String usernamae) {
    String url = "https://jsonplaceholder.typicode.com/users?username="+usernamae;
    return this.restTemplate.getForObject(url, User[].class)[0];
  }
  public List<Post> getPostForUserId(int userId){
    String url ="https://jsonplaceholder.typicode.com/posts?userId="+userId;
    return     Arrays.asList(this.restTemplate.getForObject(url, Post[].class));

  }
  public List<Comment> getCommentsForPostId(int postId){
    String url ="https://jsonplaceholder.typicode.com/comments?postId="+postId;
    return     Arrays.asList(this.restTemplate.getForObject(url, Comment[].class));

  }
}
