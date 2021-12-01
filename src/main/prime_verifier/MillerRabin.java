package src.main.prime_verifier;

import src.main.number_generator.PseudoRandomNumberException;
import src.main.number_generator.PseudoRandomNumberGenerator;

import java.math.BigInteger;

public class MillerRabin extends PrimeVerifier {
    private static final BigInteger TWO = new BigInteger(new byte[]{2});

    public MillerRabin(PseudoRandomNumberGenerator generator) {
        super(generator);
    }

    @Override
    public boolean verify(BigInteger integer) throws PseudoRandomNumberException {
        if (integer.compareTo(BigInteger.ONE) > 0 && // O valor é positivo (positivo) ?
            integer.testBit(0)) { // O último bit é um (impar)?
            BigInteger nMinusOne = integer.flipBit(0);
            BigInteger powerOfTwo = TWO;
            BigInteger k, q;
            k = BigInteger.ONE;
            q = null;
            BigInteger[] div;

            // Procura-se pelos valores de 'k' e 'q'
            while (powerOfTwo.compareTo(nMinusOne) <= 0) {
                div = nMinusOne.divideAndRemainder(powerOfTwo);
                if (div[1].compareTo(BigInteger.ZERO) == 0) {
                    q = div[0];
                    break;
                }
                powerOfTwo = powerOfTwo.shiftLeft(1);
                k = k.add(BigInteger.ONE);
            }

            // comparação de segurança para evitar NullPointer
            if (q == null) {
                return false;
            }

            // Gera o um valor 'a' aleatório
            BigInteger a = generator.createNumberSmallerThan(integer);
            while (a.compareTo(integer) >= 0) { // Esse while existe por segurança. Não precisa mesmo existir.
                a = generator.createNumberSmallerThan(integer);
            }

            BigInteger x = a.modPow(q, integer);
            // verifica-se o primeiro teste de primalidade, somente precisa-se que um seja verdade
            if (x.compareTo(BigInteger.ONE) == 0 || x.compareTo(nMinusOne) == 0) {
                return true;
            }

            BigInteger kMinuxOne = k.subtract(BigInteger.ONE);
            for (BigInteger j = BigInteger.ZERO; j.compareTo(kMinuxOne) <= 0; j = j.add(BigInteger.ONE)) {
                // procura-se um valor para 'r' que satisfaça a segunda condição do teste de primalidade
                x = x.modPow(TWO, integer);
                if (x.compareTo(nMinusOne) == 0) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }
}
