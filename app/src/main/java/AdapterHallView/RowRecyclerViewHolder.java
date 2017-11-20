package AdapterHallView;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.List;
import java.util.concurrent.ExecutionException;

import AdapterFilmView.TimeRecylerAdapter;
import Entity.Place;
import Entity.Row;
import best.the.rodionofatenko.com.clientfortestingcodequality.R;

public class RowRecyclerViewHolder extends RecyclerView.ViewHolder
{
    private TextView row;
    private RecyclerView recyclerView;
    private LinearLayoutManager horizontalLinearLayoutManager;
    private PlaceRecyclerAdapter adapter;

    public RowRecyclerViewHolder(View itemView)
    {
        super(itemView);
        row=(TextView) itemView.findViewById(R.id.row);

        horizontalLinearLayoutManager=new LinearLayoutManager(itemView.getContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerView=(RecyclerView)itemView.findViewById(R.id.recyclerPlaces);
        recyclerView.setLayoutManager(horizontalLinearLayoutManager);
        adapter=new PlaceRecyclerAdapter();
        recyclerView.setAdapter(adapter);

    }
    public void bind(Row row,List<Place> places)
    {
        adapter.addAll(places);
        adapter.notifyItemChanged(places.size());

        this.row.setText("№"+row.getNumber()+", всего мест "+ row.getCount());
    }
}
