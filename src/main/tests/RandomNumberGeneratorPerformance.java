package src.main.tests;

import src.main.number_generator.PseudoRandomNumberException;
import src.main.number_generator.PseudoRandomNumberGenerator;
import src.main.number_generator.lagged_fibonacci.LaggedFibonacciGenerator;
import src.main.number_generator.xor_shift.XorShiftGenerator;

import java.math.BigInteger;

public class RandomNumberGeneratorPerformance extends AbstractTest {

    public static void test() throws PseudoRandomNumberException {
        int[] sizes = {4096};

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
                    integer = generator.createNumber(size/8);
//                    System.out.println("\t\tGenerated " + integer.toString());
                    endTime = System.currentTimeMillis();
                    totalTime += (endTime - beginTime);
                }
                timeInSeconds = ((double) totalTime / AMOUNT / TO_SECONDS);
                System.out.println("Test gerador Pseudo aleatório " + generator.getClass().getName() + " (size = " + size + ") : took " + timeInSeconds);
            }
        }
    }
}
