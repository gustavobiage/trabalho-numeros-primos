package src.main.tests;

import src.main.number_generator.PseudoRandomNumberException;
import src.main.number_generator.PseudoRandomNumberGenerator;
import src.main.number_generator.lagged_fibonacci.LaggedFibonacciGenerator;
import src.main.number_generator.lagged_fibonacci.LaggedFibonacciSeed;
import src.main.number_generator.xor_shift.XorShiftGenerator;
import src.main.prime_verifier.PrimeVerifier;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;

public class AbstractTest {

    public static final int AMOUNT = 100;
    public static final long TO_SECONDS = 1000;

    public static final long XORSHIFT_SEED = 27260;
    public static final int LAGGED_FIBONACCI_J = 353;
    public static final int LAGGED_FIBONACCI_K = 521;
    public static final int[] LAGGED_FIBONACCI_HISTORY = {
            0, 0, 29, 123, 187, 90, 139, 27, 118, 66, 19, 44, 185, 2, 94, 231, 176, 212, 93, 249, 43, 192, 54, 136, 86,
            7, 195, 166, 187, 101, 160, 0, 96, 27, 61, 131, 37, 20, 81, 242, 242, 192, 4, 116, 116, 226, 10, 135, 187,
            144, 138, 33, 104, 24, 251, 158, 240, 74, 187, 76, 187, 186, 5, 75, 67, 76, 138, 231, 204, 221, 128, 130,
            144, 19, 84, 252, 235, 12, 34, 100, 171, 26, 47, 186, 122, 76, 173, 68, 51, 79, 231, 46, 172, 38, 140, 118,
            58, 224, 250, 147, 251, 83, 38, 226, 147, 224, 115, 59, 115, 6, 141, 133, 45, 109, 32, 157, 175, 100, 103,
            30, 166, 164, 232, 3, 78, 217, 237, 125, 79, 55, 29, 76, 64, 212, 236, 6, 63, 70, 160, 43, 104, 23, 23, 44,
            131, 52, 169, 133, 224, 172, 24, 245, 214, 135, 204, 223, 70, 43, 172, 172, 204, 154, 65, 16, 23, 213, 155,
            19, 19, 184, 55, 102, 45, 23, 128, 72, 191, 203, 245, 186, 43, 225, 54, 39, 238, 230, 223, 114, 253, 156,
            198, 21, 148, 27, 210, 225, 130, 228, 67, 250, 38, 181, 38, 65, 131, 117, 80, 87, 205, 215, 181, 37, 239,
            108, 185, 232, 85, 17, 187, 78, 122, 133, 61, 192, 45, 112, 225, 126, 1, 120, 56, 158, 26, 184, 6, 44, 78,
            172, 14, 203, 85, 205, 218, 38, 25, 184, 167, 81, 63, 164, 164, 229, 186, 154, 62, 248, 204, 48, 131, 116,
            103, 86, 75, 26, 87, 146, 87, 132, 150, 177, 220, 0, 177, 59, 183, 154, 60, 137, 217, 152, 62, 218, 238, 0,
            136, 84, 18, 229, 0, 67, 30, 105, 196, 59, 75, 193, 18, 76, 242, 30, 120, 179, 145, 96, 13, 224, 26, 253, 23,
            105, 17, 207, 217, 72, 86, 178, 194, 69, 135, 135, 160, 82, 129, 155, 204, 195, 133, 125, 252, 81, 134, 91,
            77, 210, 146, 43, 95, 203, 188, 33, 100, 136, 193, 224, 33, 191, 22, 97, 41, 115, 8, 250, 173, 25, 12, 44,
            232, 182, 117, 76, 31, 6, 57, 249, 190, 222, 46, 35, 223, 21, 37, 225, 125, 101, 6, 110, 14, 143, 196, 178,
            73, 75, 63, 250, 53, 39, 36, 106, 231, 68, 252, 160, 228, 232, 26, 191, 214, 169, 217, 200, 16, 77, 229, 22,
            169, 16, 79, 0, 49, 3, 174, 36, 37, 169, 240, 120, 220, 38, 22, 163, 83, 33, 200, 203, 192, 188, 4, 116, 223,
            56, 167, 55, 70, 101, 78, 218, 6, 227, 249, 216, 24, 113, 66, 246, 165, 163, 99, 180, 53, 222, 68, 245, 92,
            100, 108, 158, 85, 82, 170, 237, 111, 35, 200, 197, 1, 239, 230, 220, 34, 67, 152, 216, 91, 183, 157, 194,
            98, 68, 190, 244, 169, 255, 52, 46, 182, 183, 195, 25, 160, 34, 109, 226, 202, 176, 74, 19, 94, 86, 13, 0,
            236, 161, 54, 170, 97, 61, 178, 151, 133, 252, 97, 144, 58, 138, 26, 106, 179, 150, 203, 151, 65
    };

    public static LaggedFibonacciGenerator createLaggedFibonacci() {
        LaggedFibonacciSeed seed =
                new LaggedFibonacciSeed(LAGGED_FIBONACCI_J, LAGGED_FIBONACCI_K, LAGGED_FIBONACCI_HISTORY);
        return new LaggedFibonacciGenerator(seed, LaggedFibonacciGenerator.Operation.XOR);
    }

    public static XorShiftGenerator createXorShift() {
        return new XorShiftGenerator(XORSHIFT_SEED);
    }

    public static double findPrime(PrimeVerifier verifier, PseudoRandomNumberGenerator generator, int size) throws PseudoRandomNumberException {
        boolean prime = false;
        long beginTime, endTime;
        BigInteger integer = null;
        beginTime = System.currentTimeMillis();
        while (!prime) { // Loop util prime is found
            integer = generator.createNumber(size/8);
            prime = true;
            for (int i = 0; i < AMOUNT && prime; i++) { // Test for probable prime 'AMOUNT' times
                prime = verifier.verify(integer);
            }
        }
        endTime = System.currentTimeMillis();
        double time = ((double)(endTime - beginTime) / TO_SECONDS);
        System.out.println("Prime " + integer.toString() + " found, took " + time + " seconds");
        System.out.println("");
        return time;
    }

    public static double findPrime(Constructor primeVerifierConstructor, PseudoRandomNumberGenerator generator, int size) throws InvocationTargetException, InstantiationException, IllegalAccessException, PseudoRandomNumberException {
        PrimeVerifier verifier = (PrimeVerifier) primeVerifierConstructor.newInstance(generator);
        System.out.println("Generating prime (" +
                "verifier = " + verifier.getClass().getName() +
                ", generator = " + generator.getClass().getName() +
                ", size = " + size +
                ") : ");
        return findPrime(verifier, generator, size);
    }
}
