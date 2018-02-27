package org.qbit.challenge.challenge.dev.repository;

import org.qbit.challenge.challenge.dev.model.Follower;
import org.qbit.challenge.challenge.dev.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GenericFollowerDAO extends CrudRepository<Follower,Long> {

    Follower findByOwner(User user);
}
