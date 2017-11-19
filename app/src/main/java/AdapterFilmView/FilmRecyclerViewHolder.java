package AdapterFilmView;


import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import Entity.Film;
import best.the.rodionofatenko.com.clientfortestingcodequality.R;
import best.the.rodionofatenko.com.clientfortestingcodequality.RecyclerItemClickListener;

public class FilmRecyclerViewHolder extends RecyclerView.ViewHolder
{

    private RecyclerView recyclerView;
    private LinearLayoutManager horizontalLinearLayoutManager;
    private TimeRecylerAdapter adapter;
    private TextView name;
    private TextView description;
    private Context ctx;

    public FilmRecyclerViewHolder(View itemView)
    {
        super(itemView);
        ctx=itemView.getContext();
        recyclerView =(RecyclerView) itemView.findViewById(R.id.recyclerTimes);
        name=(TextView) itemView.findViewById(R.id.textViewFilmName);
        description=(TextView) itemView.findViewById(R.id.textViewDescription);
        horizontalLinearLayoutManager=new LinearLayoutManager(itemView.getContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(horizontalLinearLayoutManager);

        adapter=new TimeRecylerAdapter();
        recyclerView.setAdapter(adapter);

    }

    public void bind(final ArrayList<String> times, Film film)
    {
        this.name.setText(film.getName());
        this.description.setText(film.getDescription());
        adapter.addAll(times);
        adapter.notifyItemChanged(times.size());
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(itemView.getContext(), recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        Toast.makeText(ctx,"onItemClick "+times.get(position),Toast.LENGTH_LONG).show();
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        Toast.makeText(ctx,"onLongItemClick "+times.get(position),Toast.LENGTH_LONG).show();
                    }
                })
        );
    }
}
