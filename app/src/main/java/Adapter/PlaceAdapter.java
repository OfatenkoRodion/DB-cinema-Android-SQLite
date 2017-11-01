package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import Entity.Place;
import best.the.rodionofatenko.com.clientfortestingcodequality.R;

public class PlaceAdapter extends BaseAdapter
{
    Context ctx;
    LayoutInflater lInflater;
    ArrayList<Place> objects;

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
        ((TextView) view.findViewById(R.id.textId)).setText("id:"+String.valueOf(p.getId()));
        ((TextView) view.findViewById(R.id.textNumber)).setText("№:"+String.valueOf(p.getNumber()));
        ((TextView) view.findViewById(R.id.textId_Row)).setText("id ряда:"+String.valueOf(p.getId_Row()));

        return view;
    }
    public Place getProduct(int position)
    {
        return ((Place) getItem(position));
    }
}