package src.main.number_generator.xor_shift;

import src.main.number_generator.PseudoRandomNumberException;
import src.main.number_generator.PseudoRandomNumberGenerator;

public class XorShiftGenerator implements PseudoRandomNumberGenerator {

    private final byte[] memory = new byte[8];
    private int position = 8;
    private long seed;

    public static final long A = 13;
    public static final long B = 17;
    public static final long C = 5;

    public final long a;
    public final long b;
    public final long c;

    public XorShiftGenerator(long seed) {
        this(seed, A, B, C);
    }

    public XorShiftGenerator(long seed, long a, long b, long c) {
        this.seed = seed;
        this.a = a;
        this.b = b;
        this.c = c;
    }

    private void updateMemory() {
        // Generate new number
        seed ^= (seed << a);
        seed ^= (seed >>> b);
        seed ^= (seed << c);

        // Fill byte memory with number value
        int mask = ((1 << 8) - 1); // mask = 11111111b
        for (int i = 0; i < 8; i++) {
            memory[7-i] = (byte) ((seed >>> (i*8)) & mask);
        }
        position = 0;
    }

    public byte generate() {
        /*
         * O XorShift somente permite gerar valores do tipo 'long' (utilizado na seed).
         * Por este motivo, então mantemos uma memória de bytes do último valor gerado, e consumimos essa memória para
         * permitir gerar valores que não são múltiplos de oito bytes.
         */
        if (position == 8) {
            updateMemory();
        }
        return memory[position++];
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
