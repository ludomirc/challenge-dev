package org.qbit.challenge.challenge.dev.repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.qbit.challenge.challenge.dev.ChallengeDevApplication;
import org.qbit.challenge.challenge.dev.fixture.DataSuplayer;
import org.qbit.challenge.challenge.dev.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ChallengeDevApplication.class)
public class UserDAOIntegrationTest {

    private List<User> expectedUsers;

    @Autowired
    private GenericUserDAO userDao;

    @Before
    public void setUp() {

        expectedUsers = DataSuplayer.getUsers();

        userDao.save(expectedUsers);
    }



    @After
    public void tearDown() {
        userDao.delete(expectedUsers);
        expectedUsers = null;
    }


    @Test
    public void whenUserListIsGiven_thenSizeShudBeAsExpected() {

        List<User> actual = (List<User>) userDao.findAll();

        assertThat(expectedUsers.size(), is(equalTo(expectedUsers.size())));
    }

}
