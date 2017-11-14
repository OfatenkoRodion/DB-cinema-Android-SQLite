package AdapterFilmView;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import best.the.rodionofatenko.com.clientfortestingcodequality.R;

public class TimeRecyclerViewHolder extends RecyclerView.ViewHolder
    {
        private TextView time;

    public TimeRecyclerViewHolder(View itemView)
        {
            super(itemView);
            time=(TextView) itemView.findViewById(R.id.time);
        }
    public void bind(String time)
    {
        this.time.setText(time);
    }
}
