package org.example;

import org.example.implementation.Match;
import org.example.implementation.Scoreboard;

import java.util.ArrayList;

/**
 * Simple example that uses the Scoreboard class functionalities
 */
public class Main {
    public static void main(String[] args) {
        Scoreboard scoreboard = new Scoreboard();

        scoreboard.startNewMatch("Mexico","Canada");
        scoreboard.startNewMatch("Spain","Brazil");
        scoreboard.startNewMatch("Germany","France");
        scoreboard.startNewMatch("Uruguay","Italy");
        scoreboard.startNewMatch("Argentina","Australia");

        scoreboard.updateScore("Mexico", "Canada", 0, 5);
        scoreboard.updateScore("Spain", "Brazil", 10, 2);
        scoreboard.updateScore("Germany", "France", 2, 2);
        scoreboard.updateScore("Uruguay", "Italy", 6, 6);
        scoreboard.updateScore("Argentina", "Australia", 3, 1);

        ArrayList<Match> matches = scoreboard.getCurrentMatches();
        for(Match match : matches){
            System.out.println(match);
        }
    }
}