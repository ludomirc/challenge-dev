package org.qbit.challenge.challenge.dev.mapper;

import org.junit.Test;
import org.qbit.challenge.challenge.dev.dto.PostDto;
import org.qbit.challenge.challenge.dev.fixture.DataSuplayer;
import org.qbit.challenge.challenge.dev.model.Post;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class PostMapperTest {

    @Test(expected = NullPointerException.class)
    public void whenPostIsNull_thenThrowException() throws Exception {

        PostMapper.PostToDto(null);
    }

    @Test
    public void whenPostIsGiven_thenMapToDto() throws Exception {
        Post expected = DataSuplayer.getPost();

        PostDto actual = PostMapper.PostToDto(expected);

        assertThat(expected.getId(), is(equalTo(actual.getId())));
        assertThat(expected.getUser().getId(), is(equalTo(actual.getUserId())));
        assertThat(expected.getBody(), is(equalTo(actual.getBody())));

    }

    @Test(expected = NullPointerException.class)
    public void whenPostDtoIsNull_thenThrowException() throws Exception {

        PostMapper.DtoToPost(null);
    }

    @Test
    public void whenPostDtoIsGiven_thenMapToDto() throws Exception {

        PostDto expected = DataSuplayer.getPsotDto();

        Post actual = PostMapper.DtoToPost(expected);

        assertThat(expected.getId(), is(equalTo(actual.getId())));
        assertThat(expected.getUserId(), is(equalTo(actual.getUser().getId())));
        assertThat(expected.getBody(), is(equalTo(actual.getBody())));

    }

}