package advent.com;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class BingoGame {

    class BingoGameHistory{
        Integer winningBingoCardIndex = NO_WINNER;
        Integer numberCalledToWin = NO_WINNER;
    }

    private static final int NUMBER_OF_ROWS_AND_COLUMNS = 5;
    private static final int NO_WINNER = -1;

    private List<LinkedHashMap<Integer, Boolean>> bingoCards;
    private List<Integer> bingoNumbers;
    private List<BingoGameHistory> bingoGameHistoryList = new ArrayList<>();
//    private int lastNumberCalled = 0;

    public BingoGame(List<LinkedHashMap<Integer, Boolean>> bingoCards, List<Integer> bingoNumbers) {
        this.bingoCards = bingoCards;
        this.bingoNumbers = bingoNumbers;
    }

    public BingoGameHistory playBingo(){
        resetBingoCards(bingoCards);

        for(int bingoNumber : bingoNumbers){
            List<BingoGameHistory> winningBingoCards = setFlagForBingoNumberAndCheckForWin(bingoCards, bingoNumber);
            if (!winningBingoCards.isEmpty()){
                return winningBingoCards.get(0);
            }
        }
        return  null;
    }

    public BingoGameHistory findLastBingoCardToWinWhenAllNumbersHaveBeenCalled(){

        resetBingoCards(bingoCards);

        for(int bingoNumber : bingoNumbers){
            List<BingoGameHistory> winningBingoCards = setFlagForBingoNumberAndCheckForWin(bingoCards, bingoNumber);
            if (!winningBingoCards.isEmpty()){
                for(BingoGameHistory bingoCard : winningBingoCards){
                    if (!bingoGameHistoryList.stream().filter(index -> index.winningBingoCardIndex.equals(bingoCard.winningBingoCardIndex)).findFirst().isPresent()){
                        bingoGameHistoryList.add(bingoCard);
                    }
                }
            }
        }
        return  (bingoGameHistoryList.isEmpty()? null: bingoGameHistoryList.get(bingoGameHistoryList.size()-1));
    }

    public int calculateWinningScore(BingoGameHistory winningBingoCardHistory){

        int totalOfUncalledNumbers = bingoCards.get(winningBingoCardHistory.winningBingoCardIndex).entrySet().stream().filter(number -> Boolean.FALSE.equals(number.getValue())).map(Map.Entry::getKey).reduce(0, Integer::sum);
        return totalOfUncalledNumbers * winningBingoCardHistory.numberCalledToWin;
    }

    private List<BingoGameHistory> setFlagForBingoNumberAndCheckForWin(List<LinkedHashMap<Integer, Boolean>> bingoCards, int number) {

        List<BingoGameHistory> winningBingoCards = new ArrayList<>();

        for (LinkedHashMap<Integer, Boolean> bingoCard : bingoCards) {
            List<Integer> bingoNumbersSoFarInRows = new ArrayList<>();
            List<Integer> bingoNumbersSoFarInColumns = new ArrayList<>();

            Optional<Map.Entry<Integer, Boolean>> entryInMap =
                    bingoCard.entrySet().stream().filter(bingoEntry -> bingoEntry.getKey().equals(number)).findAny();
            List<Integer> bingoNumbersForCard = bingoCard.keySet().stream().collect(Collectors.toUnmodifiableList());
            if (entryInMap.isPresent()) {
                entryInMap.get().setValue(true);
                Integer bingoNumberRow = bingoNumbersForCard.indexOf(number) / NUMBER_OF_ROWS_AND_COLUMNS;
                Integer bingoNumberColumn = bingoNumbersForCard.indexOf(number) % NUMBER_OF_ROWS_AND_COLUMNS;

                bingoNumbersSoFarInRows.addAll(bingoCard.keySet().stream().filter(bingoNumber -> bingoNumberRow.equals(bingoNumbersForCard.indexOf(bingoNumber) / NUMBER_OF_ROWS_AND_COLUMNS))
                                                        .filter(bingoNumber -> bingoCard.get(bingoNumber).equals(Boolean.TRUE)).collect(Collectors.toList()));
                bingoNumbersSoFarInColumns.addAll(bingoCard.keySet().stream().filter(bingoNumber -> bingoNumberColumn.equals(bingoNumbersForCard.indexOf(bingoNumber) % NUMBER_OF_ROWS_AND_COLUMNS))
                                                        .filter(bingoNumber -> bingoCard.get(bingoNumber).equals(Boolean.TRUE)).collect(Collectors.toList()));
                if ((bingoNumbersSoFarInRows.size() == NUMBER_OF_ROWS_AND_COLUMNS) || (bingoNumbersSoFarInColumns.size() == NUMBER_OF_ROWS_AND_COLUMNS)) {
                    System.out.println("bingoNumbersSoFarInColumns = " + bingoNumbersSoFarInColumns);
                    System.out.println("bingoNumbersSoFarInRows = " + bingoNumbersSoFarInRows);
                    System.out.println(bingoCards.indexOf(bingoCard)+ "\n");
                    System.out.println(number + "\n");
                    BingoGameHistory bingoGameHistory = new BingoGameHistory();
                    bingoGameHistory.winningBingoCardIndex = bingoCards.indexOf(bingoCard);
                    bingoGameHistory.numberCalledToWin = number;
//                    int bingoCardIndex = bingoCards.indexOf(bingoCard);
                    winningBingoCards.add(bingoGameHistory);
                }
            }
        }
        return winningBingoCards;
    }

    private void resetBingoCards(List<LinkedHashMap<Integer, Boolean>> bingoCardsList) {
        for (Map<Integer, Boolean> bingoCard : bingoCardsList) {
            bingoCard.replaceAll((n, v) -> Boolean.FALSE);
        }
    }
}

