package org.qbit.challenge.challenge.dev.repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.qbit.challenge.challenge.dev.ChallengeDevApplication;
import org.qbit.challenge.challenge.dev.model.TimeLine;
import org.qbit.challenge.challenge.dev.model.TimeLinePK;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ChallengeDevApplication.class)
public class GenericTimeLineDAOIntegrationTest {


    private List<TimeLine> expectedTimeLine;

    @Autowired
    private GenericTimeLineDAO timeLineDAO;

    @Before
    public void setUp() {

        TimeLinePK pk1 = new TimeLinePK("u1","u2");
        TimeLinePK pk2 = new TimeLinePK("u1","u2");
        TimeLinePK pk3 = new TimeLinePK("u2","u1");

        TimeLine tl1 = new TimeLine(pk1);
        TimeLine tl2 = new TimeLine(pk2);
        TimeLine tl3 = new TimeLine(pk3);

        expectedTimeLine = Arrays.asList(tl1,tl2,tl3);

        timeLineDAO.save(expectedTimeLine);
    }

    @After
    public void tearDown() {
        timeLineDAO.delete(expectedTimeLine);
        expectedTimeLine = null;
    }


    @Test
    public void whenTimeLineListIsGiven_thenSizeShudBeAsExpected() {

        List<TimeLine> actual = (List<TimeLine>) timeLineDAO.findAll();

        System.err.println(String.format("actual: %d, expected: %d",actual.size(),expectedTimeLine.size()));

        assertThat(actual.size(), is(equalTo(expectedTimeLine.size())));
    }
}