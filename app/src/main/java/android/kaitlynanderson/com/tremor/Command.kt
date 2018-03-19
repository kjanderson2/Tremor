package android.kaitlynanderson.com.tremor

/**
 * Created by kaitlynanderson on 3/18/18.
 * Parent command interface
 */
interface Command<out T> {
    fun execute(north: Float, south: Float, east: Float, west: Float, minMag: Float, date: String, useDate: Boolean, maxRows: Int): T
}