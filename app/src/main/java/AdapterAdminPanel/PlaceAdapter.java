package AdapterAdminPanel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import Entity.Place;
import best.the.rodionofatenko.com.clientfortestingcodequality.DB_Cinema;
import best.the.rodionofatenko.com.clientfortestingcodequality.R;

public class PlaceAdapter extends BaseAdapter
{
    private Context ctx;
    private LayoutInflater lInflater;
    private ArrayList<Place> objects;

    public PlaceAdapter(Context context, ArrayList<Place> products)
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
            view = lInflater.inflate(R.layout.place, parent, false);
        }
        Place p = (Place)getProduct(position);
        ((TextView) view.findViewById(R.id.textNumber)).setText("Место №"+String.valueOf(p.getNumber()));
        DB_Cinema db_cinema = new DB_Cinema(ctx);
        ((TextView) view.findViewById(R.id.textId_Row)).setText("Ряд №"+db_cinema.getRowNumberById(p.getId_Row()));

        return view;
    }
    public Place getProduct(int position)
    {
        return ((Place) getItem(position));
    }
}