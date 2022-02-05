package advent.com;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Map.entry;
import static org.junit.jupiter.api.Assertions.*;

class BingoGameTest {

    private static final List<Integer> BINGO_NUMBERS = List.of(67,75,9,66,4);
    private static final List<Integer> BINGO_NUMBERS_ROW_WINS = List.of(67,7,75,66,4);
    private static final List<Integer> BINGO_NUMBERS_COLUMN_WINS = List.of(67,35,58,55,38);
    private static final int WINNING_BINGO_CARD = 0;


    //this does not insert them in the correct order
//    private static LinkedHashMap<Integer, Boolean> BINGO_CARD = new LinkedHashMap<>(Map.ofEntries(entry(67, false), entry(7, false), entry(75, false), entry(66, false), entry(4, false),
//                                                                                                  entry(35, false), entry(97, false), entry(21, false), entry(29, false), entry(95, false),
//                                                                                                  entry(58, false), entry(98, false), entry(56, false), entry(71, false), entry(65, false),
//                                                                                                  entry(55, false), entry(61, false), entry(19, false), entry(64, false), entry(9, false),
//                                                                                                  entry(38, false), entry(34, false), entry(42, false), entry(30, false), entry(2, false)));

    private static LinkedHashMap<Integer, Boolean> BINGO_CARD = new LinkedHashMap<>();

    static{
        BINGO_CARD.put(67, false); BINGO_CARD.put(7, false); BINGO_CARD.put(75, false); BINGO_CARD.put(66, false); BINGO_CARD.put(4, false);
        BINGO_CARD.put(35, false); BINGO_CARD.put(97, false); BINGO_CARD.put(21, false); BINGO_CARD.put(29, false); BINGO_CARD.put(95, false);
        BINGO_CARD.put(58, false); BINGO_CARD.put(98, false); BINGO_CARD.put(56, false); BINGO_CARD.put(71, false); BINGO_CARD.put(65, false);
        BINGO_CARD.put(55, false); BINGO_CARD.put(61, false); BINGO_CARD.put(19, false); BINGO_CARD.put(64, false); BINGO_CARD.put(9, false);
        BINGO_CARD.put(38, false); BINGO_CARD.put(34, false); BINGO_CARD.put(42, false); BINGO_CARD.put(30, false); BINGO_CARD.put(2, false);
    }


    @Test
    public void shouldSetFlagWhenNumberCalled(){
        List<Map<Integer, Boolean>> bingoCards = List.of(BINGO_CARD);
        BingoGame bingoGame = new BingoGame();

        BINGO_NUMBERS.forEach(number ->{
            bingoGame.setFlagForBingoNumberAndCheckForWin(bingoCards, number);
            assertTrue(BINGO_CARD.get(number));
        });

    }

    @Test
    public void shouldSetBingoWinWhenRowComplete(){
        List<Map<Integer, Boolean>> bingoCards = List.of(BINGO_CARD);
        BingoGame bingoGame = new BingoGame();
        int winningBingoCard = -1;

        for(int number : BINGO_NUMBERS_ROW_WINS){
            winningBingoCard = bingoGame.setFlagForBingoNumberAndCheckForWin(bingoCards, number);
        }
        assertEquals(WINNING_BINGO_CARD, winningBingoCard);


    }

    @Test
    public void shouldSetBingoWinWhenColumnComplete(){
        List<Map<Integer, Boolean>> bingoCards = List.of(BINGO_CARD);
        BingoGame bingoGame = new BingoGame();
        int winningBingoCard = -1;

        for(int number : BINGO_NUMBERS_COLUMN_WINS){
            winningBingoCard = bingoGame.setFlagForBingoNumberAndCheckForWin(bingoCards, number);
        }
        assertEquals(WINNING_BINGO_CARD, winningBingoCard);

    }

    @Test
    public void shouldCalculateTotalOfWinningBoard(){
        int expectedBingoTotal = 1198;
        BingoGame bingoGame = new BingoGame();

        int actualBingoTotal = bingoGame.calculateTotalOfBingoCard(BINGO_CARD);
        assertEquals(expectedBingoTotal, actualBingoTotal);
    }

}