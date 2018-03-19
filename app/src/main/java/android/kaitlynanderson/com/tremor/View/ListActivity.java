package android.kaitlynanderson.com.tremor.View;

import android.kaitlynanderson.com.tremor.Model.Earthquake;
import android.kaitlynanderson.com.tremor.Model.EarthquakeResult;
import android.kaitlynanderson.com.tremor.R;
import android.kaitlynanderson.com.tremor.RequestEarthquakeCommand;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by kaitlynanderson on 3/13/18.
 * Main activity that shows a list of earthquakes from an API
 */

public class ListActivity extends AppCompatActivity implements EarthquakeAdapter.Listener {

    private RecyclerView mRecyclerView;

    private RecyclerView.LayoutManager mLayoutManager;
    
    private ProgressBar mProgressBar;

    private EarthquakeAdapter mEarthquakeAdapter;

    private AsyncTask getEarthquakesTask;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mProgressBar = findViewById(R.id.progressBar);
        mRecyclerView = findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mEarthquakeAdapter = new EarthquakeAdapter();
        mRecyclerView.setAdapter(mEarthquakeAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        mEarthquakeAdapter.setListener(this);
        requestEarthquakes();
    }

    @Override
    protected void onPause() {
        super.onPause();

        mEarthquakeAdapter.setListener(null);
    }

    private void requestEarthquakes() {
        GetEarthquakeTask asyncTask = new GetEarthquakeTask(this);
        asyncTask.execute();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.action_refresh:
                requestEarthquakes();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void updateView(List<Earthquake> list) {
        mProgressBar.setVisibility(View.GONE);
        mEarthquakeAdapter.setEarthquakeItems(list);
    }

    @Override
    public void earthquakeClicked(Earthquake earthquake) {
        //TODO open earthquake view
    }

    private static class GetEarthquakeTask extends AsyncTask<Void, Void, EarthquakeResult> {

        private WeakReference<ListActivity> mActivityReference;

        GetEarthquakeTask(ListActivity context){
            mActivityReference = new WeakReference<>(context);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            ProgressBar progressBar = mActivityReference.get().mProgressBar;
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected EarthquakeResult doInBackground(Void... voids) {
            RequestEarthquakeCommand command = new RequestEarthquakeCommand();
            return command.execute();
        }

        @Override
        protected void onPostExecute(EarthquakeResult earthquakeResult) {
            ListActivity activity = mActivityReference.get();
            if (activity == null || activity.isFinishing()) return;

            activity.updateView(earthquakeResult.getEarthquakes());
        }
    }
}
