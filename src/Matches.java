import java.util.ArrayList;
import java.util.Collections;

/**
 * The Matches class assembles all the team seeds into a linear ArrayList.
 */
public class Matches {
    ArrayList<String> teams;
    ArrayList<Integer> seeds;
    int numTeams;
    int rounds;
    int mpr;

    /**
     * Constructor for the Matches class. This creates a new instance of a Match using the teams parameters.
     * @param teams represents the list of teams
     */
    public Matches(ArrayList<String> teams) {
        this.teams = teams;
        this.numTeams = teams.size();
        this.seeds = new ArrayList<Integer>();
        for (int i = 0; i < numTeams; i++) {
            seeds.add(i + 1);
        }
        this.rounds = numTeams - 1;
        this.mpr = (rounds + 1) / 2;
        if (teams.size() % 2 == 0) {
            rounds--;
            mpr--;
        }
        if (teams.size() % 2 != 0) {
            teams.add("Break");
            seeds.add(seeds.get((seeds.size() - 1)) + 1);
        }
    }

    /**
     * The MatchUps method creates all the seed match ups in the tournament and returns them in one ArrayList. (Currently not sorted into explicit pairs)
     * @return returns an ArrayList of seed match ups.
     */
    private ArrayList<Integer> MatchesWithSeeds() {
        ArrayList<Integer> allMatches = new ArrayList<Integer>();
        int originalLength = seeds.size();
        for (int i = 0; i < originalLength; i++) {
            for (int j = 1 + i; j < seeds.size(); j++) {
                allMatches.add(i + 1);
                allMatches.add(j + 1);
            }
            seeds.subList(1, seeds.size() - 1);
        }
        return allMatches;
    }

    public ArrayList<ArrayList<Integer>> MatchesWithTeams(){ //creates pairs from the ArrayList of matches, places them in the overall 2D ArrayList, clears the current pair, rinse and repeat
        ArrayList<Integer> matches = MatchesWithSeeds();
        ArrayList<ArrayList<Integer>> matchSets = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> pairs = new ArrayList<Integer>();
        for (int i = 0; i <= matches.size() - 1; i+=2) {
            pairs.add(matches.get(i));
            pairs.add(matches.get(i + 1));
            matchSets.add(new ArrayList<>(pairs));
            pairs.clear();
        }
        Collections.shuffle(matchSets);
        return matchSets;
    }

    /**
     * Seeds getter method
     * @return returns an ArrayList of the team seeds.
     */
    public ArrayList<Integer> getseeds() {
        return seeds;
    }

    /**
     * Teams list getter method
     * @return returns an ArrayList of the teams.
     */
    public ArrayList<String> getTeams() {
        return teams;
    }

    /**
     * Rounds getter method
     * @return returns an integer of the rounds within the tournament
     */
    public int getRounds() {
        return rounds;
    }

    /**
     * Matches per round getter method
     * @return returns an integer of the number of matches to be played per round
     */
    public int getMpr() {
        return mpr;
    }
}