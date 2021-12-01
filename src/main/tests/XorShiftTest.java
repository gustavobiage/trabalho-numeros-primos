package src.main.tests;

import src.main.number_generator.PseudoRandomNumberException;
import src.main.number_generator.xor_shift.XorShiftGenerator;

import java.math.BigInteger;

public class XorShiftTest extends AbstractTest {

    public static void test() throws PseudoRandomNumberException {
        XorShiftGenerator xorShift = createXorShift();
        BigInteger integer = xorShift.createNumber(4096/8);
        System.out.println("Número de 4096 bits gerado com XORSHIFT : " + integer.toString());
    }
}
