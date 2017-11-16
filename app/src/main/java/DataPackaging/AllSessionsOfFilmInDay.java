package DataPackaging;

import java.util.ArrayList;

import Entity.Film;


public class AllSessionsOfFilmInDay
{
    private ArrayList<String> allSessionsTimes;
    private Film film;

    public AllSessionsOfFilmInDay(ArrayList<String> allSessionsTimes, Film film)
    {
        this.allSessionsTimes = allSessionsTimes;
        this.film = film;
    }

    public ArrayList<String> getAllSessionsTimes()
    {
        return allSessionsTimes;
    }

    public Film getFilm()
    {
        return film;
    }
}
