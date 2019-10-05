package org.qbit.challenge.challenge.dev.repository;

import org.qbit.challenge.challenge.dev.model.Follower;
import org.qbit.challenge.challenge.dev.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GenericFollowerDAO extends CrudRepository<Follower,Long> {

    Optional<Follower> findByOwner(User user);
}
