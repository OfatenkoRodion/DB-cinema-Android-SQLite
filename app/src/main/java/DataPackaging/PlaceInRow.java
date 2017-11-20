package DataPackaging;


import java.util.ArrayList;

import Entity.Place;
import Entity.Row;

public class PlaceInRow
{
    private Row row;
    private ArrayList<Place> places;

    public PlaceInRow(Row row, ArrayList<Place> places)
    {
        this.row = row;
        this.places = places;
    }

    public Row getRow()
    {
        return row;
    }

    public ArrayList<Place> getPlaces()
    {
        return places;
    }
}
