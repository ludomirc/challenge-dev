package org.qbit.challenge.challenge.dev.fixture;

import javafx.geometry.Pos;
import org.qbit.challenge.challenge.dev.dto.PostDto;
import org.qbit.challenge.challenge.dev.model.Post;
import org.qbit.challenge.challenge.dev.model.User;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class DataSuplayer {

    private DataSuplayer() {
    }

    public static List<User> getUsers() {
        return Arrays.asList(
                new User("user1"),
                new User("user2"),
                new User("user3")
        );
    }


    public static Post getPost() {
        Post post = new Post();
        post.setId(1L);
        post.setUser(DataSuplayer.getUsers().get(0));
        post.setBody("testBody");

        return post;
    }

    public static List<Post> getPosts(User user, int count) {

        List<Post> posts = Stream.iterate(1L, i -> i)
                .limit(count)
                .map(i -> {
                    Post p = new Post();
                    p.setId(i);
                    p.setUser(user);
                    p.setBody("testBody" + i);
                    return p;
                }).collect(Collectors.toList());

        return posts;
    }


    public static PostDto getPsotDto() {

        PostDto postDto = new PostDto();

        postDto.setUserId("testUserId");
        postDto.setBody("testBody");
        postDto.setId(1L);

        return postDto;
    }
}
