package Entity;

public class Hall
{
    private LazyImmutableInt id;
    private int number;
    private int spaciousness;//вместительность

    public Hall()
    {

    }

    public Hall(int id, int number, int spaciousness)
    {
        this.id = new LazyImmutableInt();
        this.id.setValue(id);
        this.number = number;
        this.spaciousness = spaciousness;
    }

    public int getId()
    {
        return id.getValue();
    }

    public int getNumber()
    {
        return number;
    }

    public void setNumber(int number)
    {
        this.number = number;
    }

    public int getSpaciousness()
    {
        return spaciousness;
    }

    public void setSpaciousness(int spaciousness)
    {
        this.spaciousness = spaciousness;
    }
}
