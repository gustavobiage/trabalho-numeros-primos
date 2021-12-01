package src.main.number_generator;

import java.math.BigInteger;
import java.util.ArrayList;

import src.main.number_generator.lagged_fibonacci.LaggedFibonacciGenerator;
import src.main.number_generator.lagged_fibonacci.LaggedFibonacciSeed;
import src.main.number_generator.xor_shift.XorShiftGenerator;
import src.main.tests.AbstractTest;

public class Test {
    
    public static void main(String[] args) throws PseudoRandomNumberException {
//        int j = 353;
//        int k = 521;
//        ArrayList<Integer> list = new ArrayList<>();
//        XorShiftGenerator generator = new XorShiftGenerator(124123213);
//        for (int i = 0; i < 521; i++) {
//            list.add(generator.generate(1)[0] & 0xFF);
//        }
//        for (int i = 0; i < list.size(); i++) {
//            System.out.print(", " + list.get(i));
//        }

        int[] sizes = {40, 56, 80, 128, 168, 224, 256, 512, 1024, 2048, 4096};
//        LaggedFibonacciSeed lfSeed = new LaggedFibonacciSeed(3, 7, new int[]{0, 6,4,2,1,8,9,3});
//        long xorSeed = 10;
//        PseudoRandomNumberGenerator[] generators = {new LaggedFibonacciGenerator(lfSeed), new XorShiftGenerator(xorSeed)};

        PseudoRandomNumberGenerator[] generators = {AbstractTest.createLaggedFibonacci(), AbstractTest.createXorShift()};
        final int AMOUNT = 100;
        long totalTime;
        long beginTime;
        long endTime;
        int calculated;
        for (int size : sizes) {
            for (PseudoRandomNumberGenerator generator : generators) {
                totalTime = 0;
                calculated = 0;
                for (int i = 0; i < AMOUNT; i++) {
                    BigInteger integer;
                    beginTime = System.currentTimeMillis();
                    try {
                        integer = generator.createNumber(size);
                        System.out.println("Generated (" + size + "): " + integer.toString());
                    } catch (PseudoRandomNumberException e) {
                        break;
                    }
                    endTime = System.currentTimeMillis();
                    calculated++;
                    totalTime = totalTime + (endTime - beginTime);
                }
                if (calculated == AMOUNT) {
                    System.out.println(generator.getClass().getName() + " (" + size + "): " + totalTime/ (double) calculated);
                }
            }
        }
    }
}
