package org.qbit.challenge.challenge.dev.service.impl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.qbit.challenge.challenge.dev.ChallengeDevApplication;
import org.qbit.challenge.challenge.dev.model.Follower;
import org.qbit.challenge.challenge.dev.model.User;
import org.qbit.challenge.challenge.dev.repository.GenericFollowerDAO;
import org.qbit.challenge.challenge.dev.repository.GenericUserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

@Transactional
@RunWith(SpringRunner.class)
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

    @Before
    public void setUp() {

        expectedUsers = Arrays.asList(new User("u1"), new User("u2"), new User("u3"));

        expectedUsers.forEach(u -> em.persist(u));
        em.flush();
    }

    @After
    public void tearDown() {
        userDAO.delete(expectedUsers);
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

        followerDAO.delete(actual);
    }
}