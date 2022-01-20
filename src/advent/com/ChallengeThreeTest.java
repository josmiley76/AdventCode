package advent.com;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ChallengeThreeTest {

        ChallengeThree challengeThree = new ChallengeThree();

    @Test
    public void ShouldCalculateBinaryStates(){
        final int [] TEST_VALUES = { 2048, 1024 ,512, 256, 128, 64, 32, 16, 9, 4, 3, 1};
        final int[] EXPECTED_BINARY_STATE_COUNT_FOR_ONES = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3};
        final int[] EXPECTED_BINARY_STATE_COUNT_FOR_ZEROS = {11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 9};
//        BigInteger[] testValuesAsBigInteger = Arrays.stream(TEST_VALUES)
//                .mapToObj(BigInteger::valueOf)
//                .toArray(BigInteger[]::new);
        BigInteger[] testValuesAsBigInteger = Arrays.stream(TEST_VALUES).mapToObj(big -> BigInteger.valueOf(big)).toArray(BigInteger[]::new);
        challengeThree.calculateBinaryStateCounts(testValuesAsBigInteger);
        assertArrayEquals(EXPECTED_BINARY_STATE_COUNT_FOR_ONES, challengeThree.getBinaryStateCountForOnes());
        assertArrayEquals(EXPECTED_BINARY_STATE_COUNT_FOR_ZEROS, challengeThree.getBinaryStateCountForZeros());

    }

    @Test
    public void ShouldCalculateGammaRate(){
        final int GAMMA_RATE = 0x18;
        final int[] TEST_VALUES = {1032, 25, 22, 23, 8};

        BigInteger[] testValuesAsBigInteger = Arrays.stream(TEST_VALUES).mapToObj(big -> BigInteger.valueOf(big)).toArray(BigInteger[]::new);
        challengeThree.calculateBinaryStateCounts(testValuesAsBigInteger);
        assertEquals(GAMMA_RATE,challengeThree.calculateGammaRate());
    }

    @Test
    public void ShouldCalculateEpsilonRate(){
        final int EPSILON_RATE = 0xFE7;
        final int[] TEST_VALUES = {1032, 25, 22, 23, 8};

        BigInteger[] testValuesAsBigInteger = Arrays.stream(TEST_VALUES).mapToObj(big -> BigInteger.valueOf(big)).toArray(BigInteger[]::new);
        challengeThree.calculateBinaryStateCounts(testValuesAsBigInteger);
        assertEquals(EPSILON_RATE,challengeThree.calculateEpsilonRate());
    }

    @Test
    public void ShouldCalculatePowerConsumption(){
        final int[] TEST_VALUES = {1032, 25, 22, 23, 8};
        final int POWER_CONSUMPTION = 0x18 * 0xFE7;

        BigInteger[] testValuesAsBigInteger = Arrays.stream(TEST_VALUES).mapToObj(big -> BigInteger.valueOf(big)).toArray(BigInteger[]::new);
        challengeThree.calculateBinaryStateCounts(testValuesAsBigInteger);
        int powerConsumption = challengeThree.calculateGammaRate() * challengeThree.calculateEpsilonRate();
        assertEquals(POWER_CONSUMPTION, powerConsumption);
    }

}