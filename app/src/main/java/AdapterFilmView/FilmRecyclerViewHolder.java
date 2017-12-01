package AdapterFilmView;


import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import DataPackaging.AllSessionOfFilmInDayGroupBy2;
import Entity.Film;
import best.the.rodionofatenko.com.Main.HallActivity;
import best.the.rodionofatenko.com.Main.R;
import best.the.rodionofatenko.com.Main.RecyclerItemClickListener;

import static android.support.v4.content.ContextCompat.startActivity;

public class FilmRecyclerViewHolder extends RecyclerView.ViewHolder
{

    private RecyclerView recyclerView1;
    private LinearLayoutManager horizontalLinearLayoutManager1;
    private TimeRecylerAdapter adapter1;
    private TextView name1;
    private TextView description1;

    private RecyclerView recyclerView2;
    private LinearLayoutManager horizontalLinearLayoutManager2;
    private TimeRecylerAdapter adapter2;
    private TextView name2;
    private TextView description2;

    public FilmRecyclerViewHolder(View itemView)
    {
        super(itemView);

        recyclerView1 =(RecyclerView) itemView.findViewById(R.id.recyclerTimes1);
        name1 =(TextView) itemView.findViewById(R.id.textViewFilmName1);
        description1 =(TextView) itemView.findViewById(R.id.textViewDescription1);
        horizontalLinearLayoutManager1 =new LinearLayoutManager(itemView.getContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerView1.setLayoutManager(horizontalLinearLayoutManager1);
        adapter1 =new TimeRecylerAdapter();
        recyclerView1.setAdapter(adapter1);

        recyclerView2 =(RecyclerView) itemView.findViewById(R.id.recyclerTimes2);
        name2 =(TextView) itemView.findViewById(R.id.textViewFilmName2);
        description2 =(TextView) itemView.findViewById(R.id.textViewDescription2);
        horizontalLinearLayoutManager2 =new LinearLayoutManager(itemView.getContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerView2.setLayoutManager(horizontalLinearLayoutManager2);
        adapter2 =new TimeRecylerAdapter();
        recyclerView2.setAdapter(adapter2);
    }

    public void bind(AllSessionOfFilmInDayGroupBy2 sessionOfFilmInDayGroupBy2, final String date)
    {

        final ArrayList<String> times=sessionOfFilmInDayGroupBy2.getAllSessionsOfFilmInDay1().getAllSessionsTimes();
        final Film film=sessionOfFilmInDayGroupBy2.getAllSessionsOfFilmInDay1().getFilm();
        this.name1.setText(film.getName());

        String temp=film.getDescription();
        if (temp.length()>=200)
        {
            temp=temp.substring(0,200)+"...";
        }
        this.description1.setText(temp);
        adapter1.addAll(times);
        adapter1.notifyItemChanged(times.size());
        recyclerView1.addOnItemTouchListener(
                new RecyclerItemClickListener(itemView.getContext(), recyclerView1,new RecyclerItemClickListener.OnItemClickListener()
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


        if(sessionOfFilmInDayGroupBy2.getAllSessionsOfFilmInDay2().getFilm()!=null){
        final ArrayList<String> times2=sessionOfFilmInDayGroupBy2.getAllSessionsOfFilmInDay2().getAllSessionsTimes();
        final Film film2=sessionOfFilmInDayGroupBy2.getAllSessionsOfFilmInDay2().getFilm();
        this.name2.setText(film2.getName());

        String temp2=film2.getDescription();
        if (temp2.length()>=200)
        {
            temp2=temp2.substring(0,200)+"...";
        }
        this.description2.setText(temp2);
        adapter2.addAll(times2);
        adapter2.notifyItemChanged(times2.size());
        recyclerView2.addOnItemTouchListener(
                new RecyclerItemClickListener(itemView.getContext(), recyclerView2,new RecyclerItemClickListener.OnItemClickListener()
                {
                    @Override public void onItemClick(View view, int position)
                    {
                        Intent intent = new Intent(itemView.getContext(), HallActivity.class);
                        intent.putExtra("filmName",film2.getName());
                        intent.putExtra("time",times2.get(position));
                        intent.putExtra("date",date);

                        startActivity(itemView.getContext(),intent,null);
                    }

                    @Override public void onLongItemClick(View view, int position)
                    {
                        Intent intent = new Intent(itemView.getContext(), HallActivity.class);
                        intent.putExtra("filmName",film2.getName());
                        intent.putExtra("time",times2.get(position));
                        intent.putExtra("date",date);

                        startActivity(itemView.getContext(),intent,null);
                    }
                })
        );}
        else
        {
            this.name2.setText("Тут могла быть ваша реклама");
            this.description2.setText("madnessjust4@gmail.com");
        }
    }
}