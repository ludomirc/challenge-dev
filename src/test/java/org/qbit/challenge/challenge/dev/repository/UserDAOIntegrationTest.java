package org.qbit.challenge.challenge.dev.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.qbit.challenge.challenge.dev.ChallengeDevApplication;
import org.qbit.challenge.challenge.dev.fixture.DataSuplayer;
import org.qbit.challenge.challenge.dev.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@Transactional
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ChallengeDevApplication.class)
public class UserDAOIntegrationTest {

    private List<User> expectedUsers;

    @Autowired
    private GenericUserDAO userDao;

    @BeforeEach
    public void setUp() {

        expectedUsers = DataSuplayer.getUsers();

        userDao.saveAll(expectedUsers);
    }

    @AfterEach
    public void tearDown() {
        userDao.deleteAll(expectedUsers);
        expectedUsers = null;
    }


    @Test
    public void whenUserListIsGiven_thenSizeShudBeAsExpected() {

        List<User> actual = (List<User>) userDao.findAll();

        assertThat(actual.size(), is(equalTo(expectedUsers.size())));
    }

}
