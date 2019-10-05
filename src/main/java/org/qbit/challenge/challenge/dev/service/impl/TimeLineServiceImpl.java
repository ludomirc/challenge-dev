package org.qbit.challenge.challenge.dev.service.impl;

import org.qbit.challenge.challenge.dev.model.Follower;
import org.qbit.challenge.challenge.dev.model.User;
import org.qbit.challenge.challenge.dev.repository.GenericFollowerDAO;
import org.qbit.challenge.challenge.dev.service.TimeLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.InvalidParameterException;
import java.util.Arrays;

@Service
public class TimeLineServiceImpl extends BaseService implements TimeLineService {

    @Autowired
    GenericFollowerDAO followerDAO;

    @Transactional
    @Override
    public void saveTimeLine(String userId, String observedUserId) {

        validateInputDate(userId, observedUserId);

        User owner = getUser(userId);
        User observedUser = getUser(observedUserId);

        Follower follower = getFollower(owner,observedUser);

        follower.getObservedUsers().add(observedUser);

        followerDAO.save(follower);
    }

    private void validateInputDate(String userId, String observedUserId) {
        if(userId == null)
            throw new InvalidParameterException("userId cannot be null");

        if(userId.equals(observedUserId))
            throw new InvalidParameterException(
                    String.format("userId '%s' cannot be the same like observedUserId '%s'",userId,observedUserId));
    }

    private Follower getFollower(User owner, User observedUser) {
        return followerDAO.findByOwner(owner).orElseGet(() -> new Follower(owner, Arrays.asList(observedUser)));
    }
}