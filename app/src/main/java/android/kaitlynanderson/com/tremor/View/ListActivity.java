package android.kaitlynanderson.com.tremor.View;

import android.app.Activity;
import android.content.Intent;
import android.kaitlynanderson.com.tremor.Model.Earthquake;
import android.kaitlynanderson.com.tremor.Model.EarthquakeResult;
import android.kaitlynanderson.com.tremor.PrefsHelper;
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
import java.util.List;

/**
 * Created by kaitlynanderson on 3/13/18.
 * Main activity that shows a list of earthquakes from an API
 */

public class ListActivity extends AppCompatActivity {

    private static final String TAG_INFO_DIALOG = "InfoDialogTag";

    private ProgressBar mProgressBar;

    private EarthquakeAdapter mEarthquakeAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mProgressBar = findViewById(R.id.progressBar);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mEarthquakeAdapter = new EarthquakeAdapter();
        recyclerView.setAdapter(mEarthquakeAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        requestEarthquakes();
    }

    private void requestEarthquakes() {
        GetEarthquakeTask asyncTask = new GetEarthquakeTask(this);
        asyncTask.execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_refresh:
                requestEarthquakes();
                return true;
            case R.id.action_info:
                openInfoDialog();
                return true;
            case R.id.action_settings:
                openSettings();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void openSettings() {
        Intent settingsIntent = new Intent(this, SettingsActivity.class);
        startActivity(settingsIntent);
    }

    private void openInfoDialog() {
        InfoDialogFragment dialogFragment = new InfoDialogFragment();
        dialogFragment.show(getFragmentManager(), TAG_INFO_DIALOG);
    }

    private void updateView(List<Earthquake> list) {
        mProgressBar.setVisibility(View.GONE);
        mEarthquakeAdapter.setEarthquakeItems(list);
    }

    private static class GetEarthquakeTask extends AsyncTask<Void, Void, EarthquakeResult> {

        private WeakReference<ListActivity> mActivityReference;

        GetEarthquakeTask(ListActivity context) {
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
            Activity context = mActivityReference.get();
            return command.execute(PrefsHelper.getCoordinateNorth(context),
                    PrefsHelper.getCoordinateSouth(context),
                    PrefsHelper.getCoordinateEast(context),
                    PrefsHelper.getCoordinateWest(context),
                    PrefsHelper.getMinMagnitude(context),
                    PrefsHelper.getDate(context),
                    PrefsHelper.shouldUseDate(context),
                    PrefsHelper.getMaxRows(context));
        }

        @Override
        protected void onPostExecute(EarthquakeResult earthquakeResult) {
            ListActivity activity = mActivityReference.get();
            if (activity == null || activity.isFinishing()) {
                return;
            }

            activity.updateView(earthquakeResult.getEarthquakes());
        }
    }
}
