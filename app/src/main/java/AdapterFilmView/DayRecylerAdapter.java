package AdapterFilmView;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import DataPackaging.AllSessionTimesByDay;
import best.the.rodionofatenko.com.Main.R;

public class DayRecylerAdapter extends RecyclerView.Adapter<DayRecyclerViewHolder>
{
    private ArrayList<AllSessionTimesByDay> items = new ArrayList<>();

    public void addAll(ArrayList<AllSessionTimesByDay> items)
    {
        this.items.clear();
        int pos = getItemCount();
        this.items.addAll(items);
        notifyItemChanged(pos,this.items.size());
    }
    @Override
    public DayRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.day_item_card,parent,false);
        return new DayRecyclerViewHolder(view);
    }
    @Override
    public void onBindViewHolder(DayRecyclerViewHolder holder, int position)
    {
        holder.bind(items.get(position).getAllSessionsTimes(),items.get(position).getDate());
    }

    @Override
    public int getItemCount()
    {
        return items.size();
    }
}