package org.qbit.challenge.challenge.dev.repository;

import org.qbit.challenge.challenge.dev.model.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenericPostDAO extends CrudRepository<Post,Long> {
}
