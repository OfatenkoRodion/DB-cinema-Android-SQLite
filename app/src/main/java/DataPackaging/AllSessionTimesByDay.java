package DataPackaging;

import java.util.ArrayList;

public class AllSessionTimesByDay
{
    private ArrayList<String> allSessionsTimes;
    private String date;

    public AllSessionTimesByDay(ArrayList<String> allDate, String date)
    {
        this.allSessionsTimes = allDate;
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
