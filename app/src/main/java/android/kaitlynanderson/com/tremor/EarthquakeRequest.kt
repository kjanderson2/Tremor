package android.kaitlynanderson.com.tremor

import android.kaitlynanderson.com.tremor.Model.EarthquakeResult
import com.google.gson.GsonBuilder
import java.net.URL

/**
 * Created by kaitlynanderson on 3/13/18.
 * Request class housing the API URL and running the url to be read with gson conversion
 */
class EarthquakeRequest {
    private val API_URL = "http://api.geonames.org/earthquakesJSON?formatted=true&north=44.1&south=-9.9&east=-22.4&west=55.2&username=mkoppelman&maxRows=20"

    fun execute(): EarthquakeResult {
        val earthquakesJsonString = URL(API_URL).readText()
        val builder = GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create()
        return builder.fromJson(earthquakesJsonString, EarthquakeResult::class.java)
    }
}