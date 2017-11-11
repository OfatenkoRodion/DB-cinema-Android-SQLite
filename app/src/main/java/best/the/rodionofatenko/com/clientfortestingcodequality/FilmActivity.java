package best.the.rodionofatenko.com.clientfortestingcodequality;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class FilmActivity extends AppCompatActivity
{

    ListView timesList;
    ArrayAdapter timesAdapter;
    String[] items = new String[]{"one","two"};
    DB_Cinema db_cinema;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film);
        db_cinema = new DB_Cinema(this);
        timesList=(ListView)findViewById(R.id.timesList);

        timesAdapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,db_cinema.getListTimeByDate("1234"));
        timesList .setAdapter(timesAdapter);


    }
}
