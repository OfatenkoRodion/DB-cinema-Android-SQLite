package AdapterHallView;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import DataPackaging.PlaceInRow;
import ro.db_cinema.R;

public class RowRecyclerAdapter extends RecyclerView.Adapter<RowRecyclerViewHolder>
{
    private ArrayList<PlaceInRow> rows = new ArrayList<>();
    private String id_session;

    public void addAll(List<PlaceInRow> items, String id_session)
    {
        rows.clear();
        this.id_session=id_session;
        int pos = getItemCount();
        rows.addAll(items);
        notifyItemChanged(pos,this.rows.size());
    }
    @Override
    public RowRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_card,parent,false);
        return new RowRecyclerViewHolder(view);
    }
    @Override
    public void onBindViewHolder(RowRecyclerViewHolder holder, int position)
    {
        holder.bind(rows.get(position).getRow(),rows.get(position).getPlaces(), id_session);
    }
    @Override
    public int getItemCount()
    {
        return rows.size();
    }
}