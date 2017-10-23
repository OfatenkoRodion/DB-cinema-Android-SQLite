package best.the.rodionofatenko.com.clientfortestingcodequality;

import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Toast;

import java.util.ArrayList;

import Adapter.FilmAdapter;
import Adapter.HallAdapter;
import Adapter.PlaceCategoryAdapter;
import Adapter.SessionAdapter;
import Entity.Film;
import Entity.Hall;
import Entity.PlaceCategory;
import Entity.Session;

public class AddSessionActivity extends AppCompatActivity implements View.OnClickListener
{
    ArrayList<Film> films = new ArrayList<Film>();
    ArrayList<Hall> halls = new ArrayList<Hall>();
    ArrayList<Session> sessions = new ArrayList<Session>();
    ArrayList<PlaceCategory> placeCategorys = new ArrayList<PlaceCategory>();
    FilmAdapter filmAdapter;
    HallAdapter hallAdapter;
    SessionAdapter sessionAdapter;
    PlaceCategoryAdapter placeCategoryAdapter;
    Button insertFilm, insertHall, insertSession,insertPlaceCategory;
    DB_Cinema db_cinema;

    EditText film_name, film_description;
    EditText hall_number, hall_spaciousness;
    EditText session_date, session_time, session_idFilm, session_IdHall;
    EditText placeCategory_name;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_session);
        db_cinema = new DB_Cinema(this);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        initializationButtons();
        initializationEditText();
        initializationListViewFilm();
        initializationListViewHall();
        initializationListViewSession();
        initializationListViewPlaceCategory();
        initializationHorizontalScrollView();
    }
    @Override
    public void onClick(View view)
    {
        if(view == insertFilm)
        {
            insertFilmOnClickActions();
        }
        else
        if(view == insertHall)
        {
            insertHallOnClickActions();
        }
        else
        if(view == insertSession)
        {
            insertSessionOnClickActions();
        }
        else
        if(view == insertPlaceCategory)
        {
            insertPlaceCategoryOnClickActions();
        }

    }
    private void initializationButtons()
    {
        insertFilm=(Button) findViewById(R.id.insertFilmButton);
        insertHall=(Button) findViewById(R.id.insertHallButton);
        insertSession=(Button) findViewById(R.id.insertSessionButton);
        insertPlaceCategory=(Button) findViewById(R.id.insertPlaceCategory);

        insertFilm.setOnClickListener(this);
        insertHall.setOnClickListener(this);
        insertSession.setOnClickListener(this);
        insertPlaceCategory.setOnClickListener(this);
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

        placeCategory_name=(EditText)findViewById(R.id.placeCategoryNameEdit);
    }
    private void initializationListViewFilm()
    {
        films.clear();
        films.addAll(0, db_cinema.getListFilm());
        filmAdapter = new FilmAdapter(this, films);
        ListView lvFilm = (ListView) findViewById(R.id.filmsList);
        lvFilm.setAdapter(filmAdapter);
    }
    private void initializationListViewHall()
    {
        try{
            Cursor cursor= db_cinema.getWritableDatabase().query("Hall",null,null,null,null,null,null);
            if (cursor.moveToFirst()){
                do  {
                    Hall hall = new Hall(cursor.getInt(cursor.getColumnIndex("id")),cursor.getInt(cursor.getColumnIndex("number")),cursor.getInt(cursor.getColumnIndex("spaciousness")));
                    halls.add(hall) ;
                }   while (cursor.moveToNext());
            }
            hallAdapter = new HallAdapter(this,halls);
            ListView lvHall = (ListView) findViewById(R.id.hallsList);
            lvHall.setAdapter(hallAdapter);
        }
        catch (Exception e){
            Toast.makeText(this,e.toString(),Toast.LENGTH_LONG).show();
        }
    }
    private void initializationListViewSession()
    {
        try{
            Cursor cursor= db_cinema.getWritableDatabase().query("Session",null,null,null,null,null,null);
            if (cursor.moveToFirst()){
                do  {
                    Session session = new Session(cursor.getInt(cursor.getColumnIndex("id")),cursor.getString(cursor.getColumnIndex("date")),cursor.getString(cursor.getColumnIndex("time")),cursor.getInt(cursor.getColumnIndex("id_Hall")),cursor.getInt(cursor.getColumnIndex("id_Film")));
                    sessions.add(session);
                } while (cursor.moveToNext());
            }
            sessionAdapter = new SessionAdapter(this,sessions);
            ListView lvSession = (ListView) findViewById(R.id.sessionList);
            lvSession.setAdapter(sessionAdapter);
        }
        catch (Exception e){
            Toast.makeText(this,e.toString(),Toast.LENGTH_LONG).show();
        }
    }
    private void initializationListViewPlaceCategory()
    {
        placeCategorys.clear();
        placeCategorys.addAll(0, db_cinema.getListPlaceCategory());
        placeCategoryAdapter = new PlaceCategoryAdapter(this, placeCategorys);
        ListView lvPlaceCategory = (ListView) findViewById(R.id.placeCategoryList);
        lvPlaceCategory .setAdapter(placeCategoryAdapter);
    }
    private void initializationHorizontalScrollView()
    {
        final HorizontalScrollView hScroll = (HorizontalScrollView) findViewById(R.id.scrollHorizontal);
        final ScrollView vScroll = (ScrollView)findViewById(R.id.scrollVertical);
        vScroll.setOnTouchListener(new View.OnTouchListener() { //inner scroll listener
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
        hScroll.setOnTouchListener(new View.OnTouchListener() { //outer scroll listener
            private float mx, my, curX, curY;
            private boolean started = false;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                curX = event.getX();
                curY = event.getY();
                int dx = (int) (mx - curX);
                int dy = (int) (my - curY);
                switch (event.getAction()) {
                    case MotionEvent.ACTION_MOVE:
                        if (started) {
                            vScroll.scrollBy(0, dy);
                            hScroll.scrollBy(dx, 0);
                        } else {
                            started = true;
                        }
                        mx = curX;
                        my = curY;
                        break;
                    case MotionEvent.ACTION_UP:
                        vScroll.scrollBy(0, dy);
                        hScroll.scrollBy(dx, 0);
                        started = false;
                        break;
                }
                return true;
            }
        });
    }
    private void insertFilmOnClickActions()
    {
        db_cinema.addFilm(film_name.getText().toString(),film_description.getText().toString());
        films.clear();
        films.addAll(0, db_cinema.getListFilm());
        filmAdapter.notifyDataSetChanged();
    }
    private void insertHallOnClickActions()
    {
        db_cinema.addHall(Integer.valueOf(hall_number.getText().toString()),Integer.valueOf(hall_spaciousness.getText().toString()));
        halls.clear();
        halls.addAll(0, db_cinema.getListHall());
        hallAdapter.notifyDataSetChanged();
    }
    private void insertSessionOnClickActions()
    {
        db_cinema.addSession(session_date.getText().toString(),
                session_time.getText().toString(),
                Integer.valueOf(session_IdHall.getText().toString()),
                Integer.valueOf(session_idFilm.getText().toString()));
        sessions.clear();
        sessions.addAll(0, db_cinema.getListSession());
        sessionAdapter.notifyDataSetChanged();
    }
    private void insertPlaceCategoryOnClickActions()
    {
        db_cinema.addPlaceCategory(placeCategory_name.getText().toString());
        placeCategorys.clear();
        placeCategorys.addAll(0, db_cinema.getListPlaceCategory());
        placeCategoryAdapter.notifyDataSetChanged();
    }
}
