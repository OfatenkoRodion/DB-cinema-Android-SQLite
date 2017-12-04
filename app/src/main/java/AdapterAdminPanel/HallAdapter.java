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

import Entity.Hall;
import Entity.Place;
import Entity.PriceCategory;
import Entity.Row;
import ro.db_cinema.DB_Cinema;
import ro.db_cinema.R;

public class HallAdapter extends BaseAdapter {
    private Context ctx;
    private LayoutInflater lInflater;
    private ArrayList<Hall> objects;

    public HallAdapter(Context context, ArrayList<Hall> products) {
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
            view = lInflater.inflate(R.layout.hall, parent, false);
        }
        final Hall p = (Hall)getProduct(position);
        ((TextView) view.findViewById(R.id.textNumber)).setText("№"+String.valueOf(p.getNumber()));
        ((TextView) view.findViewById(R.id.textSpaciousness)).setText(" - "+String.valueOf(p.getSpaciousness())+" мест");

        ImageButton imageButton = (ImageButton) view.findViewById(R.id.buttonDel);
        imageButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                DB_Cinema db_cinema = new DB_Cinema(view.getContext());

                ArrayList<Row> rows = db_cinema.getListRowsByHallId(p.getId());

                    for (Row row : rows)
                    {
                        ArrayList<Place> places = db_cinema.getListPlacerByRowId(row.getId());

                        for (Place pl : places)
                        {
                            db_cinema.delTicketbyIdPlace(pl.getId());
                            db_cinema.delPlace(pl.getId());
                        }
                        db_cinema.delRow(row.getId());
                    }

                db_cinema.delSessionByHallId(p.getId());

                db_cinema.delHall(p.getId());
                objects.remove(p);
                HallAdapter.this.notifyDataSetChanged();
                Toast.makeText(view.getContext(), "Удалено", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
    public Hall getProduct(int position) {
        return ((Hall) getItem(position));
    }

}