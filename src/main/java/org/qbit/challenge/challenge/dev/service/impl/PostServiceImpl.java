package org.qbit.challenge.challenge.dev.service.impl;

import org.qbit.challenge.challenge.dev.dto.PostDto;
import org.qbit.challenge.challenge.dev.converter.PostMapper;
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
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class PostServiceImpl extends BaseService implements PostService {

    @Autowired
    GenericPostDAO postDAO;

    @Autowired
    GenericFollowerDAO followerDAO;

    @Override
    @Transactional(readOnly = true)
    public List<PostDto> findPostsByUserId(String userId) {

        return postDAO.findByUserOrderByIdDesc(getUser(userId))
                .map(PostMapper::PostToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = false)
    public void crate(PostDto postDto) {
        postDAO.save(getPost(postDto));
    }

    @Transactional(readOnly = false)
    protected Post getPost(PostDto postDto) {

        User user = userDAO.findById(postDto.getUserId())
                .orElseGet(() -> userDAO.save(new User(postDto.getUserId())));

        Post post = PostMapper.DtoToPost(postDto);
        post.setUser(user);

        return post;
    }

    @Override
    @Transactional(readOnly = true)
    public List<PostDto> findFollowedPostsByOwnerId(String ownerId) {

        User owner = getUser(ownerId);

        return getObservedUsers(owner)
                .map(postDAO::findByUser)
                .flatMap(postStream -> postStream )
                .sorted(Comparator.comparingLong(Post::getId).reversed())
                .map(PostMapper::PostToDto)
                .collect(Collectors.toList());
    }

    protected Stream<User> getObservedUsers(User owner) {

        Optional<Follower> follower = followerDAO.findByOwner(owner);
        if(follower.isEmpty()) return Stream.empty();

        return follower.get().getObservedUsers().stream();
    }
}
