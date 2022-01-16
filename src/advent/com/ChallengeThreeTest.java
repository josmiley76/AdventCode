package advent.com;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChallengeThreeTest {

    private static int[] binaryStateCountsForOnes = {0,0,0,0,0};
    private static int[] binaryStateCountsForZeros = {0,0,0,0,0};

    @Test
    public void ShouldCalculateBinaryStates(){
        final int[] TEST_VALUES = {16, 9, 4, 3, 1};
        final int[] EXPECTED_BINARY_STATE_COUNT_FOR_ONES = {1, 1, 1, 1, 3};
        final int[] EXPECTED_BINARY_STATE_COUNT_FOR_ZEROS = {4, 4, 4, 4, 2};
        ChallengeThree.calculateBinaryStateCounts(TEST_VALUES);
        assertArrayEquals(EXPECTED_BINARY_STATE_COUNT_FOR_ONES, ChallengeThree.getBinaryStateCountForOnes());
        assertArrayEquals(EXPECTED_BINARY_STATE_COUNT_FOR_ZEROS, ChallengeThree.getBinaryStateCountForZeros());

    }

    @Test
    public void ShouldCalculateGammaRate(){
        final int GAMMA_RATE = 0x12;
        final int[] TEST_VALUES = {18, 25, 22, 23, 8};

        ChallengeThree.calculateBinaryStateCounts(TEST_VALUES);
        assertEquals(GAMMA_RATE,ChallengeThree.calculateGammaRate());
    }

    @Test
    public void ShouldCalculateEpsilonRate(){
        final int EPSILON_RATE = 0x0D;
        final int[] TEST_VALUES = {18, 25, 22, 23, 8};

        ChallengeThree.calculateBinaryStateCounts(TEST_VALUES);
        assertEquals(EPSILON_RATE,ChallengeThree.calculateEpsilonRate());
    }

    @Test
    public void ShouldCalculatePowerConsumption(){
        final int[] TEST_VALUES = {18, 25, 22, 23, 8};
        final int POWER_CONSUMPTION = 0x12 * 0x0D;

        ChallengeThree.calculateBinaryStateCounts(TEST_VALUES);
        int powerConsumption = ChallengeThree.calculateGammaRate() * ChallengeThree.calculateEpsilonRate();
        assertEquals(POWER_CONSUMPTION, powerConsumption);
    }

}