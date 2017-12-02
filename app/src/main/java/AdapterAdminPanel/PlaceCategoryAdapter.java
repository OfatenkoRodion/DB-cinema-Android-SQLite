package AdapterAdminPanel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.PrintStream;
import java.util.ArrayList;

import Entity.Place;
import Entity.PlaceCategory;
import Entity.PriceCategory;
import Entity.Row;
import best.the.rodionofatenko.com.Main.DB_Cinema;
import best.the.rodionofatenko.com.Main.R;

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
        final PlaceCategory p = getProduct(position);
        ((TextView) view.findViewById(R.id.textName)).setText("Категория: "+String.valueOf(p.getName()));

        ImageButton imageButton = (ImageButton) view.findViewById(R.id.buttonDel);
        imageButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                DB_Cinema db_cinema=new DB_Cinema(view.getContext());

                ArrayList<PriceCategory> priceCategories=db_cinema.getListPriceCategoryByPlaceCategoryId(p.getId());

                for (PriceCategory pc:priceCategories)
                    db_cinema.delPriceCategory(pc.getId());

                ArrayList<Row> rows = db_cinema.getListRowsByPlaceCategoryId(p.getId());

                for (Row row: rows)
                {
                    ArrayList<Place> places = db_cinema.getListPlacerByRowId(row.getId());

                    for (Place pl:places)
                    {
                        db_cinema.delTicketbyIdPlace(pl.getId());
                        db_cinema.delPlace(pl.getId());
                    }
                }
                db_cinema.delPlaceCategory(p.getId());
                objects.remove(p);
                PlaceCategoryAdapter.this.notifyDataSetChanged();
                Toast.makeText(view.getContext(),"Удалено",Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
    public PlaceCategory getProduct(int position) {
        return  (PlaceCategory)getItem(position);
    }

}