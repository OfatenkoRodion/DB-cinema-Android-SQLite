package AdapterAdminPanel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import Entity.PlaceCategory;
import best.the.rodionofatenko.com.clientfortestingcodequality.R;

public class PlaceCategoryAdapter extends BaseAdapter {
    private Context ctx;
    private LayoutInflater lInflater;
    private ArrayList<PlaceCategory> objects;

    public PlaceCategoryAdapter(Context context, ArrayList<PlaceCategory> products) {
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
            view = lInflater.inflate(R.layout.place_category, parent, false);
        }
        PlaceCategory p = getProduct(position);
        ((TextView) view.findViewById(R.id.textName)).setText("Категория: "+String.valueOf(p.getName()));

        return view;
    }
    public PlaceCategory getProduct(int position) {
        return  (PlaceCategory)getItem(position);
    }

}