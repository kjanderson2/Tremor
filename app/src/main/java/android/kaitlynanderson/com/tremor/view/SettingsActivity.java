package android.kaitlynanderson.com.tremor.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.kaitlynanderson.com.tremor.PrefsHelper;
import android.kaitlynanderson.com.tremor.R;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by kaitlynanderson on 3/18/18.
 * Activity for updating the settings which we use to call the API
 */

public class SettingsActivity extends AppCompatActivity {

    private EditText mNorthEditText;

    private EditText mEastEditText;

    private EditText mSouthEditText;

    private EditText mWestEditText;

    private CheckBox mUseDateCheckBox;

    private EditText mDateEditText;

    private EditText mMinMagEditText;

    private EditText mMaxRowsEditText;

    private TextView mDateExplanationText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_settings);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        mNorthEditText = findViewById(R.id.editText_north);
        mEastEditText = findViewById(R.id.editText_east);
        mSouthEditText = findViewById(R.id.editText_south);
        mWestEditText = findViewById(R.id.editText_west);
        mUseDateCheckBox = findViewById(R.id.checkBox_use_date);
        mDateEditText = findViewById(R.id.editText_date);
        mMinMagEditText = findViewById(R.id.editText_min_mag);
        mMaxRowsEditText = findViewById(R.id.editText_max_rows);
        mDateExplanationText = findViewById(R.id.textView_date_explanation);
        Button restoreDefaultsButton = findViewById(R.id.button_restore_defaults);
        restoreDefaultsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PrefsHelper.restoreDefaults(SettingsActivity.this);
                updateViewsFromPrefs();
            }
        });

        updateViewsFromPrefs();

        mUseDateCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mDateEditText.setEnabled(true);
                    mDateExplanationText.setVisibility(View.VISIBLE);
                } else {
                    mDateEditText.setEnabled(false);
                    mDateExplanationText.setVisibility(View.GONE);
                }
            }
        });
    }

    private void updateViewsFromPrefs() {
        mNorthEditText.setText(String.valueOf(PrefsHelper.getCoordinateNorth(this)));
        mEastEditText.setText(String.valueOf(PrefsHelper.getCoordinateEast(this)));
        mSouthEditText.setText(String.valueOf(PrefsHelper.getCoordinateSouth(this)));
        mWestEditText.setText(String.valueOf(PrefsHelper.getCoordinateWest(this)));
        mUseDateCheckBox.setChecked(PrefsHelper.shouldUseDate(this));
        if (mUseDateCheckBox.isChecked()) {
            mDateEditText.setEnabled(mUseDateCheckBox.isChecked());
            mDateEditText.setText(PrefsHelper.getDate(this));
            mDateExplanationText.setVisibility(View.VISIBLE);
        } else {
            mDateEditText.setEnabled(false);
            mDateExplanationText.setVisibility(View.GONE);
        }
        mMinMagEditText.setText(String.valueOf(PrefsHelper.getMinMagnitude(this)));
        mMaxRowsEditText.setText(String.valueOf(PrefsHelper.getMaxRows(this)));
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getString(R.string.settings_back_alert_message)).setPositiveButton(
                getString(R.string.settings_back_alert_pos_button),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
        builder.setNegativeButton(getString(R.string.settings_back_alert_neg_button),
                null);
        builder.show();
    }

    private void saveSettings() {
        PrefsHelper.editPrefFloat(this, PrefsHelper.KEY_COORDINATE_NORTH,
                Float.parseFloat(mNorthEditText.getText().toString()));
        PrefsHelper.editPrefFloat(this, PrefsHelper.KEY_COORDINATE_EAST,
                Float.parseFloat(mEastEditText.getText().toString()));
        PrefsHelper.editPrefFloat(this, PrefsHelper.KEY_COORDINATE_SOUTH,
                Float.parseFloat(mSouthEditText.getText().toString()));
        PrefsHelper.editPrefFloat(this, PrefsHelper.KEY_COORDINATE_WEST,
                Float.parseFloat(mWestEditText.getText().toString()));
        PrefsHelper.editPrefsBoolean(this, PrefsHelper.KEY_USE_DATE, mUseDateCheckBox.isChecked());
        PrefsHelper.editPrefString(this, PrefsHelper.KEY_DATE, mDateEditText.getText().toString());
        PrefsHelper.editPrefFloat(this, PrefsHelper.KEY_MIN_MAGNITUDE,
                Float.parseFloat(mMinMagEditText.getText().toString()));
        PrefsHelper.editPrefsInt(this, PrefsHelper.KEY_MAX_ROWS,
                Integer.parseInt(mMaxRowsEditText.getText().toString()));
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_confirm:
                saveSettings();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
