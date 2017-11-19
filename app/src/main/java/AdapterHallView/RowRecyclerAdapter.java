package AdapterHallView;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import Entity.Row;
import best.the.rodionofatenko.com.clientfortestingcodequality.R;

public class RowRecyclerAdapter extends RecyclerView.Adapter<RowRecyclerViewHolder>
{
    private ArrayList<Row> rows = new ArrayList<>();

    public void addAll(List<Row> items)
    {
        int pos = getItemCount();
        this.rows.addAll(items);
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
        holder.bind(rows.get(position));
    }

    @Override
    public int getItemCount()
    {
        return rows.size();
    }
}