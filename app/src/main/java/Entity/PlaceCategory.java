package Entity;

public class PlaceCategory
{
    private LazyImmutableInt id;
    private String name;

    public PlaceCategory()
    {
        name="";
    }

    public PlaceCategory(int id, String name)
    {
        this.id = new LazyImmutableInt();
        this.id.setValue(id);
        this.name = name;
    }

    public int getId()
    {
        return id.getValue();
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

}

