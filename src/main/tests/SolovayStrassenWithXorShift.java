package src.main.tests;

import src.main.number_generator.PseudoRandomNumberException;
import src.main.number_generator.PseudoRandomNumberGenerator;
import src.main.number_generator.xor_shift.XorShiftGenerator;
import src.main.prime_verifier.SolovayStrassen;

import java.lang.reflect.InvocationTargetException;

public class SolovayStrassenWithXorShift extends AbstractTest {

    public static void test() throws NoSuchMethodException, PseudoRandomNumberException, InvocationTargetException, InstantiationException, IllegalAccessException {
        int[] sizes = {40, 56, 80, 128, 168, 224, 256, 512, 1024, 2048, 4096};
        XorShiftGenerator xorShiftGenerator;
        for (int size : sizes) {
            xorShiftGenerator = createXorShift();
            findPrime(SolovayStrassen.class.getConstructor(PseudoRandomNumberGenerator.class), xorShiftGenerator, size);
        }
    }
}
