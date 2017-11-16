package AdapterFilmView;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import DataPackaging.AllSessionTimesByDay;
import best.the.rodionofatenko.com.clientfortestingcodequality.R;

public class FilmRecylerAdapter extends RecyclerView.Adapter<FilmRecyclerViewHolder>
{
    private ArrayList<AllSessionTimesByDay> items = new ArrayList<>();

    public void addAll(ArrayList<AllSessionTimesByDay> items)
    {
        int pos = getItemCount();
        this.items.addAll(items);
        notifyItemChanged(pos,this.items.size());
    }
    @Override
    public FilmRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.film_item_card,parent,false);
        return new FilmRecyclerViewHolder(view);
    }
    @Override
    public void onBindViewHolder(FilmRecyclerViewHolder holder, int position)
    {
        holder.bind(items.get(position).getAllSessionsTimes(),items.get(position).getDate());
    }

    @Override
    public int getItemCount()
    {
        return items.size();
    }
}