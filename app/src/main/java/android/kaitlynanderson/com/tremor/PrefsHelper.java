package android.kaitlynanderson.com.tremor;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by kaitlynanderson on 3/18/18.
 * Helper class for reading from and writing to Shared Preferences
 */

public class PrefsHelper {

    private static final String PREF_KEY_SETTINGS = "shared_preferences_settings";

    public static final String KEY_COORDINATE_NORTH = "key_coordinate_north";

    private static final Float DEFAULT_COORDINATE_NORTH = 90f;

    public static final String KEY_COORDINATE_EAST = "key_coordinate_east";

    private static final Float DEFAULT_COORDINATE_EAST = -22.4f;

    public static final String KEY_COORDINATE_SOUTH = "key_coordinate_south";

    private static final Float DEFAULT_COORDINATE_SOUTH = -9.9f;

    public static final String KEY_COORDINATE_WEST = "key_coordinate_west";

    private static final Float DEFAULT_COORDINATE_WEST = 55.2f;

    public static final String KEY_DATE = "key_date";

    public static final String KEY_USE_DATE = "should_use_date";

    public static final String KEY_MIN_MAGNITUDE = "key_min_magnitude";

    private static final Float DEFAULT_MIN_MAGNITUDE = 0f;

    public static final String KEY_MAX_ROWS = "key_max_rows";

    private static final int DEFAULT_MAX_ROWS = 20;

    public static void editPrefFloat(Context context, String prefKey, Float newValue) {
        SharedPreferences sharedPreferences = context
                .getSharedPreferences(PREF_KEY_SETTINGS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat(prefKey, newValue);
        editor.apply();
    }

    public static void editPrefString(Context context, String prefKey, String newValue) {
        SharedPreferences sharedPreferences = context
                .getSharedPreferences(PREF_KEY_SETTINGS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(prefKey, newValue);
        editor.apply();
    }

    public static void editPrefsInt(Context context, String prefKey, Integer newValue) {
        SharedPreferences sharedPreferences = context
                .getSharedPreferences(PREF_KEY_SETTINGS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(prefKey, newValue);
        editor.apply();
    }

    public static void editPrefsBoolean(Context context, String prefKey, Boolean newValue) {
        SharedPreferences sharedPreferences = context
                .getSharedPreferences(PREF_KEY_SETTINGS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(prefKey, newValue);
        editor.apply();
    }

    public static float getCoordinateNorth(Context context) {
        SharedPreferences sharedPreferences = context
                .getSharedPreferences(PREF_KEY_SETTINGS, Context.MODE_PRIVATE);
        return sharedPreferences.getFloat(KEY_COORDINATE_NORTH, DEFAULT_COORDINATE_NORTH);
    }

    public static float getCoordinateEast(Context context) {
        SharedPreferences sharedPreferences = context
                .getSharedPreferences(PREF_KEY_SETTINGS, Context.MODE_PRIVATE);
        return sharedPreferences.getFloat(KEY_COORDINATE_EAST, DEFAULT_COORDINATE_EAST);
    }

    public static float getCoordinateSouth(Context context) {
        SharedPreferences sharedPreferences = context
                .getSharedPreferences(PREF_KEY_SETTINGS, Context.MODE_PRIVATE);
        return sharedPreferences.getFloat(KEY_COORDINATE_SOUTH, DEFAULT_COORDINATE_SOUTH);
    }

    public static float getCoordinateWest(Context context) {
        SharedPreferences sharedPreferences = context
                .getSharedPreferences(PREF_KEY_SETTINGS, Context.MODE_PRIVATE);
        return sharedPreferences.getFloat(KEY_COORDINATE_WEST, DEFAULT_COORDINATE_WEST);
    }

    public static String getDate(Context context) {
        SharedPreferences sharedPreferences = context
                .getSharedPreferences(PREF_KEY_SETTINGS, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_DATE, "");
    }

    public static Boolean shouldUseDate(Context context) {
        SharedPreferences sharedPreferences = context
                .getSharedPreferences(PREF_KEY_SETTINGS, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(KEY_USE_DATE, false);
    }

    public static Float getMinMagnitude(Context context) {
        SharedPreferences sharedPreferences = context
                .getSharedPreferences(PREF_KEY_SETTINGS, Context.MODE_PRIVATE);
        return sharedPreferences.getFloat(KEY_MIN_MAGNITUDE, DEFAULT_MIN_MAGNITUDE);
    }

    public static int getMaxRows(Context context) {
        SharedPreferences sharedPreferences = context
                .getSharedPreferences(PREF_KEY_SETTINGS, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(KEY_MAX_ROWS, DEFAULT_MAX_ROWS);
    }

    public static void restoreDefaults(Context context){
        SharedPreferences sharedPreferences = context
                .getSharedPreferences(PREF_KEY_SETTINGS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat(KEY_COORDINATE_NORTH, DEFAULT_COORDINATE_NORTH);
        editor.putFloat(KEY_COORDINATE_EAST, DEFAULT_COORDINATE_EAST);
        editor.putFloat(KEY_COORDINATE_SOUTH, DEFAULT_COORDINATE_SOUTH);
        editor.putFloat(KEY_COORDINATE_WEST, DEFAULT_COORDINATE_WEST);
        editor.putBoolean(KEY_USE_DATE, false);
        editor.putString(KEY_DATE, "");
        editor.putFloat(KEY_MIN_MAGNITUDE, DEFAULT_MIN_MAGNITUDE);
        editor.putInt(KEY_MAX_ROWS, DEFAULT_MAX_ROWS);
        editor.apply();
    }

}
