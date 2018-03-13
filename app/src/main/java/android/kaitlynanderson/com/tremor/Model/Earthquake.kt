package android.kaitlynanderson.com.tremor.Model

import java.util.Date

/**
 * Created by kaitlynanderson on 3/13/18.
 * Model class for a single earthquake
 */
data class Earthquake(val datetime: Date, val depth: Float, val lng: Float, val src: String, val eqid: String, val magnitude: Float, val lat: Float)