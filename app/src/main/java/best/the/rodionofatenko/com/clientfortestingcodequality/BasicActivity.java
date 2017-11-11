package best.the.rodionofatenko.com.clientfortestingcodequality;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class BasicActivity extends AppCompatActivity implements View.OnClickListener
{
Button adminPanel, userPanel;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic);
        adminPanel=(Button) findViewById(R.id.adminPanelButton);
        userPanel=(Button) findViewById(R.id.userPanelButton);
        adminPanel.setOnClickListener(this);
        userPanel.setOnClickListener(this);
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
    }
}
