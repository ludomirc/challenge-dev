package org.qbit.challenge.challenge.dev.service;

import org.qbit.challenge.challenge.dev.dto.PostDto;

import java.util.List;

public interface PostService {

   List<PostDto> findPostsByUserId(String userId);

   void crate(PostDto postDto);

   List<PostDto> findFollowedPostsByUserId(String userId);
}
