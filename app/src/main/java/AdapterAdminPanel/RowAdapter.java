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

import Entity.Place;
import Entity.Row;
import best.the.rodionofatenko.com.Main.DB_Cinema;
import best.the.rodionofatenko.com.Main.R;

public class RowAdapter extends BaseAdapter
{
    private Context ctx;
    private LayoutInflater lInflater;
    private ArrayList<Row> objects;

    public RowAdapter(Context context, ArrayList<Row> products)
    {
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
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View view = convertView;
        if (view == null)
        {
            view = lInflater.inflate(R.layout.row, parent, false);
        }
        final Row p = (Row)getProduct(position);
        ((TextView) view.findViewById(R.id.textNumber)).setText("Ряд № "+String.valueOf(p.getNumber()));

        DB_Cinema db_cinema = new DB_Cinema(ctx);
        ((TextView) view.findViewById(R.id.textId_Hall)).setText("Зал № "+String.valueOf(db_cinema.getHallNumberById(p.getId_Hall())));
        ((TextView) view.findViewById(R.id.textCount)).setText("Мест в ряду:  "+String.valueOf(p.getCount()));

        ImageButton imageButton = (ImageButton) view.findViewById(R.id.buttonDel);
        imageButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                DB_Cinema db_cinema=new DB_Cinema(view.getContext());

                ArrayList<Place> places = db_cinema.getListPlacerByRowId(p.getId());

                for (Place pl:places)
                {
                    db_cinema.delTicketbyIdPlace(pl.getId());
                    db_cinema.delPlace(pl.getId());
                }

                db_cinema.delRow(p.getId());
                objects.remove(p);
                RowAdapter.this.notifyDataSetChanged();
                Toast.makeText(view.getContext(),"Удалено",Toast.LENGTH_SHORT).show();

            }
        });
        return view;
    }
    public Row getProduct(int position)
    {
        return ((Row) getItem(position));
    }
}