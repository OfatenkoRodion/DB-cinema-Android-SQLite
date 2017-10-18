package Entity;

public class Session
{
    private LazyImmutableInt id;
    private String date;
    private String time;
    private int id_Hall;
    private int id_Film;

    public Session()
    {
    }

    public Session(int id, String date, String time, int id_Hall, int id_Film)
    {
        this.id = new LazyImmutableInt();
        this.id.setValue(id);
        this.date = date;
        this.time = time;
        this.id_Hall = id_Hall;
        this.id_Film = id_Film;
    }

    public int getId()
    {
        return id.getValue();
    }

    public String getDate()
    {
        return date;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public String getTime()
    {
        return time;
    }

    public void setTime(String time)
    {
        this.time = time;
    }

    public int getId_Hall()
    {
        return id_Hall;
    }

    public void setId_Hall(int id_Hall)
    {
        this.id_Hall = id_Hall;
    }

    public int getId_Film()
    {
        return id_Film;
    }

    public void setId_Film(int id_Film)
    {
        this.id_Film = id_Film;
    }
}
