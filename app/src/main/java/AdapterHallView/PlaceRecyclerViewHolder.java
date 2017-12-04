package AdapterHallView;


import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import Entity.Place;
import ro.db_cinema.DB_Cinema;
import ro.db_cinema.R;

public class PlaceRecyclerViewHolder extends RecyclerView.ViewHolder
{
    private TextView place;
    private CardView placeCardView;

    public PlaceRecyclerViewHolder(View itemView)
    {
        super(itemView);
        place =(TextView) itemView.findViewById(R.id.place);
        placeCardView=(CardView)itemView.findViewById(R.id.placeCardView);
    }
    public void bind(Place place, Context ctx, String  id_session)
    {
        this.place.setText(String.valueOf(place.getNumber()));
        DB_Cinema db_cinema = new DB_Cinema(ctx);

        if (db_cinema.getTicketByIdPlaceAndIdSession(place.getId(), Integer.valueOf(id_session) )==null)
        {
            placeCardView.setBackgroundColor(android.graphics.Color.argb(255,124,252,0));
        }
        else
            placeCardView.setBackgroundColor(android.graphics.Color.argb(255,255,0,0));

    }
}
