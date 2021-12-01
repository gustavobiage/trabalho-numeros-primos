package src.main.number_generator.lagged_fibonacci;

import src.main.number_generator.PseudoRandomNumberException;
import src.main.number_generator.PseudoRandomNumberGenerator;

public class LaggedFibonacciGenerator implements PseudoRandomNumberGenerator {

    public enum Operation {
        ADDITION, SUBTRACTION, MULTIPLICATION, XOR;
    };

    public LaggedFibonacciSeed seed;
    public final int m;
    public final Operation operation;

    public LaggedFibonacciGenerator(LaggedFibonacciSeed seed) {
        this(seed, Operation.ADDITION);
    }

    public LaggedFibonacciGenerator(LaggedFibonacciSeed seed, Operation operation) {
        this.operation = operation;
        this.seed = seed;
        m = 1 << 8;
    }

    public byte generate() throws PseudoRandomNumberException {
        int s1, s2, s0;
        s1 = seed.getFromHistory(seed.j);
        s2 = seed.getFromHistory(seed.k);
        switch (operation) {
            case ADDITION:
            s0 = (s1 + s2) % m;
            break;
            case SUBTRACTION:
            s0 = (s1 - s2 + m) % m;
            break;
            case MULTIPLICATION:
            s0 = (s1 * s2) % m;
            break;
            case XOR:
            s0 = (s1 ^ s2) % m;
            break;
            default:
            throw new PseudoRandomNumberException("nenhuma operação foi definida");
        }
        seed.pushToHistory(s0);
        return (byte) s0;
    }

    @Override
    public byte[] generate(int nBytes) throws PseudoRandomNumberException {
        byte[] generated = new byte[nBytes];
        for (int i = 0; i < nBytes; i++) {
            generated[i] = this.generate();
        }
        return generated;
    }
}