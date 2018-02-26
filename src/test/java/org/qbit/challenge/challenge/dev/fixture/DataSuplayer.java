package org.qbit.challenge.challenge.dev.fixture;

import org.qbit.challenge.challenge.dev.dto.PostDto;
import org.qbit.challenge.challenge.dev.model.Post;
import org.qbit.challenge.challenge.dev.model.User;

import java.util.Arrays;
import java.util.List;

public final class DataSuplayer {

    private DataSuplayer() {}

    public static List<User> getUsers() {
        return Arrays.asList(
                new User("user1"),
                new User("user2"),
                new User("user3")
        );
    }


    public static  Post getPost() {
        Post post = new Post();
        post.setId(1L);
        post.setUser(DataSuplayer.getUsers().get(0));
        post.setBody("testBody");

        return post;
    }

    public static PostDto getPsotDto() {

        PostDto postDto = new PostDto();

        postDto.setUserId("testUserId");
        postDto.setBody("testBody");
        postDto.setId(1L);

        return postDto;
    }
}
