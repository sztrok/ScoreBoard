package org.example.implementation;

import org.example.implementation.Match;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class MatchTest {

    String homeTeam;
    String awayTeam;

    @Before
    public void setUp() {
        this.homeTeam = "team1";
        this.awayTeam = "team2";
    }

    @Test
    public void shouldCreateNewMatchWithCorrectTeamNamesAndScore(){
        Match match = new Match(homeTeam, awayTeam);
        Assert.assertTrue(
                match.getHomeTeamName().equals(homeTeam) &&
                        match.getAwayTeamName().equals(awayTeam) &&
                        match.getHomeTeamScore() == 0 &&
                        match.getAwayTeamScore() == 0
        );
    }

    @Test
    public void shouldUpdateScoreIfScoreValuesAreValid(){
        Match match = new Match(homeTeam, awayTeam);
        String result1 = match.updateScore(-1,0);
        String result2 = match.updateScore(0,0);
        String result3 = match.updateScore(5,5);
        Assert.assertTrue(
                result1.startsWith("Provided score values are not valid") &&
                        result2.equals("Score has been successfully updated") &&
                        result3.equals("Score has been successfully updated") &&
                        match.getTotalScore() == 10
        );

    }

}