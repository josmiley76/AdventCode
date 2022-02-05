package advent.com;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class ChallengeFour {

    private static final String EMPTY_STRING ="";
    private static final String WHITE_SPACE =" ";
    private static final double BINGO_CARD_SIZE = 25.0;

    public List<Integer> readBingoNumbersFromFile(String filename){

        String bingoNumbersAsString = readBingoNumbers(filename);

        if(!bingoNumbersAsString.isEmpty()) {
            return Arrays.stream(bingoNumbersAsString.split(",")).map(Integer::parseInt).collect(Collectors.toUnmodifiableList());
        }
        return Collections.emptyList();
    }

    private String readBingoNumbers(String fileValues) {
        BufferedReader bufReader;
        String line = "";
        {
            try {
                bufReader = new BufferedReader(new FileReader(fileValues));
                line = bufReader.readLine();
                bufReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return line;
    }

    public List<LinkedHashMap<Integer, Boolean>> readBingoCardsFromFile(String fileValues, String seperatorPattern) {
        BufferedReader bufReader;
        ArrayList<LinkedHashMap<Integer, Boolean>> listOfMaps = new ArrayList<>();
        LinkedHashMap<Integer, Boolean> bingoMap = new LinkedHashMap<>();

        int linecount = 1;

        {
            try {
                bufReader = new BufferedReader(new FileReader(fileValues));
                String line = bufReader.readLine();
                while (line != null) {
                    if(!line.contains(",") && !seperatorPattern.equals(line)){
                        bingoMap.putAll(Arrays.stream(line.split(WHITE_SPACE))
                                       .filter(value -> !EMPTY_STRING.equals(value))
                                       .peek(System.out::println)
                                       .map(Integer::parseInt)
                                       .collect(Collectors.toMap(p -> p, p -> Boolean.TRUE)));
                        if(linecount == Math.sqrt(BINGO_CARD_SIZE)){
                            listOfMaps.add(bingoMap);
                        }
                        linecount++;
                    }else{
                        linecount = 1;
                        bingoMap.clear();
                    }
                    line = bufReader.readLine();
                }
                bufReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return listOfMaps;
    }
}
