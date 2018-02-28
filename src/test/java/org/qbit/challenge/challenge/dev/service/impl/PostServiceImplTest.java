package org.qbit.challenge.challenge.dev.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.qbit.challenge.challenge.dev.dto.PostDto;
import org.qbit.challenge.challenge.dev.fixture.DataSuplayer;
import org.qbit.challenge.challenge.dev.model.Follower;
import org.qbit.challenge.challenge.dev.model.Post;
import org.qbit.challenge.challenge.dev.model.User;
import org.qbit.challenge.challenge.dev.repository.GenericFollowerDAO;
import org.qbit.challenge.challenge.dev.repository.GenericPostDAO;
import org.qbit.challenge.challenge.dev.repository.GenericUserDAO;

import java.util.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PostServiceImplTest {

    @Mock
    GenericUserDAO userDAO;

    @Mock
    GenericPostDAO postDAO;

    @Mock
    GenericFollowerDAO followerDAO;

    @InjectMocks
    PostServiceImpl postServiceImpl;


    @Test(expected = RuntimeException.class)
    public void whenGivenUserNotExits_thenThrowRuntimeException() throws Exception {

        String userId = "testUserId";

        postServiceImpl.findPostsByUserId(userId);
    }

    @Test
    public void whenUserPostsExits_thenReturnPosts() throws Exception {

        User expectedUser = new User("testUserId");
        when(userDAO.findOne(expectedUser.getId())).thenReturn(expectedUser);

        List<Post> expectedPosts = DataSuplayer.getPosts(expectedUser, 3);
        when(postDAO.findByUserOrderByIdDesc(expectedUser)).thenReturn(expectedPosts);

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
        User ob1 = new User("observed1");

        Follower expectedFollower = new Follower();
        expectedFollower.setOwner(expectedOwner);
        expectedFollower.setObserved(Arrays.asList(ob1));

        Post p1 = new Post();
        Post p2 = new Post();
        Post p3 = new Post();

        p1.setId(1l);
        p2.setId(2l);
        p3.setId(3l);

        List<Post> expectedPosts = Arrays.asList(p1,p2,p3);

        when(userDAO.findOne(expectedOwner.getId())).thenReturn(expectedOwner);
        when(followerDAO.findByOwner(expectedOwner)).thenReturn(expectedFollower);
        when(postDAO.findByUser(ob1)).thenReturn(expectedPosts);

        List<PostDto> actual = postServiceImpl.findFollowedPostsByOwnerId(expectedOwner.getId());

        assertThat(actual.get(0).getId(),equalTo(p3.getId()));
        assertThat(actual.get(1).getId(),equalTo(p2.getId()));
        assertThat(actual.get(2).getId(),equalTo(p1.getId()));
    }
}