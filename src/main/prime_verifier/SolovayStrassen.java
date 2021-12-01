package src.main.prime_verifier;

import src.main.number_generator.PseudoRandomNumberException;
import src.main.number_generator.PseudoRandomNumberGenerator;

import java.math.BigDecimal;
import java.math.BigInteger;

public class SolovayStrassen extends PrimeVerifier {

    private static final BigInteger TWO = new BigInteger(new byte[]{2});
    private static final BigInteger FOUR = new BigInteger("4");
    private static final BigInteger THREE = new BigInteger("3");
    private static final BigInteger MINUS_ONE = new BigInteger("-1");

    private static final BigInteger EIGHT = new BigInteger("8");
    private static final BigInteger FIVE = new BigInteger("5");

    public SolovayStrassen(PseudoRandomNumberGenerator generator) {
        super(generator);
    }

    @Override
    public boolean verify(BigInteger integer) throws PseudoRandomNumberException {
        if (integer.compareTo(BigInteger.ONE) > 0 && // O valor é positivo (positivo) ?
                integer.testBit(0)) { // O último bit é um (impar)?

            BigInteger nMinusOne = integer.subtract(BigInteger.ONE);

            BigInteger a = generator.createNumberSmallerThan(integer);
            while (a.compareTo(integer) >= 0) { // Esse while existe por segurança. Não precisa mesmo existir.
                a = generator.createNumberSmallerThan(integer);
            }

            // Encontra o Símbolo de Jacobi
            BigInteger x = jacobiSymbol(a, integer);
            // Realiza 'a' elevado ao possível número primo, menos um e então divido por dois.
            BigInteger x_ = a.modPow(nMinusOne.divide(TWO), integer);
            // Compara-se os valores obtidos para testar a primalidade.
            return x.compareTo(BigInteger.ZERO) != 0 && x.compareTo(x_) == 0;
        }
        return false;
    }

    public BigInteger jacobiSymbol(BigInteger a, BigInteger p) {
        BigInteger originalP = p;
        BigInteger x, aux;
        x = BigInteger.ONE;
        while (a.compareTo(BigInteger.ZERO) != 0) {
            while (a.mod(TWO).equals(BigInteger.ZERO)) {
                a = a.divide(TWO);
                aux = p.mod(EIGHT);
                if (aux.compareTo(THREE) == 0 || aux.compareTo(FIVE) == 0) {
                    x = x.multiply(MINUS_ONE);
                }
            }

            //Swap
            aux = a;
            a = p;
            p = aux;

            if (a.mod(FOUR).compareTo(THREE) == 0 && p.mod(FOUR).compareTo(THREE) == 0) {
                x = x.multiply(MINUS_ONE);
            }

            a = a.mod(p);
        }

        if (p.compareTo(BigInteger.ONE) == 0) {
            if (x.compareTo(MINUS_ONE) == 0) {
                return originalP.subtract(BigInteger.ONE);
            } else {
                return x;
            }
        } else {
            return BigInteger.ZERO;
        }
    }
}
