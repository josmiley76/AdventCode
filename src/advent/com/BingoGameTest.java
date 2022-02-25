package advent.com;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

class BingoGameTest {

    private static final List<Integer> BINGO_NUMBERS = List.of(67,75,9,66,4);
    private static final List<Integer> BINGO_NUMBERS_CARDTWO_ROW_WINS = List.of(44,35,8,25,96,4);
    private static final List<Integer> BINGO_NUMBERS_ROW_WINS = List.of(67,7,75,66,4);
    private static final List<Integer> BINGO_NUMBERS_COLUMN_WINS = List.of(67,35,58,55,38);
    private static final List<Integer> BINGO_NUMBERS_FOR_ALL_CARDS_TO_HAVE_WON_ONCE = List.of(44,35,8,25,96,4,67,7,75,66,29);
    private static final List<Integer> BINGO_NUMBERS_WHERE_TWO_CARDS_WIN_SIMULTANEOUSLY = List.of(44,35,8,25,96,67,7,75,66,29,4);
    private static final int LAST_NUMBER_CALLED_FOR_ALL_CARDS_TO_HAVE_WON_ONCE = 66;
    private static final int LAST_NUMBER_CALLED_FOR_MULTIPLE_CARDS_TO_WIN_WITH_LAST_NUMBER = 4;
    private static final int BINGO_CARD_ONE_WINS = 0;
    private static final int BINGO_CARD_TWO_WINS = 1;

    private static LinkedHashMap<Integer, Boolean> BINGO_CARD; 
    private static LinkedHashMap<Integer, Boolean> BINGO_CARD_TWO; 


    static{
        LinkedHashMap<Integer, Boolean> myCard = new LinkedHashMap<>();
        myCard.put(67, false); myCard.put(7, false); myCard.put(75, false); myCard.put(66, false); myCard.put(4, false);
        myCard.put(35, false); myCard.put(97, false); myCard.put(21, false); myCard.put(29, false); myCard.put(95, false);
        myCard.put(58, false); myCard.put(98, false); myCard.put(56, false); myCard.put(71, false); myCard.put(65, false);
        myCard.put(55, false); myCard.put(61, false); myCard.put(19, false); myCard.put(64, false); myCard.put(9, false);
        myCard.put(38, false); myCard.put(34, false); myCard.put(42, false); myCard.put(30, false); myCard.put(2, false);
        BINGO_CARD = new LinkedHashMap<>(myCard);
    }

    static{
        LinkedHashMap<Integer, Boolean> myCard = new LinkedHashMap<>();
        myCard.put(44, false); myCard.put(8, false); myCard.put(25, false); myCard.put(96, false); myCard.put(4, false);
        myCard.put(35, false); myCard.put(97, false); myCard.put(21, false); myCard.put(29, false); myCard.put(95, false);
        myCard.put(57, false); myCard.put(91, false); myCard.put(59, false); myCard.put(72, false); myCard.put(61, false);
        myCard.put(50, false); myCard.put(11, false); myCard.put(9, false); myCard.put(42, false); myCard.put(12, false);
        myCard.put(18, false); myCard.put(14, false); myCard.put(32, false); myCard.put(20, false); myCard.put(10, false);
        BINGO_CARD_TWO = new LinkedHashMap<>(myCard);
    }

    @BeforeEach
    public void setup(){
        resetBingoCard(BINGO_CARD);
        resetBingoCard(BINGO_CARD_TWO);
    }


    @Test
    public void shouldSetNumbersAsCalledDuringBingoGame(){
        List<LinkedHashMap<Integer, Boolean>> bingoCards = List.of(BINGO_CARD);
        BingoGame bingoGame = new BingoGame(bingoCards, BINGO_NUMBERS);

        bingoGame.playBingo();

        BINGO_NUMBERS.forEach(number ->{
            assertTrue(bingoCards.get(0).get(number));
        });

    }

