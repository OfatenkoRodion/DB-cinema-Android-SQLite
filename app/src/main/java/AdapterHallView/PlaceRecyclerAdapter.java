package AdapterHallView;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import Entity.Place;
import Entity.Row;
import best.the.rodionofatenko.com.clientfortestingcodequality.R;

public class PlaceRecyclerAdapter extends RecyclerView.Adapter<PlaceRecyclerViewHolder>
{
    private ArrayList<Place> places = new ArrayList<>();

    public void addAll(List<Place> items)
    {
        int pos = getItemCount();
        this.places.addAll(items);
        notifyItemChanged(pos,this.places.size());
    }
    @Override
    public PlaceRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.place_item_card,parent,false);
        return new PlaceRecyclerViewHolder(view);
    }
    @Override
    public void onBindViewHolder(PlaceRecyclerViewHolder holder, int position)
    {
        holder.bind(places.get(position));
    }
    @Override
    public int getItemCount()
    {
        return places.size();
    }
}