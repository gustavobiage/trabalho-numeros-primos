package src.main.tests;

import src.main.number_generator.PseudoRandomNumberException;
import src.main.tests.*;

import java.lang.reflect.InvocationTargetException;

public class Main {

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, PseudoRandomNumberException {
        /* Pseudo Random Numbers */
        XorShiftTest.test();
        LaggedFibonacciTest.test();
//        RandomNumberGeneratorPerformance.test();
//
//        /* Prime Verifiers */
//        MillerRabinWithXorShift.test();
//        MillerRabinWithLaggedFibonacci.test();
//
//        SolovayStrassenWithXorShift.test();
//        SolovayStrassenWithLaggedFibonacci.test();
//
//        PrimeVerifierPerformance.test();
    }
}
