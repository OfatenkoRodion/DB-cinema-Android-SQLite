package Entity;

public class Place
{
    private LazyImmutableInt id;
    private int id_Row;
    private int number;

    public Place()
    {
        number=0;
        id_Row =0;
    }

    public Place(int id, int number, int id_Row)
    {
        this.id = new LazyImmutableInt();
        this.id.setValue(id);
        this.number = number;
        this.id_Row = id_Row;
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

    public int getId_Row()
    {
        return id_Row;
    }

    public void setId_Row(int id_Row)
    {
        this.id_Row = id_Row;
    }

}

