import java.util.ArrayList;

import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.*;

import java.io.*;

/**
 * The TournamentXSSF class creates a spreadsheet file for the tournament matches.
 */
public class TournamentXSSF {
    private ArrayList<ArrayList<Integer>> roundSets;
    private ArrayList<String> teams;
    private int rounds;
    private int mpr;

    /**
     * Constructor for the TournamentXSSF class; creates a new instances of a TournamentXSSF (or xlsx file)
     * @param roundSets represents the two-dimensional ArrayList of match pairs created in the Rounds Class
     * @param teams represents the ArrayList of teams acquired in the runner class RoundRobin
     */
    public TournamentXSSF(ArrayList<ArrayList<Integer>> roundSets, ArrayList<String> teams){
        this.roundSets = roundSets;
        this.teams = teams;
        this.rounds = teams.size() - 1;
        this.mpr = (rounds + 1) / 2;
    }

    /**
     * The generate_xlsx method creates a spreadsheet file with all tournament matches using data from the 2-d arraylist of seed pairs. Spreadsheet file
     * is placed into the source folder
     * @throws IOException this portion is in the case the creation of the xlsx file doesn't work for one reason or another
     */
    public void generateXlsx() throws IOException { //creates the workbook, sheet, row, and cells; inputs teamnames based on the index given from a particular pair, and writes it in
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Matches");
        for (int i = 0; i < roundSets.size(); i++) {
            XSSFRow row = sheet.createRow(i);
            for (int j = 0; j < 2; j++) {
                XSSFCell cell = row.createCell(j);

                XSSFCellStyle breaks = workbook.createCellStyle();
                breaks.setFillForegroundColor(IndexedColors.BRIGHT_GREEN.getIndex());
                breaks.setFillPattern(FillPatternType.BIG_SPOTS);
                cell.setCellValue(teams.get((roundSets.get(i).get(j) - 1)));
                if (cell.getStringCellValue().equals("Break")){
                    cell.setCellStyle(breaks);
                }
            }
        }

        String filePath = ".\\src\\RoundRobin.xlsx";
        FileOutputStream outputStream = new FileOutputStream(filePath);
        workbook.write(outputStream);
        outputStream.close();
    }
}