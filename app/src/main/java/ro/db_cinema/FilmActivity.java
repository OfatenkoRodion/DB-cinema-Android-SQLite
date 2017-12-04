package ro.db_cinema;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashSet;

import AdapterFilmView.DayRecylerAdapter;
import DataPackaging.AllSessionTimesByDay;
import DataPackaging.AllSessionsOfFilmInDay;
import Entity.Film;

public class FilmActivity extends AppCompatActivity
{

    DB_Cinema db_cinema;
    private RecyclerView recyclerView;
    private LinearLayoutManager horizontalLinearLayoutManager;
    private DayRecylerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film);
        db_cinema = new DB_Cinema(this);

        initRecycler();
    }
    private void initRecycler()
    {
        horizontalLinearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        recyclerView.setLayoutManager(horizontalLinearLayoutManager);
        adapter=new DayRecylerAdapter();
        recyclerView.setAdapter(adapter);

        HashSet<String> allDate = db_cinema.getSetAllDate();
        ArrayList<AllSessionTimesByDay> arrayLists = new ArrayList<AllSessionTimesByDay>();
        ArrayList<AllSessionsOfFilmInDay> allSessionsOfFilmInDays = new ArrayList<>();
        HashSet<Film> films ;

        for ( String date : allDate )
        {
            films=db_cinema.getHashSetFilm((db_cinema.getHashSetFilmsByDate(date)));
            allSessionsOfFilmInDays.clear();
            for (Film film : films)
            {
                allSessionsOfFilmInDays.add(new AllSessionsOfFilmInDay(db_cinema.getListTimeByDate(date,String.valueOf(film.getId())),film));
            }
            arrayLists.add(new AllSessionTimesByDay(allSessionsOfFilmInDays,date));
            films.clear();
        }
        adapter.addAll(arrayLists);
    }
}
