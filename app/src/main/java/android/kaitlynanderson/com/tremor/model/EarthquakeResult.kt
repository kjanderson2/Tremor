package android.kaitlynanderson.com.tremor.model

/**
 * Created by kaitlynanderson on 3/13/18.
 * Model class for Earthquake response from API containing a list of Earthquake objects
 */
data class EarthquakeResult(val earthquakes: List<Earthquake>)