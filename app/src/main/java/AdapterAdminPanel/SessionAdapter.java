package AdapterAdminPanel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import Entity.Session;
import best.the.rodionofatenko.com.clientfortestingcodequality.DB_Cinema;
import best.the.rodionofatenko.com.clientfortestingcodequality.R;

public class SessionAdapter extends BaseAdapter  {
    Context ctx;
    LayoutInflater lInflater;
    ArrayList<Session> objects;

    public SessionAdapter(Context context, ArrayList<Session> products) {
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
            view = lInflater.inflate(R.layout.session, parent, false);
        }
        Session p = (Session)getProduct(position);
        ((TextView) view.findViewById(R.id.textDate)).setText(String.valueOf(p.getDate()));
        ((TextView) view.findViewById(R.id.textTime)).setText(String.valueOf(p.getTime())+" ");
        ((TextView) view.findViewById(R.id.textId_Film)).setText(String.valueOf(p.getId_Film()));

        return view;
    }
    public Session getProduct(int position) {
        return ((Session) getItem(position));
    }

}