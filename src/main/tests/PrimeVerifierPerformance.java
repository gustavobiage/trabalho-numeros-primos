package src.main.tests;

import src.main.number_generator.PseudoRandomNumberException;
import src.main.number_generator.xor_shift.XorShiftGenerator;
import src.main.prime_verifier.MillerRabin;
import src.main.prime_verifier.PrimeVerifier;
import src.main.prime_verifier.SolovayStrassen;

public class PrimeVerifierPerformance extends AbstractTest {

    public static void test() throws PseudoRandomNumberException {
        int[] amounts = {100, 100, 100, 100, 100, 100, 100, 100, 80, 60, 40};
        int[] sizes = {40, 56, 80, 128, 168, 224, 256, 512, 1024, 2048, 4096};
        XorShiftGenerator xorShiftGenerator = new XorShiftGenerator(10);
        PrimeVerifier millerRabin = new MillerRabin(xorShiftGenerator);
        PrimeVerifier solovayStrassen = new SolovayStrassen(xorShiftGenerator);
        PrimeVerifier[] verifiers = {millerRabin, solovayStrassen};

        double timeInSeconds;

        for (PrimeVerifier verifier : verifiers) {
            for (int i = 0; i < sizes.length; i++) {
                int size = sizes[i];
                int amount = amounts[i];
                timeInSeconds = 0;
                for (int j = 0; j < amount; j++) {
                    timeInSeconds = timeInSeconds + findPrime(verifier, xorShiftGenerator, size);
                }
                timeInSeconds = timeInSeconds / amount;
                System.out.println("Verifier " + verifier.getClass().getName() +
                                " (XorShift, size = " + size + ") : took " + timeInSeconds + " seconds.");
            }
        }
    }
}
