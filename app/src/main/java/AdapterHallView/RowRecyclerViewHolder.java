package AdapterHallView;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import Entity.Row;
import best.the.rodionofatenko.com.clientfortestingcodequality.R;

public class RowRecyclerViewHolder extends RecyclerView.ViewHolder
{
    private TextView row;

    public RowRecyclerViewHolder(View itemView)
    {
        super(itemView);
        row=(TextView) itemView.findViewById(R.id.row);
    }
    public void bind(Row row)
    {
        this.row.setText("№"+row.getNumber()+", всего мест "+ row.getCount());
    }
}
