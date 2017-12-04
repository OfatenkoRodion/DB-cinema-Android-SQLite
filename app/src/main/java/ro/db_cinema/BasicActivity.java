package ro.db_cinema;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class BasicActivity extends AppCompatActivity implements View.OnClickListener
{
    Button adminPanel, userPanel;
    Button clearDB;
    Button testDB;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic);
        adminPanel=(Button) findViewById(R.id.adminPanelButton);
        userPanel=(Button) findViewById(R.id.userPanelButton);
        clearDB=(Button) findViewById(R.id.clearButton);
        testDB=(Button) findViewById(R.id.testButton);
        adminPanel.setOnClickListener(this);
        userPanel.setOnClickListener(this);
        clearDB.setOnClickListener(this);
        testDB.setOnClickListener(this);
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
            DB_Cinema db_cinema = new DB_Cinema(this);
            db_cinema.clear();

        }
        if (view == testDB)
        {
            DB_Cinema db_cinema = new DB_Cinema(this);
            db_cinema.clear();

            db_cinema.addFilm("Назад в будущее","«Назад в будущее» — это история о подростке, который случайно попал из 1985 года в 1955 год. Он встречается со своими родителями, учащимися средней школы, и случайно пробуждает к себе романтический интерес со стороны своей матери. Марти должен исправить вызванные им нарушения в ходе истории, заставив своих родителей полюбить друг друга, а также найти способ вернуться обратно в 1985 год.");
            db_cinema.addFilm("Терминатор","В центре сюжета — противостояние живого солдата и киборга-терминатора, прибывших в 1984 год из постапокалиптического 2029 года. Цель терминатора: убить Сару Коннор — девушку, чей ещё нерождённый сын выиграет войну человечества с машинами. Влюблённый в Сару солдат Кайл Риз пытается помешать терминатору. В фильме поднимаются проблемы путешествий во времени, судьбы, создания искусственного интеллекта, поведения людей в экстремальных ситуациях.");
            db_cinema.addFilm("Парк юрского периода","В фильме рассказывается о вымышленном острове, где учёные создали парк развлечений с клонированными динозаврами. Джон Хэммонд (Ричард Аттенборо) приглашает группу учёных, сыгранную Сэмом Ниллом, Джеффом Голдблюмом и Лорой Дерн, чтобы они проинспектировали парк до его открытия. Из-за диверсии, устроенной одним из сотрудников, динозавры прорываются на волю, а технический персонал и посетители пытаются спастись.");
            db_cinema.addFilm("Годзилла 1998г.","Действие переносится в 1998 год и рассказывает о том, как несколько японских рыбацких судов в южной части Тихого Океана, неподалёку от островов Французской Полинезии, подверглись нападению загадочного хищника. Эти события привлекают внимание правительств США и Франции. Чтобы разобраться, что это за чудовище, к расследованию привлекли сотрудника комитета ядерного контроля доктора Ника Татопулоса, который до этого 3 года исследовал гигантских дождевых червей-мутантов в окрестностях Чернобыля.");

            db_cinema.addPlaceCategory("vip");
            db_cinema.addPlaceCategory("good");

            db_cinema.addHall(1,60);
            for (int i=1;i<=6;i++)
            {
                db_cinema.addRow(i,1,1,10);
                for (int j=1;j<=10;j++)
                {
                    db_cinema.addPlace(j,i);
                }
            }
            db_cinema.addHall(2,60);
            for (int i=7;i<=12;i++)
            {
                db_cinema.addRow(i-6,2,1,10);
                for (int j=1;j<=10;j++)
                {
                    db_cinema.addPlace(j,i);
                }
            }
            db_cinema.addHall(3,60);
            for (int i=13;i<=18;i++)
            {
                db_cinema.addRow(i-12,3,1,10);
                for (int j=1;j<=10;j++)
                {
                    db_cinema.addPlace(j,i);
                }
            }

            db_cinema.addSession("24.11.2017","13:15",3,1);
            db_cinema.addSession("24.11.2017","18:00",1,1);
            db_cinema.addSession("24.11.2017","23:00",1,1);
            db_cinema.addSession("24.11.2017","8:00",2,2);
            db_cinema.addSession("24.11.2017","13:45",2,2);
            db_cinema.addSession("24.11.2017","22:00",2,2);
            db_cinema.addSession("24.11.2017","17:30",2,3);

            db_cinema.addSession("25.11.2017","14:35",2,3);
            db_cinema.addSession("25.11.2017","17:35",2,3);
            db_cinema.addSession("25.11.2017","11:20",3,1);
            db_cinema.addSession("25.11.2017","13:15",3,1);
            db_cinema.addSession("25.11.2017","18:00",1,1);
            db_cinema.addSession("25.11.2017","23:00",1,1);
            db_cinema.addSession("25.11.2017","15:50",2,2);

            db_cinema.addSession("26.11.2017","15:50",2,2);
            db_cinema.addSession("26.11.2017","17:55",2,2);
            db_cinema.addSession("26.11.2017","13:15",3,1);
            db_cinema.addSession("26.11.2017","18:00",1,1);
            db_cinema.addSession("26.11.2017","23:00",1,1);
            db_cinema.addSession("26.11.2017","11:00",2,4);
            db_cinema.addSession("26.11.2017","13:00",2,4);
            db_cinema.addSession("26.11.2017","16:30",1,4);
            db_cinema.addSession("26.11.2017","22:55",3,3);

        }
    }
}
