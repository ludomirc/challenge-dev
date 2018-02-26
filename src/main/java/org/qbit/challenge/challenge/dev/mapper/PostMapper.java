package org.qbit.challenge.challenge.dev.mapper;

import org.qbit.challenge.challenge.dev.dto.PostDto;
import org.qbit.challenge.challenge.dev.model.Post;
import org.qbit.challenge.challenge.dev.model.User;

public class PostMapper {

    public static PostDto PostToDto(Post post){

        if(post == null) throw new NullPointerException("post can not be null");

        PostDto dto = new PostDto();

        dto.setId(post.getId());
        dto.setBody(post.getBody());

        if(post.getUser()!= null) dto.setUserId(post.getUser().getId());

        return dto;
    }

    public static Post DtoToPost(PostDto dto){

        if(dto == null) throw new NullPointerException("postDto can not be null");

        Post post = new Post();

        post.setBody(dto.getBody());

        User user = new User(dto.getUserId());
        post.setUser(user);

        if(dto.getId() != null) post.setId(dto.getId());

        return post;
    }
}
