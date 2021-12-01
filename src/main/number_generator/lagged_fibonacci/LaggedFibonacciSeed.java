package src.main.number_generator.lagged_fibonacci;
import src.main.number_generator.PseudoRandomNumberException;
import src.main.number_generator.PseudoRandomNumberGenerator;

import java.util.LinkedList;

public class LaggedFibonacciSeed {

    public final int j;
    public final int k;
    private final LinkedList<Integer> history;

    public LaggedFibonacciSeed(int j, int k, PseudoRandomNumberGenerator generator) throws PseudoRandomNumberException {
        if (j > k) {
            int aux = j;
            j = k;
            k = aux;
        }
        this.j = j;
        this.k = k;
        this.history = new LinkedList<>();
        byte[] bytes;
        for (int i = 0; i < k; i++) {
            bytes = generator.generate(1);
            this.history.addLast(bytes[0] & 0xFF);
        }
    }

    public LaggedFibonacciSeed(int j, int k, int[] history) {
        if (j > k) {
            int aux = j;
            j = k;
            k = aux;
        }
        this.j = j;
        this.k = k;
        this.history = new LinkedList<>();
        assert history.length >= k;
        for (int i = 0; i < k; i++) {
            int value = history[i];
            this.history.addLast(value);
        }
    }

    public int getFromHistory(int index) {
        return this.history.get(k-index);
    }

    public void pushToHistory(int value) {
        this.history.removeFirst();
        this.history.addLast(value);
    }
}