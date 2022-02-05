package advent.com;

import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


class ChallengeFourTest {

    private final static String TEST_DATA_FILE = "/Users/JDY22/AdventCode/src/advent/com/bingo_test.txt";
    private final static int LAST_BINGO_NUMBER = 65;
    private final static int NUMBER_OF_BINGO_CARDS = 2;


    @Test
    public void shouldReadBingoNumbersFromFile(){

        ChallengeFour challengeFour = new ChallengeFour();

        List<Integer> bingoNumbers = challengeFour.readBingoNumbersFromFile(TEST_DATA_FILE);
        int actualLastBingoNumber = bingoNumbers.get(bingoNumbers.size() - 1);

        assertEquals(LAST_BINGO_NUMBER, actualLastBingoNumber);

    }

    @Test
    public void shouldReadBingoCardsFromFile(){

        ChallengeFour challengeFour = new ChallengeFour();

        List<LinkedHashMap<Integer, Boolean>> bingoCards = challengeFour.readBingoCardsFromFile(TEST_DATA_FILE, "");
        int actualNumberOfBingoCards = bingoCards.size();

        assertEquals(NUMBER_OF_BINGO_CARDS, actualNumberOfBingoCards);

    }



}