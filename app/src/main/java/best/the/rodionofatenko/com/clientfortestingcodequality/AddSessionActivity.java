package best.the.rodionofatenko.com.clientfortestingcodequality;

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
import Adapter.PlaceAdapter;
import Adapter.PlaceCategoryAdapter;
import Adapter.PriceCategoryAdapter;
import Adapter.RowAdapter;
import Adapter.SessionAdapter;
import Entity.Film;
import Entity.Hall;
import Entity.Place;
import Entity.PlaceCategory;
import Entity.PriceCategory;
import Entity.Row;
import Entity.Session;

public class AddSessionActivity extends AppCompatActivity implements View.OnClickListener
{
    ArrayList<Film> films = new ArrayList<Film>();
    ArrayList<Hall> halls = new ArrayList<Hall>();
    ArrayList<Session> sessions = new ArrayList<Session>();
    ArrayList<PlaceCategory> placeCategorys = new ArrayList<PlaceCategory>();
    ArrayList<PriceCategory> priceCategorys = new ArrayList<PriceCategory>();
    ArrayList<Row> rows = new ArrayList<Row>();
    ArrayList<Place> places = new ArrayList<Place>();
    FilmAdapter filmAdapter;
    HallAdapter hallAdapter;
    SessionAdapter sessionAdapter;
    PlaceCategoryAdapter placeCategoryAdapter;
    PriceCategoryAdapter priceCategoryAdapter;
    RowAdapter rowAdapter;
    PlaceAdapter placeAdapter;
    Button insertFilm, insertHall, insertSession,insertPlaceCategory,insertPriceCategory,insertRow,insertPlace;
    DB_Cinema db_cinema;

