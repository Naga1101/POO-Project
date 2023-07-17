import java.io.Serializable;
import java.util.Objects;

public class Tuple<A, B> implements Serializable {
    private A first;
    private B second;

    public Tuple(A first, B second) {
        this.first = first;
        this.second = second;
    }

    public A getFirst() {
        return first;
    }

    public void setFirst(A first) {
        this.first = first;
    }

    public B getSecond() {
        return second;
    }

    public void setSecond(B second) {
        this.second = second;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tuple<A, B> tuple = (Tuple<A, B>) o;
        return Objects.equals(first, tuple.first) &&
                Objects.equals(second, tuple.second);
    }   

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }
}
