package src.main.tests;

import src.main.number_generator.PseudoRandomNumberException;
import src.main.number_generator.PseudoRandomNumberGenerator;
import src.main.number_generator.lagged_fibonacci.LaggedFibonacciGenerator;
import src.main.number_generator.xor_shift.XorShiftGenerator;

import java.math.BigInteger;

public class RandomNumberGeneratorPerformance extends AbstractTest {

    public static final int AMOUNT = 1000;

    public static void test() throws PseudoRandomNumberException {
        int[] sizes = {40, 56, 80, 128, 168, 224, 256, 512, 1024, 2048, 4096};

        XorShiftGenerator xorShiftGenerator = createXorShift();
        LaggedFibonacciGenerator laggedFibonacciGenerator = createLaggedFibonacci();
        PseudoRandomNumberGenerator[] generators = new PseudoRandomNumberGenerator[] {xorShiftGenerator, laggedFibonacciGenerator};
        long beginTime, endTime, totalTime;
        double timeInSeconds;
        BigInteger integer;

        for (PseudoRandomNumberGenerator generator : generators) {
            for (int size : sizes) {
                totalTime = 0;
                for (int i = 0; i < AMOUNT; i++) {
                    beginTime = System.currentTimeMillis();
                    generator.createNumber(size/8);
                    endTime = System.currentTimeMillis();
                    totalTime += (endTime - beginTime);
                }
                timeInSeconds = ((double) totalTime / AMOUNT / TO_SECONDS);
                System.out.println("Test gerador Pseudo aleatório " + generator.getClass().getName() + " (size = " + size + ", amount = " + AMOUNT + ") : took " + timeInSeconds);
            }
        }
    }
}
