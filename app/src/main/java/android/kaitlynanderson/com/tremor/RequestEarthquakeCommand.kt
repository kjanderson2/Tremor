package android.kaitlynanderson.com.tremor

import android.kaitlynanderson.com.tremor.model.EarthquakeResult

/**
 * Created by kaitlynanderson on 3/18/18.
 * Kotlin command for getting earthquakes from api using EarthquakeRequest
 */
class RequestEarthquakeCommand : Command<EarthquakeResult> {
    override fun execute(north: Float, south: Float, east: Float, west: Float, minMag: Float, date: String, useDate: Boolean, maxRows: Int): EarthquakeResult {
        return EarthquakeRequest().execute(north, south, east, west, minMag, date, useDate, maxRows)
    }
}