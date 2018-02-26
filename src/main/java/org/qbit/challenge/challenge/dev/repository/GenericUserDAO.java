package org.qbit.challenge.challenge.dev.repository;

import org.qbit.challenge.challenge.dev.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenericUserDAO extends CrudRepository<User,String> {
}
