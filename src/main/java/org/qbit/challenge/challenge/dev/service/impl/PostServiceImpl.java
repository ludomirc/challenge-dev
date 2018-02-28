package org.qbit.challenge.challenge.dev.service.impl;

import org.qbit.challenge.challenge.dev.dto.PostDto;
import org.qbit.challenge.challenge.dev.mapper.PostMapper;
import org.qbit.challenge.challenge.dev.model.Follower;
import org.qbit.challenge.challenge.dev.model.Post;
import org.qbit.challenge.challenge.dev.model.User;
import org.qbit.challenge.challenge.dev.repository.GenericFollowerDAO;
import org.qbit.challenge.challenge.dev.repository.GenericPostDAO;
import org.qbit.challenge.challenge.dev.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl extends BaseService implements PostService {

    @Autowired
    GenericPostDAO postDAO;

    @Autowired
    GenericFollowerDAO followerDAO;

    @Override
    @Transactional(readOnly = true)
    public List<PostDto> findPostsByUserId(String userId) {

        User user = getUser(userId);

        List<Post> posts = postDAO.findByUserOrderByIdDesc(user);

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

    @Transactional(readOnly = true)
    @Override
    public List<PostDto> findFollowedPostsByOwnerId(String ownerId) {

        User user = getUser(ownerId);

        Follower follower =  followerDAO.findByOwner(user);
        List<User> observed =  follower.getObserved();

        List<Post> posts = new LinkedList<>();
        observed.forEach(o -> posts.addAll(postDAO.findByUser(o)));

        Comparator<Post> postOrderByDesc = (p1, p2) -> {

            long p1Id = p1.getId();
            long p2Id = p2.getId();

            return p2Id < p1Id ? -1 : p2Id == p1Id ? 0 : 1;
        };

        List<PostDto> postDtos = posts.stream().sorted(postOrderByDesc)
                .map(p-> PostMapper.PostToDto(p)).collect(Collectors.toList());

        return postDtos;
    }
}
