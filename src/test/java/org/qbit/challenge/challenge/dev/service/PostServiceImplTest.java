package org.qbit.challenge.challenge.dev.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.qbit.challenge.challenge.dev.dto.PostDto;
import org.qbit.challenge.challenge.dev.fixture.DataSuplayer;
import org.qbit.challenge.challenge.dev.model.Post;
import org.qbit.challenge.challenge.dev.model.User;
import org.qbit.challenge.challenge.dev.repository.GenericPostDAO;
import org.qbit.challenge.challenge.dev.repository.GenericUserDAO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        when(postDAO.findByUser(expectedUser)).thenReturn(expectedPosts);

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
}