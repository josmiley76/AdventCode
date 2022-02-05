package advent.com;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BingoGame {

    private static final int NUMBER_OF_ROWS_AND_COLUMNS = 5;
    private static final int NO_WINNER = -1;

    public int setFlagForBingoNumberAndCheckForWin(List<Map<Integer, Boolean>> bingoCards, int number) {

        int winningbingoCard = NO_WINNER;
        List<Integer> bingoNumbersSoFarInRows = new ArrayList<>();
        List<Integer> bingoNumbersSoFarInColumns = new ArrayList<>();

        for (Map<Integer, Boolean> bingoCard : bingoCards) {
            Optional<Map.Entry<Integer, Boolean>> entryInMap =
                    bingoCard.entrySet().stream().filter(bingoEntry -> bingoEntry.getKey().equals(number)).findAny();
            List<Integer> bingoNumbers = bingoCard.keySet().stream().collect(Collectors.toUnmodifiableList());
            if (entryInMap.isPresent()) {
                entryInMap.get().setValue(true);
                Integer bingoNumberRow = bingoNumbers.indexOf(number) / NUMBER_OF_ROWS_AND_COLUMNS;
                Integer bingoNumberColumn = bingoNumbers.indexOf(number) % NUMBER_OF_ROWS_AND_COLUMNS;

                bingoNumbersSoFarInRows.addAll(bingoCard.keySet().stream().filter(bingoNumber -> bingoNumberRow.equals(bingoNumbers.indexOf(bingoNumber) / NUMBER_OF_ROWS_AND_COLUMNS))
                                                        .filter(bingoNumber -> bingoCard.get(bingoNumber).equals(Boolean.TRUE)).collect(Collectors.toList()));
                bingoNumbersSoFarInColumns.addAll(bingoCard.keySet().stream().filter(bingoNumber -> bingoNumberColumn.equals(bingoNumbers.indexOf(bingoNumber) % NUMBER_OF_ROWS_AND_COLUMNS))
                                                        .filter(bingoNumber -> bingoCard.get(bingoNumber).equals(Boolean.TRUE)).collect(Collectors.toList()));
                if ((bingoNumbersSoFarInRows.size() == NUMBER_OF_ROWS_AND_COLUMNS) || (bingoNumbersSoFarInColumns.size() == NUMBER_OF_ROWS_AND_COLUMNS)) {
                    winningbingoCard = bingoCards.indexOf(bingoCard);
                }
            }
        }
        return winningbingoCard;
    }

    public int calculateTotalOfBingoCard(Map<Integer, Boolean> bingoCard){
        return bingoCard.keySet().stream().reduce(0, Integer::sum);
    }
}

