package core.sort.util.vector;

public class OutOfComponentBoundException extends RuntimeException {
    public OutOfComponentBoundException() {
        super("More than 4 or less than 1 points per vertex are not supported");
    }

}
