package best.the.rodionofatenko.com.clientfortestingcodequality;

import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import Adapter.FilmAdapter;
import Adapter.HallAdapter;
import Adapter.SessionAdapter;
import Entity.Film;
import Entity.Hall;
import Entity.Session;

public class AddSessionActivity extends AppCompatActivity implements View.OnClickListener
{
    ArrayList<Film> films = new ArrayList<Film>();
    ArrayList<Hall> halls = new ArrayList<Hall>();
    ArrayList<Session> sessions = new ArrayList<Session>();
    FilmAdapter filmAdapter;
    HallAdapter hallAdapter;
    SessionAdapter sessionAdapter;
    Button insertFilm, insertHall, insertSession;
    Button showFilms, showHalls, showSessions;
    DB_Cinema  bd_cinema;

    EditText film_name, film_description;
    EditText hall_number, hall_spaciousness;
    EditText session_date, session_time, session_idFilm, session_IdHall;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_session);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        bd_cinema = new DB_Cinema(this);

        initializationButtons();
        initializationEditText();
        initializationListViewFilm();
        initializationListViewHall();
        initializationListViewSession();
    }

    @Override
    public void onClick(View view)
    {
        try{
        if(view == insertFilm)
        {
            bd_cinema.addFilm(film_name.getText().toString(),film_description.getText().toString());
            films.clear();
            Cursor cursor= bd_cinema.getWritableDatabase().query("Film",null,null,null,null,null,null);
            if (cursor.moveToFirst()){
                do  {
                    Film film = new Film(cursor.getInt(cursor.getColumnIndex("id")), cursor.getString(cursor.getColumnIndex("name")), cursor.getString(cursor.getColumnIndex("description")));
                    films.add(film);
                } while (cursor.moveToNext());
            }
            //films=(ArrayList<Film>)bd_cinema.getListFilm();
            filmAdapter.notifyDataSetChanged();

        } else
        if(view == insertHall)
        {
            bd_cinema.addHall(Integer.valueOf(hall_number.getText().toString()),Integer.valueOf(hall_spaciousness.getText().toString()));
            halls.clear();
            Cursor cursor2= bd_cinema.getWritableDatabase().query("Hall",null,null,null,null,null,null);
            if (cursor2.moveToFirst()){
                do
                {
                     Hall hall = new Hall(cursor2.getInt(cursor2.getColumnIndex("id")),cursor2.getInt(cursor2.getColumnIndex("number")),cursor2.getInt(cursor2.getColumnIndex("spaciousness")));
                     halls.add(hall) ;
                } while (cursor2.moveToNext());
           }

            hallAdapter.notifyDataSetChanged();
        } else
        if(view == insertSession)
        {
            bd_cinema.addSession(session_date.getText().toString(),
                                session_time.getText().toString(),
                                Integer.valueOf(session_IdHall.getText().toString()),
                                Integer.valueOf(session_idFilm.getText().toString()));
           sessions.clear();
            Cursor cursor= bd_cinema.getWritableDatabase().query("Session",null,null,null,null,null,null);
            if (cursor.moveToFirst()){
                do  {
                    Session session = new Session(cursor.getInt(cursor.getColumnIndex("id")),cursor.getString(cursor.getColumnIndex("date")),cursor.getString(cursor.getColumnIndex("time")),cursor.getInt(cursor.getColumnIndex("id_Hall")),cursor.getInt(cursor.getColumnIndex("id_Film")));
                    sessions.add(session);
                } while (cursor.moveToNext());
            }
            sessionAdapter.notifyDataSetChanged();
        } else
        if(view == showFilms)
        {
            Cursor cursor= bd_cinema.getWritableDatabase().query("Film",null,null,null,null,null,null);
            if (cursor.moveToFirst()){
                do  {
                    Toast.makeText(getApplicationContext(),
                            "id:"+cursor.getInt(cursor.getColumnIndex("id"))
                                    +" name:"+cursor.getString(cursor.getColumnIndex("name"))
                                    +" description:"+cursor.getString(cursor.getColumnIndex("description")), Toast.LENGTH_SHORT).show();

                } while (cursor.moveToNext());
            }
        } else
        if(view == showHalls)
        {
            Cursor cursor= bd_cinema.getWritableDatabase().query("Hall",null,null,null,null,null,null);
            if (cursor.moveToFirst()){
                do  {
                    Toast.makeText(getApplicationContext(),
                            "id:"+cursor.getInt(cursor.getColumnIndex("id"))
                                    +" number:"+cursor.getInt(cursor.getColumnIndex("number"))
                                    +" spaciousness:"+cursor.getInt(cursor.getColumnIndex("spaciousness")), Toast.LENGTH_SHORT).show();

                } while (cursor.moveToNext());
            }
        } else
        if(view == showSessions)
        {
            Cursor cursor= bd_cinema.getWritableDatabase().query("Session",null,null,null,null,null,null);
            if (cursor.moveToFirst()){
                do  {
                    Toast.makeText(getApplicationContext(),
                            "session_date:"+cursor.getString(cursor.getColumnIndex("date"))
                                    +" session_time:"+cursor.getString(cursor.getColumnIndex("time"))
                                    +" id_Hall:"+cursor.getInt(cursor.getColumnIndex("id_Hall"))
                                    +" id_Film:"+cursor.getInt(cursor.getColumnIndex("id_Film")), Toast.LENGTH_SHORT).show();
                } while (cursor.moveToNext());
            }
        }
        }
        catch (Exception e){
            Toast.makeText(this,e.toString(),Toast.LENGTH_LONG).show();
        }
    }
    private void initializationButtons()
    {
        insertFilm=(Button) findViewById(R.id.insertFilmButton);
        insertHall=(Button) findViewById(R.id.insertHallButton);
        insertSession=(Button) findViewById(R.id.insertSessionButton);

        insertFilm.setOnClickListener(this);
        insertHall.setOnClickListener(this);
        insertSession.setOnClickListener(this);

        showFilms=(Button)findViewById(R.id.filmButton);
        showHalls=(Button)findViewById(R.id.hallButton);
        showSessions=(Button)findViewById(R.id.sessionButton);

        showFilms.setOnClickListener(this);
        showHalls.setOnClickListener(this);
        showSessions.setOnClickListener(this);
    }
    private void initializationEditText()
    {
        film_name = (EditText) findViewById(R.id.filmNameEdit);
        film_description=(EditText)findViewById(R.id.filmDescriptionEdit);

        hall_number=(EditText)findViewById(R.id.hallNomberEdit);
        hall_spaciousness=(EditText)findViewById(R.id.hallSpaciousnessEdit);

        session_date=(EditText)findViewById(R.id.sessionDateEdit);
        session_time=(EditText)findViewById(R.id.sessionTimeEdit);
        session_idFilm=(EditText)findViewById(R.id.sessionId_FilmEdit);
        session_IdHall=(EditText)findViewById(R.id.sessionId_HallEdit);
    }
    private void initializationListViewFilm()
    {
        //films=(ArrayList<Film>)bd_cinema.getListFilm();

        Cursor cursor= bd_cinema.getWritableDatabase().query("Film",null,null,null,null,null,null);
        if (cursor.moveToFirst()){
            do  {
                Film film = new Film(cursor.getInt(cursor.getColumnIndex("id")), cursor.getString(cursor.getColumnIndex("name")), cursor.getString(cursor.getColumnIndex("description")));
                films.add(film);
            } while (cursor.moveToNext());
        }
        filmAdapter = new FilmAdapter(this, films);
        ListView lvFilm = (ListView) findViewById(R.id.filmsList);
        lvFilm.setAdapter(filmAdapter);
    }
    private void initializationListViewHall()
    {
        try{
            Cursor cursor= bd_cinema.getWritableDatabase().query("Hall",null,null,null,null,null,null);
            if (cursor.moveToFirst()){
                do  {
                    Hall hall = new Hall(cursor.getInt(cursor.getColumnIndex("id")),cursor.getInt(cursor.getColumnIndex("number")),cursor.getInt(cursor.getColumnIndex("spaciousness")));
                    halls.add(hall) ;
                }   while (cursor.moveToNext());
            }
            hallAdapter = new HallAdapter(this,halls);
            ListView lvFilmH = (ListView) findViewById(R.id.hallsList);
            lvFilmH.setAdapter(hallAdapter);
        }
        catch (Exception e){
            Toast.makeText(this,e.toString(),Toast.LENGTH_LONG).show();
        }
    }
    private void initializationListViewSession()
    {
        try{
            Cursor cursor= bd_cinema.getWritableDatabase().query("Session",null,null,null,null,null,null);
            if (cursor.moveToFirst()){
                do  {
                    Session session = new Session(cursor.getInt(cursor.getColumnIndex("id")),cursor.getString(cursor.getColumnIndex("date")),cursor.getString(cursor.getColumnIndex("time")),cursor.getInt(cursor.getColumnIndex("id_Hall")),cursor.getInt(cursor.getColumnIndex("id_Film")));
                    sessions.add(session);
                } while (cursor.moveToNext());
            }
            sessionAdapter = new SessionAdapter(this,sessions);
            ListView lvFilmS = (ListView) findViewById(R.id.sessionList);
            lvFilmS.setAdapter(sessionAdapter);
        }
        catch (Exception e){
            Toast.makeText(this,e.toString(),Toast.LENGTH_LONG).show();
        }
    }
}
