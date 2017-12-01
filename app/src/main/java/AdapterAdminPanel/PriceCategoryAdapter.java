package AdapterAdminPanel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import Entity.PriceCategory;
import Entity.Session;
import best.the.rodionofatenko.com.Main.DB_Cinema;
import best.the.rodionofatenko.com.Main.R;

public class PriceCategoryAdapter extends BaseAdapter {
    private Context ctx;
    private LayoutInflater lInflater;
    private ArrayList<PriceCategory> objects;

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
        ((TextView) view.findViewById(R.id.textPrice)).setText(" "+String.valueOf(p.getPrice())+"грн.");

        DB_Cinema db_cinema = new DB_Cinema(ctx);
        Session session = db_cinema.getSessionById(p.getId_session());
        String filmName=db_cinema.getFilmNameById(session.getId_Film());
        ((TextView) view.findViewById(R.id.textId_PlaceCategory)).setText(db_cinema.getPlaceCategoryById(p.getId_PlaceCategory()));
        ((TextView) view.findViewById(R.id.textFilm)).setText(filmName);
        ((TextView) view.findViewById(R.id.textSession)).setText(session.getTime()+" "+session.getDate());


        return view;
    }
    public PriceCategory getProduct(int position) {
        return ((PriceCategory) getItem(position));
    }

}