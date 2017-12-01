package AdapterFilmView;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import best.the.rodionofatenko.com.Main.R;

public class TimeRecylerAdapter extends RecyclerView.Adapter<TimeRecyclerViewHolder>
{
    private ArrayList<String> items = new ArrayList<>();

    public void addAll(List<String> fakeItems)
    {
        items.clear();
        int pos = getItemCount();
        this.items.addAll(fakeItems);
        notifyItemChanged(pos,this.items.size());
    }
    @Override
    public TimeRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.time_item_card,parent,false);
        return new TimeRecyclerViewHolder(view);
    }
    @Override
    public void onBindViewHolder(TimeRecyclerViewHolder holder, int position)
    {
        holder.bind(items.get(position));
    }
    @Override
    public int getItemCount()
    {
        return items.size();
    }
}