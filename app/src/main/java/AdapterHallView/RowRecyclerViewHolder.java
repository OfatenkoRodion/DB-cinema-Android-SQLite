package AdapterHallView;


import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import Entity.Place;
import Entity.Row;
import best.the.rodionofatenko.com.Main.DB_Cinema;
import best.the.rodionofatenko.com.Main.R;
import best.the.rodionofatenko.com.Main.RecyclerItemClickListener;

public class RowRecyclerViewHolder extends RecyclerView.ViewHolder
{
    private TextView row;
    private RecyclerView recyclerView;
    private LinearLayoutManager horizontalLinearLayoutManager;
    private PlaceRecyclerAdapter adapter;
    private Context ctx;

    public RowRecyclerViewHolder(View itemView)
    {
        super(itemView);
        row=(TextView) itemView.findViewById(R.id.row);
        ctx=itemView.getContext();
        horizontalLinearLayoutManager=new LinearLayoutManager(itemView.getContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerView=(RecyclerView)itemView.findViewById(R.id.recyclerPlaces);
        recyclerView.setLayoutManager(horizontalLinearLayoutManager);
        adapter=new PlaceRecyclerAdapter();
        recyclerView.setAdapter(adapter);

    }
    public void bind(Row row, final List<Place> places, final String  id_session)
    {
        adapter.addAll(places,id_session);
        adapter.notifyItemChanged(places.size());
        this.row.setText("№"+row.getNumber());
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(itemView.getContext(), recyclerView ,new RecyclerItemClickListener.OnItemClickListener()
                {
                    @Override public void onItemClick(View view, int position)
                    {
                        DB_Cinema db_cinema = new DB_Cinema(ctx);
                        db_cinema.addTicket(Integer.valueOf(id_session),places.get(position).getId(),"Куплено");
                        adapter.notifyDataSetChanged();
                    }

                    @Override public void onLongItemClick(View view, int position)
                    {
                        DB_Cinema db_cinema = new DB_Cinema(ctx);
                        db_cinema.addTicket(Integer.valueOf(id_session),places.get(position).getId(),"Куплено");
                        adapter.notifyDataSetChanged();
                    }
                })
        );
    }
}
