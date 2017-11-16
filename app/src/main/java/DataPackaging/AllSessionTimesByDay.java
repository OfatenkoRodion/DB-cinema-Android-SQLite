package DataPackaging;

import java.util.ArrayList;

public class AllSessionTimesByDay
{
    private ArrayList<String> allSessionsTimes = new ArrayList<>();
    private String date;

    public AllSessionTimesByDay(ArrayList<AllSessionsOfFilmInDay> allDate, String date)
    {
        for  (int i=0;i<allDate.size();i++)
        {
            this.allSessionsTimes.addAll(allDate.get(i).getAllSessionsTimes());
        }
        this.date = date;
    }

    public ArrayList<String> getAllSessionsTimes()
    {
        return allSessionsTimes;
    }

    public String getDate()
    {
        return date;
    }
}
