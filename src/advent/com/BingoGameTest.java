package advent.com;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class BingoGameTest {

    private static final List<Integer> BINGO_NUMBERS = List.of(67,77,5,66,4);


    @Test
    public void shouldSetFlagWhenNumberCalled(Map<Integer, Boolean> bingoCard){
//        BINGO_NUMBERS.forEach(number ->{
//            Optional<Map.Entry<Integer, Boolean>> entryInMap = bingoCard.entrySet().stream().filter(bingoEntry -> bingoEntry.getKey().equals(number)).findFirst();
//            if(entryInMap.isPresent()){
//                int positionInMap = entryInMap.
//            };
//        });

    }

    @Test
    public void shouldSetBingoWinWhenRowOrColumnComplete(){

    }

    @Test
    public void shouldNotSetBingoWinWhenRowOrColumnsIncomplete(){

    }

    @Test
    public void shouldCalculateTotalOfWinningBoard(){

    }

}