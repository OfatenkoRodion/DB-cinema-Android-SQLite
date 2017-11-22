package AdapterFilmView;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import Entity.Film;
import best.the.rodionofatenko.com.clientfortestingcodequality.HallActivity;
import best.the.rodionofatenko.com.clientfortestingcodequality.R;
import best.the.rodionofatenko.com.clientfortestingcodequality.RecyclerItemClickListener;

import static android.support.v4.app.ActivityCompat.startActivityForResult;
import static android.support.v4.content.ContextCompat.startActivity;

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

    public void bind(final ArrayList<String> times, final Film film, final String date)
    {
        this.name.setText(film.getName());

        String temp=film.getDescription();
        if (temp.length()>=200)
        {
            temp=temp.substring(0,200)+"...";
        }
        this.description.setText(temp);
        adapter.addAll(times);
        adapter.notifyItemChanged(times.size());
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(itemView.getContext(), recyclerView ,new RecyclerItemClickListener.OnItemClickListener()
                {
                    @Override public void onItemClick(View view, int position)
                    {
                        Intent intent = new Intent(itemView.getContext(), HallActivity.class);
                        intent.putExtra("filmName",film.getName());
                        intent.putExtra("time",times.get(position));
                        intent.putExtra("date",date);

                        startActivity(itemView.getContext(),intent,null);
                    }

                    @Override public void onLongItemClick(View view, int position)
                    {
                        Intent intent = new Intent(itemView.getContext(), HallActivity.class);
                        intent.putExtra("filmName",film.getName());
                        intent.putExtra("time",times.get(position));
                        intent.putExtra("date",date);

                        startActivity(itemView.getContext(),intent,null);
                    }
                })
        );
    }
}
