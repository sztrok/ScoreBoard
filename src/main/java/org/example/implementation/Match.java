package org.example.implementation;
import java.time.LocalDateTime;


/**
 * Class used for representing a match.
 */
public class Match {

    private static final Integer INITIAL_TEAM_SCORE = 0;
    private Integer homeTeamScore;
    private Integer awayTeamScore;
    private Integer totalScore;
    private final String homeTeamName;
    private final String awayTeamName;
    private LocalDateTime startOfTheMatch;

    /**
     * Constructor, creates a new Match instance between given teams
     * @param homeTeamName - name of home team
     * @param awayTeamName - name of away team
     */
    public Match(String homeTeamName, String awayTeamName) {
        this.homeTeamName = homeTeamName;
        this.awayTeamName = awayTeamName;
        homeTeamScore = INITIAL_TEAM_SCORE;
        awayTeamScore = INITIAL_TEAM_SCORE;
        totalScore = INITIAL_TEAM_SCORE;
        this.startOfTheMatch = LocalDateTime.now();
    }

    /**
     * Method used for updating a score of the match
     * @param homeTeamScore - new home team score
     * @param awayTeamScore - new away team score
     * @return - String representing the result of operation
     */
    public String updateScore(final Integer homeTeamScore, final Integer awayTeamScore){
        if(checkIfScoreValuesAreValid(homeTeamScore,awayTeamScore)){
            this.homeTeamScore = homeTeamScore;
            this.awayTeamScore = awayTeamScore;
            this.totalScore = homeTeamScore + awayTeamScore;
            return "Score has been successfully updated";
        }
        else{
            return ("Provided score values are not valid: " + homeTeamScore + " | " + awayTeamScore);
        }
    }

    /**
     * Method used for checking if new score values are acceptable
     * @param homeTeamScore - new home team score
     * @param awayTeamScore - new away team score
     * @return - True if both values are not negative
     */
    private boolean checkIfScoreValuesAreValid(final Integer homeTeamScore, final Integer awayTeamScore){
        return homeTeamScore>=0 && awayTeamScore>=0;
    }

    public Integer getHomeTeamScore() {
        return homeTeamScore;
    }

    public Integer getAwayTeamScore() {
        return awayTeamScore;
    }

    public String getHomeTeamName() {
        return homeTeamName;
    }

    public String getAwayTeamName() {
        return awayTeamName;
    }

    public Integer getTotalScore() {
        return totalScore;
    }

    public LocalDateTime getStartOfTheMatch() {
        return startOfTheMatch;
    }

    public void setStartOfTheMatch(LocalDateTime startOfTheMatch) {
        this.startOfTheMatch = startOfTheMatch;
    }

    @Override
    public String toString() {
        return homeTeamName + " " + homeTeamScore + " - " + awayTeamName + " " + awayTeamScore;
    }
}
