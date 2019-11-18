package com.test.demo;


import com.test.demo.domain.model.Comment;
import com.test.demo.domain.model.Post;
import com.test.demo.domain.model.User;
import com.test.demo.domain.service.RestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class ServiceTest {

  @Autowired
  private RestService restService;


  @Test
  public void testAPI() {
    User user=restService.getUser("Samantha");
    System.out.println(user.getEmail());
    List<Post>  posts = restService.getPostForUserId(user.getId());
    for(Post post :posts){

      System.out.println(post);
      List<Comment>  comments = restService.getCommentsForPostId(post.getId());

      for (Comment comment: comments
           ) {
        System.out.println(comment);

      }


    }


  }


}
