package best.the.rodionofatenko.com.clientfortestingcodequality;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import java.util.Calendar;


import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;

import Adapter.FilmAdapter;
import Adapter.HallAdapter;
import Adapter.PlaceAdapter;
import Adapter.PlaceCategoryAdapter;
import Adapter.PriceCategoryAdapter;
import Adapter.RowAdapter;
import Adapter.SessionAdapter;
import Adapter.TicketAdapter;
import Entity.Film;
import Entity.Hall;
import Entity.Place;
import Entity.PlaceCategory;
import Entity.PriceCategory;
import Entity.Row;
import Entity.Session;
import Entity.Ticket;

public class AddSessionActivity extends AppCompatActivity implements View.OnClickListener
{
    ArrayList<Film> films = new ArrayList<Film>();
    ArrayList<Hall> halls = new ArrayList<Hall>();
    ArrayList<Session> sessions = new ArrayList<Session>();
    ArrayList<PlaceCategory> placeCategorys = new ArrayList<PlaceCategory>();
    ArrayList<PriceCategory> priceCategorys = new ArrayList<PriceCategory>();
    ArrayList<Row> rows = new ArrayList<Row>();
    ArrayList<Place> places = new ArrayList<Place>();
    ArrayList<Ticket> tickets = new ArrayList<Ticket>();

    FilmAdapter filmAdapter;
    HallAdapter hallAdapter;
    SessionAdapter sessionAdapter;
    PlaceCategoryAdapter placeCategoryAdapter;
    PriceCategoryAdapter priceCategoryAdapter;
    RowAdapter rowAdapter;
    PlaceAdapter placeAdapter;
    TicketAdapter ticketAdapter;

    Button insertFilm, insertHall, insertSession,insertPlaceCategory,insertPriceCategory,insertRow,insertPlace,insertTicket;

    DB_Cinema db_cinema;

    EditText film_name, film_description;
    EditText hall_number, hall_spaciousness;
    EditText session_date, session_time;
    EditText placeCategory_name;
    EditText priceCategory_Price;
    EditText row_Number,row_Сount;
    EditText place_Number;
    EditText ticket_Id_Session, ticket_Id_Place,ticket_Status;

    Spinner sessionId_FilmSpinner, sessionId_HallSpinner;
    Spinner priceCategoryId_PlaceCategorySpinner,priceCategoryId_sessionSpinner;
    Spinner rowId_HallSpinner,rowId_PlaceCategorySpinner;
    Spinner placeId_RowSpinner;

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
        initializationListViewPlace();
        initializationListViewTicket();

