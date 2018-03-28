package android.kaitlynanderson.com.tremor

import android.kaitlynanderson.com.tremor.model.EarthquakeResult
import com.google.gson.GsonBuilder
import java.net.URL

/**
 * Created by kaitlynanderson on 3/13/18.
 * Request class housing the API URL and running the url to be read with gson conversion
 */
class EarthquakeRequest {
    private val BASE_API_URL = "http://api.geonames.org/earthquakesJSON?formatted=true&username=mkoppelman"

    fun execute(north: Float, south: Float, east: Float, west: Float, minMag: Float, date: String, useDate: Boolean, maxRows: Int): EarthquakeResult {
        var QUERY_URL = BASE_API_URL
        QUERY_URL += "&north=$north&south=$south&east=$east&west=$west"
        QUERY_URL += "&minMagnitude=$minMag"
        if (useDate) {
            QUERY_URL += "&date=$date"
        }
        QUERY_URL += "&maxRows=$maxRows"
        val earthquakesJsonString = URL(QUERY_URL).readText()
        val builder = GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create()
        return builder.fromJson(earthquakesJsonString, EarthquakeResult::class.java)
    }
}