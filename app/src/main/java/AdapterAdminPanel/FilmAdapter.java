package AdapterAdminPanel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import Entity.Film;
import best.the.rodionofatenko.com.clientfortestingcodequality.R;

public class FilmAdapter extends BaseAdapter {
    Context ctx;
    LayoutInflater lInflater;
    ArrayList<Film> objects;

    public FilmAdapter(Context context, ArrayList<Film> products) {
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
            view = lInflater.inflate(R.layout.film, parent, false);
        }
        Film p = (Film)getProduct(position);
        ((TextView) view.findViewById(R.id.textId)).setText("id:"+String.valueOf(p.getId()));
        ((TextView) view.findViewById(R.id.textName)).setText(" Name:"+String.valueOf(p.getName()));
        ((TextView) view.findViewById(R.id.textDescription)).setText(" Des.:"+String.valueOf(p.getDescription()));

        return view;
    }
    public Film getProduct(int position) {
        return ((Film) getItem(position));
    }

}