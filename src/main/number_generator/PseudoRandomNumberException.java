package src.main.number_generator;
public class PseudoRandomNumberException extends Exception {
    /* Define um erro ao gerar números pseudo aleatórios */
    public PseudoRandomNumberException(String message) {
        super(message);
    }
}
