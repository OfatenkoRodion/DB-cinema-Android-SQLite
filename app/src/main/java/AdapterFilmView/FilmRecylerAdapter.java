package AdapterFilmView;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import best.the.rodionofatenko.com.clientfortestingcodequality.R;

public class FilmRecylerAdapter extends RecyclerView.Adapter<FilmRecyclerViewHolder>
{
    private ArrayList<ArrayList<String>> items = new ArrayList<>();

    public void addAll(ArrayList<ArrayList<String>> items)
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
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount()
    {
        return items.size();
    }
}