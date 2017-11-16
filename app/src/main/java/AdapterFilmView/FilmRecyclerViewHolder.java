package AdapterFilmView;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import Entity.Film;
import best.the.rodionofatenko.com.clientfortestingcodequality.R;

public class FilmRecyclerViewHolder extends RecyclerView.ViewHolder
{

    private RecyclerView recyclerView;
    private LinearLayoutManager horizontalLinearLayoutManager;
    private TimeRecylerAdapter adapter;
    private TextView name;
    private TextView description;

    public FilmRecyclerViewHolder(View itemView)
    {
        super(itemView);
        recyclerView =(RecyclerView) itemView.findViewById(R.id.recyclerTimes);
        name=(TextView) itemView.findViewById(R.id.textViewFilmName);
        description=(TextView) itemView.findViewById(R.id.textViewDescription);
        horizontalLinearLayoutManager=new LinearLayoutManager(itemView.getContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(horizontalLinearLayoutManager);

        adapter=new TimeRecylerAdapter();
        recyclerView.setAdapter(adapter);
    }

    public void bind(ArrayList<String> times, Film film)
    {
        this.name.setText(film.getName());
        this.description.setText(film.getDescription());
        adapter.addAll(times);
        adapter.notifyItemChanged(times.size());
    }
}
