package org.qbit.challenge.challenge.dev.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.qbit.challenge.challenge.dev.ChallengeDevApplication;
import org.qbit.challenge.challenge.dev.model.Follower;
import org.qbit.challenge.challenge.dev.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@Transactional
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ChallengeDevApplication.class)
public class GenericFollowerDAOIntegrationTest {

    @PersistenceContext
    private EntityManager em;

    private List<Follower> expectedFollowers;
    private List<User> expectedUsers;

    @Autowired
    private GenericFollowerDAO followerDAO;

    @Autowired
    private GenericUserDAO userDAO;

    @BeforeEach
    public void setUp() {

        expectedUsers  = Arrays.asList(new User("u1"),new User("u2"),new User("u3"));

        expectedUsers.forEach(u -> em.persist(u));
        em.flush();

        Follower follower1 = new Follower();
        Follower follower2 = new Follower();

        follower1.setOwner(expectedUsers.get(0));
        follower2.setOwner(expectedUsers.get(1));

        expectedFollowers = new LinkedList<>();

        expectedFollowers.add(follower1);
        expectedFollowers.add(follower2);

        List<User> observed1 = new LinkedList<>();
        observed1.add(expectedUsers.get(2));

        follower1.setObservedUsers(observed1);
        follower2.setObservedUsers(observed1);

        followerDAO.saveAll(expectedFollowers);

    }


    @AfterEach
    public void tearDown() {

        followerDAO.deleteAll(expectedFollowers);
        userDAO.deleteAll(expectedUsers);

        expectedUsers = null;
        expectedFollowers = null;
    }


    @Test
    public void whenFollowerListIsGiven_thenSizeShouldBeAsExpected() {

        List<Follower> actual = (List<Follower>) followerDAO.findAll();

        assertThat(actual.size(), is(equalTo(expectedFollowers.size())));

    }

    @Test
    public void whenUserIsGiven_thenFindFollower() {

        User expectedUser = expectedUsers.get(0);

        Optional<Follower> actual =  followerDAO.findByOwner(expectedUser);

        assertThat(actual.get().getOwner(), is(equalTo(expectedUser)));
    }
}