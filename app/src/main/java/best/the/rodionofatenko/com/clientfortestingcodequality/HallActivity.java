package best.the.rodionofatenko.com.clientfortestingcodequality;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;

import AdapterHallView.RowRecyclerAdapter;
import DataPackaging.PlaceInRow;
import Entity.Row;

public class HallActivity extends AppCompatActivity
{
    DB_Cinema db_cinema;
    private RecyclerView recyclerView;
    private RowRecyclerAdapter adapter;
    private LinearLayoutManager verticalLinearLayoutManager;

    private String filmName,time,date;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hall);
        db_cinema = new DB_Cinema(this);
        verticalLinearLayoutManager=new LinearLayoutManager(this);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        recyclerView.setLayoutManager(verticalLinearLayoutManager);
        adapter=new RowRecyclerAdapter();
        recyclerView.setAdapter(adapter);

        filmName=(getIntent().getStringExtra("filmName"));
        time=getIntent().getStringExtra("time");
        date=getIntent().getStringExtra("date");

        String hall_number=db_cinema.getHallByDateTimeFilm(db_cinema.getFilmIdByName(filmName),time,date);

        ArrayList<PlaceInRow> placeInRows = new ArrayList<>();
        ArrayList<Row> rows = db_cinema.getListRowByHall(hall_number);

        for (Row row: rows)
        {
          placeInRows.add(new PlaceInRow(row, db_cinema.getListPlacerByRowId(row.getId())));
        }

        adapter.addAll(placeInRows,db_cinema.getIdByDateTimeFilm(db_cinema.getFilmIdByName(filmName),time,date));
    }
}
