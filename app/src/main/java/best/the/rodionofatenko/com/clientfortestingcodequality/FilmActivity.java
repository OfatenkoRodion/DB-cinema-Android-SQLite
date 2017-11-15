package best.the.rodionofatenko.com.clientfortestingcodequality;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashSet;

import AdapterFilmView.FilmRecylerAdapter;
import AdapterFilmView.TimeRecylerAdapter;

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

        horizontalLinearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        recyclerView.setLayoutManager(horizontalLinearLayoutManager);
        adapter=new FilmRecylerAdapter();
        recyclerView.setAdapter(adapter);


        HashSet<String> allDate = db_cinema.getSetAllDate();
        ArrayList<ArrayList<String>> arrayLists = new ArrayList<ArrayList<String>>();

        for ( String date : allDate )
        {
            arrayLists.add(db_cinema.getListTimeByDate(date));
        }
        adapter.addAll(arrayLists);

    }
}
