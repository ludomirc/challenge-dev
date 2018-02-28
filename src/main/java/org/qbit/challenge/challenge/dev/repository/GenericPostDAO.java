package org.qbit.challenge.challenge.dev.repository;

import org.qbit.challenge.challenge.dev.model.Post;
import org.qbit.challenge.challenge.dev.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenericPostDAO extends CrudRepository<Post,Long> {

    List<Post> findByUserOrderByIdDesc(User user);

    List<Post> findByUser(User user);
}
