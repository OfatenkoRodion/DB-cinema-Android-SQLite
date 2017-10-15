package best.the.rodionofatenko.com.clientfortestingcodequality;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText name, phone,id,studId,lesson;
    Button insert, show, delete,deleteById,updata,insLesson,showLesson;
    DataBase dataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataBase = new DataBase(this);

        name = (EditText) findViewById(R.id.nameEdit);
        phone = (EditText) findViewById(R.id.phoneEdit);
        id = (EditText) findViewById(R.id.editId);

        studId = (EditText) findViewById(R.id.idStudEditText);
        lesson = (EditText) findViewById(R.id.lessonEditText);

        insert = (Button) findViewById(R.id.insertBtn);
        delete = (Button) findViewById(R.id.deleteBtn);
        show = (Button) findViewById(R.id.showBtn);
        deleteById = (Button) findViewById(R.id.deleteByIdBtn);
        updata = (Button) findViewById(R.id.updataBtn);

        insLesson = (Button) findViewById(R.id.insertLessonBtn);
        showLesson = (Button) findViewById(R.id.showLessonBtn);

        final SQLiteDatabase sqLiteDatabase = dataBase.getWritableDatabase();
        final ContentValues contentValues = new ContentValues();

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contentValues.put("name",name.getText().toString());
                contentValues.put("phone",phone.getText().toString());
                sqLiteDatabase.insert("Contact",null,contentValues);
                Toast.makeText(getApplicationContext(),"Добавил", Toast.LENGTH_SHORT).show();
            }
        });
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor cursor= sqLiteDatabase.query("Contact",null,null,null,null,null,null);
                if (cursor.moveToFirst()){
                    do  {
                        Toast.makeText(getApplicationContext(),
                                "id:"+cursor.getInt(cursor.getColumnIndex("id"))
                                +" name:"+cursor.getString(cursor.getColumnIndex("name"))
                                +" phone:"+cursor.getInt(cursor.getColumnIndex("phone")), Toast.LENGTH_SHORT).show();

                        } while (cursor.moveToNext());
                }
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqLiteDatabase.delete("Contact",null,null);
                Toast.makeText(getApplicationContext(),"База данных удалена", Toast.LENGTH_SHORT).show();
            }
        });
        updata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contentValues.put("name",name.getText().toString());
                contentValues.put("phone",phone.getText().toString());
                sqLiteDatabase.update("Contact",contentValues,"id = ?",new String[]{id.getText().toString()});
                Toast.makeText(getApplicationContext(),"Обновили", Toast.LENGTH_SHORT).show();
            }
        });
        deleteById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqLiteDatabase.delete("Contact","id = "+id.getText().toString(),null);
                Toast.makeText(getApplicationContext(),"Удалил", Toast.LENGTH_SHORT).show();
            }
        });
        insLesson.setOnClickListener(new View.OnClickListener() {
            ContentValues contentValues2 = new ContentValues();
            @Override
           public void onClick(View view) {
                try{
                contentValues2.put("idStud",studId.getText().toString());
                contentValues2.put("lesson",lesson.getText().toString());
                sqLiteDatabase.insert("Lesson",null,contentValues2);
                Toast.makeText(getApplicationContext(),"Добавил", Toast.LENGTH_SHORT).show();}
                catch (Exception e){
                    Toast.makeText(getApplicationContext(),e.toString(), Toast.LENGTH_SHORT).show();
                }
            }

        });
        showLesson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              try{
                Cursor cursor= sqLiteDatabase.query("Lesson",null,null,null,null,null,null);
                if (cursor.moveToFirst()){
                    do  {
                        Toast.makeText(getApplicationContext(),
                                "id:"+cursor.getInt(cursor.getColumnIndex("id"))
                                        +" idStud:"+cursor.getInt(cursor.getColumnIndex("idStud"))
                                        +" lesson:"+cursor.getString(cursor.getColumnIndex("lesson")), Toast.LENGTH_SHORT).show();
                    } while (cursor.moveToNext());
                }}
                catch (Exception e){
                    Toast.makeText(getApplicationContext(),e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
