package DataPackaging;

public class AllSessionOfFilmInDayGroupBy2
{
    private AllSessionsOfFilmInDay allSessionsOfFilmInDay1;
    private AllSessionsOfFilmInDay allSessionsOfFilmInDay2;

    public AllSessionOfFilmInDayGroupBy2(AllSessionsOfFilmInDay allSessionsOfFilmInDay1, AllSessionsOfFilmInDay allSessionsOfFilmInDay2)
    {
        this.allSessionsOfFilmInDay1 = allSessionsOfFilmInDay1;
        this.allSessionsOfFilmInDay2 = allSessionsOfFilmInDay2;
    }

    public AllSessionsOfFilmInDay getAllSessionsOfFilmInDay1()
    {
        return allSessionsOfFilmInDay1;
    }

    public AllSessionsOfFilmInDay getAllSessionsOfFilmInDay2()
    {
        return allSessionsOfFilmInDay2;
    }
}
