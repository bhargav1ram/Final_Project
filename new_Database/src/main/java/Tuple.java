/**
 * Generic tuple class to be used a util class throughout the game.
 */
public class Tuple<T1, T2> {
    // the two values of the tuple
    private T1 first;
    private T2 second;

    public Tuple(T1 i, T2 j) {
        setFirst(i);
        setSecond(j);
    }

    // getters and setters
    
    public T1 getFirst() {
        return first;
    }

    public void setFirst(T1 val) {
        first = val;
    }

    public T2 getSecond() {
        return second;
    }

    public void setSecond(T2 val) {
        second = val;
    }

    public String toString(){
        return "("+getFirst()+", "+getSecond()+")";
    }
}
