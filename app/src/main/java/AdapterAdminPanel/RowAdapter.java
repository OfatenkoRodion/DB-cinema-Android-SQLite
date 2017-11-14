package AdapterAdminPanel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import Entity.Row;
import best.the.rodionofatenko.com.clientfortestingcodequality.R;

public class RowAdapter extends BaseAdapter
{
    Context ctx;
    LayoutInflater lInflater;
    ArrayList<Row> objects;

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
        Row p = (Row)getProduct(position);
        ((TextView) view.findViewById(R.id.textId)).setText("id:"+String.valueOf(p.getId()));
        ((TextView) view.findViewById(R.id.textNumber)).setText("№:"+String.valueOf(p.getNumber()));
        ((TextView) view.findViewById(R.id.textId_Hall)).setText("id_Hall:"+String.valueOf(p.getId_Hall()));
        ((TextView) view.findViewById(R.id.textId_PlaceCategory)).setText("d_PC:"+String.valueOf(p.getId_PlaceCategory()));
        ((TextView) view.findViewById(R.id.textCount)).setText("Count:"+String.valueOf(p.getCount()));

        return view;
    }
    public Row getProduct(int position)
    {
        return ((Row) getItem(position));
    }
}