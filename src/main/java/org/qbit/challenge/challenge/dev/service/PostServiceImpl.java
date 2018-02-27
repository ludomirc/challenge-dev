package org.qbit.challenge.challenge.dev.service;

import org.qbit.challenge.challenge.dev.dto.PostDto;
import org.qbit.challenge.challenge.dev.mapper.PostMapper;
import org.qbit.challenge.challenge.dev.model.Post;
import org.qbit.challenge.challenge.dev.model.User;
import org.qbit.challenge.challenge.dev.repository.GenericPostDAO;
import org.qbit.challenge.challenge.dev.repository.GenericUserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    GenericPostDAO postDAO;

    @Autowired
    GenericUserDAO userDAO;

    @Override
    @Transactional(readOnly = true)
    public List<PostDto> findPostsByUserId(String userId) {

        User user = userDAO.findOne(userId);
        if (user == null) throw new RuntimeException(String.format("user %s not fund", userId));

        List<Post> posts = postDAO.findByUser(user);

        List<PostDto> postDtos = posts.stream()
                .map(p -> PostMapper.PostToDto(p)).collect(Collectors.toList());

        return postDtos;
    }

    @Override
    @Transactional
    public void crate(PostDto postDto) {

        User user = userDAO.findOne(postDto.getUserId());
        if (user == null) {

            user = new User(postDto.getUserId());
            user =  userDAO.save(user);
        }

        Post post = PostMapper.DtoToPost(postDto);
        post.setUser(user);

        postDAO.save(post);

    }
}
