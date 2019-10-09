package org.qbit.challenge.challenge.dev.service.impl;

import org.qbit.challenge.challenge.dev.expections.MessengersException;
import org.qbit.challenge.challenge.dev.model.User;
import org.qbit.challenge.challenge.dev.repository.GenericUserDAO;
import org.springframework.beans.factory.annotation.Autowired;

import static org.qbit.challenge.challenge.dev.expections.ErrorConstants.USER_NOT_FOUND;

public abstract class BaseService {

    @Autowired
    GenericUserDAO userDAO;

    protected User getUser(String userId) {
        return userDAO.findById(userId)
                .orElseThrow(() -> new MessengersException(String.format(USER_NOT_FOUND, userId)));
    }
}
