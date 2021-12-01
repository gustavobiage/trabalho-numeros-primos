package src.main.number_generator;

import java.math.BigInteger;

public interface PseudoRandomNumberGenerator {

    byte[] generate(int nBytes) throws PseudoRandomNumberException;

    default byte[] readAsUnsignedNumber(byte[] bytes) {
        int length = bytes.length + 1;
        byte[] unsignedBytes = new byte[length];
        unsignedBytes[0] = 0;
        System.arraycopy(bytes, 0, unsignedBytes, 1, bytes.length);
        return unsignedBytes;
    }

    default BigInteger createNumberSmallerThan(BigInteger integer) throws PseudoRandomNumberException {
        int length = integer.bitLength();
        int nBytes = length / 8;
        byte mask;
        if (length % 8 > 0) {
            nBytes++;
            mask = (byte) ((1 << ((length % 8) - 1)) - 1);
        } else {
            mask = (byte) 127;
        }
        byte[] unsignedNumberBytes = createBytes(nBytes);
        unsignedNumberBytes[1] = (byte) (unsignedNumberBytes[1] & mask);
        return new BigInteger(unsignedNumberBytes);
    }

    default byte[] createBytes(int nBytes) throws PseudoRandomNumberException {
        byte[] bytes = generate(nBytes);
        return readAsUnsignedNumber(bytes);
    }

    default BigInteger createNumber(int nBytes) throws PseudoRandomNumberException {
        try {
            byte[] unsignedNumberBytes = createBytes(nBytes);
            return new BigInteger(unsignedNumberBytes);
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
}
