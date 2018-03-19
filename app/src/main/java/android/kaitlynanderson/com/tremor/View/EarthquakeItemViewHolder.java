package android.kaitlynanderson.com.tremor.View;

import android.content.Context;
import android.kaitlynanderson.com.tremor.Model.Earthquake;
import android.kaitlynanderson.com.tremor.R;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by kaitlynanderson on 3/18/18.
 * Viewholder for each earthquake item. Held in a cardview and showing relevant information
 * to the user
 */

class EarthquakeItemViewHolder extends RecyclerView.ViewHolder {

    private CardView mContainer;

    private TextView mMagnitudeView;

    private TextView mDepthView;

    private TextView mDateView;

    private TextView mLocationView;

    EarthquakeItemViewHolder(View itemView) {
        super(itemView);

        mContainer = itemView.findViewById(R.id.view_container);
        mMagnitudeView = itemView.findViewById(R.id.textView_magnitude);
        mDepthView = itemView.findViewById(R.id.textView_depth);
        mDateView = itemView.findViewById(R.id.textView_date);
        mLocationView = itemView.findViewById(R.id.textView_location);
    }

    void populate(Earthquake earthquake) {
        Context context = itemView.getContext();
        mMagnitudeView
                .setText(context.getString(R.string.item_magnitude,
                        String.valueOf(earthquake.getMagnitude())));
        mDepthView.setText(
                context.getString(R.string.item_depth, String.valueOf(earthquake.getDepth())));
        mDateView.setText(
                context.getString(R.string.item_date, formatDate(earthquake.getDatetime())));
        mLocationView.setText(
                context.getString(R.string.item_location, String.valueOf(earthquake.getLat()),
                        String.valueOf(earthquake.getLng())));

        // Change background color of card depending on magnitude of quake
        if (earthquake.getMagnitude() < 2.5f) {
            mContainer.setCardBackgroundColor(
                    ContextCompat.getColor(itemView.getContext(), R.color.light_green));
        } else if (earthquake.getMagnitude() < 6f) {
            mContainer.setCardBackgroundColor(
                    ContextCompat.getColor(itemView.getContext(), R.color.light_yellow));
        } else {
            mContainer.setCardBackgroundColor(
                    ContextCompat.getColor(itemView.getContext(), R.color.light_red));
        }
    }

    private String formatDate(Date date) {
        SimpleDateFormat formatter;

        formatter = new SimpleDateFormat("YYYY-MM-dd", Locale.getDefault());
        return formatter.format(date);
    }
}
