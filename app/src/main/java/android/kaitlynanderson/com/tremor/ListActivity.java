package android.kaitlynanderson.com.tremor;

import android.kaitlynanderson.com.tremor.Model.Earthquake;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ProgressBar;

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

    private Runnable mGetListRunnable;


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
        updateView(null);
        //TODO run asynctask here
    }

    @Override
    protected void onPause() {
        super.onPause();

        mEarthquakeAdapter.setListener(null);
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
                //TODO refresh list
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void updateView(List<Earthquake> list) {
        List<Earthquake> eList = new ArrayList<>();

        Earthquake test1 = new Earthquake(new Date(),1f, 1f, "Source", "id", 1f, 1f);
        Earthquake test2 = new Earthquake(new Date(),2f, 2f, "Source", "id", 2f, 2f);
        Earthquake test3 = new Earthquake(new Date(),3f, 3f, "Source", "id", 3f, 3f);
        Earthquake test4 = new Earthquake(new Date(),4f, 4f, "Source", "id", 4f, 4f);
        Earthquake test5 = new Earthquake(new Date(),5f, 5f, "Source", "id", 5f, 5f);
        Earthquake test6 = new Earthquake(new Date(),6f, 6f, "Source", "id", 6f, 6f);
        Earthquake test7 = new Earthquake(new Date(),7f, 7f, "Source", "id", 7f, 7f);

        eList.add(test1);
        eList.add(test2);
        eList.add(test3);
        eList.add(test4);
        eList.add(test5);
        eList.add(test6);
        eList.add(test7);
        mEarthquakeAdapter.setEarthquakeItems(eList);
    }

    @Override
    public void earthquakeClicked(Earthquake earthquake) {
        //TODO open earthquake view
    }
}
