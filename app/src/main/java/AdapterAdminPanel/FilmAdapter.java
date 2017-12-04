package AdapterAdminPanel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import Entity.Film;
import Entity.Session;
import ro.db_cinema.DB_Cinema;
import ro.db_cinema.R;

public class FilmAdapter extends BaseAdapter {
    private Context ctx;
    private LayoutInflater lInflater;
    private ArrayList<Film> objects;

    public FilmAdapter(Context context, ArrayList<Film> products) {
        ctx = context;
        objects = products;
        lInflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.film, parent, false);
        }
        final Film p = (Film)getProduct(position);

        ((TextView) view.findViewById(R.id.textName)).setText(String.valueOf(p.getName()));
        String temp=String.valueOf(p.getDescription());
        if (temp.length()>=22)
        {
            temp=temp.substring(0,22)+"...";
        }
        ((TextView) view.findViewById(R.id.textDescription)).setText(temp);

        ImageButton imageButton = (ImageButton) view.findViewById(R.id.buttonDel);
        imageButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                DB_Cinema db_cinema=new DB_Cinema(view.getContext());

                ArrayList<Session> sessions = db_cinema.getListSessionByFilmId(p.getId());

                for (Session session: sessions)
                {
                    db_cinema.delTicketbyIdSession(session.getId());
                    db_cinema.delPriceCategorybyIdSession(session.getId());

                }
                db_cinema.delSessionbyFilmId(p.getId());

                db_cinema.delFilm(p.getId());
                objects.remove(p);
                FilmAdapter.this.notifyDataSetChanged();
                Toast.makeText(view.getContext(),"Удалено",Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
    public Film getProduct(int position) {
        return ((Film) getItem(position));
    }

}