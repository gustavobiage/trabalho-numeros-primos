package src.main.tests;

import src.main.number_generator.PseudoRandomNumberException;
import src.main.number_generator.xor_shift.XorShiftGenerator;

import java.math.BigInteger;

public class XorShiftTest extends AbstractTest {

    public static void test() throws PseudoRandomNumberException {
        XorShiftGenerator xorShift = createXorShift();
        int[] sizes = {40, 56, 80, 128, 168, 224, 256, 512, 1024, 2048, 4096};

        for (int size : sizes) {
            BigInteger integer = xorShift.createNumber(size/8);
            System.out.println("Número de " + size + " bits gerado com XORSHIFT : " + integer.toString());
        }
    }
}
