import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        ArrayList<String> teams = new ArrayList<String>();
        Scanner input = new Scanner(System.in);
        System.out.println("How many teams will be participating? ");
        int numTeams = Integer.parseInt(input.nextLine());
        for (int i = 0; i < numTeams; i++) {
            System.out.println("Team " + (i + 1) + ": ");
            teams.add(input.nextLine());
        }
        Matches matches = new Matches(teams);
        TournamentXSSF tournamentXSSF = new TournamentXSSF(matches.MatchesWithTeams(), teams);
        tournamentXSSF.generateXlsx();
    }
}