package src.main.tests;

import src.main.number_generator.PseudoRandomNumberException;
import src.main.number_generator.PseudoRandomNumberGenerator;
import src.main.number_generator.lagged_fibonacci.LaggedFibonacciGenerator;
import src.main.prime_verifier.SolovayStrassen;

import java.lang.reflect.InvocationTargetException;

public class SolovayStrassenWithLaggedFibonacci extends AbstractTest {

    public static void test() throws NoSuchMethodException, PseudoRandomNumberException, InvocationTargetException, InstantiationException, IllegalAccessException {
        int[] sizes = {128, 256, 512, 1024, 2048, 4096};
        LaggedFibonacciGenerator laggedFibonacciGenerator;
        for (int size : sizes) {
            laggedFibonacciGenerator = createLaggedFibonacci();
            findPrime(SolovayStrassen.class.getConstructor(PseudoRandomNumberGenerator.class), laggedFibonacciGenerator, size);
        }
    }
}
