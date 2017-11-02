package Entity;

public class Ticket
{
    private LazyImmutableInt id;
    private int id_Session;
    private int id_Place;
    private String status;

    public Ticket()
    {
        id_Session=0;
        id_Place=0;
        status="free";
    }

    public Ticket(int id, int id_Session, int id_Place, String status)
    {
        this.id = new LazyImmutableInt();
        this.id.setValue(id);
        this.id_Session = id_Session;
        this.id_Place = id_Place;
        this.status = status;
    }

    public int getId_Session()
    {
        return id_Session;
    }

    public void setId_Session(int id_Session)
    {
        this.id_Session = id_Session;
    }

    public int getId_Place()
    {
        return id_Place;
    }

    public void setId_Place(int id_Place)
    {
        this.id_Place = id_Place;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public int getId()
    {
        return id.getValue();
    }

}

