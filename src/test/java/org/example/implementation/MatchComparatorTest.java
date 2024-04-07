package org.example.implementation;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;


public class MatchComparatorTest {

    public MatchComparator comparator;
    Match match1;
    Match match2;
    Match match3;

    @Before
    public void setUp(){
        this.comparator = new MatchComparator();
        match1 = new Match("team1", "team2");
        match2 = new Match("team3", "team4");
        match3 = new Match("team5", "team6");
    }

    @Test
    public void compareMatchesBasedOnTotalScore(){
        match1.updateScore(1,1);
        match2.updateScore(2,2);
        Assert.assertEquals(1, comparator.compare(match1, match2));
        Assert.assertEquals(-1, comparator.compare(match2, match1));
    }

    @Test
    public void compareMatchesBasedOnStartTime(){
        LocalDateTime dateTime = match1.getStartOfTheMatch().plusDays(1);
        match2.setStartOfTheMatch(dateTime);
        Assert.assertEquals(-1, comparator.compare(match1, match2));
        Assert.assertEquals(1, comparator.compare(match2, match1));
    }
}