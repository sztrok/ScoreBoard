package org.example.implementation;

import org.example.implementation.Match;
import org.example.implementation.Scoreboard;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;


public class ScoreboardTest {

    public Scoreboard scoreboard;
    String team1;
    String team2;
    String team3;
    String team4;

    @Before
    public void setUp() {
        this.scoreboard = new Scoreboard();
        this.team1 = "team1";
        this.team2 = "team2";
        this.team3 = "team3";
        this.team4 = "team4";
    }

    @Test
    public void shouldCreateNewMatch(){
        String response = scoreboard.startNewMatch(team1, team2);
        Assert.assertTrue(response.startsWith("New match started"));
    }

    @Test
    public void shouldNotCreateNewMatchForTeamsInAnotherMatch(){
        scoreboard.startNewMatch(team1, team2);
        String response1 = scoreboard.startNewMatch(team1, team3);
        String response2 = scoreboard.startNewMatch(team2, team3);
        System.out.println(response1);
        System.out.println(response2);
        Assert.assertTrue(
                response1.startsWith("Can't create match") &&
                response2.startsWith("Can't create match")
        );
    }

    @Test
    public void shouldUpdateScoreIfMatchExists(){
        scoreboard.startNewMatch(team1, team2);
        String response1 = scoreboard.updateScore(team1, team2, 1,0);
        String response2 = scoreboard.updateScore(team1, team3, 1,0);
        String response3 = scoreboard.updateScore(team3, team2, 1,0);
        Assert.assertTrue(
                response1.startsWith("Score updated") &&
                response2.startsWith("Couldn't find match for teams") &&
                response3.startsWith("Couldn't find match for teams")
        );
    }

    @Test
    public void shouldFinishMatchIfExists(){
        scoreboard.startNewMatch(team1, team2);
        String response1 = scoreboard.finishMatch(team1, team2);
        String response2 = scoreboard.finishMatch(team1, team3);
        String response3 = scoreboard.finishMatch(team3, team2);
        Assert.assertTrue(
                response1.startsWith("Successfully ended match") &&
                        response2.startsWith("Couldn't find match for teams") &&
                        response3.startsWith("Couldn't find match for teams")
        );
    }

    @Test
    public void shouldReturnAllCurrentMatches(){
        ArrayList<Match> list1 = scoreboard.getCurrentMatches();
        scoreboard.startNewMatch(team1, team2);
        scoreboard.startNewMatch(team3, team4);
        ArrayList<Match> list2 = scoreboard.getCurrentMatches();
        Assert.assertTrue(list1.isEmpty());
        for(Match match : list2){
            Assert.assertTrue(
                    match.getHomeTeamName().equals(team1) ||
                            match.getHomeTeamName().equals(team3)
            );
        }
    }
}