        initializationSessionsSpinner();
        initializationPriceCategorySpinner();
        initializationRowSpinner();
        initializationPlaceSpinner();

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
            insertPlaceOnClickActions();
        }
        else
        if (view == insertTicket)
        {
            insertTicketOnClickActions();
        }
        else
        if (view == session_date)
        {
            selectDateOnClickActions();
        }
        else
        if (view == session_time)
        {
            selectTimeOnClickActions();
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
        insertTicket=(Button) findViewById(R.id.insertTicketButton);

        insertFilm.setOnClickListener(this);
        insertHall.setOnClickListener(this);
        insertSession.setOnClickListener(this);
        insertPlaceCategory.setOnClickListener(this);
        insertPriceCategory.setOnClickListener(this);
        insertRow.setOnClickListener(this);
        insertPlace.setOnClickListener(this);
        insertTicket.setOnClickListener(this);
    }
    private void initializationEditText()
    {
        film_name = (EditText) findViewById(R.id.filmNameEdit);
        film_description=(EditText)findViewById(R.id.filmDescriptionEdit);

        hall_number=(EditText)findViewById(R.id.hallNomberEdit);
        hall_spaciousness=(EditText)findViewById(R.id.hallSpaciousnessEdit);

        session_date=(EditText)findViewById(R.id.sessionDateEdit);
        session_time=(EditText)findViewById(R.id.sessionTimeEdit);
        session_date.setOnClickListener(this);
        session_time.setOnClickListener(this);

        placeCategory_name=(EditText)findViewById(R.id.placeCategoryNameEdit);

        priceCategory_Price=(EditText)findViewById(R.id. priceCategory_PriceEdit);

        row_Number=(EditText)findViewById(R.id.rowNumberEdit);
        row_Сount=(EditText)findViewById(R.id.rowСountEdit);

        place_Number=(EditText)findViewById(R.id.placeNumberEdit);

        ticket_Id_Place=(EditText)findViewById(R.id.ticketId_PlaceEdit);
        ticket_Id_Session=(EditText)findViewById(R.id.ticketId_SessionEdit);
        ticket_Status=(EditText)findViewById(R.id.ticketStatusEdit);
    }
    private void initializationSessionsSpinner()
    {
        sessionId_HallSpinner = (Spinner) findViewById(R.id.sessionId_HallSpinner);
        sessionId_HallSpinner.setAdapter(hallAdapter);
        sessionId_HallSpinner.setPrompt("Halls");
        sessionId_HallSpinner.setSelection(0);

        sessionId_FilmSpinner = (Spinner) findViewById(R.id.sessionId_FilmSpinner);
        sessionId_FilmSpinner.setAdapter(filmAdapter);
        sessionId_FilmSpinner.setPrompt("Films");
        sessionId_FilmSpinner.setSelection(0);
    }
    private void initializationPriceCategorySpinner()
    {
        priceCategoryId_PlaceCategorySpinner = (Spinner) findViewById(R.id.priceCategoryId_PlaceCategorySpinner);
        priceCategoryId_PlaceCategorySpinner.setAdapter(placeCategoryAdapter);
        priceCategoryId_PlaceCategorySpinner.setPrompt("PriceCategory");
        priceCategoryId_PlaceCategorySpinner.setSelection(0);

        priceCategoryId_sessionSpinner = (Spinner) findViewById(R.id.priceCategoryId_sessionSpinner);
        priceCategoryId_sessionSpinner.setAdapter(sessionAdapter);
        priceCategoryId_sessionSpinner.setPrompt("PriceCategory");
        priceCategoryId_sessionSpinner.setSelection(0);
    }
    private void initializationRowSpinner()
    {
        rowId_HallSpinner = (Spinner) findViewById(R.id.rowId_HallSpinner);
        rowId_HallSpinner.setAdapter(hallAdapter);
        rowId_HallSpinner.setPrompt("Hall");
        rowId_HallSpinner.setSelection(0);

        rowId_PlaceCategorySpinner = (Spinner) findViewById(R.id.rowId_PlaceCategorySpinner);
        rowId_PlaceCategorySpinner.setAdapter(placeCategoryAdapter);
        rowId_PlaceCategorySpinner.setPrompt("PlaceCategory");
        rowId_PlaceCategorySpinner.setSelection(0);
    }
    private void initializationPlaceSpinner()
    {
        placeId_RowSpinner = (Spinner) findViewById(R.id.placeId_RowSpinner);
        placeId_RowSpinner.setAdapter(rowAdapter);
        placeId_RowSpinner.setPrompt("Place");
        placeId_RowSpinner.setSelection(0);
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
    private void initializationListViewTicket()
    {
        tickets.clear();
        tickets.addAll(0, db_cinema.getListTicket());
        ticketAdapter = new TicketAdapter(this, tickets);
        ListView lvTicket = (ListView) findViewById(R.id.ticketList);
        lvTicket.setAdapter(ticketAdapter);
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
        try
        {
            db_cinema.addSession(session_date.getText().toString(),
                    session_time.getText().toString(),
                    ((Hall) sessionId_HallSpinner.getSelectedItem()).getId(),
                    ((Film) sessionId_FilmSpinner.getSelectedItem()).getId());
            sessions.clear();
            sessions.addAll(0, db_cinema.getListSession());
            sessionAdapter.notifyDataSetChanged();
        }
        catch (Exception e)
        {
            Toast.makeText(this,e.toString(),Toast.LENGTH_LONG).show();
        }
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
        try
        {
            db_cinema.addPriceCategory(((PlaceCategory) priceCategoryId_PlaceCategorySpinner.getSelectedItem()).getId(), ((Session) priceCategoryId_sessionSpinner.getSelectedItem()).getId(), Integer.valueOf(priceCategory_Price.getText().toString()));
            priceCategorys.clear();
            priceCategorys.addAll(0, db_cinema.getListPriceCategory());
            priceCategoryAdapter.notifyDataSetChanged();
        }
        catch (Exception e)
        {
            Toast.makeText(this,e.toString(),Toast.LENGTH_LONG).show();
        }
    }
    private void insertRowOnClickActions()
    {
        try
        {
            db_cinema.addRow(Integer.valueOf(row_Number.getText().toString()),((Hall)rowId_HallSpinner.getSelectedItem()).getId(),((PlaceCategory)rowId_PlaceCategorySpinner.getSelectedItem()).getId(),Integer.valueOf(row_Сount.getText().toString()));
            rows.clear();
            rows.addAll(0, db_cinema.getListRow());
            rowAdapter.notifyDataSetChanged();
        }
        catch (Exception e)
        {
            Toast.makeText(this,e.toString(),Toast.LENGTH_LONG).show();
        }
    }
    private void insertPlaceOnClickActions()
    {
        db_cinema.addPlace(Integer.valueOf(place_Number.getText().toString()),((Row)placeId_RowSpinner.getSelectedItem()).getId());
        places.clear();
        places.addAll(0, db_cinema.getListPlace());
        placeAdapter.notifyDataSetChanged();
    }
    private void insertTicketOnClickActions()
    {
        db_cinema.addTicket(Integer.valueOf(ticket_Id_Session.getText().toString()),Integer.valueOf(ticket_Id_Place.getText().toString()),ticket_Status.getText().toString());
        tickets.clear();
        tickets.addAll(0, db_cinema.getListTicket());
        ticketAdapter.notifyDataSetChanged();
    }
    private void selectTimeOnClickActions()
    {
        final Calendar calendar = Calendar.getInstance();
        int hours = calendar.get(Calendar.HOUR_OF_DAY);
        int minutes = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener()
        {
            @Override
            public void onTimeSet(TimePicker view, int hours, int minutes)
            {
                session_time.setText(hours+":"+minutes);
            }
        },hours,minutes,false);
        timePickerDialog.show();
    }
    private void selectDateOnClickActions()
    {
        final Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day)
            {
                session_date.setText(day+"."+month+"."+year);
            }
        },day,month,year);
        datePickerDialog.show();
    }
}
