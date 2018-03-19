package android.kaitlynanderson.com.tremor.View;

import android.kaitlynanderson.com.tremor.Model.Earthquake;
import android.kaitlynanderson.com.tremor.R;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by kaitlynanderson on 3/18/18.
 */

public class EarthquakeItemViewHolder extends RecyclerView.ViewHolder {

    private CardView mContainer;

    private TextView mMagnitudeView;

    public EarthquakeItemViewHolder(View itemView) {
        super(itemView);

        mContainer = itemView.findViewById(R.id.view_container);
        mMagnitudeView = itemView.findViewById(R.id.textView_magnitude);
    }

    public void populate(Earthquake earthquake) {
        mMagnitudeView.setText(String.valueOf(earthquake.getMagnitude()));

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
}
