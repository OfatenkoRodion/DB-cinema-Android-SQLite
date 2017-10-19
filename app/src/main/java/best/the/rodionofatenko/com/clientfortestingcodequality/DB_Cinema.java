package best.the.rodionofatenko.com.clientfortestingcodequality;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

import Entity.Film;

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
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        contentValues.put("description",description);
        sqLiteDatabase.insert("Film",null,contentValues);
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
}