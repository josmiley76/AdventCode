package advent.com;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        ChallengeOne challengeOne = new ChallengeOne();

        List<String> testValueList = challengeOne.readValuesFromFile("/Users/JDY22/AdventCode/src/advent/com/file.txt");
        System.out.println(testValueList.size());
        System.out.println(challengeOne.countValuesGreaterThanPreviousValue(testValueList));

        List<String> submarineDirectionsList = challengeOne.readValuesFromFile("/Users/JDY22/AdventCode/src/advent/com/subdirections.txt");
        for(String position : submarineDirectionsList){
            ChallengeTwo.calculatePosition(position);
        }
        System.out.println("Submarine Position = " + ChallengeTwo.getFinalSubmarinePosition());

        List<String> submarinePositionsList = challengeOne.readValuesFromFile("/Users/JDY22/AdventCode/src/advent/com/positions.txt");
        ChallengeThree.calculateBinaryStateCounts(submarinePositionsList);
        System.out.println("Power Consumption = " + ChallengeThree.calculateGammaRate() * ChallengeThree.calculateEpsilonRate());
    }
}
