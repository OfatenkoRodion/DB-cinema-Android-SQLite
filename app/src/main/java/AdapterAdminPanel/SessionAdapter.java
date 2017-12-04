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

import Entity.Session;
import ro.db_cinema.DB_Cinema;
import ro.db_cinema.R;

public class SessionAdapter extends BaseAdapter  {
    private Context ctx;
    private LayoutInflater lInflater;
    private ArrayList<Session> objects;

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
        final Session p = (Session)getProduct(position);
        ((TextView) view.findViewById(R.id.textDate)).setText(String.valueOf(p.getDate()));
        ((TextView) view.findViewById(R.id.textTime)).setText(String.valueOf(p.getTime())+" ");

        DB_Cinema db_cinema = new DB_Cinema(ctx);
        ((TextView) view.findViewById(R.id.textId_Film)).setText(db_cinema.getFilmNameById(p.getId_Film()));

        ImageButton imageButton = (ImageButton) view.findViewById(R.id.buttonDel);
        imageButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                DB_Cinema db_cinema=new DB_Cinema(view.getContext());
                
                db_cinema.delTicketbyIdSession(p.getId());
                db_cinema.delPriceCategorybyIdSession(p.getId());

                db_cinema.delSession(p.getId());
                objects.remove(p);
                SessionAdapter.this.notifyDataSetChanged();
                Toast.makeText(view.getContext(),"Удалено",Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
    public Session getProduct(int position) {
        return ((Session) getItem(position));
    }

}