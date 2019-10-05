package org.qbit.challenge.challenge.dev.service.impl;

import org.qbit.challenge.challenge.dev.expections.MessengersException;
import org.qbit.challenge.challenge.dev.model.User;
import org.qbit.challenge.challenge.dev.repository.GenericUserDAO;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseService {

    @Autowired
    GenericUserDAO userDAO;

    protected User getUser(String userId) {
        return userDAO.findById(userId)
                .orElseThrow(() -> new MessengersException(String.format("user %s not fund", userId)));
    }
}
