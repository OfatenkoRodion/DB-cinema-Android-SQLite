package best.the.rodionofatenko.com.clientfortestingcodequality;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBase extends SQLiteOpenHelper{


    public DataBase(Context context) {
        super(context,"db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //sqLiteDatabase.setForeignKeyConstraintsEnabled (true);
        sqLiteDatabase.execSQL("PRAGMA foreign_keys=ON;");
        sqLiteDatabase.execSQL("create table Contact(id integer primary key autoincrement, name text, phone integer);");
        sqLiteDatabase.execSQL("create table Lesson(id integer primary key autoincrement, idStud integer, lesson text, FOREIGN KEY (idStud) REFERENCES Contact(id) ON DELETE CASCADE);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
