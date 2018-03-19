package android.kaitlynanderson.com.tremor

import android.kaitlynanderson.com.tremor.Model.EarthquakeResult

/**
 * Created by kaitlynanderson on 3/18/18.
 */
class RequestEarthquakeCommand() : Command<EarthquakeResult> {
    override fun execute(): EarthquakeResult {
        return EarthquakeRequest().execute()
    }
}