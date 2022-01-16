package advent.com;

public class ChallengeThree {

    private static final int [] BITS_TO_TEST = {0x10, 0x08, 0x04, 0x02, 0x01};

    public static int[] getBinaryStateCountForOnes() {
        return binaryStateCountForOnes;
    }

    public static int[] getBinaryStateCountForZeros() {
        return binaryStateCountForZeros;
    }

    private static int[] binaryStateCountForOnes = {0, 0, 0, 0, 0};
    private static int[] binaryStateCountForZeros = {0, 0, 0, 0, 0};


    public static void calculateBinaryStateCounts( int [] valuesForCalculation){
        for(int index = 0; index < binaryStateCountForOnes.length; index ++){
            for(int bit = 0 ; bit < BITS_TO_TEST.length; bit++) {
                int bitwiseValue = valuesForCalculation[index] & BITS_TO_TEST[bit];
                if(bitwiseValue == BITS_TO_TEST[bit]){
                    binaryStateCountForOnes[bit]++;
                }else{
                    binaryStateCountForZeros[bit]++;
                }
            }
        }
    }

    public static int calculateGammaRate(){
        int gammaRate = 0;

        for(int bit = 0; bit < BITS_TO_TEST.length; bit++){
            if(binaryStateCountForOnes[bit] > binaryStateCountForZeros[bit]){
                gammaRate = gammaRate |  (0x10 >> bit);
            }
        }
        return gammaRate;
    }

    public static int calculateEpsilonRate(){
        int epsilonRate = 0;

        for(int bit = 0; bit < BITS_TO_TEST.length; bit++){
            if(binaryStateCountForOnes[bit] < binaryStateCountForZeros[bit]){
                epsilonRate = epsilonRate |  (0x10 >> bit);
            }
        }
        return epsilonRate;
    }
}