    EditText film_name, film_description;
    EditText hall_number, hall_spaciousness;
    EditText session_date, session_time, session_idFilm, session_IdHall;
    EditText placeCategory_name;
    EditText priceCategory_Id_session,priceCategory_Id_PlaceCategory,priceCategory_Price;
    EditText row_Number,row_Id_Hall,row_Id_PlaceCategory, row_Сount;
    EditText place_Number, place_Id_Row;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_session);
        db_cinema = new DB_Cinema(this);
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        initializationButtons();
        initializationEditText();
        initializationListViewFilm();
        initializationListViewHall();
        initializationListViewSession();
        initializationListViewPlaceCategory();
        initializationListViewPriceCategory();
        initializationListViewRow();
        try{
        initializationListViewPlace();}
        catch (Exception ex){
            Toast.makeText(this,ex.toString()+ex.getCause(),Toast.LENGTH_LONG).show();
        }
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
        else
        if (view == insertPriceCategory)
        {
            insertPriceCategoryOnClickActions();
        }
        else
        if (view == insertRow)
        {
            insertRowOnClickActions();
        }
        else
        if (view == insertPlace)
        {
            try{
            insertPlaceOnClickActions();}
            catch (Exception ex){
                Toast.makeText(this,ex.toString()+ex.getCause(),Toast.LENGTH_LONG).show();
            }
        }
    }
    private void initializationButtons()
    {
        insertFilm=(Button) findViewById(R.id.insertFilmButton);
        insertHall=(Button) findViewById(R.id.insertHallButton);
        insertSession=(Button) findViewById(R.id.insertSessionButton);
        insertPlaceCategory=(Button) findViewById(R.id.insertPlaceCategoryButton);
        insertPriceCategory=(Button) findViewById(R.id.insertPriceCategoryButton);
        insertRow=(Button) findViewById(R.id.insertRowButton);
        insertPlace=(Button) findViewById(R.id.insertPlaceButton);

        insertFilm.setOnClickListener(this);
        insertHall.setOnClickListener(this);
        insertSession.setOnClickListener(this);
        insertPlaceCategory.setOnClickListener(this);
        insertPriceCategory.setOnClickListener(this);
        insertRow.setOnClickListener(this);
        insertPlace.setOnClickListener(this);
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

        priceCategory_Id_PlaceCategory=(EditText)findViewById(R.id.priceCategoryId_PlaceCategoryEdit);
        priceCategory_Id_session=(EditText)findViewById(R.id.priceCategoryId_sessionEdit);
        priceCategory_Price=(EditText)findViewById(R.id. priceCategory_PriceEdit);

        row_Number=(EditText)findViewById(R.id.rowNumberEdit);
        row_Id_Hall=(EditText)findViewById(R.id.rowId_HallEdit);
        row_Id_PlaceCategory=(EditText)findViewById(R.id.rowId_PlaceCategoryEdit);
        row_Сount=(EditText)findViewById(R.id.rowСountEdit);

        place_Number=(EditText)findViewById(R.id.placeNumberEdit);
        place_Id_Row=(EditText)findViewById(R.id.placeId_RowEdit);
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
        halls.clear();
        halls.addAll(0,db_cinema.getListHall());
        hallAdapter = new HallAdapter(this,halls);
        ListView lvHall = (ListView) findViewById(R.id.hallsList);
        lvHall.setAdapter(hallAdapter);
    }
    private void initializationListViewSession()
    {
        sessions.clear();
        sessions.addAll(0,db_cinema.getListSession());
        sessionAdapter = new SessionAdapter(this,sessions);
        ListView lvSession = (ListView) findViewById(R.id.sessionList);
        lvSession.setAdapter(sessionAdapter);

    }
    private void initializationListViewPlaceCategory()
    {
        placeCategorys.clear();
        placeCategorys.addAll(0, db_cinema.getListPlaceCategory());
        placeCategoryAdapter = new PlaceCategoryAdapter(this, placeCategorys);
        ListView lvPlaceCategory = (ListView) findViewById(R.id.placeCategoryList);
        lvPlaceCategory .setAdapter(placeCategoryAdapter);
    }
    private void initializationListViewPriceCategory()
    {
        priceCategorys.clear();
        priceCategorys.addAll(0, db_cinema.getListPriceCategory());
        priceCategoryAdapter = new PriceCategoryAdapter(this, priceCategorys);
        ListView lvPriceCategory = (ListView) findViewById(R.id.priceCategoryList);
        lvPriceCategory .setAdapter(priceCategoryAdapter);
    }
    private void initializationListViewRow()
    {
        rows.clear();
        rows.addAll(0, db_cinema.getListRow());
        rowAdapter = new RowAdapter(this, rows);
        ListView lvRow = (ListView) findViewById(R.id.rowList);
        lvRow.setAdapter(rowAdapter);
    }
    private void initializationListViewPlace()
    {
        places.clear();
        places.addAll(0, db_cinema.getListPlace());
        placeAdapter = new PlaceAdapter(this, places);
        ListView lvPlace = (ListView) findViewById(R.id.placeList);
        lvPlace.setAdapter(placeAdapter);
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
    private void insertPriceCategoryOnClickActions()
    {
        db_cinema.addPriceCategory(Integer.valueOf(priceCategory_Id_PlaceCategory.getText().toString()),Integer.valueOf(priceCategory_Id_session.getText().toString()),Integer.valueOf(priceCategory_Price.getText().toString()));
        priceCategorys.clear();
        priceCategorys.addAll(0, db_cinema.getListPriceCategory());
        priceCategoryAdapter.notifyDataSetChanged();
    }
    private void insertRowOnClickActions()
    {
        db_cinema.addRow(Integer.valueOf(row_Number.getText().toString()),Integer.valueOf(row_Id_Hall.getText().toString()),Integer.valueOf(row_Id_PlaceCategory.getText().toString()),Integer.valueOf(row_Сount.getText().toString()));
        rows.clear();
        rows.addAll(0, db_cinema.getListRow());
        rowAdapter.notifyDataSetChanged();
    }
    private void insertPlaceOnClickActions()
    {
        db_cinema.addPlace(Integer.valueOf(place_Number.getText().toString()),Integer.valueOf(place_Id_Row.getText().toString()));
        places.clear();
        places.addAll(0, db_cinema.getListPlace());
        placeAdapter.notifyDataSetChanged();
    }
}
