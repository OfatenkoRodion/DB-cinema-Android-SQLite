package best.the.rodionofatenko.com.clientfortestingcodequality;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
        sqLiteDatabase.execSQL("CREATE table Film(id integer PRIMARY KEY autoincrement, name text, description text);");
        sqLiteDatabase.execSQL("CREATE table Hall(id integer PRIMARY KEY autoincrement, number integer, spaciousness integer);");
        sqLiteDatabase.execSQL("CREATE table Session(date text, time text, id_Hall integer, id_Film integer," +
                " FOREIGN KEY(id_Film) REFERENCES Film(id)," +
                " FOREIGN KEY(id_Hall) REFERENCES Hall(id)," +
                " PRIMARY KEY (date, time, id_Hall));");
        sqLiteDatabase.execSQL("CREATE table PlaceCategory(id integer PRIMARY KEY autoincrement, name text);");
        sqLiteDatabase.execSQL("CREATE table PriceCategory(id_PlaceCategory integer, session_date text, session_time text, session_id_Hall integer," +
                " FOREIGN KEY(id_PlaceCategory) REFERENCES PlaceCategory(id)" +
                " FOREIGN KEY(session_date) REFERENCES Session(data)" +
                " FOREIGN KEY(session_time) REFERENCES Session(time)" +
                " FOREIGN KEY(session_id_Hall) REFERENCES Session(id_Hall)" +
                " PRIMARY KEY (session_date, session_time, session_id_Hall,id_PlaceCategory));");
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
}