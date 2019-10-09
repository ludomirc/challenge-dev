package org.qbit.challenge.challenge.dev.web;

import org.qbit.challenge.challenge.dev.dto.PostDto;
import org.qbit.challenge.challenge.dev.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@RestController
@RequestMapping("/v001")
public class PostResource {

    @Autowired
    PostService postService;

    @GetMapping("/{userId}/posts")
    ResponseEntity<?> getPosts(@Valid  @Size(min = 1, max = 50) @PathVariable String userId){

        List<PostDto> posts = postService.findPostsByUserId(userId);

        return new ResponseEntity<> (posts, HttpStatus.OK);
    }

    @PostMapping("/{userId}/post")
    ResponseEntity<?> createPost(@Valid  @Size(min = 1, max = 50) @PathVariable String userId, @Valid @RequestBody PostDto post){

        post.setUserId(userId);
        postService.crate(post);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{userId}/followed")
    ResponseEntity<?> getFollowedPosts(@Valid  @Size(min = 1, max = 50) @PathVariable String userId){

        List<PostDto> posts = postService.findFollowedPostsByOwnerId(userId);

        return new ResponseEntity<> (posts, HttpStatus.OK);
    }
}
