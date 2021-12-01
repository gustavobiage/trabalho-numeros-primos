package src.main.tests;

import src.main.number_generator.PseudoRandomNumberException;
import src.main.number_generator.lagged_fibonacci.LaggedFibonacciGenerator;
import src.main.number_generator.xor_shift.XorShiftGenerator;

import java.math.BigInteger;
import java.util.ArrayList;

public class LaggedFibonacciTest extends AbstractTest {

    public static void test() throws PseudoRandomNumberException {
        LaggedFibonacciGenerator laggedFibonacci = createLaggedFibonacci();
        BigInteger integer = laggedFibonacci.createNumber(4096/8);
        System.out.println("Número de 4096 bits gerado com LAGGED FIBONACCI : " + integer.toString());
    }

    private static void printLaggedFibonacciSeed() throws PseudoRandomNumberException {
        int j = 353;
        int k = 521;
        ArrayList<Integer> list = new ArrayList<>();
        XorShiftGenerator generator = new XorShiftGenerator(124123213);
        for (int i = 0; i < 521; i++) {
            list.add(generator.generate(1)[0] & 0xFF);
        }
        for (Integer integer : list) {
            System.out.print(", " + integer);
        }
    }
}
