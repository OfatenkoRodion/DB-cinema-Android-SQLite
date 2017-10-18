package Entity;

public class Film
{
    private LazyImmutableInt id;
    private String name;
    private String description;

    public Film()
    {
        name="";
        description="";
    }

    public Film(int id, String name, String description)
    {
        this.id = new LazyImmutableInt();
        this.id.setValue(id);
        this.name = name;
        this.description = description;
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

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }
}

