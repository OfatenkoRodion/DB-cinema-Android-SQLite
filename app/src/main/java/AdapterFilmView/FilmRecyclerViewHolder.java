package AdapterFilmView;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import best.the.rodionofatenko.com.clientfortestingcodequality.R;

public class FilmRecyclerViewHolder extends RecyclerView.ViewHolder
{

    private RecyclerView recyclerView;
    private LinearLayoutManager horizontalLinearLayoutManager;
    private TimeRecylerAdapter adapter;
    private TextView date;

    public FilmRecyclerViewHolder(View itemView)
    {
        super(itemView);
        recyclerView =(RecyclerView) itemView.findViewById(R.id.recyclerTimes);
        date=(TextView) itemView.findViewById(R.id.textViewDate);
        horizontalLinearLayoutManager=new LinearLayoutManager(itemView.getContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(horizontalLinearLayoutManager);

        adapter=new TimeRecylerAdapter();
        recyclerView.setAdapter(adapter);
    }

    public void bind(ArrayList<String> times,String date)
    {
        this.date.setText(date);
        adapter.addAll(times);
        adapter.notifyItemChanged(times.size());
    }
}
