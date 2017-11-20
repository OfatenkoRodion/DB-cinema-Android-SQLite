package AdapterHallView;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import Entity.Place;
import Entity.Row;
import best.the.rodionofatenko.com.clientfortestingcodequality.R;

public class PlaceRecyclerViewHolder extends RecyclerView.ViewHolder
{
    private TextView place;

    public PlaceRecyclerViewHolder(View itemView)
    {
        super(itemView);
        place =(TextView) itemView.findViewById(R.id.place);
    }
    public void bind(Place place)
    {
        this.place.setText(String.valueOf(place.getNumber()));
    }
}
