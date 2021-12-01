package src.main.tests;

import src.main.number_generator.PseudoRandomNumberException;
import src.main.number_generator.PseudoRandomNumberGenerator;
import src.main.number_generator.lagged_fibonacci.LaggedFibonacciGenerator;
import src.main.prime_verifier.MillerRabin;

import java.lang.reflect.InvocationTargetException;

public class MillerRabinWithLaggedFibonacci extends AbstractTest {

    public static void test() throws NoSuchMethodException, PseudoRandomNumberException, InvocationTargetException, InstantiationException, IllegalAccessException {
        int[] sizes = {128, 256, 512, 1024, 2048, 4096};
        LaggedFibonacciGenerator laggedFibonacciGenerator;
        for (int size : sizes) {
            // Find Prime (Miller Rabin, XorShift, seed=X)
            laggedFibonacciGenerator = createLaggedFibonacci();
            findPrime(MillerRabin.class.getConstructor(PseudoRandomNumberGenerator.class), laggedFibonacciGenerator, size);
        }
    }
}
