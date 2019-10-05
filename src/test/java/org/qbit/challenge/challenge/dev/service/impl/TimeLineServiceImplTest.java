package org.qbit.challenge.challenge.dev.service.impl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.qbit.challenge.challenge.dev.ChallengeDevApplication;
import org.qbit.challenge.challenge.dev.model.Follower;
import org.qbit.challenge.challenge.dev.model.User;
import org.qbit.challenge.challenge.dev.repository.GenericFollowerDAO;
import org.qbit.challenge.challenge.dev.repository.GenericUserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@Transactional
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ChallengeDevApplication.class)
public class TimeLineServiceImplTest {

    @PersistenceContext
    private EntityManager em;

    private List<User> expectedUsers;

    @Autowired
    private TimeLineServiceImpl timeLineService;

    @Autowired
    private GenericUserDAO userDAO;

    @Autowired
    private GenericFollowerDAO followerDAO;

    @BeforeEach
    public void setUp() {

        expectedUsers = Arrays.asList(new User("u1"), new User("u2"), new User("u3"));

        expectedUsers.forEach(u -> em.persist(u));
        em.flush();
    }

    @AfterEach
    public void tearDown() {
        userDAO.deleteAll(expectedUsers);
        expectedUsers = null;
    }

    @Test
    public void saveTimeLine() throws Exception {

        timeLineService.saveTimeLine(expectedUsers.get(0).getId(), expectedUsers.get(1).getId());
        timeLineService.saveTimeLine(expectedUsers.get(0).getId(), expectedUsers.get(2).getId());

        timeLineService.saveTimeLine(expectedUsers.get(1).getId(), expectedUsers.get(0).getId());
        timeLineService.saveTimeLine(expectedUsers.get(1).getId(), expectedUsers.get(2).getId());

        List<Follower> actual = (List<Follower>) followerDAO.findAll();

        int expectedSize = 2;
        assertThat(actual.size(), is(equalTo(expectedSize)));

        followerDAO.deleteAll(actual);
    }
}