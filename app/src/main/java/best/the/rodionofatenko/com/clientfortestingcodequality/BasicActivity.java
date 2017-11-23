package best.the.rodionofatenko.com.clientfortestingcodequality;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class BasicActivity extends AppCompatActivity implements View.OnClickListener
{
    Button adminPanel, userPanel;
    Button clearDB;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic);
        adminPanel=(Button) findViewById(R.id.adminPanelButton);
        userPanel=(Button) findViewById(R.id.userPanelButton);
        clearDB=(Button) findViewById(R.id.clearButton);
        adminPanel.setOnClickListener(this);
        userPanel.setOnClickListener(this);
        clearDB.setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        if (view == adminPanel)
        {
            Intent intent = new Intent(BasicActivity.this,AddSessionActivity.class);

            startActivity(intent);
        } else
        if (view == userPanel)
        {
            Intent intent = new Intent(BasicActivity.this,FilmActivity.class);

            startActivity(intent);
        }
        if (view == clearDB)
        {
            try{
           DB_Cinema db_cinema = new DB_Cinema(this);
            db_cinema.clear();}
            catch (Exception e){
                Toast.makeText(this,e.toString(),Toast.LENGTH_LONG).show();
            }
        }
    }
}
