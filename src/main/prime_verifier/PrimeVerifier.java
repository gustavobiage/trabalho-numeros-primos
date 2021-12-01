package src.main.prime_verifier;

import src.main.number_generator.PseudoRandomNumberException;
import src.main.number_generator.PseudoRandomNumberGenerator;

import java.math.BigInteger;

public abstract class PrimeVerifier {
    protected PseudoRandomNumberGenerator generator;

    protected PrimeVerifier(PseudoRandomNumberGenerator generator) {
        this.generator = generator;
    }

    public abstract boolean verify(BigInteger integer) throws PseudoRandomNumberException;
}
