package Entity;

public class PriceCategory
{
    private LazyImmutableInt id;
    private int id_PlaceCategory;
    private int id_session;
    private int price;

    public PriceCategory()
    {
    }

    public PriceCategory(int id, int id_PlaceCategory, int id_session, int price)
    {
        this.id.setValue(id);
        this.id_PlaceCategory = id_PlaceCategory;
        this.id_session = id_session;
        this.price = price;
    }

    public int getId() {
        return id.getValue();
    }

    public int getId_PlaceCategory() {
        return id_PlaceCategory;
    }

    public void setId_PlaceCategory(int id_PlaceCategory) {
        this.id_PlaceCategory = id_PlaceCategory;
    }

    public int getId_session() {
        return id_session;
    }

    public void setId_session(int id_session) {
        this.id_session = id_session;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}

