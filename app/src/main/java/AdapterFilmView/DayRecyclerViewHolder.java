package AdapterFilmView;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import DataPackaging.AllSessionsOfFilmInDay;
import best.the.rodionofatenko.com.clientfortestingcodequality.R;

public class DayRecyclerViewHolder extends RecyclerView.ViewHolder
{

    private RecyclerView recyclerView;
    private LinearLayoutManager horizontalLinearLayoutManager;
    private FilmRecylerAdapter adapter;
    private TextView date;

    public DayRecyclerViewHolder(View itemView)
    {
        super(itemView);
        recyclerView =(RecyclerView) itemView.findViewById(R.id.recyclerDays);
        date=(TextView) itemView.findViewById(R.id.textViewDate);
        horizontalLinearLayoutManager=new LinearLayoutManager(itemView.getContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(horizontalLinearLayoutManager);

        adapter=new FilmRecylerAdapter();
        recyclerView.setAdapter(adapter);
    }

    public void bind(ArrayList<AllSessionsOfFilmInDay> times, String date)
    {
        this.date.setText("Расписание на "+date);
        adapter.addAll(times,date);
        adapter.notifyItemChanged(times.size());
    }
}
