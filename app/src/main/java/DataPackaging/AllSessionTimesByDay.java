package DataPackaging;

import java.util.ArrayList;

public class AllSessionTimesByDay
{
    private ArrayList<AllSessionsOfFilmInDay> allSessionsTimes = new ArrayList<>();
    private String date;

    public AllSessionTimesByDay(ArrayList<AllSessionsOfFilmInDay> allDate, String date)
    {
        allSessionsTimes.addAll(allDate);
        this.date = date;
    }

    public ArrayList<AllSessionsOfFilmInDay> getAllSessionsTimes()
    {
        return allSessionsTimes;
    }

    public String getDate()
    {
        return date;
    }
}
