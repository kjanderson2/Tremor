package android.kaitlynanderson.com.tremor

import android.kaitlynanderson.com.tremor.Model.Earthquakes
import com.google.gson.Gson
import java.net.URL

/**
 * Created by kaitlynanderson on 3/13/18.
 */
class EarthquakeRequest() {
    val API_URL = "http://api.geonames.org/earthquakesJSON?formatted=true&north=44.1&south=-9.9&east=-22.4&west=55.2&username=mkoppelman";

    fun execute(): Earthquakes{
        val earthquakesJsonString = URL(API_URL).readText()
        return Gson().fromJson(earthquakesJsonString, Earthquakes::class.java);
    }
}