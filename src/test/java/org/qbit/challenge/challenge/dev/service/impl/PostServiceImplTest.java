package org.qbit.challenge.challenge.dev.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.qbit.challenge.challenge.dev.dto.PostDto;
import org.qbit.challenge.challenge.dev.fixture.DataSuplayer;
import org.qbit.challenge.challenge.dev.model.Follower;
import org.qbit.challenge.challenge.dev.model.Post;
import org.qbit.challenge.challenge.dev.model.User;
import org.qbit.challenge.challenge.dev.repository.GenericFollowerDAO;
import org.qbit.challenge.challenge.dev.repository.GenericPostDAO;
import org.qbit.challenge.challenge.dev.repository.GenericUserDAO;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PostServiceImplTest {

    @Mock
    GenericUserDAO userDAO;

    @Mock
    GenericPostDAO postDAO;

    @Mock
    GenericFollowerDAO followerDAO;

    @InjectMocks
    PostServiceImpl postServiceImpl;


   /* @Test(expected = RuntimeException.class)
    public void whenGivenUserNotExits_thenThrowRuntimeException() throws Exception {

        String userId = "testUserId";

        postServiceImpl.findPostsByUserId(userId);
    }*/

    @Test
    public void whenUserPostsExits_thenReturnPosts() throws Exception {

        User expectedUser = new User("testUserId");
        Optional<User> expectedUserOptional = Optional.of(expectedUser);
        when(userDAO.findById(expectedUser.getId())).thenReturn(expectedUserOptional);

        List<Post> expectedPosts = DataSuplayer.getPosts(expectedUser, 3);
        when(postDAO.findByUserOrderByIdDesc(expectedUser)).thenReturn(expectedPosts.stream());

        List<PostDto> actual = postServiceImpl.findPostsByUserId(expectedUser.getId());

        assertThat(actual.size(), is(equalTo(actual.size())));

        Map<Long,PostDto> actualDtoMap = new HashMap<>();

        actual.forEach(dto -> actualDtoMap.put(dto.getId(),dto));

        expectedPosts.forEach(ePost -> {

            PostDto actualDto =  actualDtoMap.get(ePost.getId());
            assertThat(actualDto.getUserId(), is(equalTo(ePost.getUser().getId())));
            assertThat(actualDto.getBody(),is(equalTo(ePost.getBody())));
        });
    }

    @Test
    public void whenCallFindFollowedPostsByUserId_thenGetSortedDescPost(){

        User expectedOwner = new User("testOwnerId");
        Optional<User> expectedOwnerOptional = Optional.of(expectedOwner);
        User ob1 = new User("observed1");

        Follower expectedFollower = new Follower();
        expectedFollower.setOwner(expectedOwner);
        expectedFollower.setObservedUsers(Arrays.asList(ob1));

        Optional<Follower> expectedFollowerOptional = Optional.of(expectedFollower);

        Post p1 = new Post();
        Post p2 = new Post();
        Post p3 = new Post();

        p1.setId(1l);
        p2.setId(2l);
        p3.setId(3l);

        List<Post> expectedPosts = Arrays.asList(p1,p2,p3);

        when(userDAO.findById(expectedOwner.getId())).thenReturn(expectedOwnerOptional);
        when(followerDAO.findByOwner(expectedOwner)).thenReturn(expectedFollowerOptional);
       // when(postDAO.findByUser(ob1)).thenReturn(expectedFollowerOptional);

        List<PostDto> actual = postServiceImpl.findFollowedPostsByOwnerId(expectedOwner.getId());

        assertThat(actual.get(0).getId(),equalTo(p3.getId()));
        assertThat(actual.get(1).getId(),equalTo(p2.getId()));
        assertThat(actual.get(2).getId(),equalTo(p1.getId()));
    }
}