package Entity;

public class Row
{
    private LazyImmutableInt id;
    private int number;
    private int id_Hall;
    private int id_PlaceCategory;
    private int count;

    public Row()
    {
        number=0;
        id_Hall=0;
        id_PlaceCategory=0;
        count=0;
    }

    public Row(int id, int number, int id_Hall, int id_PlaceCategory, int count)
    {
        this.id = new LazyImmutableInt();
        this.id.setValue(id);
        this.number = number;
        this.id_Hall = id_Hall;
        this.id_PlaceCategory = id_PlaceCategory;
        this.count = count;
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

    public int getId_Hall()
    {
        return id_Hall;
    }

    public void setId_Hall(int id_Hall)
    {
        this.id_Hall = id_Hall;
    }

    public int getId_PlaceCategory()
    {
        return id_PlaceCategory;
    }

    public void setId_PlaceCategory(int id_PlaceCategory)
    {
        this.id_PlaceCategory = id_PlaceCategory;
    }

    public int getCount()
    {
        return count;
    }

    public void setCount(int count)
    {
        this.count = count;
    }
}

