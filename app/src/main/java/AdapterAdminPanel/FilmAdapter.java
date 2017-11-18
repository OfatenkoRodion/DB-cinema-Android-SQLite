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
    private Context ctx;
    private LayoutInflater lInflater;
    private ArrayList<Film> objects;

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

        ((TextView) view.findViewById(R.id.textName)).setText(String.valueOf(p.getName()));
        String temp=String.valueOf(p.getDescription());
        if (temp.length()>=22)
        {
            temp=temp.substring(0,22)+"...";
        }
        ((TextView) view.findViewById(R.id.textDescription)).setText(temp);

        return view;
    }
    public Film getProduct(int position) {
        return ((Film) getItem(position));
    }

}