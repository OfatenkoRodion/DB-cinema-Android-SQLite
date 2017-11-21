package best.the.rodionofatenko.com.clientfortestingcodequality;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

import Entity.Film;
import Entity.Hall;
import Entity.Place;
import Entity.PlaceCategory;
import Entity.PriceCategory;
import Entity.Row;
import Entity.Session;
import Entity.Ticket;

public class DB_Cinema extends SQLiteOpenHelper
{
    static final String BD_NAME ="Cinema";
    static final int VERSION =1;

    public DB_Cinema(Context context)
    {
        super(context, BD_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {   sqLiteDatabase.execSQL("PRAGMA foreign_keys=1;");
        sqLiteDatabase.execSQL("CREATE table Film(id integer PRIMARY KEY autoincrement, name text NOT NULL, description text NOT NULL, UNIQUE(name));");
        sqLiteDatabase.execSQL("CREATE table Hall(id integer PRIMARY KEY autoincrement, number integer NOT NULL, spaciousness integer NOT NULL, UNIQUE(number));");
        sqLiteDatabase.execSQL("CREATE table Session(id integer PRIMARY KEY autoincrement, date text NOT NULL, time text NOT NULL, id_Hall integer NOT NULL, id_Film integer NOT NULL," +
                " FOREIGN KEY(id_Film) REFERENCES Film(id)," +
                " FOREIGN KEY(id_Hall) REFERENCES Hall(id)," +
                " UNIQUE (date, time, id_Hall));");
        sqLiteDatabase.execSQL("CREATE table PlaceCategory(id integer PRIMARY KEY autoincrement, name text NOT NULL);");
        sqLiteDatabase.execSQL("CREATE table PriceCategory(id integer PRIMARY KEY autoincrement,id_PlaceCategory integer NOT NULL, id_session integer NOT NULL, price integer NOT NULL," +
                " FOREIGN KEY(id_PlaceCategory) REFERENCES PlaceCategory(id)," +
                " FOREIGN KEY(id_session) REFERENCES Session(id)," +
                " UNIQUE (id_PlaceCategory,id_session));");
        sqLiteDatabase.execSQL("CREATE table Row(id integer PRIMARY KEY autoincrement,number integer NOT NULL, id_Hall integer NOT NULL, id_PlaceCategory integer NOT NULL, count integer NOT NULL," +
                " FOREIGN KEY(id_Hall) REFERENCES Hall(id)," +
                " FOREIGN KEY(id_PlaceCategory) REFERENCES PlaceCategory(id)," +
                " UNIQUE (number,id_Hall,id_PlaceCategory),UNIQUE (number,id_Hall));");
        sqLiteDatabase.execSQL("CREATE table Place(id integer PRIMARY KEY autoincrement,id_Row integer NOT NULL, number integer NOT NULL," +
                " FOREIGN KEY(id_Row) REFERENCES Row(id)," +
                " UNIQUE(id_Row,number));");
        sqLiteDatabase.execSQL("CREATE table Ticket(id integer PRIMARY KEY autoincrement, id_Session integer NOT NULL, id_Place integer NOT NULL, status text NOT NULL," +
                " FOREIGN KEY(id_Session) REFERENCES Session(id)," +
                " FOREIGN KEY(id_Place) REFERENCES Place(id)," +
                " UNIQUE(id_Session, id_Place));");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {

    }
    @Override
    public void onConfigure(SQLiteDatabase db)
    {
        // включаем поддержку foreign key
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }
    public void addFilm(final String name,final String description)
    {
        //SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        contentValues.put("description",description);
        this.getWritableDatabase().insert("Film",null,contentValues);
    }
    public void addHall(final int number,final int spaciousness)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("number",number);
        contentValues.put("spaciousness",spaciousness);
        sqLiteDatabase.insert("Hall",null,contentValues);
    }
    public void addSession(final String session_date,final String session_time,final int id_Hall,final int id_Film)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("date",session_date);
        contentValues.put("time",session_time);
        contentValues.put("id_Hall",id_Hall);
        contentValues.put("id_Film",id_Film);
        sqLiteDatabase.insert("Session",null,contentValues);
    }
    public void addPlaceCategory(final String name)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        sqLiteDatabase.insert("PlaceCategory",null,contentValues);
    }
    public void addPriceCategory(final int id_PlaceCategory, final int id_session, final int price)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id_PlaceCategory",id_PlaceCategory);
        contentValues.put("id_session",id_session);
        contentValues.put("price",price);
        sqLiteDatabase.insert("PriceCategory",null,contentValues);
    }
    public void addRow(final int number,final int id_Hall,final int id_PlaceCategory,final int count)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("number",number);
        contentValues.put("id_Hall",id_Hall);
        contentValues.put("id_PlaceCategory",id_PlaceCategory);
        contentValues.put("count",count);
        sqLiteDatabase.insert("Row",null,contentValues);
    }
    public void addPlace(final int number,final int id_Row)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id_Row",id_Row);
        contentValues.put("number",number);
        sqLiteDatabase.insert("Place",null,contentValues);
    }
    public void addTicket(final int id_Session,final int id_Place,final String status)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id_Session",id_Session);
        contentValues.put("id_Place",id_Place);
        contentValues.put("status",status);
        sqLiteDatabase.insert("Ticket",null,contentValues);
    }
    public ArrayList<Film> getListFilm()
    {
        ArrayList<Film> films = new ArrayList<Film>();
            Cursor cursor = this.getWritableDatabase().query("Film", null, null, null, null, null, null);
            if (cursor.moveToFirst()) {
                do {
                    Film film = new Film((int) cursor.getInt(cursor.getColumnIndex("id")), (String) cursor.getString(cursor.getColumnIndex("name")), (String) cursor.getString(cursor.getColumnIndex("description")));
                    films.add(film);
                } while (cursor.moveToNext());
            }
            return films;
    }
    public ArrayList<Hall> getListHall()
    {
        ArrayList<Hall> halls = new ArrayList<Hall>();
        Cursor cursor= this.getWritableDatabase().query("Hall",null,null,null,null,null,null);
        if (cursor.moveToFirst()){
            do
            {
                Hall hall = new Hall(cursor.getInt(cursor.getColumnIndex("id")),cursor.getInt(cursor.getColumnIndex("number")),cursor.getInt(cursor.getColumnIndex("spaciousness")));
                halls.add(hall) ;
            } while (cursor.moveToNext());
        }
        return  halls;
    }
    public ArrayList<Session> getListSession()
    {
        ArrayList<Session> sessions = new ArrayList<Session>();
        Cursor cursor= this.getWritableDatabase().query("Session",null,null,null,null,null,null);
        if (cursor.moveToFirst()){
            do  {
                Session session = new Session(cursor.getInt(cursor.getColumnIndex("id")),cursor.getString(cursor.getColumnIndex("date")),cursor.getString(cursor.getColumnIndex("time")),cursor.getInt(cursor.getColumnIndex("id_Hall")),cursor.getInt(cursor.getColumnIndex("id_Film")));
                sessions.add(session);
            } while (cursor.moveToNext());
        }
        return sessions;
    }
    public ArrayList<PlaceCategory> getListPlaceCategory()
    {
            ArrayList<PlaceCategory> placeCategorys = new ArrayList<PlaceCategory>();
            Cursor cursor= this.getWritableDatabase().query("PlaceCategory",null,null,null,null,null,null);
            if (cursor.moveToFirst()){
                do  {
                    PlaceCategory placeCategory = new PlaceCategory(cursor.getInt(cursor.getColumnIndex("id")),cursor.getString(cursor.getColumnIndex("name")));
                    placeCategorys.add(placeCategory);
                } while (cursor.moveToNext());
            }
            return placeCategorys;

    }
    public ArrayList<PriceCategory> getListPriceCategory()
    {
        ArrayList<PriceCategory> priceCategorys = new ArrayList<PriceCategory>();
        Cursor cursor= this.getWritableDatabase().query("PriceCategory",null,null,null,null,null,null);
        if (cursor.moveToFirst()){
            do  {
                PriceCategory priceCategory = new PriceCategory(cursor.getInt(cursor.getColumnIndex("id")),cursor.getInt(cursor.getColumnIndex("id_PlaceCategory")),cursor.getInt(cursor.getColumnIndex("id_session")),cursor.getInt(cursor.getColumnIndex("price")));
                priceCategorys.add(priceCategory);
            } while (cursor.moveToNext());
        }
        return priceCategorys;

    }
    public ArrayList<Row> getListRow() 
    {
        ArrayList<Row> rows = new ArrayList<Row>();
        Cursor cursor= this.getWritableDatabase().query("Row",null,null,null,null,null,null);
        if (cursor.moveToFirst()){
            do  {
                Row row = new Row(cursor.getInt(cursor.getColumnIndex("id")),cursor.getInt(cursor.getColumnIndex("number")),cursor.getInt(cursor.getColumnIndex("id_Hall")),cursor.getInt(cursor.getColumnIndex("id_PlaceCategory")),cursor.getInt(cursor.getColumnIndex("count")));
                rows.add(row);
            } while (cursor.moveToNext());
        }
        return rows;
    }
    public ArrayList<Place> getListPlace()
    {
        ArrayList<Place> places = new ArrayList<Place>();
        Cursor cursor= this.getWritableDatabase().query("Place",null,null,null,null,null,null);
        if (cursor.moveToFirst()){
            do  {
                Place place = new Place(cursor.getInt(cursor.getColumnIndex("id")),cursor.getInt(cursor.getColumnIndex("number")),cursor.getInt(cursor.getColumnIndex("id_Row")));
                places.add(place);
            } while (cursor.moveToNext());
        }
        return places;
    }
    public ArrayList<Ticket> getListTicket()
    {
        ArrayList<Ticket> tickets = new ArrayList<Ticket>();
        Cursor cursor= this.getWritableDatabase().query("Ticket",null,null,null,null,null,null);
        if (cursor.moveToFirst()){
            do  {
                Ticket ticket = new Ticket(cursor.getInt(cursor.getColumnIndex("id")),cursor.getInt(cursor.getColumnIndex("id_Session")),cursor.getInt(cursor.getColumnIndex("id_Place")),cursor.getString(cursor.getColumnIndex("status")));
                tickets.add(ticket);
            } while (cursor.moveToNext());
        }
        return tickets;
    }
    public ArrayList<String> getListTimeByDate(String date,String id_film)
    {
        ArrayList<String> times = new ArrayList<String>();
        Cursor cursor= this.getWritableDatabase().query("Session",
                null,
                "date = ? and id_Film = ?",
                new String[]{String.valueOf(date),String.valueOf(id_film)}, null, null, null);
        if (cursor.moveToFirst()){
            do  {
                times.add(cursor.getString(cursor.getColumnIndex("time")));

            } while (cursor.moveToNext());
        }
        return times;
    }
    public HashSet<String> getSetAllDate()
    {
        HashSet<String> times = new HashSet<String>();
        Cursor cursor= this.getWritableDatabase().query("Session",null,null, null,null,null,null);
        if (cursor.moveToFirst()){
            do  {
                times.add(cursor.getString(cursor.getColumnIndex("date")));

            } while (cursor.moveToNext());
        }
        return times;
    }
    public  HashSet<String> getHashSetFilmsByDate(String date)
    {
        HashSet<String> ids = new HashSet<String>();
        String selection = "date = ?";
        String selectionArgs[]=new String[] { date };
        Cursor cursor= this.getWritableDatabase().query("Session",null,selection,selectionArgs,null,null,null);
        if (cursor.moveToFirst()){
            do  {
                ids.add(cursor.getString(cursor.getColumnIndex("id_Film")));

            } while (cursor.moveToNext());
        }
        return ids;
    }
    public HashSet<Film> getHashSetFilm(HashSet<String> ids)
    {
        HashSet<Film> films = new HashSet<Film>();
        for (String str: ids)
        {
            String[] mass ={str};
            Cursor cursor2 = this.getWritableDatabase().query("Film", null, "id = ?",mass, null, null, null);
            if (cursor2.moveToFirst()) {
                do {
                    Film film = new Film((int) cursor2.getInt(cursor2.getColumnIndex("id")), (String) cursor2.getString(cursor2.getColumnIndex("name")), (String) cursor2.getString(cursor2.getColumnIndex("description")));
                    films.add(film);
                } while (cursor2.moveToNext());
            }
        }
        return films;
    }
    public String getFilmNameById(int id)
    {
        String selection = "id = ?";
        String selectionArgs[]=new String[] { String.valueOf(id) };
        Cursor cursor = this.getWritableDatabase().query("Film", null,selection,selectionArgs, null, null, null);
        if (cursor.moveToFirst()) {
            do
            {
                return (String) cursor.getString(cursor.getColumnIndex("name"));
            }
            while (cursor.moveToNext());
        }
        return null;
    }
    public String getFilmIdByName(String name)
    {
        String selection = "name = ?";
        String selectionArgs[]=new String[] { String.valueOf(name) };
        Cursor cursor = this.getWritableDatabase().query("Film", null,selection,selectionArgs, null, null, null);
        if (cursor.moveToFirst()) {
            do
            {
                return (String) cursor.getString(cursor.getColumnIndex("id"));
            }
            while (cursor.moveToNext());
        }
        return null;
    }
    public String getPlaceCategoryById(int id)
    {
        String selection = "id = ?";
        String selectionArgs[]=new String[] { String.valueOf(id) };
        Cursor cursor = this.getWritableDatabase().query("PlaceCategory", null,selection,selectionArgs, null, null, null);
        if (cursor.moveToFirst()) {
            do
            {
                return (String) cursor.getString(cursor.getColumnIndex("name"));
            }
            while (cursor.moveToNext());
        }
        return null;
    }
    public Session getSessionById(int id)
    {
        String selection = "id = ?";
        String selectionArgs[]=new String[] { String.valueOf(id) };
        Cursor cursor = this.getWritableDatabase().query("Session", null,selection,selectionArgs, null, null, null);
        if (cursor.moveToFirst()) {
            do
            {
                return new Session(cursor.getInt(cursor.getColumnIndex("id")),cursor.getString(cursor.getColumnIndex("date")),cursor.getString(cursor.getColumnIndex("time")),cursor.getInt(cursor.getColumnIndex("id_Hall")),cursor.getInt(cursor.getColumnIndex("id_Film")));
            }
            while (cursor.moveToNext());
        }
        return null;
    }
    public String getHallNumberById(int id)
    {
        String selection = "id = ?";
        String selectionArgs[]=new String[] { String.valueOf(id) };
        Cursor cursor = this.getWritableDatabase().query("Hall", null,selection,selectionArgs, null, null, null);
        if (cursor.moveToFirst()) {
            do
            {
                return (String) cursor.getString(cursor.getColumnIndex("number"));
            }
            while (cursor.moveToNext());
        }
        return null;
    }
    public String getRowNumberById(int id)
    {
        String selection = "id = ?";
        String selectionArgs[]=new String[] { String.valueOf(id) };
        Cursor cursor = this.getWritableDatabase().query("Row", null,selection,selectionArgs, null, null, null);
        if (cursor.moveToFirst()) {
            do
            {
                return (String) cursor.getString(cursor.getColumnIndex("number"));
            }
            while (cursor.moveToNext());
        }
        return null;
    }
    public String getPlaceNumberById(int id)
    {
        String selection = "id = ?";
        String selectionArgs[]=new String[] { String.valueOf(id) };
        Cursor cursor = this.getWritableDatabase().query("Place", null,selection,selectionArgs, null, null, null);
        if (cursor.moveToFirst()) {
            do
            {
                return (String) cursor.getString(cursor.getColumnIndex("number"));
            }
            while (cursor.moveToNext());
        }
        return null;
    }
    public String getHallByDateTimeFilm(String id_film,String time,String date)
    {
        Cursor cursor= this.getWritableDatabase().query("Session",
                null,
                "date = ? and id_Film = ? and time = ?",
                new String[]{String.valueOf(date),String.valueOf(id_film),String.valueOf(time)}, null, null, null);
        if (cursor.moveToFirst()){
            do  {
                return cursor.getString(cursor.getColumnIndex("id_Hall"));

            } while (cursor.moveToNext());
        }
        return null;
    }
    public ArrayList<Row> getListRowByHall(String Hall)
    {
        ArrayList<Row> rows = new ArrayList<Row>();
        String selection = "id_Hall = ?";
        String selectionArgs[]=new String[] { String.valueOf(Hall) };
        Cursor cursor= this.getWritableDatabase().query("Row",null,selection,selectionArgs,null,null,null);
        if (cursor.moveToFirst()){
            do  {
                Row row = new Row(cursor.getInt(cursor.getColumnIndex("id")),cursor.getInt(cursor.getColumnIndex("number")),cursor.getInt(cursor.getColumnIndex("id_Hall")),cursor.getInt(cursor.getColumnIndex("id_PlaceCategory")),cursor.getInt(cursor.getColumnIndex("count")));
                rows.add(row);
            } while (cursor.moveToNext());
        }
        return rows;
    }
    public ArrayList<Place> getListPlacerByRowId(int id)
    {
        ArrayList<Place> places= new ArrayList<>();
        String selection = "id_Row = ?";
        String selectionArgs[]=new String[] { String.valueOf(id) };
        Cursor cursor = this.getWritableDatabase().query("Place", null,selection,selectionArgs, null, null, null);
        if (cursor.moveToFirst()) {
            do
            {
                Place place = new Place(cursor.getInt(cursor.getColumnIndex("id")),cursor.getInt(cursor.getColumnIndex("number")),cursor.getInt(cursor.getColumnIndex("id_Row")));
                places.add(place);
            }
            while (cursor.moveToNext());
        }
        return places;
    }
    public String getTicketByIdPlaceAndIdSession(int idPlace,int idSession)
    {
        Cursor cursor= this.getWritableDatabase().query("Ticket",
                null,
                "id_Place = ? and id_Session = ? ",
                new String[]{String.valueOf(idPlace),String.valueOf(idSession)}, null, null, null);
        if (cursor.moveToFirst()){
            do  {
                return cursor.getString(cursor.getColumnIndex("status"));

            } while (cursor.moveToNext());
        }
        return null;
    }
    public String getIdByDateTimeFilm(String id_film,String time,String date)
    {
        Cursor cursor= this.getWritableDatabase().query("Session",
                null,
                "date = ? and id_Film = ? and time = ?",
                new String[]{String.valueOf(date),String.valueOf(id_film),String.valueOf(time)}, null, null, null);
        if (cursor.moveToFirst()){
            do  {
                return cursor.getString(cursor.getColumnIndex("id"));

            } while (cursor.moveToNext());
        }
        return null;
    }
}