package android.kaitlynanderson.com.tremor.View;

import android.kaitlynanderson.com.tremor.Model.Earthquake;
import android.kaitlynanderson.com.tremor.R;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kaitlynanderson on 3/18/18.
 */

public class EarthquakeAdapter extends RecyclerView.Adapter<EarthquakeItemViewHolder> {

    private List<Earthquake> mEarthquakeList = new ArrayList<>();

    private Listener mListener;

    public void setEarthquakeItems(List<Earthquake> items) {
        mEarthquakeList = items;
        notifyDataSetChanged();
    }

    public void setListener(Listener listener) {
        mListener = listener;
    }

    @NonNull
    @Override
    public EarthquakeItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View quakeView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_quake_item, parent, false);
        return new EarthquakeItemViewHolder(quakeView);
    }

    @Override
    public void onBindViewHolder(@NonNull EarthquakeItemViewHolder holder, int position) {
        Earthquake earthquake = mEarthquakeList.get(position);
        holder.populate(earthquake);
    }

    @Override
    public int getItemCount() {
        return mEarthquakeList.size();
    }

    public interface Listener {
        void earthquakeClicked(Earthquake earthquake);
    }
}