    @Test
    public void shouldSetBingoWinWhenRowComplete(){
        List<LinkedHashMap<Integer, Boolean>> bingoCards = List.of(BINGO_CARD);
        BingoGame bingoGame = new BingoGame(bingoCards, BINGO_NUMBERS_ROW_WINS);

        BingoGame.BingoGameHistory winningBingoCard = bingoGame.playBingo();

        assertEquals(BINGO_CARD_ONE_WINS, winningBingoCard.winningBingoCardIndex);


    }

    @Test
    public void shouldSetBingoWinWhenColumnComplete(){
        List<LinkedHashMap<Integer, Boolean>> bingoCards = List.of(BINGO_CARD);
        BingoGame bingoGame = new BingoGame(bingoCards, BINGO_NUMBERS_COLUMN_WINS);

        BingoGame.BingoGameHistory winningBingoCard = bingoGame.playBingo();

        assertEquals(BINGO_CARD_ONE_WINS, winningBingoCard.winningBingoCardIndex);

    }

    @Test
    public void shouldSetBingoWinWhenRowCompleteOnBoardTwo(){
        List<LinkedHashMap<Integer, Boolean>> bingoCards = List.of(BINGO_CARD, BINGO_CARD_TWO);
        BingoGame bingoGame = new BingoGame(bingoCards, BINGO_NUMBERS_CARDTWO_ROW_WINS);

        BingoGame.BingoGameHistory winningBingoCard = bingoGame.playBingo();

        assertEquals(BINGO_CARD_TWO_WINS, winningBingoCard.winningBingoCardIndex);

    }

    @Test
    public void shouldCalculateScoreUsingBoardTwoWithNumberFour(){
        int expectedBingoTotal = 800 * 4;
        List<LinkedHashMap<Integer, Boolean>> bingoCards = List.of(BINGO_CARD, BINGO_CARD_TWO);
        BingoGame bingoGame = new BingoGame(bingoCards, BINGO_NUMBERS_CARDTWO_ROW_WINS);

        BingoGame.BingoGameHistory winningBingoCard = bingoGame.playBingo();

        int actualBingoTotal = bingoGame.calculateWinningScore(winningBingoCard);
        assertEquals(expectedBingoTotal, actualBingoTotal);
    }

    private void resetBingoCard( LinkedHashMap<Integer, Boolean> bingoCard){
        bingoCard.replaceAll((n, v) -> Boolean.FALSE);
    }

    @Test
    public void shouldCalculateScoreForWhichBingoCardWonLastWhenAllNumbersHaveBeenCalled(){
        int expectedBingoTotal = 915 * LAST_NUMBER_CALLED_FOR_ALL_CARDS_TO_HAVE_WON_ONCE;

        List<LinkedHashMap<Integer, Boolean>> bingoCards = List.of(BINGO_CARD, BINGO_CARD_TWO);
        BingoGame bingoGame = new BingoGame(bingoCards, BINGO_NUMBERS_FOR_ALL_CARDS_TO_HAVE_WON_ONCE);

        BingoGame.BingoGameHistory winningBingoCard = bingoGame.findLastBingoCardToWinWhenAllNumbersHaveBeenCalled();
        int actualBingoTotal = bingoGame.calculateWinningScore(winningBingoCard);

        assertEquals(expectedBingoTotal, actualBingoTotal);

    }

    @Test
    public void shouldHandleCasesWhenMoreThanOneCardWinsWhenANumberIsCalled(){
        int expectedBingoTotal = 771 * LAST_NUMBER_CALLED_FOR_MULTIPLE_CARDS_TO_WIN_WITH_LAST_NUMBER;

        List<LinkedHashMap<Integer, Boolean>> bingoCards = List.of(BINGO_CARD, BINGO_CARD_TWO);
        BingoGame bingoGame = new BingoGame(bingoCards, BINGO_NUMBERS_WHERE_TWO_CARDS_WIN_SIMULTANEOUSLY);

        BingoGame.BingoGameHistory winningBingoCard = bingoGame.findLastBingoCardToWinWhenAllNumbersHaveBeenCalled();
        int actualBingoTotal = bingoGame.calculateWinningScore(winningBingoCard);

        assertEquals(expectedBingoTotal, actualBingoTotal);

    }
}