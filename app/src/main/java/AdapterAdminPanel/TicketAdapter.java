package AdapterAdminPanel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import Entity.Session;
import Entity.Ticket;
import best.the.rodionofatenko.com.clientfortestingcodequality.DB_Cinema;
import best.the.rodionofatenko.com.clientfortestingcodequality.R;

public class TicketAdapter extends BaseAdapter {
    private Context ctx;
    private LayoutInflater lInflater;
    private ArrayList<Ticket> objects;

    public TicketAdapter(Context context, ArrayList<Ticket> products) {
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
            view = lInflater.inflate(R.layout.ticket, parent, false);
        }
        Ticket p = (Ticket)getProduct(position);

        DB_Cinema db_cinema = new DB_Cinema(ctx);
        Session session = db_cinema.getSessionById(p.getId_Session());
        String filmName=db_cinema.getFilmNameById(session.getId_Film());

        ((TextView) view.findViewById(R.id.textId_Session)).setText(filmName + " "+session.getTime()+" "+session.getDate());

        ((TextView) view.findViewById(R.id.textId_Place)).setText("Место №"+db_cinema .getPlaceNumberById(p.getId_Place()));
        ((TextView) view.findViewById(R.id.textStatus)).setText(String.valueOf(p.getStatus()));

        return view;
    }
    public Ticket getProduct(int position) {
        return ((Ticket) getItem(position));
    }

}