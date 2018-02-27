package org.qbit.challenge.challenge.dev.repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.qbit.challenge.challenge.dev.ChallengeDevApplication;
import org.qbit.challenge.challenge.dev.model.Follower;
import org.qbit.challenge.challenge.dev.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ChallengeDevApplication.class)
public class GenericFollowerDAOIntegrationTest {

    @PersistenceContext
    private EntityManager em;


    private List<Follower> expectedFollowers;

    @Autowired
    private GenericFollowerDAO followerDAO;

    @Before
    public void setUp() {

        List<User> users  = Arrays.asList(new User("u1"),new User("u2"),new User("u3"));

        users.forEach(u -> em.persist(u));
        em.flush();

        Follower follower1 = new Follower();
        Follower follower2 = new Follower();

        follower1.setOwner(users.get(0));
        follower2.setOwner(users.get(1));

        expectedFollowers = new LinkedList<>();

        expectedFollowers.add(follower1);
        expectedFollowers.add(follower2);

        List<User> observed1 = new LinkedList<>();
        observed1.add(users.get(2));

        follower1.setObserved(observed1);
        follower2.setObserved(observed1);

        followerDAO.save(expectedFollowers);

    }


    @After
    public void tearDown() {
        followerDAO.delete(expectedFollowers);
        expectedFollowers = null;
    }


    @Test
    public void whenTimeLineListIsGiven_thenSizeShouldBeAsExpected() {

        List<Follower> actual = (List<Follower>) followerDAO.findAll();

        assertThat(actual.size(), is(equalTo(expectedFollowers.size())));

    }

   /* @Test
    public void whenUserIsGiven_thenGetTimeLine() {

        List<Follower> actual = (List<Follower>) timeLineDAO.findAll();

        assertThat(actual.size(), is(equalTo(expectedFollowers.size())));

    }*/


}