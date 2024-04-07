package org.example.implementation;

import java.util.ArrayList;
import java.util.HashSet;


/**
 * Class used for representing a scoreboard.
 * Allows to start a new match, update and finish an existing match, and to get a summary of ongoing matches
 */
public class Scoreboard {

    private final ArrayList<Match> matches = new ArrayList<>();
    private final HashSet<String> teamsCurrentlyPlaying = new HashSet<>();

    /**
     * Method used for starting a new match.
     * @param homeTeamName - name of home team
     * @param awayTeamName - name of away team
     * @return - String representing the result of operation
     */
    public String startNewMatch(final String homeTeamName, final String awayTeamName){
        if(teamsAreNotInAnotherMatch(homeTeamName,awayTeamName)){
            Match newMatch = new Match(homeTeamName, awayTeamName);
            addNewMatchToScoreboard(homeTeamName, awayTeamName, newMatch);
            return("New match started: " + newMatch);
        }
        else{
            return("Can't create match because one or both teams are in another match already: " +
                    homeTeamName + ", " + awayTeamName);
        }
    }

    /**
     * Method used for updating score for a given match
     * @param homeTeamName - name of home team, used for finding a correct match
     * @param awayTeamName - name of away team, used for finding a correct match
     * @param homeTeamScore - new home team score
     * @param awayTeamScore - new home team score
     * @return - String representing the result of operation
     */
    public String updateScore(final String homeTeamName, final String awayTeamName,
                            final Integer homeTeamScore, final Integer awayTeamScore){
        Match match = findMatchGivenTeamNames(homeTeamName, awayTeamName);
        if(match != null){
            match.updateScore(homeTeamScore, awayTeamScore);
            return("Score updated: " + match);
        }
        else{
            return("Couldn't find match for teams: " + homeTeamName + ", " + awayTeamName);
        }

    }

    /**
     * Method used for finishing an ongoing match
     * @param homeTeamName - name of home team, used for finding a correct match
     * @param awayTeamName - name of away team, used for finding a correct match
     * @return - String representing the result of operation
     */
    public String finishMatch(final String homeTeamName, final String awayTeamName){
        Match match = findMatchGivenTeamNames(homeTeamName, awayTeamName);
        if(match != null){
            removeMatchFromScoreboardAndTeamsFromCurrentlyPlaying(match, homeTeamName, awayTeamName);
            return("Successfully ended match: " + match);
        }
        else{
            return("Couldn't find match for teams: " + homeTeamName + ", " + awayTeamName);
        }
    }

    /**
     * Method used for getting all currently ongoing matches
     * @return - Sorted list of ongoing matches
     */
    public ArrayList<Match> getCurrentMatches(){
        MatchComparator matchComparator = new MatchComparator();
        ArrayList<Match> sortedMatches = new ArrayList<>(matches);
        sortedMatches.sort(matchComparator);
        return sortedMatches;
    }

    /**
     * Method used for finding match between two given teams
     * @param homeTeamName - name of home team, used for finding a correct match
     * @param awayTeamName - name of away team, used for finding a correct match
     * @return - Match instance if exists, else null
     */
    private Match findMatchGivenTeamNames(final String homeTeamName, final String awayTeamName){
        for(Match match : matches){
            if(match.getHomeTeamName().equals(homeTeamName) && match.getAwayTeamName().equals(awayTeamName)){
                return match;
            }
        }
        return null;
    }

    /**
     * Method used for checking if any of the given teams is currently in another match
     * @param homeTeamName - name of home team, used for checking if the team is currently in a match
     * @param awayTeamName - name of away team, used for checking if the team is currently in a match
     * @return - True if both teams are not currently playing, false if one or both teams are currently playing
     */
    private boolean teamsAreNotInAnotherMatch(final String homeTeamName, final String awayTeamName){
        return !this.teamsCurrentlyPlaying.contains(homeTeamName) && !this.teamsCurrentlyPlaying.contains(awayTeamName);
    }


    /**
     * Method used for removing match from the list of ongoing matches and to remove team names from currently playing
     * @param match - finished match
     * @param homeTeamName - name of home team that finished the match
     * @param awayTeamName - name of away team that finished the match
     */
    private void removeMatchFromScoreboardAndTeamsFromCurrentlyPlaying(Match match, String homeTeamName, String awayTeamName) {
        matches.remove(match);
        teamsCurrentlyPlaying.remove(homeTeamName);
        teamsCurrentlyPlaying.remove(awayTeamName);
    }

    /**
     * Method used for adding new match to the list of matches, and for storing team names that are currently playing
     * @param homeTeamName - name of home team that we want to store
     * @param awayTeamName - name of away team that we want to store
     * @param newMatch - new Match instance that we want to keep in list of ongoing matches
     */
    private void addNewMatchToScoreboard(String homeTeamName, String awayTeamName, Match newMatch) {
        matches.add(newMatch);
        teamsCurrentlyPlaying.add(homeTeamName);
        teamsCurrentlyPlaying.add(awayTeamName);
    }

}
