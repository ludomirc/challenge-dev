package org.qbit.challenge.challenge.dev.repository;

import org.qbit.challenge.challenge.dev.model.Post;
import org.qbit.challenge.challenge.dev.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Stream;

@Repository
public interface GenericPostDAO extends CrudRepository<Post,Long> {

    Stream<Post> findByUserOrderByIdDesc(User user);

    Stream<Post> findByUser(User user);
}
