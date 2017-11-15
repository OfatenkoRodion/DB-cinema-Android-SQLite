package best.the.rodionofatenko.com.clientfortestingcodequality;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import AdapterFilmView.TimeRecylerAdapter;

public class FilmActivity extends AppCompatActivity
{

    DB_Cinema db_cinema;
    private RecyclerView recyclerView;
    private LinearLayoutManager horizontalLinearLayoutManager;
    private TimeRecylerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film);
        db_cinema = new DB_Cinema(this);

        horizontalLinearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        recyclerView.setLayoutManager(horizontalLinearLayoutManager);
        adapter=new TimeRecylerAdapter();
        recyclerView.setAdapter(adapter);
        adapter.addAll(db_cinema.getListTimeByDate("12345"));
    }
}
