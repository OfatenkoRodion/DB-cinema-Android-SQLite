package AdapterFilmView;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import DataPackaging.AllSessionOfFilmInDayGroupBy2;
import DataPackaging.AllSessionTimesByDay;
import DataPackaging.AllSessionsOfFilmInDay;
import best.the.rodionofatenko.com.clientfortestingcodequality.R;

public class FilmRecylerAdapter extends RecyclerView.Adapter<FilmRecyclerViewHolder>
{
    private ArrayList<AllSessionOfFilmInDayGroupBy2> myItems = new ArrayList<>();
    private String date;

    public void addAll(ArrayList<AllSessionsOfFilmInDay> items, String date)
    {
        myItems.clear();
        if (items.size()%2==0)
        {
            for (int i=0;i<items.size();i=i+2)
            {
                myItems.add(new AllSessionOfFilmInDayGroupBy2(items.get(i),items.get(i+1)));
            }
        }
        else
        {
            for (int i=0;i<items.size()-1;i=i+2)
            {
                myItems.add(new AllSessionOfFilmInDayGroupBy2(items.get(i),items.get(i+1)));
            }
            myItems.add(new AllSessionOfFilmInDayGroupBy2(items.get(items.size()-1),new AllSessionsOfFilmInDay(null,null)));
        }
        int pos = getItemCount();
        this.date=date;
        notifyItemChanged(pos,this.myItems.size());
    }
    @Override
    public FilmRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.two_films_item_card,parent,false);
        return new FilmRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FilmRecyclerViewHolder holder, int position)
    {
        holder.bind(myItems.get(position),date);
    }

    @Override
    public int getItemCount()
    {
        return myItems.size();
    }
}