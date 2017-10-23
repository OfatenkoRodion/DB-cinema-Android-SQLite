package best.the.rodionofatenko.com.clientfortestingcodequality;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

import Entity.Film;
import Entity.Hall;
import Entity.PlaceCategory;
import Entity.Session;

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
        sqLiteDatabase.execSQL("CREATE table PriceCategory(id integer PRIMARY KEY autoincrement,id_PlaceCategory integer NOT NULL, id_session integer NOT NULL, price text NOT NULL," +
                " FOREIGN KEY(id_PlaceCategory) REFERENCES PlaceCategory(id)," +
                " FOREIGN KEY(id_session) REFERENCES Session(id)," +
                " UNIQUE (id_PlaceCategory,price));");
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
    public ArrayList<Film> getListFilm() {
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
            /*Cursor cursor= this.getWritableDatabase().query("PlaceCategory",null,null,null,null,null,null);
            if (cursor.moveToFirst()){
                do  {
                    PlaceCategory placeCategory = new PlaceCategory(cursor.getInt(cursor.getColumnIndex("id")),cursor.getString(cursor.getColumnIndex("name")));
                    placeCategorys.add(placeCategory);
                } while (cursor.moveToNext());
            }*/
            return placeCategorys;

    }
}