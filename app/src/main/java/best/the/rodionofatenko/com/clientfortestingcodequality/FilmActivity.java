package best.the.rodionofatenko.com.clientfortestingcodequality;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;

import AdapterFilmView.FilmRecylerAdapter;
import AdapterFilmView.TimeRecylerAdapter;
import DataPackaging.AllSessionTimesByDay;
import DataPackaging.AllSessionsOfFilmInDay;
import Entity.Film;

public class FilmActivity extends AppCompatActivity
{

    DB_Cinema db_cinema;
    private RecyclerView recyclerView;
    private LinearLayoutManager horizontalLinearLayoutManager;
    private FilmRecylerAdapter adapter;

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
        adapter=new FilmRecylerAdapter();
        recyclerView.setAdapter(adapter);

        HashSet<String> allDate = db_cinema.getSetAllDate();
        ArrayList<AllSessionTimesByDay> arrayLists = new ArrayList<AllSessionTimesByDay>();
        ArrayList<AllSessionsOfFilmInDay> allSessionsOfFilmInDays = new ArrayList<>();
        HashSet<Film> films = db_cinema.getHashSetFilm();

        for ( String date : allDate )
        {
            allSessionsOfFilmInDays.clear();
            for (Film film : films)
            {
                allSessionsOfFilmInDays.add(new AllSessionsOfFilmInDay(db_cinema.getListTimeByDate(date,String.valueOf(film.getId())),film));
            }
            arrayLists.add(new AllSessionTimesByDay(allSessionsOfFilmInDays,date));
        }
        adapter.addAll(arrayLists);
    }
}
