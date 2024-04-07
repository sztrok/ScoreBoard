package org.example.implementation;

import java.util.Comparator;


/**
 * Custom comparator for class Match. Used for sorting matches from highest totalScore value to lowest.
 * When totalScore for two matches is equal, then the match that started more recently is declared as greater.
 */
public class MatchComparator implements Comparator<Match> {

    /**
     * Compares matches based on total score, if total score is equal, date started is compared
     * @param o1 the first object to be compared.
     * @param o2 the second object to be compared.
     */
    @Override
    public int compare(Match o1, Match o2) {
        if(o1.getTotalScore().equals(o2.getTotalScore())){
            if(o1.getStartOfTheMatch().isAfter(o2.getStartOfTheMatch())){
                return 1;
            }
            else return -1;
        }
        else return -Integer.compare(o1.getTotalScore(), o2.getTotalScore());
    }

}
