package advent.com;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChallengeThreeTest {
    @BeforeEach
    public void setup(){
        int[] binaryStateCountForOnes = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        int[] binaryStateCountForZeros = {0, 0, 0, 0, 0, 0, 0, 0, 0 ,0 ,0, 0};

        ChallengeThree.setBinaryStateCountForOnes(binaryStateCountForOnes);
        ChallengeThree.setBinaryStateCountForZeros(binaryStateCountForZeros);

    }

    @Test
    public void ShouldCalculateBinaryStates(){
        final int[] TEST_VALUES = { 2048,1024 ,512, 256, 128, 64, 32, 16, 9, 4, 3, 1};
        final int[] EXPECTED_BINARY_STATE_COUNT_FOR_ONES = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3};
        final int[] EXPECTED_BINARY_STATE_COUNT_FOR_ZEROS = {11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 9};
        ChallengeThree.calculateBinaryStateCounts(TEST_VALUES);
        assertArrayEquals(EXPECTED_BINARY_STATE_COUNT_FOR_ONES, ChallengeThree.getBinaryStateCountForOnes());
        assertArrayEquals(EXPECTED_BINARY_STATE_COUNT_FOR_ZEROS, ChallengeThree.getBinaryStateCountForZeros());

    }

    @Test
    public void ShouldCalculateGammaRate(){
        final int GAMMA_RATE = 0x18;
        final int[] TEST_VALUES = {1032, 25, 22, 23, 8};

        ChallengeThree.calculateBinaryStateCounts(TEST_VALUES);
        assertEquals(GAMMA_RATE,ChallengeThree.calculateGammaRate());
    }

    @Test
    public void ShouldCalculateEpsilonRate(){
        final int EPSILON_RATE = 0xFE7;
        final int[] TEST_VALUES = {1032, 25, 22, 23, 8};

        ChallengeThree.calculateBinaryStateCounts(TEST_VALUES);
        assertEquals(EPSILON_RATE,ChallengeThree.calculateEpsilonRate());
    }

    @Test
    public void ShouldCalculatePowerConsumption(){
        final int[] TEST_VALUES = {1032, 25, 22, 23, 8};
        final int POWER_CONSUMPTION = 0x18 * 0xFE7;

        ChallengeThree.calculateBinaryStateCounts(TEST_VALUES);
        int powerConsumption = ChallengeThree.calculateGammaRate() * ChallengeThree.calculateEpsilonRate();
        assertEquals(POWER_CONSUMPTION, powerConsumption);
    }

}