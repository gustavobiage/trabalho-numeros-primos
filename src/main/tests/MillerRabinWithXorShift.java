package src.main.tests;

import src.main.number_generator.PseudoRandomNumberException;
import src.main.number_generator.PseudoRandomNumberGenerator;
import src.main.number_generator.xor_shift.XorShiftGenerator;
import src.main.prime_verifier.MillerRabin;

import java.lang.reflect.InvocationTargetException;

public class MillerRabinWithXorShift extends AbstractTest {

    public static void test() throws NoSuchMethodException, PseudoRandomNumberException, InvocationTargetException, InstantiationException, IllegalAccessException {
        int[] sizes = {128, 256, 512, 1024, 2048, 4096};
        XorShiftGenerator xorShiftGenerator;
        for (int size : sizes) {
            // Find Prime (Miller Rabin, XorShift, seed=X)
            xorShiftGenerator = createXorShift();
            findPrime(MillerRabin.class.getConstructor(PseudoRandomNumberGenerator.class), xorShiftGenerator, size);
        }
    }
}
