package advent.com;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        ChallengeOne challengeOne = new ChallengeOne();
        ChallengeThree challengeThree = new ChallengeThree();

        List<String> testValueList = challengeOne.readValuesFromFile("/Users/JDY22/AdventCode/src/advent/com/file.txt");
        System.out.println(testValueList.size());
        System.out.println(challengeOne.countValuesGreaterThanPreviousValue(testValueList));

        List<String> submarineDirectionsList = challengeOne.readValuesFromFile("/Users/JDY22/AdventCode/src/advent/com/subdirections.txt");
        for(String position : submarineDirectionsList){
            ChallengeTwo.calculatePosition(position);
        }
        System.out.println("Submarine Position = " + ChallengeTwo.getFinalSubmarinePosition());

        List<String> submarinePositionsList = challengeOne.readValuesFromFile("/Users/JDY22/AdventCode/src/advent/com/positions1.txt");
//        int[] positionsAsIntegerArray = submarinePositionsList.stream().mapToInt(s -> Integer.parseInt(s)).toArray();
        BigInteger[] positionsAsIntegerArray = submarinePositionsList.stream().map(s -> BigInteger.valueOf(Long.parseLong(s))).toArray(BigInteger[]::new);
        challengeThree.calculateBinaryStateCounts(positionsAsIntegerArray);
        System.out.println("Power Consumption = " + challengeThree.calculateGammaRate() * challengeThree.calculateEpsilonRate());
    }
}
