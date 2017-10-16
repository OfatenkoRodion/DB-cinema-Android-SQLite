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
        sqLiteDatabase.execSQL("create table Film(id integer primary key autoincrement, name text, description text);");
        sqLiteDatabase.execSQL("create table Hall(id integer primary key autoincrement, number integer, spaciousness integer);");
        sqLiteDatabase.execSQL("create table Session(session_date text, session_time text, id_Hall integer, id_Film integer," +
                " FOREIGN KEY(id_Film) REFERENCES Film(id)," +
                " FOREIGN KEY(id_Hall) REFERENCES Hall(id)," +
                " PRIMARY KEY (session_date, session_time, id_Hall));");
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
        contentValues.put("session_date",session_date);
        contentValues.put("session_time",session_time);
        contentValues.put("id_Hall",id_Hall);
        contentValues.put("id_Film",id_Film);
        sqLiteDatabase.insert("Session",null,contentValues);
    }
}