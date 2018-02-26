package org.qbit.challenge.challenge.dev.web;

import org.qbit.challenge.challenge.dev.model.Post;
import org.qbit.challenge.challenge.dev.model.User;
import org.qbit.challenge.challenge.dev.repository.GenericPostDAO;
import org.qbit.challenge.challenge.dev.repository.GenericUserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/v001")
public class PostResource {

    @Autowired
    GenericUserDAO userDAO;

    @Autowired
    GenericPostDAO postDAO;


    @GetMapping("/post/{userId}")
    List<Post> getPosts(@PathVariable String userId){

        System.out.println(String.format("userId: %s",userId));
        List<Post> post  = new  LinkedList();
        Post post1 = new Post();
        post1.setBody("testBody");
        post1.setUser(new User(userId));
        post.add(post1);
        return post;
    }

    @PostMapping("/post/{userId}")
    List<Post> createPost(@PathVariable String userId,@RequestBody Post post){

        System.out.println(String.format("userId: %s post: %s",userId,post));

        return null;
    }

}
