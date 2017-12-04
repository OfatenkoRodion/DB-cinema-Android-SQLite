package AdapterAdminPanel;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import Entity.Session;
import Entity.Ticket;
import ro.db_cinema.DB_Cinema;
import ro.db_cinema.R;;

public class TicketAdapter extends BaseAdapter {
    private Context ctx;
    private LayoutInflater lInflater;
    final private ArrayList<Ticket> objects;

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
        final Ticket p = (Ticket)getProduct(position);

        DB_Cinema db_cinema = new DB_Cinema(ctx);
        Session session = db_cinema.getSessionById(p.getId_Session());
        String filmName=db_cinema.getFilmNameById(session.getId_Film());

        ((TextView) view.findViewById(R.id.textId_Session)).setText(filmName + " "+session.getTime()+" "+session.getDate());

        ((TextView) view.findViewById(R.id.textId_Place)).setText("Место №"+db_cinema .getPlaceNumberById(p.getId_Place()));
        ((TextView) view.findViewById(R.id.textStatus)).setText(String.valueOf(p.getStatus()));

        ImageButton imageButton = (ImageButton) view.findViewById(R.id.buttonDel);
        imageButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                DB_Cinema db_cinema=new DB_Cinema(view.getContext());
                db_cinema.delTicket(p.getId());
                objects.remove(p);
                TicketAdapter.this.notifyDataSetChanged();
                Toast.makeText(view.getContext(),"Удалено",Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
    public Ticket getProduct(int position) {
        return ((Ticket) getItem(position));
    }


}