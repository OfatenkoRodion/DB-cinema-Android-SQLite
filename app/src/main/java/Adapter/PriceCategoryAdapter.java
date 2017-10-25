package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import Entity.PriceCategory;
import best.the.rodionofatenko.com.clientfortestingcodequality.R;

public class PriceCategoryAdapter extends BaseAdapter {
    Context ctx;
    LayoutInflater lInflater;
    ArrayList<PriceCategory> objects;

    public PriceCategoryAdapter(Context context, ArrayList<PriceCategory> products) {
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
        if (view == null) {
            view = lInflater.inflate(R.layout.price_category, parent, false);
        }
        PriceCategory p = getProduct(position);
        ((TextView) view.findViewById(R.id.textId)).setText("id:"+String.valueOf(p.getId()));
        ((TextView) view.findViewById(R.id.textPrice)).setText(" Цена:"+String.valueOf(p.getPrice()));
        ((TextView) view.findViewById(R.id.textId_PlaceCategory)).setText("id Категории:"+String.valueOf(p.getId_PlaceCategory()));
        ((TextView) view.findViewById(R.id.textId_session)).setText(" id Сеанса:"+String.valueOf(p.getId_session()));

        return view;
    }
    public PriceCategory getProduct(int position) {
        return ((PriceCategory) getItem(position));
    }

}