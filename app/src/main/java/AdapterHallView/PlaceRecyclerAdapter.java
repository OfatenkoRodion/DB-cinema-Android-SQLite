package AdapterHallView;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import Entity.Place;
import best.the.rodionofatenko.com.Main.R;

public class PlaceRecyclerAdapter extends RecyclerView.Adapter<PlaceRecyclerViewHolder>
{
    private ArrayList<Place> places = new ArrayList<>();

    private Context ctx;
    private String id_session;
    public void addAll(List<Place> items, String  id_session)
    {
        places.clear();
        this.id_session=id_session;
        int pos = getItemCount();
        this.places.addAll(items);
        notifyItemChanged(pos,this.places.size());
    }
    public void clear()
    {
        places.clear();
    }
    public void reload()
    {
        notifyItemChanged(getItemCount(),this.places.size());
    }
    @Override
    public PlaceRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        ctx=parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.place_item_card,parent,false);
        return new PlaceRecyclerViewHolder(view);
    }
    @Override
    public void onBindViewHolder(PlaceRecyclerViewHolder holder, int position)
    {
        holder.bind(places.get(position),ctx,id_session);
    }
    @Override
    public int getItemCount()
    {
        return places.size();
    }
}