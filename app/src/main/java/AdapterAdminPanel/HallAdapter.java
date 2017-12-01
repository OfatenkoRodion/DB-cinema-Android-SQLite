package AdapterAdminPanel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import Entity.Hall;
import best.the.rodionofatenko.com.Main.R;

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
        Hall p = (Hall)getProduct(position);
        ((TextView) view.findViewById(R.id.textNumber)).setText("№"+String.valueOf(p.getNumber()));
        ((TextView) view.findViewById(R.id.textSpaciousness)).setText(" - "+String.valueOf(p.getSpaciousness())+" мест");

        return view;
    }
    public Hall getProduct(int position) {
        return ((Hall) getItem(position));
    }

}