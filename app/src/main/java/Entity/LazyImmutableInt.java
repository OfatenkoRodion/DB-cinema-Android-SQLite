package Entity;

public class LazyImmutableInt
{
    private int value=-1;

    public void setValue(int value) {
        if (this.value != -1) {
            return; // the value has already been set
        }
        this.value = value;
    }
    public int getValue() {return value;}
}
