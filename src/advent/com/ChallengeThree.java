package advent.com;

import java.math.BigInteger;

public class ChallengeThree {

    private static final int [] BITS_TO_TEST = {0x800, 0x400, 0x200, 0x100, 0x80, 0x40, 0x20, 0x10, 0x08, 0x04, 0x02, 0x01};

    private int[] binaryStateCountForOnes;
    private int[] binaryStateCountForZeros;

    public ChallengeThree() {
        this.binaryStateCountForOnes = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        this.binaryStateCountForZeros = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    }

    public int[] getBinaryStateCountForOnes() {
        return binaryStateCountForOnes;
    }

    public int[] getBinaryStateCountForZeros() {
        return binaryStateCountForZeros;
    }

    public void calculateBinaryStateCounts( BigInteger[] valuesForCalculation){
        for(int index = 0; index < valuesForCalculation.length; index ++){
            for(int bit = 0 ; bit < BITS_TO_TEST.length; bit++) {
                int bitwiseValue = valuesForCalculation[index].and(BigInteger.valueOf(BITS_TO_TEST[bit])).intValue();
                if(bitwiseValue == BITS_TO_TEST[bit]){
                    binaryStateCountForOnes[bit]++;
                }else{
                    binaryStateCountForZeros[bit]++;
                }
            }
        }
    }

    public int calculateGammaRate(){
        int gammaRate = 0;

        for(int bit = 0; bit < BITS_TO_TEST.length; bit++){
            if(binaryStateCountForOnes[bit] > binaryStateCountForZeros[bit]){
                gammaRate = gammaRate |  (0x800 >> bit);
            }
        }
        return gammaRate;
    }

    public int calculateEpsilonRate(){
        int epsilonRate = 0;

        for(int bit = 0; bit < BITS_TO_TEST.length; bit++){
            if(binaryStateCountForOnes[bit] < binaryStateCountForZeros[bit]){
                epsilonRate = epsilonRate |  (0x800 >> bit);
            }
        }
        return epsilonRate;
    }
}
